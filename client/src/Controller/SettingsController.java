package Controller;

import Models.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Dag Frode on 03.03.2015.
 */
public class SettingsController implements Initializable {

    @FXML
    private Button cancel;
    @FXML
    private Button save;
    @FXML
    private TextField urlInput;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO Initialize
    }

    @FXML
    private void onCancel(ActionEvent event) {
        //TODO Cancel

        ((Node)event.getSource()).getScene().getWindow().hide();
    }
    @FXML
    private void onSave(ActionEvent event) {
        //TODO Register
        Model.setURL(urlInput.getText());
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
}
