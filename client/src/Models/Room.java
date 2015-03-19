package Models;

import Controller.RoomsController;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Room {

    private Integer id;
    private String name;
    private Integer size;
    private String location;
    private String description;
    private RoomsController roomsController;

    public Room(){
    }
    
    public Room(String name, int size, String location, String description){
    	this.name = name;
    	this.size = size;
    	this.location = location;
    	this.description = description;
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


    public Room( Integer id){
        this.id = id;
    }


    public JSONObject toJSON(){

        JSONObject json = new JSONObject();
        try {
            if( this.id != null )json.put("RoomId", this.id.toString());
            //json.put("Name", this.name.toString());
            json.put("name", this.name.toString());
            //json.put("Size", this.size.toString());
            json.put("size", this.size.toString());
            if( this.description != null ) json.put("Description", this.description.toString());
            if( this.location != null ) json.put("Location", this.location.toString());


        }
        catch (Exception e){
            System.out.println("Could not convert user to json");
            System.out.println(e);
        }
        return json;
    }


    //GET /api/rooms/userID
    public static Room getRoomById( Object roomID ){
        JSONObject room = Model.get("/api/rooms/" + roomID.toString());
        return Room.JSONtoRoom(room);
    }

    //POST /api/rooms
    //POST /api/rooms/userID
    public boolean save(){

        JSONObject json = this.toJSON();
        if( this.id != null){
            System.out.println("update");
            System.out.println(User.post("/api/rooms/" + this.id, json.toString()));
        }
        else {
            System.out.println("create");
            System.out.println(User.post("/api/rooms", json.toString()));
        }
        return true;
    }


    //DELETE /api/rooms/userID
    public boolean delete( ){
        Model.delete("/api/rooms/" + this.id.toString());
        return true;
    }


    //GET /api/rooms
    public static ArrayList<Room> getRooms(){
        JSONObject response = Model.get("/api/rooms");
        ArrayList<Room> rooms = new ArrayList<Room>();
        try {
            JSONArray rooms_json = (JSONArray)response.get("response");
            for(int i = 0 ; i < rooms_json.length(); i++ ){
                JSONObject room =(JSONObject) rooms_json.get(i);
                rooms.add(Room.JSONtoRoom(room));
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return rooms;
    }

    private static Room JSONtoRoom(JSONObject json){
        Room room = new Room();
        try {
            room = new Room( Integer.parseInt( json.get("RoomID").toString() ) );
            room.setName(json.get("Name").toString());
            room.setSize(Integer.parseInt(json.get("Size").toString()));
            room.setDescription(json.get("Description").toString());
            room.setLocation(json.get("Location").toString());
            return room;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return room;
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
    
    public RoomsController getRoomsController() {
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
