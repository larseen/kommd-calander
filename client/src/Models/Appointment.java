package Models;

import Controller.CalendarController;
import javafx.scene.control.DatePicker;
import org.json.JSONArray;
import org.json.JSONObject;

import java.security.PublicKey;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Dag Frode on 02.03.2015.
 */
public class Appointment extends Model{

    private Integer id;
    private Calendar from = new GregorianCalendar(TimeZone.getTimeZone("Europe/Oslo"));
    private Calendar to = new GregorianCalendar(TimeZone.getTimeZone("Europe/Oslo"));
    private String description;
    private String location;
    private String title;
    private Room room;
    private User admin;


    public Appointment( Calendar from, Calendar to,String title, String description, String location, Room room, User admin) {
        this.from.setTime(from.getTime());
        this.to.setTime(to.getTime());
        this.title = title;
        this.description = description;
        this.location = location;
        this.room = room;
        this.admin = admin;
    }

    public Appointment( Integer id, Calendar from, Calendar to, String title, String description, String location, Room room, User admin) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Oslo"));
        this.id = id;
        this.from = from;
        this.to = to;
        this.title = title;
        this.description = description;
        this.location = location;
        this.room = room;
        this.admin = admin;
        System.out.println(dateFormat.format(from.getTime()));
        System.out.println(to.getTime());
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
            response = Appointment.post("/api/appointments/" + this.id, json.toString());
        }
        else {
            response = Appointment.post("/api/appointments", json.toString());
        }
        Appointment ap = Appointment.JSONtoAppointment(response);
        this.id = ap.getId();
        return true;

    }

    public JSONObject toJSON(){
        JSONObject json = new JSONObject();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Oslo"));
        try {
            if( this.title != null ) json.put("Title", this.title.toString());
            if( this.description != null ) json.put("Description", this.description.toString());
            if( this.room != null && this.room.getId() != null ) json.put("Room_RoomID", this.room.getId().toString());
            if( this.admin != null && this.admin.getId() != null) json.put("AppointmentAdmin", this.admin.getId().toString());
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

    private static Appointment JSONtoAppointment(JSONObject json){
        System.out.println("json appointment");
        System.out.println(json);

        //
        //public Appointment( Integer id, Calendar from, Calendar to, String title, String description, String location, Room room, User admin) {

        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Oslo"));
        //cal.setTime(dateFormat.parse("Mon Mar 14 16:02:37 GMT 2011"));// all done 2015-04-03T16:45:00.000Z
       try {
           Integer aID = Integer.parseInt(json.get("AppointmentID").toString());
           Calendar aDTF = Calendar.getInstance(TimeZone.getTimeZone("Europe/Oslo"));
           aDTF.setTimeZone(TimeZone.getTimeZone("Europe/Oslo"));
           aDTF.setTime(dateFormat.parse(json.get("DateTimeFrom").toString()));
           Calendar aDTT = Calendar.getInstance(TimeZone.getTimeZone("Europe/Oslo"));
           aDTF.setTimeZone(TimeZone.getTimeZone("Europe/Oslo"));
           aDTF.setTime(dateFormat.parse(json.get("DateTimeTo").toString()));
           String aTitle = json.get("Title").toString();
           String aDesc = json.get("Description").toString();
           String aLoc = json.get("Location").toString();
           Room aRoom = null;
           //Room aRoom = Room.getRoomById(Integer.parseInt(json.get("Room_RoomID").toString()));
           User aAdmin = User.getUserById(Integer.parseInt(json.get("AppointmentAdmin").toString()));

            Appointment appointment = new Appointment(
                    aID,
                    aDTF,
                    aDTT,
                    aTitle,
                    aDesc,
                    aLoc,
                    aRoom,
                    aAdmin
                    );
                    //Integer.parseInt(json.get("UserID").toString()),json.get("Name").toString(),json.get("Email").toString(),json.get("Phone").toString(),json.get("Title").toString());
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
        JSONObject response = Appointment.get("/api/appointments/user/" + user.getId().toString());
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
           // System.out.println(this.id);
            json.put("appointmentId", this.id);
            json.put("users", userIDs);
            //System.out.println(Appointment.post("/api/appointments/user", json.toString()));
        }
        catch (Exception e){
            System.out.println("Could not convert to json");
            System.out.println(e);
        }
       // {
       //     "users": [32,34,43],
        //    "appointment": 117
       // }
       // app.post('/api/appointments/user', Appointment.addUsers);

        //app.put('/api/appointments/user', Appointment.removeUsers);
    }


    public Calendar getTo(){
        return from;
    }

    public Integer getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public Room getRoom() {
        return room;
    }

    public User getAdmin() {
        return admin;
    }
}
