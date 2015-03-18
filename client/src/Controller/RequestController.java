package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Interfaces.Controller;
import Models.Appointment;
import Models.Invitation;
import Models.Request;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class RequestController implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private Label text;
    @FXML
    private Button accept;
    @FXML
    private Button decline;
    
    private Invitation invitation;
    private HomeController parentController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	//text.setText(this.desc);
    }    

    @FXML
    private void onAccept(ActionEvent event) {
    	System.out.println("Notify: Accepted!");
        this.invitation.accept();
    	root.getChildren().clear();
    }

    @FXML
    private void onDecline(ActionEvent event) {
    	this.invitation.decline();
    	System.out.println("Notify: Declined!");
    	this.parentController.update();
    	
    }
    
    public void setData(Invitation request){
    	this.invitation = request;
    	text.setText(this.invitation.getAppointment().getTitle());
    }

    public void setParentController(HomeController parentController) {
        this.parentController = parentController;
    }
}
