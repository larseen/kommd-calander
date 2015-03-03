package Models;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.util.*;

public class User extends Model{
    private Integer id;
    private String name;
    private String email;
    private String phone;

    public User(){

    }

    public static boolean login(String email, String password){
        return true;
    }


}
