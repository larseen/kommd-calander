package Models;

import Controller.MainController;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.json.JSONObject;

import java.util.*;

public class User extends Model{
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String jobTitle;

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
        return User.JSONtoUser(user);
    }

    private static User JSONtoUser(JSONObject json){
        try {
            User user = new User(Integer.parseInt(json.get("UserID").toString()),json.get("Navn").toString(),json.get("Email").toString(),json.get("Phone").toString(),json.get("Title").toString());
            return user;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return null;
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
