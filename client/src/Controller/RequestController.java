package Controller;

import java.net.URL;
import java.util.ResourceBundle;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	//text.setText(this.desc);
    }    

    @FXML
    private void onAccept(ActionEvent event) {
    	System.out.println("Notify: Accepted!");
    	root.getChildren().clear();
    }

    @FXML
    private void onDecline(ActionEvent event) {
    	this.invitation.decline();
    	System.out.println("Notify: Declined!");
    	root.getChildren().clear();
    	
    }
    
    public void setData(Invitation request){
    	this.invitation = request;
    	text.setText(Appointment.getAppointmentById(request.getAppointmentId()).getTitle());
    }
}
