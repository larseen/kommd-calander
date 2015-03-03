package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class AppointmentController implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private Label tekst;
    @FXML
    private Button seenBtn;
    
    private String text = "This is a label";
    
    private int id;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO init
    	tekst.setText(text);
    }    

    @FXML
    private void onSeen(ActionEvent event) {
    	//TODO button
    	root.getChildren().clear();
    	notifySeen();
    }
    
    public void setText(String text){
    	this.text = text;
    }
    
    public void setId(int id){
    	this.id = id;
    }
    
    private void notifySeen(){
    	System.out.println("Notify!");
    }
}
