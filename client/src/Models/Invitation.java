package Models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.codehaus.jettison.json.JSONArray;
import org.json.JSONObject;

import Controller.MainController;



public class Invitation extends Model {
	
	private Integer userID;
	private boolean participantStatus;
	private boolean viewStatus;
	private Integer id;
	private Integer appointmentId;
	
	
	
	public Invitation(Integer userID, boolean participantStatus,
			boolean viewStatus, Integer id, Integer appointmentId) {
		this.userID = userID;
		this.participantStatus = participantStatus;
		this.viewStatus = viewStatus;
		this.id = id;
		this.appointmentId = appointmentId;
	}

	public static ArrayList<Invitation> getInvitationsByUserId(Integer userID){
		ArrayList<Invitation> invites = new ArrayList<Invitation>();
		JSONObject json = Invitation.get("/api/invitations/user/" + userID.toString());
		try{
			JSONArray jsonInvites = (JSONArray) json.get("response");
			for(int i = 0 ; i < jsonInvites.length(); i++ ){
                JSONObject invitation =(JSONObject) jsonInvites.get(i);
                invites.add(Invitation.JSONtoInvitation(invitation));
            }
		}catch(Exception e){
			System.out.println(e);
		}
		return invites;
	}

	private static Invitation JSONtoInvitation(JSONObject invitation) {
		//{"Appointment_AppointmentID":122,"User_UserID":62,"ParticipantStatus":0,"ViewStatus":0,"id":97,"Group_has_GroupID":null,"Appointment":{"AppointmentID":122,"DateTimeFrom":"2015-04-03T14:00:00.000Z","DateTimeTo":"2015-04-03T16:45:00.000Z","Description":"bes","Location":"der","Room_RoomID":null,"AppointmentAdmin":62,"Title":"Møte"}
		try{
			Integer userID = invitation.getInt("User_UserID");
			boolean participantStatus = invitation.getBoolean("ParticipantStatus");
			boolean viewStatus = invitation.getBoolean("ViewStatus");
			Integer id = invitation.getInt("id");
			Integer appointmentId = invitation.getInt("Appointment_AppointmentID");
			return new Invitation(userID, participantStatus, viewStatus, id, appointmentId);
		}catch(Exception e){
			System.out.println(e);
		}
		
		
		return null;
	}

	public Integer getUserID() {
		return userID;
	}

	public boolean isParticipantStatus() {
		return participantStatus;
	}

	public boolean isViewStatus() {
		return viewStatus;
	}

	public Integer getId() {
		return id;
	}

	public Integer getAppointmentId() {
		return appointmentId;
	}

	public void decline() {
		this.participantStatus = false;
		this.save();
	}

	public boolean save(){
        JSONObject json = this.toJSON();
        JSONObject response = new JSONObject();
        if( this.id != null){
            System.out.println("update");
            response = Appointment.post("/api/appointments/" + this.id, json.toString());
        }
        return true;
    }

	public JSONObject toJSON(){
        JSONObject json = new JSONObject();
        try {
            if( this.userID != null ) json.put("User_UserID", this.userID.toString());
            json.put("ParticipantStatus", String.valueOf(this.participantStatus));
            if( this.id != null ) json.put("id", this.id.toString());
            json.put("ViewStatus", String.valueOf(this.viewStatus));
            if( this.appointmentId != null ) json.put("Appointment_AppointmentID", this.appointmentId.toString());
        }
        catch (Exception e){
            System.out.println("Could not convert user to json");
            System.out.println(e);
        }
        return json;

    }
	
	
	
}
