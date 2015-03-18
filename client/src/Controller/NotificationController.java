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
    private Notification notification;
    private int id;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	text.setText(this.desc);
    }    

    @FXML
    private void onSeen(ActionEvent event) {
        this.notification.save();
    	homeController.update();
    }
    
    public void setData(Notification notification, HomeController homeController){
        this.notification = notification;
        this.homeController = homeController;
    	this.id = notification.getId();
        this.setText(notification.getText());
    }

    private void setText(String desc){
        this.desc = desc;
        text.setText(desc);
    }

}
