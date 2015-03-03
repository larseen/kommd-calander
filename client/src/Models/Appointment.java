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



}
