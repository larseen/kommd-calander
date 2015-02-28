package Controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Dag Frode on 27.02.2015.
 */
public class MainController extends Application {

    private Pane subPane;
    private Button home;
    private Button calendar;
    private Button groups;
    private Button rooms;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/Main.fxml"));
        primaryStage.setTitle("Edit Appointment");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        this.initMenu(root);

        subPane = (Pane) root.lookup("#displayView");
        HomeController home = new HomeController(subPane);
    }

    private void initMenu(Parent root){
        home = (Button) root.lookup("#home");
        home.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent e) {
                        subPane.getChildren().removeAll(subPane.getChildren());
                        try {
                            HomeController home = new HomeController(subPane);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                });

        groups = (Button) root.lookup("#groups");
        groups.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                subPane.getChildren().removeAll(subPane.getChildren());
                try {
                    GroupsController groups = new GroupsController(subPane);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

    }

    public static void main(String[] args) {
        launch(args);
    }

}
