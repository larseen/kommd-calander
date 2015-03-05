package Controller;

import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Button register;
    @FXML
    private Button settings;
    @FXML
    private Button login;
    @FXML
    private CheckBox keepLoggedIn;
    @FXML
    private Button help;
    @FXML
    private Label errorMsg;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO Initialize
        //ArrayList<User> users = User.getUsers();
    }    



    @FXML
    private void onSettings(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/Settings.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Settings");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void onLogin(ActionEvent event) throws Exception{
    	// TODO Login


        if( authorized() ){
            ((Node)event.getSource()).getScene().getWindow().hide();
            login();

            User.get("");
        }

        User.get("");


    }

    private void login() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/Main.fxml"));
        Stage stage = new Stage();
        stage.setTitle("KVOMMD");
        stage.setScene(new Scene(root));
        stage.show();
    }

    private boolean authorized(){
        MainController.setCurrentUser(new User());
        if( username.getText().equals(password.getText())) return true;
        return false;
        //return User.login("laren@me.com","larsen");
    }

    @FXML
    private void onHelp(ActionEvent event) {
    	// TODO Help
    }
    
}
