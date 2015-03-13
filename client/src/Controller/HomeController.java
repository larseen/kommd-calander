package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;

import Interfaces.Controller;
import Models.Appointment;
import Models.Notification;
import Models.Request;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomeController implements Initializable, Controller {
	@FXML
	private AnchorPane root;
	@FXML
	private Label dateDisplay;
	@FXML
	private VBox notifications;
	@FXML
	private ListView<String> appointments;
	@FXML
	private VBox requests;
	@FXML
	private Button createEvent;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		setTime();
		update();
		
		// list with notifications
		ArrayList<Notification> notificationsList = new ArrayList<Notification>();
		notificationsList.add(new Notification(12, "1 Some text"));
		notificationsList.add(new Notification(13, "2 Some other text"));
		notificationsList.add(new Notification(14, "3 Some other text"));
		notificationsList.add(new Notification(14, "4 Some other text"));
		notificationsList.add(new Notification(14, "5 Some other text"));
		notificationsList.add(new Notification(14, "6 Some other text"));
		notificationsList.add(new Notification(14, "7 Some other text"));
		notificationsList.add(new Notification(14, "8 Some other text"));
		notificationsList.add(new Notification(14, "9 Some other text"));
		notificationsList.add(new Notification(14, "10 Some other text"));
		notificationsList.add(new Notification(14, "11 Some other text"));
		notificationsList.add(new Notification(14, "12 Some other text"));
		notificationsList.add(new Notification(14, "13 Some other text"));
		notificationsList.add(new Notification(14, "14 Some other text"));
		notificationsList.add(new Notification(14, "15 Some other text"));
		notificationsList.add(new Notification(14, "16 Some other text"));
		notificationsList.add(new Notification(14, "17 Some other text"));
		notificationsList.add(new Notification(14, "18 Some other text"));

		fillNotifications(notificationsList);
		
		//list with requests
		ArrayList<Request> requestList = new ArrayList<Request>();
		requestList.add(new Request(1,"Invited to BestGroupEver!","group"));
		requestList.add(new Request(1,"Invited to BoringMeeting!","appointment"));
		requestList.add(new Request(1,"Invited to SomeGroup!","group"));
		
		fillRequests(requestList);
	}

	@FXML
	private void onCreate(ActionEvent event) throws Exception{
		this.showEditAppointmentDialog();
	}

	private Controller showEditAppointmentDialog(  )throws Exception{

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/EditAppointment.fxml"));
		Parent root = fxmlLoader.load();
		EditAppointmentController editAppointmentController = fxmlLoader.getController();
		editAppointmentController.setMainController(this);
		editAppointmentController.setData(new Appointment());
		Stage stage = new Stage();
		stage.setTitle("New Appointment");
		stage.setScene(new Scene(root));
		stage.show();
		return editAppointmentController;
	}
	
	private void setTime(){
		//set date
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		dateDisplay.setText(sdf.format(date));
	}

	private void fillNotifications(ArrayList<Notification> notificationsList) {
		//notifications.setOrientation(Orientation.VERTICAL);
		
		// list for controllers
				ArrayList<NotificationController> notificationControllers = new ArrayList<NotificationController>();

				for (Notification notification : notificationsList) {
					try {
						FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
								"../View/Notification.fxml"));
						Parent root = fxmlLoader.load();
						NotificationController notificationController = fxmlLoader
								.getController();
						notificationController.setData(notification, this);

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

	public void removeNotification(AnchorPane notification){
		notifications.getChildren().remove(notification);
		this.redrawNotifications();
	}

	private void redrawNotifications(){
		ArrayList tmpNotifications = new ArrayList(this.notifications.getChildren());
		notifications.getChildren().removeAll(notifications.getChildren());
		notifications.getChildren().addAll(tmpNotifications);
	}

	private void fillRequests(ArrayList<Request> requestList) {
		
		// list for controllers
		ArrayList<RequestController> requestControllers = new ArrayList<RequestController>();

		for (Request request : requestList) {
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
						"../View/Request.fxml"));
				Parent root = fxmlLoader.load();
				RequestController requestController = fxmlLoader
						.getController();
				requestController.setData(request);

				requestControllers.add(requestController);
				requests.getChildren().addAll(root);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update() {

		// ask server for appointments
		ArrayList<String> appointmentList = new ArrayList<String>();
		ArrayList<Appointment> appointments = new ArrayList<Appointment>();
		appointments = Appointment.getAppointmentsByCalendar(new GregorianCalendar());
		// list with appointments
		for(Appointment appointment : appointments){
			appointmentList.add(appointment.toString());
		}
		fillAppointments(appointmentList);
	}
}
