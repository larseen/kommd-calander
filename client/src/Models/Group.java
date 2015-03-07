package Models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dag Frode on 02.03.2015.
 */
public class Group {

    private Integer id;
    private String name;
    private String description;
    private Group parent;

    public Group() {
    }

    public Group(Integer id) {

        this.id = id;
    }

    public Group(Integer id, String name, String description, Group parent) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.parent = parent;
    }

    /*
GET /api/groups
GET /api/groups/userID
POST /api/groups
POST /api/groups/userID
DELETE /api/groups/userID*/

    public JSONObject toJSON(){

        JSONObject json = new JSONObject();
        try {
            if( this.id != null )json.put("GroupId", this.id.toString());
            json.put("name", this.name.toString());
            if( this.description != null ) json.put("Description", this.description.toString());
            if( this.parent != null ) json.put("Parent", this.parent.getId().toString());


        }
        catch (Exception e){
            System.out.println("Could not convert group to json");
            System.out.println(e);
        }
        return json;
    }


    //GET /api/groups/groupID
    public static Group getRoomById( Object groupID ){
        JSONObject group = Model.get("/api/groups/" + groupID.toString());
        return Group.JSONtoGroup(group);
    }

    //POST /api/groups
    //POST /api/groups/groupID
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


    //DELETE /api/groups/groupID
    public boolean delete( ){
        Model.delete("/api/rooms/" + this.id.toString());
        return true;
    }


    //GET /api/groups
    public static ArrayList<Group> getRooms(){
        JSONObject response = Model.get("/api/groups");
        ArrayList<Group> groups = new ArrayList<Group>();
        try {
            JSONArray groups_json = (JSONArray)response.get("response");
            for(int i = 0 ; i < groups_json.length(); i++ ){
                JSONObject group =(JSONObject) groups_json.get(i);
                groups.add(Group.JSONtoGroup(group));
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return groups;
    }

    private static Group JSONtoGroup(JSONObject json){
        Group group = new Group();
        try {
            group = new Group( Integer.parseInt( json.get("GroupID").toString() ) );
            group.setName(json.get("Name").toString());
            group.setDescription(json.get("Description").toString());
            return group;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return group;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setParent(Group parent) {
        this.parent = parent;
    }

    public Integer getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Group getParent() {
        return parent;
    }
}
