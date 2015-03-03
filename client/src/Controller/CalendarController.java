package Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.*;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.HPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;

public class CalendarController implements Initializable {
    @FXML
    private AnchorPane calendarMonth;
    @FXML
    private AnchorPane calendarWeek;
    @FXML
    private AnchorPane calendarDay;
    @FXML
    private Pane calendarContent;
    @FXML
    private Pane week_mon_col;
    @FXML
    private AnchorPane root;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO Initialize


        root.widthProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                redraw();
            }
        });
        root.heightProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                redraw();
            }
        });
    }

    private void redraw(){
        Pane p = new Pane();
        Pane ap1 = new Pane(new Label("Hi"));
        Pane ap2 = new Pane(new Label("Hi2"));
        ap1.setLayoutX(0);
        ap1.setLayoutY(50);
        ap1.setLayoutX(50);
        ap1.setLayoutY(150);
        ap1.setStyle("-fx-background-color: aquamarine;");
        ap2.setStyle("-fx-background-color: cornflowerblue;");

        p.getChildren().addAll(ap1,ap2);

        week_mon_col.getChildren().removeAll(week_mon_col.getChildren());
        week_mon_col.getChildren().addAll(p);
    }


    @FXML
    private void onCreate(ActionEvent event) {
        //TODO CreateEvent
    }

    public void wr(String r){
        System.out.println(r);
    }
    
}
