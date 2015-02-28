package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class GroupsController {

    private AnchorPane root;

    private Button createGroup;

    private Button addUser;

    private Button removeUser;

    private ListView<?> groupList;

    private ListView<?> userList;

    private Label selectedUserName;
    private Label selectedUserTitle;
    private Pane parentPane;

    private ListView<?> usersGroupsList;

    public GroupsController(Pane parentPane) throws Exception{
        this.parentPane = parentPane;
        Parent root = FXMLLoader.load(getClass().getResource("../View/Groups.fxml"));
        this.parentPane.getChildren().addAll(root);
    }
    
}
