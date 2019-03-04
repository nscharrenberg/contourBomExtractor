package converter;

import converter.database.DatabaseConnection;
import converter.domain.Product;
import converter.logic.ExcellWriter;
import converter.logic.OdooData;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainController implements Initializable {

    private DatabaseConnection dc = null;
    private Connection connection = null;
    private String fileName = "tmp";

    @FXML
    private ComboBox<String> databaseTypeCombo;

    @FXML
    private TextField databaseHostText;

    @FXML
    private TextField databasePortTxt;

    @FXML
    private TextField databaseUserText;

    @FXML
    private PasswordField databasePasswordText;

    @FXML
    private TextField databaseNameText;

    @FXML
    private AnchorPane databaseConnectBtn;

    @FXML
    private Button exportExcellBtn;

    @FXML
    void connectAction(ActionEvent event) {
        try {
            int port = 0;

            if(!databasePortTxt.getText().equals("")) {
                port = Integer.parseInt(databasePortTxt.getText());
            }

            dc = new DatabaseConnection(databaseTypeCombo.getValue(), databaseHostText.getText(), port, databaseUserText.getText(), databasePasswordText.getText(), databaseNameText.getText());
            connection = dc.connect();

            if(connection != null) {
                showMessage(Alert.AlertType.INFORMATION, "Connected", "Database connection established", null);
                exportExcellBtn.setDisable(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showErrorMessage("Error " + e.getErrorCode(), e.getMessage(), null, e);
        } catch (Exception e) {
            e.printStackTrace();
            showErrorMessage("Error ", e.getMessage(), null, e);
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    showErrorMessage("Error " + e.getErrorCode(), "Something wen't wrong", e.getMessage(), e);
                }
            }
        }
    }

    @FXML
    void exportAction(ActionEvent event) {
        try {
            if(connection.isClosed()) {
                connection = dc.connect();
                exportExcellBtn.setDisable(false);
            }

            OdooData od = new OdooData(connection);
            List<Product> products = od.getBomLines();

            File currentDirectory = new File(".");
            String path = currentDirectory.getAbsolutePath();
            String fileLocation = path.substring(0, path.length() - 1) + fileName +".xlsx";
            ExcellWriter ew = new ExcellWriter();

            if(new File(fileLocation).exists()) {
                showActionDialog(path, fileName, products, ew);
            } else {
                ew.write(fileLocation, products);
            }

            showMessage(Alert.AlertType.CONFIRMATION, "Data Exported", "All data has been exported to an excell sheet", null);
        } catch (Exception e) {
            e.printStackTrace();
            showErrorMessage("Error ", e.getMessage(), null, e);
        }
    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    public void initialize(URL location, ResourceBundle resources) {
        databaseTypeCombo.setItems(getDrivers());
        databaseTypeCombo.getSelectionModel().selectFirst();
        exportExcellBtn.setDisable(true);

        portNumberListener();
    }

    /**
     * Get all the drivers and return an ObservableList with String values.
     * @return
     */
    private ObservableList<String> getDrivers() {
        ObservableList<String> options = FXCollections.observableArrayList();
        options.add("postgresql");

        return options;
    }

    /**
     * Make sure the databasePortTxt field only allows numbers
     */
    private void portNumberListener() {
        databasePortTxt.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("\\d*")) {
                    databasePortTxt.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }


    /**
     * A popup window that shows up
     * @param alertType - the alert type to use
     * @param title - the message to be displayed on the top bar of the window
     * @param header - the message to be displayed in a bigger font
     * @param content - the message to be displayed as content
     */
    private void showMessage(Alert.AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }


    /**
     * An error message popup window that shows up
     * @param title - The message to be displayed on the top bar of the window
     * @param header - the message to be displayed in a bigger font
     * @param content - the message to be displayed as content
     * @param e - the exception to be used in the stacktrace window
     */
    private void showErrorMessage(String title, String header, String content, Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

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

    private void showActionDialog(String path, String fileName, List<Product> products, ExcellWriter ew) throws IOException, ParseException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Override File");
        alert.setHeaderText("File already exists");
        alert.setContentText("Do you want to override the file?");

        ButtonType buttonTypeOne = new ButtonType("Yes");
        ButtonType buttonTypeTwo = new ButtonType("No, create new file");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);
        String fileLocation = path.substring(0, path.length() - 1) + fileName +".xlsx";

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            File file = new File(fileLocation);
            if(file.exists()) {
                file.delete();
            }

            ew.write(fileLocation, products);
        } else if (result.get() == buttonTypeTwo) {
            String newFileLocation = path.substring(0, path.length() - 1) + fileName + "_" + String.valueOf(System.currentTimeMillis() / 1000) +".xlsx";
            ew.write(newFileLocation, products);
        }  else {
            alert.close();
        }
    }
}
