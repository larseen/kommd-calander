package Models;

import Controller.CalendarController;
import Controller.MainController;
import javafx.scene.control.DatePicker;

import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.image.AreaAveragingScaleFilter;
import java.security.PublicKey;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Dag Frode on 02.03.2015.
 */
public class Appointment extends Model{

    private Integer id;
    private Calendar from = new GregorianCalendar();
    private Calendar to = new GregorianCalendar();
    private String description;
    private String location;
    private String title;
    private Room room;
    private User admin;
    private Integer roomID;
    private Integer adminID;

    public Appointment(){


    }
    public Appointment( Calendar from, Calendar to,String title, String description, String location, Integer roomID, Integer adminID) {
        this.from.setTime(from.getTime());
        this.to.setTime(to.getTime());
        this.title = title;
        this.description = description;
        this.location = location;
        this.roomID = roomID;
        this.adminID = adminID;
    }

    public Appointment( Integer id, Calendar from, Calendar to, String title, String description, String location, Integer roomID, Integer adminID) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.title = title;
        this.description = description;
        this.location = location;
        this.roomID = roomID;
        this.adminID = adminID;
    }

    public Integer getLengthInMinutes(){
        return (to.get(Calendar.HOUR_OF_DAY) - from.get(Calendar.HOUR_OF_DAY)) * 60 + to.get(Calendar.MINUTE) - from.get(Calendar.MINUTE);
    }

    public Integer getStartTimeInMinutes(){
        return from.get(Calendar.HOUR_OF_DAY) * 60 + from.get(Calendar.MINUTE);
    }

    public String getDescription(){
        return description;
    }

    public boolean isSiblings( Appointment appointment){
        Calendar af = appointment.getFrom();
        Calendar at = appointment.getTo();
        if(


                (af.after(this.from) && at.before(this.to)) ||
                        (af.equals(this.from) && at.equals(this.to)) ||
                        ( ( af.before(this.from) || af.equals(this.from) ) && ( at.after(this.from) || at.equals(this.from))) ||
                        ( ( af.before(this.to) || af.equals(this.to) ) && ( at.after(this.to) || at.equals(this.to)))
                ){
            return true;
        }
        return false;
    }

    public boolean save(){
        JSONObject json = this.toJSON();
        JSONObject response = new JSONObject();
        if( this.id != null){
            System.out.println("update");
            response = Appointment.post("/api/appointments/" + this.id, json.toString());
        }
        else {
            System.out.println("new");
            response = Appointment.post("/api/appointments", json.toString());
            Appointment ap = Appointment.JSONtoAppointment(response);
            this.id = ap.getId();
            this.inviteUser(MainController.getCurrentUser());
        }
        return true;

    }

    public JSONObject toJSON(){
        JSONObject json = new JSONObject();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        try {
            if( this.title != null ) json.put("Title", this.title.toString());
            if( this.description != null ) json.put("Description", this.description.toString());
            if( this.roomID != null  ) json.put("Room_RoomID", this.roomID.toString());
            if( this.adminID != null ) json.put("AppointmentAdmin", this.adminID.toString());
            if( this.from != null ) json.put("DateTimeFrom", dateFormat.format(this.from.getTime()).toString());
            if( this.to != null ) json.put("DateTimeTo", dateFormat.format(this.to.getTime()).toString());
            if( this.location != null ) json.put("Location", this.location.toString());
        }
        catch (Exception e){
            System.out.println("Could not convert user to json");
            System.out.println(e);
        }
        return json;

    }

    public static Appointment JSONtoAppointment(JSONObject json){
        //
        //public Appointment( Integer id, Calendar from, Calendar to, String title, String description, String location, Room room, User admin) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        //cal.setTime(dateFormat.parse("Mon Mar 14 16:02:37 GMT 2011"));// all done 2015-04-03T16:45:00.000Z
       try {
           Integer aID;
           if(json.has("AppointmentID")) {
               aID = Integer.parseInt(json.get("AppointmentID").toString());
           }
           else{
               aID = Integer.parseInt(json.get("Appointment_AppointmentID").toString());

           }

           Calendar aDTF = new GregorianCalendar();
           Date from = dateFormat.parse(json.get("DateTimeFrom").toString());
           aDTF.setTime(from);

           Calendar aDTT = new GregorianCalendar();
           Date to = dateFormat.parse(json.get("DateTimeTo").toString());
           aDTT.setTime(to);

           String aTitle = json.get("Title").toString();
           String aDesc = json.get("Description").toString();
           String aLoc = "";
           if( json.has("Location"))  aLoc = json.get("Location").toString();
           Integer aRoomID = null;
           if( json.has("Room_RoomID") && json.get("Room_RoomID").toString() != "null" ){
               aRoomID = Integer.parseInt(json.get("Room_RoomID").toString());

           }
           Integer aAdminID = null;
           if( json.get("AppointmentAdmin").toString() != "null" ){
               aAdminID = Integer.parseInt(json.get("AppointmentAdmin").toString());

           }

            Appointment appointment = new Appointment(
                    aID,
                    aDTF,
                    aDTT,
                    aTitle,
                    aDesc,
                    aLoc,
                    aRoomID,
                    aAdminID
                    );
           return appointment;
         }
        catch (Exception e){
             System.out.println(e);
         }
        return null;
    }

    public Calendar getFrom(){
        return from;
    }


    public String getTitle(){
        return title;
    }

    public static ArrayList<Appointment> getAppointmentsByUser( User user ){
        //System.out.println("Getting user " + user.getId().toString());
        ArrayList<Appointment> appointments = new ArrayList<Appointment>();
        JSONObject response = Appointment.get("/api/appointments/users/" + user.getId().toString());
        try {
            JSONArray appointments_json = (JSONArray)response.get("appointments");
            for(int i = 0 ; i < appointments_json.length(); i++ ){
                JSONObject appointment =(JSONObject) appointments_json.get(i);
                appointments.add(Appointment.JSONtoAppointment(appointment));
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return appointments;
    }

    public void inviteUser( User user ){
        ArrayList<User> users = new ArrayList<User>();
        users.add(user);
        this.inviteUsers(users);
    }

    public void inviteUsers( ArrayList<User> users ){
        ArrayList<Integer> userIDs = new ArrayList<Integer>();
        for( User user : users ){
            if( user.getId() != null)
                userIDs.add(user.getId());
        }
        JSONObject json = new JSONObject();
        try {
            json.put("appointment", this.id);
            json.put("users", userIDs);
        }
        catch (Exception e){
            System.out.println("Could not convert to json");
            System.out.println(e);
        }
       Appointment.post("/api/appointments/users", json.toString());

    }

    public void uninviteUser( User user ){
        ArrayList<User> users = new ArrayList<User>();
        users.add(user);
        this.uninviteUsers(users);
    }

    public void uninviteUsers( ArrayList<User> users ){
        ArrayList<Integer> userIDs = new ArrayList<Integer>();
        for( User user : users ){
            if( user.getId() != null)
                userIDs.add(user.getId());
        }
        JSONObject json = new JSONObject();
        try {
            json.put("appointment", this.id);
            json.put("users", userIDs);
        }
        catch (Exception e){
            System.out.println("Could not convert to json");
            System.out.println(e);
        }
        Appointment.put("/api/appointments/users", json.toString());

    }

    public Calendar getTo(){
        return to;
    }

    public Integer getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public Room getRoom() {
        if( room == null && roomID != null){
            room = Room.getRoomById(roomID);
        }
        return room;
    }

    public User getAdmin() {
        if( admin == null && adminID != null){
            admin = User.getUserById(adminID);
        }

        return admin;
    }

    public boolean delete(){
        Appointment.delete("/api/appointments/" + this.id);
        return true;
    }

    public static Appointment getAppointmentById( Object appointmentID ){
        JSONObject appointment = Appointment.get("/api/appointments/" + appointmentID.toString());
        return Appointment.JSONtoAppointment(appointment);
    }

    public static ArrayList<Appointment> getAppointments(){
        JSONObject response = Model.get("/api/appointments");
        ArrayList<Appointment> appointments = new ArrayList<Appointment>();
        try {
            JSONArray appointments_json = (JSONArray)response.get("response");
            for(int i = 0 ; i < appointments_json.length(); i++ ){
                JSONObject appointment =(JSONObject) appointments_json.get(i);
                appointments.add(Appointment.JSONtoAppointment(appointment));
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return appointments;
    }

    public void setAdmin(User admin) {

        this.admin = admin;
        this.adminID = admin.getId();
    }

    public void setFrom(Calendar from) {
        this.from = from;
    }

    public void setTo(Calendar to) {
        this.to = to;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
	public static ArrayList<Appointment> getAppointmentsByCalendar(GregorianCalendar gc) {
	        //System.out.println("Getting user " + user.getId().toString());
	        ArrayList<Appointment> appointments = new ArrayList<Appointment>();
	        JSONObject response = Appointment.get("/api/appointments/users/" + MainController.getCurrentUser().getId().toString());
	        try {
	            JSONArray appointments_json = (JSONArray)response.get("appointments");
	            for(int i = 0 ; i < appointments_json.length(); i++ ){
	                JSONObject appointment =(JSONObject) appointments_json.get(i);
	                Appointment a = Appointment.JSONtoAppointment(appointment);
	                if(a.from.get(Calendar.YEAR) == gc.get(Calendar.YEAR) && a.from.get(Calendar.MONTH) == gc.get(Calendar.MONTH) && a.from.get(Calendar.DAY_OF_MONTH) == gc.get(Calendar.DAY_OF_MONTH)) 
	                	appointments.add(a);
	                
	            }
	        }
	        catch (Exception e){
	            System.out.println(e);
	        }
	        return appointments;
	    }

	public String toString(){
		String hour = String.valueOf(this.from.get(Calendar.HOUR_OF_DAY));
		String min = String.valueOf(this.from.get(Calendar.MINUTE));
		
		return new String(hour+":"+min+" - "+this.title);
	}

    public static ArrayList<User> getInvitedUsersByAppointmentId(Integer appointmentId ){
        //{"AppointmentID":195,"DateTimeFrom":"2015-03-17T07:12:58.000Z","DateTimeTo":"2015-03-17T11:42:58.000Z","Description":"ad","Location":"null","Room_RoomID":null,"AppointmentAdmin":62,"Title":"hey you","users":[{"UserID":62,"PasswordHash":"vJ40uZjZAMxKUBczTX3UMFU9IjGoQ80kAAkf6nEwfHTS+KbfY611eZdAbb01s9gxtYNvFsjV16bGwu8tDQIEpA==","Salt":"78YQ3AIGC3Eb0++pgtmDZA==","Name":"Dag Frode","Email":"dfs@live.no","Phone":2147483647,"Title":"Test","Admin":1,"_pivot_id":122,"_pivot_Appointment_AppointmentID":195,"_pivot_User_UserID":62}]}

        ArrayList<User> invitedUsers = new ArrayList<User>();
        JSONObject response = Appointment.get("/api/appointments/users/invited/" + appointmentId.toString());
        try {
            JSONArray appointments_json = (JSONArray)response.get("users");
            for(int i = 0 ; i < appointments_json.length(); i++ ){
                JSONObject u = (JSONObject)appointments_json.get(i);
                invitedUsers.add(User.JSONtoUser(u));
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return invitedUsers;
    }

    public ArrayList<User>getInvitedUsers(){
        return Appointment.getInvitedUsersByAppointmentId(this.id);
    }

    //app.get('/api/appointments/users/:appointmentID', Appointment.getUsers);
    //app.post('/api/appointments/users', Appointment.addUsers);
    //app.put('/api/appointments/users', Appointment.removeUsers);
}

