package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Models.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class RoomController implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private Button delete;
    @FXML
    private Label name;
    
    private int id;
    private String roomName;
    private RoomsController roomsController;
    private Room room;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	name.setText(this.roomName);
    }    

    @FXML
    private void onDelete(ActionEvent event) {
    	room.delete();
    	root.getChildren().clear();
    	
    	System.out.println(id);
    	
    	//UBER MESS, FIX!!
    	try {
			wait(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	room.getRoomsController().update();
    }
    
    public void setData(Room room){
    	this.id = room.getId();
    	this.roomName = room.getName();
    	name.setText(this.roomName);
    	this.roomsController = room.getRoomsController();
    	this.room = room;
    }
    
}
