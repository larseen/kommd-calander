package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class ViewUserController implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private Label titleDisplay;
    @FXML
    private Label emailDisplay;
    @FXML
    private Label phoneDisplay;
    @FXML
    private Button viewCalendar;
    @FXML
    private Label profileName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO Initialize
    }    

    @FXML
    private void viewCalendar(ActionEvent event) {
    	//TODO ViewCalendar
    }
    
}
