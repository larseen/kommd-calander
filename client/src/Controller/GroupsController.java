package Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Interfaces.Controller;
import Models.Group;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class GroupsController implements Initializable, Controller {
    @FXML
    private AnchorPane root;
    @FXML
    private Button createGroup;
    @FXML
    private Button addUser;
    @FXML
    private Button removeUser;
    @FXML
    private ListView<String> groupList;
    @FXML
    private ListView<?> userList;
    @FXML
    private Label selectedUserName;
    @FXML
    private Label selectedUserTitle;
    @FXML
    private ListView<?> usersGroupsList;

    private ArrayList<Group> groups;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO Initialize
        this.update();
    }


    public void update(){
        groups = Group.getGroups();
        groupList.getItems().removeAll(groupList.getItems());
        for( Group group : groups){
            System.out.println(group.getName());
            groupList.getItems().add(group.getName().toString());
        }
    }

    @FXML
    private void onCreateGroup(ActionEvent event) {
    	//TODO CreateGroup
    }

    @FXML
    private void onMouseClicked(MouseEvent event){
        System.out.println("clicked on " + groupList.getSelectionModel().getSelectedIndices());
        Integer selectedIndex = Integer.parseInt(groupList.getSelectionModel().getSelectedIndices().toString());
        Group selectedGroup = groups.get(selectedIndex);
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
