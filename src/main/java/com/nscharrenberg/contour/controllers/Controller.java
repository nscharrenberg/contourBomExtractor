package com.nscharrenberg.contour.controllers;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import com.nscharrenberg.contour.domain.*;
import com.nscharrenberg.contour.repositories.BomRepository;
import com.nscharrenberg.contour.repositories.ProductRepository;
import com.nscharrenberg.contour.repositories.TemplateRepository;
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

    private TemplateRepository pr;

    private List<Template> templates;

    @FXML
    void exportEverythingAction(ActionEvent event) {
        enableOnLoading("Creating Excel file... This may take a few minutes");

        if(this.templates != null && this.templates.size() > 0) {
            Task<Void> loadTask = new Task<Void>() {
                @Override
                protected Void call() {
                    createBillofMaterial(templates);
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
        } else {
            runLaterSuccessDialog("Warning", "Nothing to export", "There is no information to export.");
        }
    }

    @FXML
    void messageListClickedAction(MouseEvent event) {

    }

    /**
     * Populate the Treeview uppon clicking the `Import` Button.
     * This will first check if an id has been specified or if all templates should be displayed.
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

                    if(formatted.equals("*")) {
                        try {
                            templates = pr.findAll();

                            if(templates.size() > 0) {
                                TreeItem result = populateTreeView(templates);

                                Platform.runLater(() -> {
                                    messageList.setRoot(result);
                                });

                                runLaterSuccessDialog("Success", "Execution passed", "Information has been imported!");
                            } else {
                                runLaterSuccessDialog("Warning", "No results found", "No results have been found.");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            runLateDisplayDialog("Something went wrong", "Error while populating TreeView", e);
                        }
                    } else {
                        templates = new ArrayList<>();
                        try {
                            templates.addAll(pr.findByDefaultCode(text));
                            if(templates.size() > 0) {
                                TreeItem result = populateTreeView(templates);

                                Platform.runLater(() -> {
                                    messageList.setRoot(result);
                                });

                                runLaterSuccessDialog("Success", "Execution passed", "Information has been imported!");
                            } else {
                                runLaterSuccessDialog("Warning", "No results found", String.format("We could not find any results with the value %s", text));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            runLateDisplayDialog("Something went wrong", "Error while populating TreeView", e);
                        }
                    }

                    return null;
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            loadTask.setOnSucceeded(e -> {
                disableProgressBar();
            });

            loadTask.setOnFailed(e -> {
                disableProgressBar();
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
        pr = new TemplateRepository();
    }

    /**
     * Populate the Treeview with data, depending on the productlist.
     * This Gets all templates, and their respective Boms and BomLines.
     * It also iterates through the BomLines and gets the Boms from each BomLine that also belongs to the product and adds it to the Bom list of the product.
     * This enables it to display it in order of Product -> Boms -> BomLine.
     * @param templates
     * @return
     */
    private TreeItem populateTreeView(List<Template> templates) {
        try {
            ArrayList<TreeItem> treeItems = new ArrayList<>();

            templates.forEach(p -> {
                TreeItem<String> template = new TreeItem<>(String.format("Hoofdstuklijst: %s", p.toString()));

                if(p.getBoms().size() > 0) {
                    p.getBoms().forEach(b -> {
                        TreeItem<String> bomItem = new TreeItem<>(b.toString());
                        template.getChildren().add(bomItem);

                        if(b.getBomLines().size() > 0) {
                            b.getBomLines().forEach(bbl -> {
                                TreeItem<String> bomLineItem = new TreeItem<>(bbl.toString());
                                bomItem.getChildren().add(bomLineItem);

                                TreeItem<String> productItem = new TreeItem<>(bbl.getProduct().toString());
                                bomLineItem.getChildren().add(productItem);
                            });
                        }
                    });
                }

                treeItems.add(template);
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

    private void createBillofMaterial(List<Template> templates) {
        try {
            // All Templates
            templates.forEach(t -> {
                // All Boms
                if(t.getBoms().size() > 0) {
                    t.getBoms().forEach(b -> {
                        // Get BomLines
                        if(b.getBomLines().size() > 0) {
                            b.getBomLines().forEach(bl -> {
                                BillOfMaterial billOfMaterial = new BillOfMaterial();
                                billOfMaterial = populateTemplate(billOfMaterial, t);
                                billOfMaterial = populateProduct(billOfMaterial, bl.getProduct());
                                billOfMaterial = populateBom(billOfMaterial, b);
                                billOfMaterial = populateBomLine(billOfMaterial, bl);

                                System.out.println(billOfMaterial.toString());
                                try {
                                    pr.createBillOfMaterial(billOfMaterial);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    runLateDisplayDialog("Something went wrong", "Error while creating Bill of Material", e);
                                }
                            });
                        } else {
                            BillOfMaterial billOfMaterial = new BillOfMaterial();
                            billOfMaterial = populateTemplate(billOfMaterial, t);
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
     * Populate the Template part of the Bill Of Material
     * @param billOfMaterial that needs to be populated
     * @param t Template that is being used to populate the billOfMaterial
     * @return the populated billOfMaterial
     */
    private BillOfMaterial populateTemplate(BillOfMaterial billOfMaterial, Template t) {
        billOfMaterial.setTemplate_id(t.getId());
        billOfMaterial.setTemplate_name(t.getName());
        billOfMaterial.setTemplate_sequence(t.getSequence());
        billOfMaterial.setTemplate_description(t.getDescription());
        billOfMaterial.setTemplate_descriptionPurchase(t.getDescriptionPurchase());
        billOfMaterial.setTemplate_descriptionSale(t.getDescriptionSale());
        billOfMaterial.setTemplate_type(t.getType());
        billOfMaterial.setTemplate_rental(t.getRental());
        billOfMaterial.setTemplate_categ_id(t.getCateg_id());
        billOfMaterial.setTemplate_listPrice(t.getListPrice());
        billOfMaterial.setTemplate_volume(t.getVolume());
        billOfMaterial.setTemplate_weight(t.getWeight());
        billOfMaterial.setTemplate_saleOk(t.getSaleOk());
        billOfMaterial.setTemplate_purchaseOk(t.getPurchaseOk());
        billOfMaterial.setTemplate_uomId(t.getUomId());
        billOfMaterial.setTemplate_uomPoId(t.getUomPoId());
        billOfMaterial.setTemplate_companyId(t.getCompanyId());
        billOfMaterial.setTemplate_active(t.getActive());
        billOfMaterial.setTemplate_color(t.getColor());
        billOfMaterial.setTemplate_defaultCode(t.getDefaultCode());
        billOfMaterial.setTemplate_messageLastPost(t.getMessageLastPost());
        billOfMaterial.setTemplate_activityDateDeadline(t.getActivityDateDeadline());
        billOfMaterial.setTemplate_createUid(t.getCreateUid());
        billOfMaterial.setTemplate_createDate(t.getCreateDate());
        billOfMaterial.setTemplate_writeUid(t.getWriteUid());
        billOfMaterial.setTemplate_writeDate(t.getWriteDate());
        billOfMaterial.setTemplate_responsibleId(t.getResponsibleId());
        billOfMaterial.setTemplate_saleDelay(t.getSaleDelay());
        billOfMaterial.setTemplate_tracking(t.getTracking());
        billOfMaterial.setTemplate_descriptionPicking(t.getDescriptionPicking());
        billOfMaterial.setTemplate_descriptionPickingOut(t.getDescriptionPickingOut());
        billOfMaterial.setTemplate_descriptionPickingIn(t.getDescriptionPickingIn());
        billOfMaterial.setTemplate_purchaseMethod(t.getPurchaseMethod());
        billOfMaterial.setTemplate_purchaseLineWarn(t.getPurchaseLineWarn());
        billOfMaterial.setTemplate_purchaseLineWarnMsg(t.getPurchaseLineWarnMsg());
        billOfMaterial.setTemplate_produceDelay(t.getProduceDelay());
        billOfMaterial.setTemplate_canBeExpensed(t.getCanBeExpensed());
        billOfMaterial.setTemplate_landedCostOk(t.getLandedCostOk());
        billOfMaterial.setTemplate_splitMethod(t.getSplitMethod());
        billOfMaterial.setTemplate_serviceType(t.getServiceType());
        billOfMaterial.setTemplate_saleLineWarn(t.getSaleLineWarn());
        billOfMaterial.setTemplate_expensePolicy(t.getExpensePolicy());
        billOfMaterial.setTemplate_invoicePolicy(t.getInvoicePolicy());
        billOfMaterial.setTemplate_serviceTracking(t.getServiceTracking());

        return billOfMaterial;
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
