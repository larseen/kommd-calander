package Models;

import org.json.JSONObject;

import java.util.ArrayList;

public class Notification extends Model {

	private int id;
	private String text;
	
	public Notification(int id, String text){
		this.id = id;
		this.text = text;
	}
	
	public int getId(){
		return id;
	}
	
	public String getText(){
		return text;
	}


	public static ArrayList<Notification> getNotificationsByUserId( Integer userId ){
		//app.post('/api/appointmentnotifications', AppointmentNotification.createAppointmentNotification);
		ArrayList<Notification> notifications = new ArrayList<Notification>();
		JSONObject response = Notification.get("/api/appointmentnotifications/" + userId.toString());

		return notifications;
	}

	public void save(){
		//app.get('/api/appointmentnotifications/:userID', AppointmentNotification.getUserAppointmentNotifications);


	}

}
