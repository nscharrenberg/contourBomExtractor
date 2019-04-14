package com.nscharrenberg.contour.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTreeView;
import com.nscharrenberg.contour.domain.Bom;
import com.nscharrenberg.contour.domain.Product;
import com.nscharrenberg.contour.dtos.ProductDto;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
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
    private TreeView<String> messageList;

    @FXML
    private JFXButton exportBtn;

    @FXML
    private VBox progressVbox;

    @FXML
    private Label progressLbl;

    @FXML
    private JFXProgressBar progressBar;

    @FXML
    void exportEverythingAction(ActionEvent event) {

    }

    @FXML
    void messageListClickedAction(MouseEvent event) {

    }

    private BiConsumer<Integer, Integer> progressUpdate ;

    public void setProgressUpdate(BiConsumer<Integer, Integer> progressUpdate) {
        this.progressUpdate = progressUpdate ;
    }

    EntityManagerFactory emf;
    EntityManager em;
    List<Product> products;

    @PostConstruct
    public void init() {

    }

    public Controller() {
        this.emf = Persistence.createEntityManagerFactory("contourDB");
        this.em = emf.createEntityManager();
    }

    private Boolean populate() {
        ModelMapper modelMapper = new ModelMapper();
        List<ProductDto> productDtos = new ArrayList<>(Arrays.asList(modelMapper.map(this.products, ProductDto[].class)));

        ArrayList<TreeItem> treeItems = new ArrayList<>();

        productDtos.forEach(p -> {
            TreeItem<String> product = new TreeItem<>(p.toString());

            p.getBomLines().forEach(bl -> {
                Bom bom = bl.getBom();
                TreeItem<String> bomItem = new TreeItem<>(bom.toString());
                product.getChildren().add(bomItem);
                bom.getBomLines().forEach(bbl -> {
                    if(bbl.getBom().equals(bom)) {
                        TreeItem<String> bomLineItem = new TreeItem<>(bbl.toString());
                        bomItem.getChildren().add(bomLineItem);
                    }
                });
            });

            treeItems.add(product);
        });

        TreeItem rootItem = new TreeItem<>("Products");
        rootItem.getChildren().addAll(treeItems);
        this.messageList.setRoot(rootItem);

        return true;
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
        progressVbox.setVisible(true);

        Task<Boolean> loadTask = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                products = em.createNamedQuery("product.findAll", Product.class).getResultList();

                return populate();
            }
        };


        loadTask.setOnSucceeded(e -> {
            progressVbox.setVisible(false);
        });

        Thread thread = new Thread(loadTask);
        thread.setDaemon(true);
        thread.start();
    }
}
