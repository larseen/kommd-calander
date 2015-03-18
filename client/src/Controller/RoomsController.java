package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Interfaces.Controller;
import Models.Notification;
import Models.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class RoomsController implements Initializable,Controller {
    @FXML
    private AnchorPane root;
    @FXML
    private Button createRoom;
    @FXML
    private VBox rooms;
    @FXML
    private TextField name;
    @FXML
    private TextField size;
    @FXML
    private TextField location;
    @FXML
    private TextField description;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        update();
    }    

    public void update(){
    	//rooms.get.removeAll(groupList.getItems());
    	rooms.getChildren().removeAll(rooms.getChildren());
    	ArrayList<Room> roomList = Room.getRooms();
    	fillRooms(roomList);
    }
    
    private void fillRooms(ArrayList<Room> roomList) {
    	// list for controllers
		ArrayList<RoomController> roomControllers = new ArrayList<RoomController>();

		for (Room room : roomList) {
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
						"../View/Room.fxml"));
				Parent root = fxmlLoader.load();
				RoomController roomController = fxmlLoader
						.getController();
				roomController.setData(room);
				roomController.setRoomsController(this);

				roomControllers.add(roomController);

				rooms.getChildren().addAll(root);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
    private void onCreate(ActionEvent event) {
    	
    	String nameField = name.getText();
		int sizeField = 0;
		if( size.getText() != "")
			sizeField = Integer.parseInt(size.getText());
    	String locationField = location.getText();
    	String descriptionField = description.getText();
    	
    	Room room = new Room(nameField, sizeField, locationField, descriptionField);
    	room.save();
    	name.clear();
    	size.clear();
    	location.clear();
    	description.clear();
    	
    	//Refreshes the rooms view
    	update();
    }
    
    
}
