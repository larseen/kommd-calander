package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

public class HomeController implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private Label dateDisplay;
    @FXML
    private ListView<?> notifications;
    @FXML
    private ListView<?> appointments;
    @FXML
    private ListView<?> requests;
    @FXML
    private Button createEvent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO Initialize
    }    

    @FXML
    private void onCreate(ActionEvent event) {
    	//TODO CreateEvent
    }
    
}
