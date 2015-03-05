import Models.Model;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.JSONObject;

/**
 * Created by Dag Frode on 27.02.2015.
 */
public class Login extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/Login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        Model.setURL("http://api.larsen.so");
        JSONObject data =  Model.post("/api/session", "{\"email\":\"larsen@me.com\",\"password\":\"larsen\"}");
        System.out.println(data);
        data =  Model.get("/api/users");
        System.out.println(data);
    }

    public static void main(String[] args) {
        launch(args);
    }


}
