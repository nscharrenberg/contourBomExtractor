package converter;

import converter.database.DatabaseConnection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private DatabaseConnection dc = null;
    private Connection connection = null;

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

    private void showMessage(Alert.AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

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

    private

    @FXML
    void exportAction(ActionEvent event) {

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

    private ObservableList<String> getDrivers() {
        ObservableList<String> options = FXCollections.observableArrayList();
        options.add("postgresql");

        return options;
    }

    private void portNumberListener() {
        databasePortTxt.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("\\d*")) {
                    databasePortTxt.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
}
