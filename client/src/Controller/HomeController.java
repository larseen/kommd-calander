package Controller;

import java.net.URL;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import Models.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

public class HomeController implements Initializable {
	@FXML
	private AnchorPane root;
	@FXML
	private Label dateDisplay;
	@FXML
	private FlowPane notifications;
	@FXML
	private ListView<String> appointments;
	@FXML
	private FlowPane requests;
	@FXML
	private Button createEvent;

	private ArrayList<?> list = new ArrayList<>();

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Initialize
		// init date
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		dateDisplay.setText(sdf.format(date));

		// ask server for appointments
		ArrayList<String> time = new ArrayList<String>();

		// testdata for appointments
		time.add("17.30");
		time.add("17.45");
		time.add("18.00");

		// fill the appointment of the day list
		ObservableList obListTime = FXCollections.observableList(time);
		appointments.getItems().clear();
		appointments.setItems(obListTime);

		ArrayList<String> list = new ArrayList<String>();
		list.add("../View/Notification.fxml");
		list.add("../View/Notification.fxml");
		list.add("../View/Notification.fxml");
		list.add("../View/Notification.fxml");

		for (String v : list) {
			showView(v);

		}

	

	}

	private void showView(String view) {
		try {
			notifications.setOrientation(Orientation.VERTICAL);
			Parent root = FXMLLoader.load(getClass().getResource(view));
			// notifications.getChildren().removeAll(notifications.getChildren());
			notifications.getChildren().addAll(root);

			// Parent root = FXMLLoader.load(getClass().getResource(view));
			// notifications.getChildren().removeAll(notifications.getChildren());
			// notifications.getChildren().addAll(root);

		} catch (Exception e) {

		}
	}

	@FXML
	private void onCreate(ActionEvent event) {
		// TODO CreateEvent
	}
}
