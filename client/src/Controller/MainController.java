package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        showView("../View/Home.fxml");
    }    

    private void showView(String view){
        try {
            Parent root = FXMLLoader.load(getClass().getResource(view));
            displayView.getChildren().removeAll(displayView.getChildren());
            displayView.getChildren().addAll(root);

        }
        catch (Exception e){

        }
    }

    @FXML
    private void onHome(ActionEvent event) {
    	//TODO Home
        showView("../View/Home.fxml");
    }

    @FXML
    private void onCalendar(ActionEvent event) {
    	//TODO Calendar
        showView("../View/Calendar.fxml");
    }

    @FXML
    private void onGroups(ActionEvent event) {
    	//TODO Groups
        showView("../View/Groups.fxml");
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
