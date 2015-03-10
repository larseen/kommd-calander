package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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

public class RoomsController implements Initializable {
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
        // TODO
    	
    	//fill rooms
    	ArrayList<Room> roomList = Room.getRooms();
//		roomList.add(new Room(1, "BeztRoom1!", 10, "Nordre gate", "Dette er et rom.", this));
//		roomList.add(new Room(2, "BeztRoom2!", 15, "Sørdre gate", "Dette er et rom.", this));
//		roomList.add(new Room(3, "BeztRoom3!", 19, "Midtre gate", "Dette er et rom.", this));
//		roomList.add(new Room(4, "BeztRoom4!", 4, "Midstrerste gate", "Dette er et rom.", this));
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
    	int sizeField = Integer.parseInt(size.getText());
    	String locationField = location.getText();
    	String descriptionField = description.getText();
    	
    	Room room = new Room(nameField, sizeField, locationField, descriptionField);
    	room.save();
    	name.clear();
    	size.clear();
    	location.clear();
    	description.clear();
    	
    	//Refreshes the rooms view
    	
    }
    
    
}
