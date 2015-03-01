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

public class GroupsController implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private Button createGroup;
    @FXML
    private Button addUser;
    @FXML
    private Button removeUser;
    @FXML
    private ListView<?> groupList;
    @FXML
    private ListView<?> userList;
    @FXML
    private Label selectedUserName;
    @FXML
    private Label selectedUserTitle;
    @FXML
    private ListView<?> usersGroupsList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO Initialize
    }    

    @FXML
    private void onCreateGroup(ActionEvent event) {
    	//TODO CreateGroup
    }

    @FXML
    private void onAddUser(ActionEvent event) {
    	//TODO AddUser
    }

    @FXML
    private void onRemoveUser(ActionEvent event) {
    	//TODO RemoveUser
    }
    
}
