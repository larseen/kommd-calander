package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class MainController implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private Button home;
    @FXML
    private Button calendar;
    @FXML
    private Button groups;
    @FXML
    private Button rooms;
    @FXML
    private Button userMenu;
    @FXML
    private Label currentUser;
    @FXML
    private Pane displayView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO Initialize
    }    

    @FXML
    private void onHome(ActionEvent event) {
    	//TODO Home
    }

    @FXML
    private void onCalendar(ActionEvent event) {
    	//TODO Calendar
    }

    @FXML
    private void onGroups(ActionEvent event) {
    	//TODO Groups
    }

    @FXML
    private void onRooms(ActionEvent event) {
    	//TODO Rooms
    }

    @FXML
    private void onUserMenu(ActionEvent event) {
    	//TODO UserMenu
    }
    
}
