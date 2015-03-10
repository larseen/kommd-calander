package Models;

import Controller.CalendarController;

import java.util.Calendar;

/**
 * Created by Dag Frode on 02.03.2015.
 */
public class Appointment {

    private Integer id;
    private Calendar from;
    private Calendar to;
    private String description;
    private String location;
    private Room room;
    private User admin;


    public Appointment(Integer id, Calendar from, Calendar to, String description, String location, Room room, User admin) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.description = description;
        this.location = location;
        this.room = room;
        this.admin = admin;
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
}
