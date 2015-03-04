package Controller;

import java.net.URL;
import java.util.ResourceBundle;

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
    
    private int id;
    private String desc;
    private String requestType;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	text.setText(this.desc);
    }    

    @FXML
    private void onAccept(ActionEvent event) {
    	System.out.println("Notify: Accepted!");
    	root.getChildren().clear();
    }

    @FXML
    private void onDecline(ActionEvent event) {
    	System.out.println("Notify: Declined!");
    	root.getChildren().clear();
    }
    
    public void setData(Request request){
    	this.desc = request.getText();
    	text.setText(this.desc);
    	this.id = request.getId();
    	this.requestType = request.getType();
    }
}
