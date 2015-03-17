package Models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.json.JSONObject;
import org.json.JSONArray;

import Controller.MainController;



public class Invitation extends Model {
	
	private Integer userID;
	private Integer id;
	private Integer appointmentId;
	private Appointment appointment;
	private boolean participantStatus;
	private boolean viewStatus;
	
	
	
	public Invitation(Integer userID, boolean participantStatus,
					  boolean viewStatus, Integer id, Integer appointmentId, Appointment appointment) {
		this.userID = userID;
		this.participantStatus = participantStatus;
		this.viewStatus = viewStatus;
		this.id = id;
		this.appointmentId = appointmentId;
		this.appointment = appointment;
	}

	public static ArrayList<Invitation> getInvitationsByUserId(Integer userID){
		ArrayList<Invitation> invites = new ArrayList<Invitation>();
		JSONObject json = Invitation.get("/api/invitations/user/" + userID.toString());
		try{
			JSONArray jsonInvites = (JSONArray) json.get("response");
			for(int i = 0 ; i < jsonInvites.length(); i++ ){
                JSONObject invitation =(JSONObject) jsonInvites.get(i);
				Invitation in = Invitation.JSONtoInvitation(invitation);
				if( !in.isViewStatus() )
                	invites.add(in);
            }
		}catch(Exception e){
			System.out.println(e);
		}
		return invites;
	}

	private static Invitation JSONtoInvitation(JSONObject invitation) {
		//{"Appointment_AppointmentID":122,"User_UserID":62,"ParticipantStatus":0,"ViewStatus":0,"id":97,"Group_has_GroupID":null,"Appointment":{"AppointmentID":122,"DateTimeFrom":"2015-04-03T14:00:00.000Z","DateTimeTo":"2015-04-03T16:45:00.000Z","Description":"bes","Location":"der","Room_RoomID":null,"AppointmentAdmin":62,"Title":"M�te"}


		try{
			Integer userID = invitation.getInt("User_UserID");
			boolean participantStatus = invitation.getInt("ParticipantStatus") > 0 ? true : false;
			boolean viewStatus = invitation.getInt("ViewStatus") > 0 ? true : false;
			Integer id = invitation.getInt("id");
			Integer appointmentId = invitation.getInt("Appointment_AppointmentID");
			Appointment appointment = Appointment.JSONtoAppointment((JSONObject) invitation.get("Appointment"));
			return new Invitation(userID, participantStatus, viewStatus, id, appointmentId, appointment);
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
		this.viewStatus = true;
		this.save();
	}

	public void accept() {
		this.participantStatus = true;
		this.viewStatus = true;
		this.save();
	}

	public boolean save(){
        JSONObject json = this.toJSON();
        JSONObject response = new JSONObject();
        if( this.id != null){
            System.out.println("update");
            response = Appointment.put("/api/invitations/" + this.id, json.toString());
        }
        return true;
    }

	public JSONObject toJSON(){
        JSONObject json = new JSONObject();
        try {
            if( this.userID != null ) json.put("User_UserID", this.userID.toString());
            json.put("ParticipantStatus", this.participantStatus ? 1 : "false");
            if( this.id != null ) json.put("id", this.id.toString());
            json.put("ViewStatus", this.viewStatus ? 1 : "false");
            if( this.appointmentId != null ) json.put("Appointment_AppointmentID", this.appointmentId.toString());
        }
        catch (Exception e){
            System.out.println("Could not convert user to json");
            System.out.println(e);
        }
        return json;

    }

	public Appointment getAppointment() {
		return appointment;
	}


}
