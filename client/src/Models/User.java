package Models;

import Controller.MainController;
import javafx.fxml.Initializable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class User extends Model{

    /*
    login
    POST /api/session
    logout
    DELETE /api/session

    getUsers
    GET /api/users
    getUser(id)
    GET /api/users/userID
    save
    POST /api/users
    save
    POST /api/users/userID
    updatePassword(oldPasswod, newPassword)
    PUT /api/users/userID
    save
    DELETE /api/users/userID */

    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String jobTitle;
    private String password;

    public User(){

    }

    public User(Integer id, String name, String email, String phone, String jobTitle) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.jobTitle = jobTitle;
    }

    public static User getUserById( Object userID ){
        JSONObject user = Model.get("/api/users/" + userID.toString());
        System.out.println(user.toString());
        return User.JSONtoUser(user);
    }

    public static ArrayList<User> getUsers(){
        JSONObject response = Model.get("/api/users");
        ArrayList<User> users = new ArrayList<User>();
        try {
            JSONArray users_json = (JSONArray)response.get("response");
            for(int i = 0 ; i < users_json.length(); i++ ){
                JSONObject user =(JSONObject) users_json.get(i);
                users.add(User.JSONtoUser(user));
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return users;
    }

    private static User JSONtoUser(JSONObject json){
        try {
            User user = new User(Integer.parseInt(json.get("UserID").toString()),json.get("Name").toString(),json.get("Email").toString(),json.get("Phone").toString(),json.get("Title").toString());
            return user;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public boolean isPasswordCorrect( String password ){
        return User.login(this.email, password);
    }

    public boolean save(){
        JSONObject json = this.toJSON();
        if( this.id != null){
            System.out.println("update");
            System.out.println(User.post("/api/users/" + this.id, json.toString()));
        }
        else {
            System.out.println("create");
            System.out.println(User.post("/api/users", json.toString()));
        }
        return true;
    }

    public JSONObject toJSON(){

        JSONObject json = new JSONObject();
        try {
            if( this.id != null )json.put("UserId", this.id.toString());
            json.put("Name", this.name.toString());
            json.put("Email", this.email.toString());
            if( this.phone != null ) json.put("Phone", this.phone.toString());
            if( this.jobTitle != null ) json.put("Title", this.jobTitle.toString());
            if( this.password != null ) json.put("Password", this.password.toString());


        }
        catch (Exception e){
            System.out.println("Could not convert user to json");
            System.out.println(e);
        }
        return json;
    }

    public Integer getId(){
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public boolean updatePassword(String oldPassword, String newPassword){
        JSONObject json = new JSONObject();
        try {

            json.put("oldPassword", oldPassword);
            json.put("newPassword", newPassword);
        }
        catch (Exception e){
            System.out.println("Problem with update password json");
        }
        JSONObject json1 = User.put("/api/users/" + this.id, json.toString());
        try {

            if( json1.get("error") == null ){
                return true;
            }
        }
        catch (Exception e){

        }
        return false;
    }

    public boolean delete(){
        User.delete("/api/users/" + this.id);
        return true;
    }



    public static boolean login(String email, String password){
        JSONObject data = new JSONObject();
        try {
            data.put("email", email);
            data.put("password", password);
        }
        catch (Exception e){
            System.out.println(e);
        }
        JSONObject response = User.post("/api/session", data.toString());
        if( response.has("userID") ){
            try {
                User user = User.getUserById((Integer) response.get("userID"));
                MainController.setCurrentUser(user);

            }catch (Exception e){
                System.out.println(e);
            }
            return true;
        }
        return false;
    }


}
