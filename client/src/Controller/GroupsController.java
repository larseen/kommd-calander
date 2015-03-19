package Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Interfaces.Controller;
import Models.Group;
import Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


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
    @FXML
    private GridPane userInfo;
    @FXML
    private ComboBox usersCombo;

    private ArrayList<Group> groups;
    private ArrayList<User> users;
    private Group activeGroup;
    private ArrayList<User> activeGroupUsers;
    private User activeUser;
    private MainController parentController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO Initialize
        this.update();
    }


    public void update(){
        users = User.getUsers();
        usersCombo.getItems().removeAll(usersCombo.getItems());
        for( User user : users){
            usersCombo.getItems().add(user.getName().toString());
        }

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
        updateUsersList();
    }

    private void updateUsersList(){
        Integer selectedIndex = Integer.parseInt(groupList.getSelectionModel().getSelectedIndices().get(0).toString());
        this.activeGroup = groups.get(selectedIndex);

        userList.getItems().removeAll(userList.getItems());
        ArrayList<String> ul = new ArrayList<String>();
        this.activeGroupUsers = this.activeGroup.getUsers();
        for( User user : this.activeGroupUsers){
            ul.add(user.getName().toString());
        }

        ObservableList obListTime = FXCollections.observableList(ul);
        userList.getItems().clear();
        userList.setItems(obListTime);
    }

    @FXML
    private void onUserSelected(MouseEvent event){

        Integer selectedIndex = userList.getSelectionModel().getSelectedIndex();
        this.activeUser = activeGroupUsers.get(selectedIndex);

        this.selectedUserName.setText(this.activeUser.getName());
        this.selectedUserTitle.setText(this.activeUser.getJobTitle());

    }

    @FXML
    private void onViewProfile( ActionEvent event){
        this.parentController.showUserProfile(this.activeUser);
    }

    @FXML
    private void onAddUser(ActionEvent event)throws Exception {
    	Integer selectedUserID = usersCombo.getSelectionModel().getSelectedIndex();
        User user = users.get(selectedUserID);
        activeGroupUsers.add(user);
        this.activeGroup.addUser(user);
        ObservableList ul = userList.getItems();
        ul.add(user.getName());
        userList.setItems(ul);
    }

    @FXML
    private void onRemoveUser(ActionEvent event) {
        Integer selectedUserID = userList.getSelectionModel().getSelectedIndex();
        User user = activeGroupUsers.get(selectedUserID);
        System.out.println(user.getName());
        this.activeGroup.removeUser(user);

        updateUsersList();
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



    public void setParentController(Controller parentController) {
        this.parentController = (MainController)parentController;
    }
}
