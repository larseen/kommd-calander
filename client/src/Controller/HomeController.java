package Controller;

import java.io.IOException;
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
import Models.Notification;
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

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Initialize
		setTime();

		// ask server for appointments
		ArrayList<String> appointmentList = new ArrayList<String>();

		// testdata for appointments
		appointmentList.add("14:15: Meet your parents at R2010");
		appointmentList.add("15:10: Meet employee at S11");
		appointmentList.add("19:20: Yoga at Super Zen");

		fillAppointments(appointmentList);
		
		// list with notifications
		ArrayList<Notification> notificationsList = new ArrayList<Notification>();
		notificationsList.add(new Notification(12, "1 Some text"));
		notificationsList.add(new Notification(13, "2 Some other text"));
		notificationsList.add(new Notification(14, "3 Some other text"));

		fillNotifications(notificationsList);
	}

	@FXML
	private void onCreate(ActionEvent event) {
		// TODO CreateEvent
	}
	
	private void setTime(){
		//set date
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		dateDisplay.setText(sdf.format(date));
	}

	private void fillNotifications(ArrayList<Notification> notificationsList) {
		notifications.setOrientation(Orientation.VERTICAL);
		
		// list for controllers
				ArrayList<NotificationController> notificationControllers = new ArrayList<NotificationController>();

				for (Notification notification : notificationsList) {
					try {
						FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
								"../View/Notification.fxml"));
						Parent root = fxmlLoader.load();
						NotificationController notificationController = fxmlLoader
								.getController();
						notificationController.setData(notification);
						System.out.println(notification.getText());

						notificationControllers.add(notificationController);

						notifications.getChildren().addAll(root);

					} catch (IOException e) {
						e.printStackTrace();
					}
				}


	}

	private void fillAppointments(ArrayList<String> appointmentList) {
		// fill the appointment of the day list
		ObservableList obListTime = FXCollections.observableList(appointmentList);
		appointments.getItems().clear();
		appointments.setItems(obListTime);
	}

	private void fillRequests() {
		// TODO requests

	}
}
