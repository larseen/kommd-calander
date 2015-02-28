package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class HomeController  {

    private AnchorPane root;

    private Label dateDisplay;

    private ListView<?> notifications;

    private ListView<?> appointments;

    private ListView<?> requests;

    private Button createEvent;

    private Pane parentPane;

 

    public HomeController(Pane parentPane)throws Exception{

        this.parentPane = parentPane;
        Parent root = FXMLLoader.load(getClass().getResource("../View/Home.fxml"));
        this.parentPane.getChildren().addAll(root);

        createEvent = (Button) root.lookup("#createEvent");
        createEvent.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                System.out.println("Create Event");
            }
        });
    }
    
}
