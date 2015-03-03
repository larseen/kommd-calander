package Controller;

import Models.Appointment;
import Models.Room;
import Models.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.time.LocalTime;
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

    private AnchorPane ap;

    ArrayList<AppointmentController> appointmentControllers;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO Initialize

        ap = new AnchorPane();

        week_mon_col.getChildren().addAll(ap);

        ArrayList<Appointment> appointments = new ArrayList<Appointment>();
        appointments.add(new Appointment(null, new GregorianCalendar(2015,03,03,14,00,00), new GregorianCalendar(2015,03,03,16,45,00), "Møte", "der", new Room(), new User()));
        appointments.add(new Appointment(null, new GregorianCalendar(2015,03,03,17,30,00), new GregorianCalendar(2015,03,03,19,00,00), "Møte", "der", new Room(), new User()));

        appointmentControllers = new ArrayList<AppointmentController>();

        for( Appointment appointment : appointments ){
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/Appointment.fxml"));
                Parent root = fxmlLoader.load();
                AppointmentController appointmentController = fxmlLoader.getController();
                appointmentController.setData(appointment);
                appointmentControllers.add(appointmentController);


                ap.getChildren().addAll(root);
            }
            catch (Exception e){
                System.out.println(e);
            }
        }


        root.widthProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                redraw();
            }
        });
    }

    private void redraw(){
        for( AppointmentController appointmentController : appointmentControllers){
            appointmentController.setWidth(root.getWidth()/8);
        }


    }


    @FXML
    private void onCreate(ActionEvent event) {
        //TODO CreateEvent
    }

    public void wr(String r){
        System.out.println(r);
    }
    
}
