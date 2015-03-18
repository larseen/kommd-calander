package Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Notification extends Model {

	private int id;
	private String text;
	private boolean seenStatus = false;
	
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
		//[
		// {"AppointmentNotification_NotificationID":8,"User_UserID":62,"SeenStatus":1,"id":1,"NotificationID":
		// {"NotificationID":8,"Message":"req.body.message","Appointment_AppointmentID":122}}
		// ,{"AppointmentNotification_NotificationID":10,"User_UserID":62,"SeenStatus":0,"id":3,"NotificationID":{"NotificationID":10,"Message":"req.body.message","Appointment_AppointmentID":122}}]

		ArrayList<Notification> notifications = new ArrayList<Notification>();

		JSONObject response = Notification.get("/api/appointmentnotifications/" + userId.toString());
		try{
			JSONArray n_json = (JSONArray) response.get("response");
			for( int i = 0; i < n_json.length(); i++){
				JSONObject j = (JSONObject) n_json.get(i);
				notifications.add( Notification.JSONtoNotification(j));
			}

		}
		catch (Exception e){

		}

		return notifications;
	}

	private static Notification JSONtoNotification(JSONObject j) throws JSONException {
		JSONObject d = (JSONObject) j.get("NotificationID");
		return new Notification( j.getInt("AppointmentNotification_NotificationID"), d.getString("Message"));
	}

	public void save(){
		//app.put('/api/notifications/appointment/:notificationID', Notification.updateAppointmentNotification);
		//app.get('/api/appointmentnotifications/:userID', AppointmentNotification.getUserAppointmentNotifications);
		//app.put('/api/notifications/appointment/:notificationID')
		Notification.put("/api/notifications/appointment/" + this.id, this.toJSON().toString());

	}

	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		try {
			json.put("SeenStatus", this.seenStatus);
		}
		catch (Exception e){
			System.out.println(e);
		}
		return json;
	}

}
