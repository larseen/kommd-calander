package Models;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.json.*;

/**
 * Created by Dag Frode on 03.03.2015.
 */
public class Model {

    private static String url;

    public Model(){

    }


    public static void setURL(String url){
        Model.url = url;
    }

    public static JSONObject post(String api_url, String data){
        Client client = Client.create();
        WebResource webResource = client.resource(url + api_url);
        ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, data);

        return Model.getResponseAsJSON(response.getEntity(String.class));
    }

    public static JSONObject get(String api_url){
        Client client = Client.create();
        WebResource webResource = client.resource(url + api_url);
        ClientResponse response = webResource.accept("application/json").type("application/json").get(ClientResponse.class);

        return Model.getResponseAsJSON(response.getEntity(String.class));
    }

    public static JSONObject getResponseAsJSON(String response){
        JSONObject obj = new JSONObject();
        try {
            obj = new JSONObject(response);
        }
        catch (Exception e){
            System.out.println(e);
        }
        return obj;
    }
}
