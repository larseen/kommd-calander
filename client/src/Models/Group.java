package Models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dag Frode on 02.03.2015.
 */
public class Group extends Model {

    private Integer id;
    private String name;
    private String description;

    public Group() {
    }

    public Group(Integer id) {

        this.id = id;
    }

    public Group(Integer id, String name, String description, Group parent) {
        this.id = id;
        this.name = name;
        this.description = description;
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
            if( this.description != null ) json.put("description", this.description.toString());

        }
        catch (Exception e){
            System.out.println("Could not convert group to json");
            System.out.println(e);
        }
        return json;
    }


    //GET /api/groups/groupID
    public static Group getGroupById( Object groupID ){
        JSONObject group = Model.get("/api/groups/" + groupID.toString());
        return Group.JSONtoGroup(group);
    }

    //POST /api/groups
    //POST /api/groups/groupID
    public boolean save(){

        JSONObject json = this.toJSON();
        if( this.id != null){
            User.post("/api/groups/" + this.id, json.toString());
        }
        else {
            User.post("/api/groups", json.toString());
        }
        return true;
    }

    public void addMember( User user ){

    }


    //DELETE /api/groups/groupID
    public boolean delete( ){
        Model.delete("/api/groups/" + this.id.toString());
        return true;
    }


    //GET /api/groups
    public static ArrayList<Group> getGroups(){
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

    public void addUser( User user ){
        ArrayList<User> users = new ArrayList<User>();
        users.add(user);
        this.addUsers(users);
    }

    public void addUsers(ArrayList<User> users ){
        ArrayList<Integer> userIDs = new ArrayList<Integer>();
        for( User user : users ){
            if( user.getId() != null)
                userIDs.add(user.getId());

        }
        JSONObject json = new JSONObject();
        try {
            json.put("group", this.id);
            json.put("users", userIDs);
        }
        catch (Exception e){
            System.out.println("Could not convert to json");
            System.out.println(e);
        }
        Group.post("/api/groups/user", json.toString());
    }

    public void removeUser( User user ){
        ArrayList<User> users = new ArrayList<User>();
        users.add(user);
        this.removeUsers(users);
    }

    public void removeUsers(ArrayList<User> users ){
        ArrayList<Integer> userIDs = new ArrayList<Integer>();
        for( User user : users ){
            if( user.getId() != null)
                userIDs.add(user.getId());

        }
        JSONObject json = new JSONObject();
        try {
            json.put("group", this.id);
            json.put("users", userIDs);
        }
        catch (Exception e){
            System.out.println("Could not convert to json");
            System.out.println(e);
        }
        Group.put("/api/groups/user", json.toString());
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
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

}
