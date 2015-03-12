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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.util.*;

import static javafx.application.Application.launch;

import java.net.URL;

public class CalendarController implements Initializable, Controller {
    @FXML
    private AnchorPane week_mon_col;

    @FXML
    private AnchorPane week_tue_col;
    @FXML
    private AnchorPane week_wed_col;
    @FXML
    private AnchorPane week_thu_col;
    @FXML
    private AnchorPane week_fri_col;
    @FXML
    private AnchorPane week_sat_col;
    @FXML
    private AnchorPane  week_sun_col;
    @FXML
    private AnchorPane root;
    @FXML
    private Label weekNumber;

    @FXML
    private Button nWeek;
    @FXML
    private Button tWeek;
    @FXML
    private Button pWeek;

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

    @FXML
    private void onTWeek(ActionEvent event) {
        //TODO CreateEvent
    }
    @FXML
    private void onNWeek(ActionEvent event) {
        //TODO CreateEvent
    }
    @FXML
    private void onPWeek(ActionEvent event) {
        //TODO CreateEvent
    }


    @Override
    public void update() {
        week_mon_col.getChildren().removeAll(week_mon_col.getChildren());
        week_tue_col.getChildren().removeAll(week_tue_col.getChildren());
        week_wed_col.getChildren().removeAll(week_wed_col.getChildren());
        week_thu_col.getChildren().removeAll(week_thu_col.getChildren());
        week_fri_col.getChildren().removeAll(week_fri_col.getChildren());
        week_sat_col.getChildren().removeAll(week_sat_col.getChildren());
        week_sun_col.getChildren().removeAll(week_sun_col.getChildren());

        this.calendar = new KVOMMDCalendar( MainController.getCurrentUser(), new GregorianCalendar(), this);

        week_mon_col.getChildren().addAll(this.calendar.getMondayAppointments());
        week_tue_col.getChildren().addAll(this.calendar.getTuesdayAppointments());
        week_wed_col.getChildren().addAll(this.calendar.getWednesdayAppointments());
        week_thu_col.getChildren().addAll(this.calendar.getThursdayAppointments());
        week_fri_col.getChildren().addAll(this.calendar.getFridayAppointments());
        week_sat_col.getChildren().addAll(this.calendar.getSaturdayAppointments());
        week_sun_col.getChildren().addAll(this.calendar.getSundayAppointments());

    }
}
