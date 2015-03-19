package Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Interfaces.Controller;
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

public class MainController implements Initializable, Controller {
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
        showView("../View/Home.fxml");
        if( !currentUser.isAdmin()) rooms.setVisible(false);
        userMenu.setText(currentUser.getName());
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
            System.out.println(e.getCause());
            System.out.println(e.initCause(e));

        }
        return null;
    }

    @FXML
    private void onHome(ActionEvent event) {
        showView("../View/Home.fxml");
    }

    @FXML
    private void onCalendar(ActionEvent event) {
        showCalendarByUser(MainController.getCurrentUser());

    }

    @FXML
    private void onGroups(ActionEvent event) {
        GroupsController groupsController =(GroupsController) showView("../View/Groups.fxml");
        groupsController.setParentController(this);
    }

    @FXML
    private void onRooms(ActionEvent event) {
    	showView("../View/Rooms.fxml");
    }

    @FXML
    private void onUserMenu(ActionEvent event) {
        showUserProfile(MainController.getCurrentUser());
    }

    public void showUserProfile( User user ){

        ViewUserController viewUserController = (ViewUserController) showView("../View/ViewUser.fxml");
        viewUserController.setMainController( this );
        viewUserController.setParentController(this);
        viewUserController.setUser(user);
    }



    public static User getCurrentUser() {
        return MainController.currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        //System.out.println("Current user set:");
        //System.out.println(currentUser);
        MainController.currentUser = currentUser;
    }

    public void showCalendarByUser(User user){

        CalendarController display_controller =(CalendarController) showView("../View/Calendar.fxml");
        display_controller.setUser( user );
    }

    @Override
    public void update() {

    }
}
