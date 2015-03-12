package Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Interfaces.ManageUser;
import Interfaces.Controller;
import Models.Group;
import Models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class GroupsController implements Initializable, Controller, ManageUser {
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
    @FXML
    private GridPane userInfo;

    private ArrayList<Group> groups;
    private Group activeGroup;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO Initialize
        this.addUser.setVisible(false);
        this.userList.setVisible(false);
        this.userInfo.setVisible(false);
        this.removeUser.setVisible(false);
        this.update();
    }


    public void update(){
        groups = Group.getGroups();
        groupList.getItems().removeAll(groupList.getItems());
        for( Group group : groups){
            groupList.getItems().add(group.getName().toString());
        }
    }

    @FXML
    private void onCreateGroup(ActionEvent event) throws Exception{
        EditGroupController editGroupController =(EditGroupController) showEditGroupDialog(new Group());
    }

    @FXML
    private void onEditGroup(ActionEvent event) throws Exception{
        if( activeGroup != null ) {
            EditGroupController editGroupController = (EditGroupController) showEditGroupDialog(activeGroup);
        }
    }

    @FXML
    private void onDeleteGroup(ActionEvent event) throws Exception{
        if( activeGroup != null ) {
            this.activeGroup.delete();
            this.update();
        }
    }

    @FXML
    private void onMouseClicked(MouseEvent event){
        Integer selectedIndex = Integer.parseInt(groupList.getSelectionModel().getSelectedIndices().get(0).toString());
        this.activeGroup = groups.get(selectedIndex);
        addUser.setVisible(true);
        userList.setVisible(true);
    }

    @FXML
    private void onAddUser(ActionEvent event)throws Exception {
    	this.showManageUserDialog();
    }

    @FXML
    private void onRemoveUser(ActionEvent event) {
    	//TODO RemoveUser
    }

    private Controller showManageUserDialog(  )throws Exception{

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/ManageUser.fxml"));
        Parent root = fxmlLoader.load();
        ManageUserController manageUserController = fxmlLoader.getController();
        manageUserController.setMainController(this);
        Stage stage = new Stage();
        stage.setTitle("Add User");
        stage.setScene(new Scene(root));
        stage.show();
        return manageUserController;
    }

    private Controller showEditGroupDialog( Group group )throws Exception{

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/EditGroup.fxml"));
        Parent root = fxmlLoader.load();
        EditGroupController editGroupController = fxmlLoader.getController();
        editGroupController.setMainController(this);
        editGroupController.setGroup(group);
        Stage stage = new Stage();
        stage.setTitle("Edit Group");
        stage.setScene(new Scene(root));
        stage.show();
        return editGroupController;
    }


    @Override
    public void addUser(User user) {
        if( activeGroup != null){
            activeGroup.addUser(user);
        }
        this.update();
    }

    @Override
    public void removeUser(User user) {
        if( activeGroup != null){
            activeGroup.removeUser(user);
        }
        this.update();

    }
}
