package Controller;

import java.net.URL;
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
import javafx.stage.Stage;

public class ViewUserController implements Initializable, Controller {
    @FXML
    private Label titleDisplay;
    @FXML
    private Label emailDisplay;
    @FXML
    private Label phoneDisplay;
    @FXML
    private Button viewCalendar;
    @FXML
    private Button editUser;
    @FXML
    private Button newUser;
    @FXML
    private Label profileName;
    private MainController mainController;

    private User user;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO Initialize
        this.setUser(MainController.getCurrentUser());
    }

    @FXML
    private void viewCalendar(ActionEvent event) {
        //TODO ViewCalendar
    }

    @FXML
    private void newUser(ActionEvent event) throws Exception{
        showEditUserDialog( new User());
    }

    @FXML
    private void editUser(ActionEvent event) throws Exception{
        showEditUserDialog( MainController.getCurrentUser() );
    }

    private Controller showEditUserDialog( User user )throws Exception{

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/EditUser.fxml"));
        Parent root = fxmlLoader.load();
        EditUserController editUserController = fxmlLoader.getController();
        editUserController.setMainController(this);
        editUserController.setUser(user);
        Stage stage = new Stage();
        stage.setTitle("Edit User");
        stage.setScene(new Scene(root));
        stage.show();
        return editUserController;
    }

    public void setUser( User user ){
        this.user = user;
        this.update();
    }

    public void setMainController( MainController mainController ){
        this.mainController = mainController;
    }

    private void showUser( User user ){
        String name = (user.getName() != null && user.getName() != "null") ? user.getName() : "";
        String phone = user.getPhone() != null && user.getPhone() != "null" ? user.getPhone() : "";
        String jobTitle = user.getJobTitle() != null && user.getJobTitle() != "null" ? user.getJobTitle() : "";
        String email = user.getEmail() != null && user.getEmail() != "null" ? user.getEmail() : "";
        profileName.setText(name);
        phoneDisplay.setText(phone);
        titleDisplay.setText(jobTitle);
        emailDisplay.setText(email);
    }

    @Override
    public void update() {

        this.showUser(user);
    }
}
