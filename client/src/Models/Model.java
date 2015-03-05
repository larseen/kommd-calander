package Models;

import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.filter.ClientFilter;
import org.json.*;

import java.util.ArrayList;

/**
 * Created by Dag Frode on 03.03.2015.
 */
public class Model {

    private static String url;
    private static Client client = Client.create();
    private static WebResource webResource;
    private static WebResource.Builder builder;
    private static ArrayList<Object>cookies;

    private static void addCookie(Object cookie){
        Model.cookies.add(cookie);
    }

    private static ArrayList<Object> getCookies(){
        return Model.cookies;
    }


    public Model(){

    }


    public static void setURL(String url){
        Model.url = url;
    }

    public static JSONObject post(String api_url, String data){
        if( Model.cookies == null){

            initCookies();

        }



        WebResource webResource = client.resource(url + api_url);

        ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, data);

        return Model.getResponseAsJSON(response.getEntity(String.class));
    }

    private static void  initCookies(){
        Model.cookies = new ArrayList<Object>();
        client.addFilter(new ClientFilter() {

            @Override
            public ClientResponse handle(ClientRequest request) throws ClientHandlerException {
                System.out.println(Model.getCookies());
                System.out.println("cookie handling");
                if (getCookies() != null) {
                    request.getHeaders().put("Cookie", getCookies());
                }
                ClientResponse response = getNext().handle(request);
                if (response.getCookies() != null) {
                    // simple addAll just for illustration (should probably check for duplicates and expired cookies)
                    addCookie(response.getCookies());
                    System.out.println("cookie :" + response.getCookies());
                }
                return response;
            }
        });
    }

    public static JSONObject get(String api_url){
        if( cookies == null){
            initCookies();
        }



        WebResource webResource = client.resource(url + api_url);
        ClientResponse response = webResource.accept("application/json").type("application/json").get(ClientResponse.class);
        System.out.println(response);
        return Model.getResponseAsJSON(response.getEntity(String.class));
    }

    public static JSONObject getResponseAsJSON(String response){
        System.out.println(response);
        JSONObject obj = new JSONObject();
        try {
            obj = new JSONObject(response);
        }
        catch (Exception e){
            try {
                obj = new JSONObject();
                obj.put("users", new JSONArray(response));
            }
            catch (Exception f){
                System.out.println(f);
            }
            System.out.println(e);
        }
        return obj;
    }
}
