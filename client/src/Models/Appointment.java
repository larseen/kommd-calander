package Models;

import java.time.LocalTime;

/**
 * Created by Dag Frode on 02.03.2015.
 */
public class Appointment {

    private Integer id;
    private LocalTime from;
    private LocalTime to;
    private String description;
    private String location;
    private Room room;
    private User admin;

    public Appointment(Integer id, LocalTime from, LocalTime to, String description, String location, Room room, User admin) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.description = description;
        this.location = location;
        this.room = room;
        this.admin = admin;
    }

    public Integer getId() {
        return id;
    }

    public LocalTime getFrom() {
        return from;
    }

    public void setFrom(LocalTime from) {
        this.from = from;
    }

    public LocalTime getTo() {
        return to;
    }

    public void setTo(LocalTime to) {
        this.to = to;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public User getAdmin() {
        return admin;
    }

}
