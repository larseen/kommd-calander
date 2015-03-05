package Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Models.User;
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

import javax.management.Notification;

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
    private Label currentUserLabel;
    @FXML
    private Pane displayView;


    private static User currentUser;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO Initialize
        showView("../View/Home.fxml");
    }    

    private Initializable showView(String view){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(view));
            Parent root = fxmlLoader.load();
            Initializable display_controller = fxmlLoader.getController();

            displayView.getChildren().removeAll(displayView.getChildren());
            displayView.getChildren().addAll(root);
            return display_controller;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    @FXML
    private void onHome(ActionEvent event) {
    	//TODO Home
        showView("../View/Home.fxml");
    }

    @FXML
    private void onCalendar(ActionEvent event) {
    	//TODO Calendar

            CalendarController display_controller =(CalendarController) showView("../View/Calendar.fxml");
            //display_controller.wr("test1234");

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

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        MainController.currentUser = currentUser;
    }
}
