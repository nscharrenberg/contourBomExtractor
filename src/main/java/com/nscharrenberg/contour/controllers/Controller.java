package com.nscharrenberg.contour.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTreeView;
import com.nscharrenberg.contour.domain.Bom;
import com.nscharrenberg.contour.domain.Product;
import com.nscharrenberg.contour.dtos.ProductDto;
import com.nscharrenberg.contour.repositories.ProductRepository;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.BiConsumer;

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

    }

    @FXML
    void messageListClickedAction(MouseEvent event) {

    }

    @FXML
    void importEverythingAction(ActionEvent event) {
        this.progressLbl.setVisible(true);
        this.progressBar.setVisible(true);

        Task<Void> loadTask = new Task<Void>() {
            @Override
            protected Void call() {
                String text = idTxt.getText();
                String formatted = text.replaceAll(" ", "");

                if(!formatted.equals("*") || !formatted.equals("0")) {
                    products = new ArrayList<>();
                    products.add(pr.findById(Integer.parseInt(formatted)));
                } else {
                    products = pr.findAll();
                }

                TreeItem result = populate(products);

                Platform.runLater(() -> {
                    messageList.setRoot(result);
                });

                return null;
            }
        };

        loadTask.setOnSucceeded(e -> {
            disableProgressBar();
        });

        loadTask.setOnFailed(e -> {
            disableProgressBar();
        });

        loadTask.setOnCancelled(e -> {
            disableProgressBar();
        });

        Thread thread = new Thread(loadTask);
        thread.setDaemon(true);
        thread.start();
    }

    @PostConstruct
    public void init() {

    }

    public Controller() {
        pr = new ProductRepository();
    }

    private TreeItem populate(List<Product> products) {
        ModelMapper modelMapper = new ModelMapper();
        List<ProductDto> productDtos = new ArrayList<>(Arrays.asList(modelMapper.map(products, ProductDto[].class)));

        ArrayList<TreeItem> treeItems = new ArrayList<>();

        productDtos.forEach(p -> {
            TreeItem<String> product = new TreeItem<>(p.toString());

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
        this.progressLbl.setVisible(false);
        this.progressBar.setVisible(false);

        idTxt.setTextFormatter(new TextFormatter<Object>(c -> {
            if(!c.getControlNewText().matches("^$|^[0-9*]+$")) {
                return null;
            } else {
                return c;
            }
        }));
    }

    private void disableProgressBar() {
        Platform.runLater(() -> {
            this.progressLbl.setVisible(false);
            this.progressBar.setVisible(false);
        });
    }
}
