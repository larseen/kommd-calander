package Models;

import Controller.RoomsController;

/**
 * Created by Dag Frode on 02.03.2015.
 */
public class Room {

    private int id;
    private String name;
    private int size;
    private String location;
    private String description;
    private RoomsController roomsController;

    public Room(){
    }
    
    public Room(int id, String name, int size, String location, String description, RoomsController roomsController){
    	this.id = id;
    	this.name = name;
    	this.size = size;
    	this.location = location;
    	this.description = description;
    	this.roomsController = roomsController;
    	
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public RoomsController getRoomsController(){
    	return roomsController;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setRoomsController(RoomsController roomsController){
    	this.roomsController = roomsController;
    }
}
