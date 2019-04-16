package com.nscharrenberg.contour.controllers;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import com.nscharrenberg.contour.domain.BillOfMaterial;
import com.nscharrenberg.contour.domain.Bom;
import com.nscharrenberg.contour.domain.BomLine;
import com.nscharrenberg.contour.domain.Product;
import com.nscharrenberg.contour.repositories.ProductRepository;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.annotation.Nullable;
import javax.annotation.PostConstruct;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

public class Controller implements Initializable {

    private Stage stage;

    @FXML
    private TreeView messageList;

    @FXML
    private JFXButton exportBtn;

    @FXML
    private JFXButton importAllBtn;

    @FXML
    private VBox progressVbox;

    @FXML
    private Label progressLbl;

    @FXML
    private JFXProgressBar progressBar;

    @FXML
    private TextField idTxt;

    private ProductRepository pr;

    private List<Product> products;

    @FXML
    void exportEverythingAction(ActionEvent event) {
        enableOnLoading("Creating Excel file... This may take a few minutes");

        if(this.products != null && this.products.size() > 0) {
            Task<Void> loadTask = new Task<Void>() {
                @Override
                protected Void call() {
                    createBillOfMaterial(products);
                    return null;
                }
            };

            loadTask.setOnSucceeded(e -> {
                disableProgressBar();
                runLaterSuccessDialog("Success", "Execution passed", "The Bill of Materials have been saved");
            });

            loadTask.setOnFailed(e -> {
                disableProgressBar();
                runLaterSuccessDialog("Failed", "Execution failed", "The task to save the bill of materials has failed");
            });

            loadTask.setOnCancelled(e -> {
                disableProgressBar();
                runLaterSuccessDialog("Cancelled", "Execution cancelled", "The task to save the bill of materials has been cancelled");
            });

            Thread thread = new Thread(loadTask);
            thread.setDaemon(true);
            thread.start();
        }
    }

    @FXML
    void messageListClickedAction(MouseEvent event) {

    }

