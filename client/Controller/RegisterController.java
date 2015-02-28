package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RegisterController implements Initializable {
    @FXML
    private Button cancel;
    @FXML
    private Button register;
    @FXML
    private TextField nameInput;
    @FXML
    private TextField phoneInput;
    @FXML
    private TextField emailInput;
    @FXML
    private TextField titleInput;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO Initialize
    }    

    @FXML
    private void onCancel(ActionEvent event) {
    	//TODO Cancel
    }
    @FXML
    private void onRegister(ActionEvent event) {
    	//TODO Register
    }
    
}
