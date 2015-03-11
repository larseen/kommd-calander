package Controller;

import Interfaces.Controller;
import Models.*;
import Models.KVOMMDCalendar;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.*;

import java.util.*;

import static javafx.application.Application.launch;

import java.net.URL;

public class CalendarController implements Initializable, Controller {
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
    private Pane day_col;
    @FXML
    private AnchorPane root;

    private KVOMMDCalendar calendar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.update();

        root.widthProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                redraw();
            }
        });
    }

    private void redraw(){
        this.calendar.redraw( root.getWidth()/8 );


    }


    @FXML
    private void onCreate(ActionEvent event) {
        //TODO CreateEvent
    }



    @Override
    public void update() {

        this.calendar = new KVOMMDCalendar( MainController.getCurrentUser() );
        ArrayList<AnchorPane> daysAppointments = this.calendar.getDayAppointmentsByDate();
        week_mon_col.getChildren().addAll(daysAppointments);

    }
}