    /**
     * Populate the Treeview uppon clicking the `Import` Button.
     * This will first check if an id has been specified or if all products should be displayed.
     * It'll do this while working on another Thread. This should avoid program sleeping while it's being processed.
     * @param event
     */
    @FXML
    void importEverythingAction(ActionEvent event) {
        enableOnLoading("Loading... This may take a few minutes");

        Task<Void> loadTask = null;
        try {
           loadTask = new Task<Void>() {
                @Override
                protected Void call() {
                    String text = idTxt.getText();
                    String formatted = text.replaceAll(" ", "");

                    if(!formatted.equals("*") || !formatted.equals("0")) {
                        products = new ArrayList<>();
                        try {
                            products.add(pr.findById(Integer.parseInt(formatted)));
                        } catch (Exception e) {
                            e.printStackTrace();
                            runLateDisplayDialog("Something went wrong", "Error while populating TreeView", e);
                        }
                    } else {
                        try {
                            products = pr.findAll();
                        } catch (Exception e) {
                            e.printStackTrace();
                            runLateDisplayDialog("Something went wrong", "Error while populating TreeView", e);
                        }
                    }
                    List<Product> newProducts = populate(products);
                    TreeItem result = populateTreeView(newProducts);

                    Platform.runLater(() -> {
                        messageList.setRoot(result);
                    });

                    return null;
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            loadTask.setOnSucceeded(e -> {
                disableProgressBar();
                runLaterSuccessDialog("Success", "Execution passed", "Information has been imported!");
            });

            loadTask.setOnFailed(e -> {
                disableProgressBar();
                runLaterSuccessDialog("Failed", "Execution failed", "The task to import the bom information has failed");
            });

            loadTask.setOnCancelled(e -> {
                disableProgressBar();
                runLaterSuccessDialog("Cancelled", "Execution cancelled", "The task to import the bom information has been cancelled");
            });

            Thread thread = new Thread(loadTask);
            thread.setDaemon(true);
            thread.start();
        }
    }

    @PostConstruct
    public void init() {

    }

    public Controller() {
        pr = new ProductRepository();
    }

    /**
     * Preload Boms from BomLines into product Bom.
     * This makes it easier to organize it lateron when exporting or showing on a Treeview or List.
     * @param products the list of products
     * @return an updated list of products
     */
    private List<Product> populate(List<Product> products) {
        try {
            IntStream.range(0, products.size()).forEach(i -> {
                Product p = products.get(i);

                if(p.getBomLines().size() > 0) {
                    List<BomLine> bomLineList = new ArrayList<>(p.getBomLines());
                    IntStream.range(0, bomLineList.size()).forEach(bli -> {
                        BomLine bl = bomLineList.get(bli);

                        if(!p.getBoms().contains(bl.getBom())) {
                            products.get(i).getBoms().add(bl.getBom());
                        }
                    });
                }
            });

            return products;
        } catch(Exception e) {
            e.printStackTrace();
            runLateDisplayDialog("Something went wrong", "Error while populating TreeView", e);
        }


        return null;
    }

    /**
     * Populate the Treeview with data, depending on the productlist.
     * This Gets all products, and their respective Boms and BomLines.
     * It also iterates through the BomLines and gets the Boms from each BomLine that also belongs to the product and adds it to the Bom list of the product.
     * This enables it to display it in order of Product -> Boms -> BomLine.
     * @param products
     * @return
     */
    private TreeItem populateTreeView(List<Product> products) {
        try {
            ArrayList<TreeItem> treeItems = new ArrayList<>();

            products.forEach(p -> {
                TreeItem<String> product = new TreeItem<>(String.format("Hoofdstuklijst: %s", p.toString()));

                if(p.getBoms().size() > 0) {
                    p.getBoms().forEach(b -> {
                        TreeItem<String> bomItem = new TreeItem<>(b.toString());
                        product.getChildren().add(bomItem);

                        if(b.getBomLines().size() > 0) {
                            b.getBomLines().forEach(bbl -> {
                                if(bbl.getBom().getId().equals(b.getId()) && bbl.getProduct().getId().equals(p.getId())) {
                                    TreeItem<String> bomLineItem = new TreeItem<>(bbl.toString());
                                    bomItem.getChildren().add(bomLineItem);
                                }
                            });
                        }
                    });
                }

                treeItems.add(product);
            });

            TreeItem rootItem = new TreeItem<>("Products");
            rootItem.getChildren().addAll(treeItems);

            return rootItem;
        } catch (Exception e){
            e.printStackTrace();
            runLateDisplayDialog("Something went wrong", "Error while populating TreeView", e);
        }

        return null;
    }

    /**
     * ----------------------------------------------------------------
     * DEPRECATED
     * ----------------------------------------------------------------
     * This method is deprecated and does not function properly.
     * Instead of the following methods:
     * `populate()` for preloading de bom data.
     * `populateTreeView()` to populate the treeview with the data that was generated in `populate()`
     * ----------------------------------------------------------------
     * Populate the Treeview with data, depending on the productlist.
     * This Gets all products, and their respective Boms and BomLines.
     * It also iterates through the BomLines and gets the Boms from each BomLine that also belongs to the product and adds it to the Bom list of the product.
     * This enables it to display it in order of Product -> Boms -> BomLine.
     * @param products
     * @return
     */
    private TreeItem populate_deprecated(List<Product> products) {
        ArrayList<TreeItem> treeItems = new ArrayList<>();

        products.forEach(p -> {
            TreeItem<String> product = new TreeItem<>(String.format("Hoofdstuklijst: %s", p.toString()));

            if(p.getBoms().size() > 0) {
                p.getBoms().forEach(b -> {
                    if(p.getId().equals(b.getProduct().getId())) {
                        TreeItem<String> bomItem = new TreeItem<>(b.toString());
                        product.getChildren().add(bomItem);
                        b.getBomLines().forEach(bbl -> {
                            if(bbl.getBom().getId().equals(b.getId()) && bbl.getProduct().getId().equals(p.getId())) {
                                TreeItem<String> bomLineItem = new TreeItem<>(bbl.toString());
                                bomItem.getChildren().add(bomLineItem);
                            }
                        });
                    }
                });
            }

            p.getBomLines().forEach(bl -> {
                Bom bom = bl.getBom();
                TreeItem<String> bomItem = new TreeItem<>(bom.toString());
                product.getChildren().add(bomItem);
                bom.getBomLines().forEach(bbl -> {
                    if(bbl.getBom().equals(bom) && bbl.getProduct().getId().equals(p.getId())) {
                        TreeItem<String> bomLineItem = new TreeItem<>(bbl.toString());
                        bomItem.getChildren().add(bomLineItem);
                    }
                });
            });

            treeItems.add(product);
        });

        TreeItem rootItem = new TreeItem<>("Products");
        rootItem.getChildren().addAll(treeItems);

        return rootItem;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        disableAfterLoading();

        idTxt.setTextFormatter(new TextFormatter<Object>(c -> {
            if(!c.getControlNewText().matches("^$|^[0-9*]+$")) {
                return null;
            } else {
                return c;
            }
        }));
    }

    /**
     * Execute the `disableAfterLoading()` method on another Thread.
     */
    private void disableProgressBar() {
        Platform.runLater(this::disableAfterLoading);
    }

    /**
     * Sets the text for the progress label
     * Disables the buttons
     * Makes progress items visible
     * @param text to be shown on the progress label
     */
    private void enableOnLoading(String text) {
        progressLbl.setText(text);
        this.importAllBtn.setDisable(true);
        this.exportBtn.setDisable(true);
        this.progressLbl.setVisible(true);
        this.progressBar.setVisible(true);
    }

    /**
     * Enables the buttons
     * Makes the progress items invisible
     */
    private void disableAfterLoading() {
        this.importAllBtn.setDisable(false);
        this.exportBtn.setDisable(false);
        this.progressLbl.setVisible(false);
        this.progressBar.setVisible(false);
    }

    private void createBillOfMaterial(List<Product> products) {
        try {
            // All Products
            products.forEach(p -> {
                // Boms of the Product
                if(p.getBoms().size() > 0) {
                    p.getBoms().forEach(b -> {
                        if(b.getBomLines().size() > 0) {
                            BomLine fbl = Iterables.tryFind(p.getBomLines(), new Predicate<BomLine>() {
                                @Override
                                public boolean apply(@Nullable BomLine input) {
                                    if(b.getBomLines().contains(input)) {
                                        return true;
                                    }

                                    return false;
                                }
                            }).orNull();

                            if(fbl == null) {
                                BillOfMaterial billOfMaterial = new BillOfMaterial();
                                billOfMaterial = populateProduct(billOfMaterial, p);
                                billOfMaterial = populateBom(billOfMaterial, b);

                                System.out.println(billOfMaterial.toString());
                                try {
                                    pr.createBillOfMaterial(billOfMaterial);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    runLateDisplayDialog("Something went wrong", "Error while creating Bill of Material", e);
                                }
                            }

                            b.getBomLines().forEach(bl -> {
                                if(bl.getBom().equals(b) && bl.getProduct().getId().equals(p.getId())) {
                                    BillOfMaterial billOfMaterial = new BillOfMaterial();
                                    billOfMaterial = populateProduct(billOfMaterial, p);
                                    billOfMaterial = populateBom(billOfMaterial, b);
                                    billOfMaterial = populateBomLine(billOfMaterial, bl);

                                    System.out.println(billOfMaterial.toString());
                                    try {
                                        pr.createBillOfMaterial(billOfMaterial);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        runLateDisplayDialog("Something went wrong", "Error while creating Bill of Material", e);
                                    }
                                }
                            });
                        } else {
                            BillOfMaterial billOfMaterial = new BillOfMaterial();
                            billOfMaterial = populateProduct(billOfMaterial, p);
                            billOfMaterial = populateBom(billOfMaterial, b);

                            System.out.println(billOfMaterial.toString());
                            try {
                                pr.createBillOfMaterial(billOfMaterial);
                            } catch (Exception e) {
                                e.printStackTrace();
                                runLateDisplayDialog("Something went wrong", "Error while creating Bill of Material", e);
                            }
                        }
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Populate the Product part of the BillOfMaterial
     * @param billOfMaterial BillOfMaterial that needs to be populated
     * @param p the Product that is being used to populate the BillOfMaterial
     * @return the populated BillOfMaterial
     */
    private BillOfMaterial populateProduct(BillOfMaterial billOfMaterial, Product p) {
        billOfMaterial.setProduct_id(p.getId());
        billOfMaterial.setProduct_defaultCode(p.getDefaultCode());
        billOfMaterial.setProduct_active(p.isActive());
        billOfMaterial.setProduct_productTmplId(p.getProductTmplId());
        billOfMaterial.setProduct_barcode(p.getBarcode());
        billOfMaterial.setProduct_volume(p.getVolume());
        billOfMaterial.setProduct_weight(p.getWeight());
        billOfMaterial.setProduct_messageLastPost(p.getMessageLastPost());
        billOfMaterial.setProduct_activityDateDeadline(p.getActivityDateDeadline());
        billOfMaterial.setProduct_createUid(p.getCreateUid());
        billOfMaterial.setProduct_createDate(p.getCreateDate());
        billOfMaterial.setProduct_writeUid(p.getWriteUid());
        billOfMaterial.setProduct_writeDate(p.getWriteDate());

        return billOfMaterial;
    }

    /**
     * Populate the Bom part of the BillOfMaterial
     * @param billOfMaterial BillOfMaterial that needs to be populated
     * @param b the Bom that is being used to populate the BillOfMaterial
     * @return the populated BillOfMaterial
     */
    private BillOfMaterial populateBom(BillOfMaterial billOfMaterial, Bom b) {
        billOfMaterial.setBom_id(b.getId());
        billOfMaterial.setBom_code(b.getCode());
        billOfMaterial.setBom_active(b.isActive());
        billOfMaterial.setBom_type(b.getType());
        billOfMaterial.setBom_productTmplId(b.getProductTmplId());
        billOfMaterial.setBom_productQty(b.getProductQty());
        billOfMaterial.setBom_productUomId(b.getProductUomId());
        billOfMaterial.setBom_sequence(b.getSequence());
        billOfMaterial.setBom_routingId(b.getRoutingId());
        billOfMaterial.setBom_readyToProduce(b.getReadyToProduce());
        billOfMaterial.setBom_pickingTypeId(b.getPickingTypeId());
        billOfMaterial.setBom_companyId(b.getCompanyId());
        billOfMaterial.setBom_messageLastPost(b.getMessageLastPost());
        billOfMaterial.setBom_createUid(b.getCreateUid());
        billOfMaterial.setBom_createDate(b.getCreateDate());
        billOfMaterial.setBom_writeUid(b.getWriteUid());
        billOfMaterial.setBom_writeDate(b.getWriteDate());

        return billOfMaterial;
    }

    /**
     * Populate the BomLine part of the BillOfMaterial
     * @param billOfMaterial BillOfMaterial that needs to be populated
     * @param bl the BomLine that is being used to populate the BillOfMaterial
     * @return the populated BillOfMaterial
     */
    private BillOfMaterial populateBomLine(BillOfMaterial billOfMaterial, BomLine bl) {
        billOfMaterial.setBomline_id(bl.getId());
        billOfMaterial.setBomline_productQty(bl.getProductQty());
        billOfMaterial.setBomline_productUomId(bl.getProductUomId());
        billOfMaterial.setBomline_sequence(bl.getSequence());
        billOfMaterial.setBomline_routingId(bl.getRoutingId());
        billOfMaterial.setBomline_operationId(bl.getOperationId());
        billOfMaterial.setBomline_createUid(bl.getCreateUid());
        billOfMaterial.setBomline_createDate(bl.getCreateDate());
        billOfMaterial.setBomline_writeUid(bl.getWriteUid());
        billOfMaterial.setBomline_writeDate(bl.getWriteDate());

        return billOfMaterial;
    }

    /**
     * Show an Exception Dialog on the screen.
     * This is mainly used to make the user aware an exception happening.
     * @param title
     * @param content
     * @param e - The exception that was thrown
     */
    private void errorDialog(String title, String content, Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(content);
        alert.setContentText(e.getMessage());

        // Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }

    /**
     * Run the ErrorDialog on `Platform.runLater`
     * @param title
     * @param content
     * @param e
     */
    private void runLateDisplayDialog(String title, String content, Exception e) {
        Platform.runLater(() -> {
            errorDialog(title, content, e);
        });
    }

    /**
     * Show a Information Dialog on the screen.
     * This is mainly used to make the user aware of an action being finished (complete, failed, cancelled etc...)
     * @param title
     * @param headerText
     * @param content
     */
    private void successDialog(String title, String headerText, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(content);

        alert.showAndWait();
    }

    /**
     * Run the SuccessDialog on `Platform.runLater`
     * @param title
     * @param headerText
     * @param content
     */
    private void runLaterSuccessDialog(String title, String headerText, String content) {
        Platform.runLater(() -> {
            successDialog(title, headerText, content);
        });
    }
}
