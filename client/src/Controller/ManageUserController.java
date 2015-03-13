package Controller;

import Interfaces.ManageUser;
import Interfaces.Controller;
import Models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ManageUserController implements Initializable, Controller, ManageUser {
    @FXML
    private ComboBox users;
    @FXML
    private Button save;
    @FXML
    private Button ok;
    @FXML
    private Button cancel;
    @FXML
    private ListView invitedUsers;

    private ArrayList<User> usersList;
    private ManageUser parentController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.update();
    }    




    public void setMainController( ManageUser parentController ){
        this.parentController = parentController;
    }


    

    @FXML
    private void onCancel(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }





    @FXML
    private void onAdd(ActionEvent event) {
        Integer selectedUser = users.getSelectionModel().getSelectedIndex();
        if( selectedUser != null ){
            parentController.addUser( usersList.get(selectedUser));
        }
    }

    @Override
    public void update() {

        usersList = User.getUsers();
        for( User user : usersList ){
            users.getItems().add(user.getName());
        }
    }


    @Override
    public void addUser(User user) {
        if( parentController != null){
            parentController.addUser(user);
        }
        this.update();
    }

    @Override
    public void removeUser(User user) {
        if( parentController != null){
            parentController.removeUser(user);
        }
        this.update();

    }
}
