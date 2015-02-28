package Controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by Dag Frode on 27.02.2015.
 */
public class MainController extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/Main.fxml"));
        primaryStage.setTitle("Edit Appointment");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        Pane subPane = (Pane) root.lookup("#subPane");
        TestController test = new TestController(subPane);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
