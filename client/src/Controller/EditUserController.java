package Controller;

import Interfaces.Controller;
import Models.Model;
import Models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditUserController implements Initializable, Controller {
    @FXML
    private TextField titleDisplay;
    @FXML
    private TextField emailDisplay;
    @FXML
    private TextField phoneDisplay;
    @FXML
    private Button viewCalendar;
    @FXML
    private Button editUser;
    @FXML
    private TextField nameDisplay;
    @FXML
    private CheckBox admin;

    private User user;
    private Controller parentController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO Initialize
    }    

    @FXML
    private void viewCalendar(ActionEvent event) {
    	//TODO ViewCalendar
    }

    @FXML
    private void editUser(ActionEvent event) {
        //TODO ViewCalendar
    }

    public void setUser(User user){
        this.user = user;
        this.editUser();
    }

    public void setMainController( Controller parentController ){
        this.parentController = parentController;
    }


    
    private void editUser( ){
        String name = (user.getName() != null && user.getName() != "null") ? user.getName() : "";
        String phone = user.getPhone() != null && user.getPhone() != "null" ? user.getPhone() : "";
        String jobTitle = user.getJobTitle() != null && user.getJobTitle() != "null" ? user.getJobTitle() : "";
        String email = user.getEmail() != null && user.getEmail() != "null" ? user.getEmail() : "";
        nameDisplay.setText(name);
        phoneDisplay.setText(phone);
        titleDisplay.setText(jobTitle);
        emailDisplay.setText(email);
        admin.setSelected(user.isAdmin());
    }

    @FXML
    private void onCancel(ActionEvent event) {
        //TODO Cancel

        ((Node)event.getSource()).getScene().getWindow().hide();
    }
    @FXML
    private void onSave(ActionEvent event) {
        //TODO Register
        user.setName(nameDisplay.getText());
        user.setPhone(phoneDisplay.getText());
        user.setJobTitle(titleDisplay.getText());
        user.setEmail(emailDisplay.getText());
        user.setAdmin(admin.isSelected());
        user.save();
        parentController.update();
        
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @Override
    public void update() {

        editUser();
    }

    @FXML
    public void onChangePassword(ActionEvent event) throws Exception{
        PasswordController passwordController = (PasswordController) showChangePasswordDialog( user );
    }

    private Controller showChangePasswordDialog( User user  )throws Exception{

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/Password.fxml"));
        Parent root = fxmlLoader.load();
        PasswordController passwordController = fxmlLoader.getController();
        passwordController.setMainController(this);
        passwordController.setUser( user );
        Stage stage = new Stage();
        stage.setTitle("Change Password");
        stage.setScene(new Scene(root));
        stage.show();
        return passwordController;
    }
}
