package Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Models.Model;
import Models.Room;
import Models.User;
import javafx.application.Application;
import javafx.application.Preloader;
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

public class LoginController  extends Application implements Initializable  {
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
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/Login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        Model.setURL("http://api.larsen.so");
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO Initialize
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
        if( User.login("dfs@live.no", "12345")){ //User.login(username.getText(), password.getText())  == true ){  //User.login("dfs@live.no", "12345")
            ((Node)event.getSource()).getScene().getWindow().hide();
            login();
        }




    }

    private void login() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/Main.fxml"));
        Stage stage = new Stage();
        stage.setTitle("KVOMMD");
        stage.setScene(new Scene(root));
        stage.show();
    }



    @FXML
    private void onHelp(ActionEvent event) {
    	// TODO Help
    }
    
}
