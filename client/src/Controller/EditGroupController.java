package Controller;

import Interfaces.Controller;
import Models.Group;
import Models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditGroupController implements Initializable, Controller {
    @FXML
    private TextField name;
    @FXML
    private TextField description;
    @FXML
    private Button save;
    @FXML
    private Button cancel;

    private Group group;
    private Controller parentController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Group noParent = new Group();

    }    


    public void setGroup( Group group){
        this.group = group;
        this.editGroup();
    }

    public void setMainController( Controller parentController ){
        this.parentController = parentController;
    }


    
    private void editGroup( ){
        String name = (group.getName() != null && group.getName() != "null") ? group.getName() : "";
        String description = group.getDescription() != null && group.getDescription() != "null" ? group.getDescription() : "";
        this.name.setText(name);
        this.description.setText(description);

    }

    @FXML
    private void onCancel(ActionEvent event) {
        //TODO Cancel

        ((Node)event.getSource()).getScene().getWindow().hide();
    }





    @FXML
    private void onSave(ActionEvent event) {
        //TODO Register
        group.setName(name.getText());
        group.setDescription(description.getText());
        //group.setParent(p.getText());
        group.save();
        parentController.update();
        
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @Override
    public void update() {

        editGroup();
    }




}
