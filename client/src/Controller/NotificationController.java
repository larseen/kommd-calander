package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Models.Notification;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class NotificationController implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private Label text;
    @FXML
    private Button seenBtn;
    
    private String desc;

    private HomeController homeController;
    
    private int id;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO init
    	text.setText(this.desc);
    }    

    @FXML
    private void onSeen(ActionEvent event) {
    	//TODO button
    	homeController.removeNotification(root);
    	notifySeen();
    }
    
    public void setData(Notification notification, HomeController homeController){
        this.homeController = homeController;
    	this.id = notification.getId();
        this.setText(notification.getText());
    }

    private void setText(String desc){
        this.desc = desc;
        text.setText(desc);
    }
    
    private void notifySeen(){
    	System.out.println("Notify!" + desc);
    }
}
