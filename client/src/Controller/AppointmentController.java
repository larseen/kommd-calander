package Controller;

import Models.Appointment;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;



/**
 * Created by Dag Frode on 04.03.2015.
 */
public class AppointmentController implements Initializable {

    private static Integer hourHeight = 30;
    @FXML
    private AnchorPane appointmentContainer;
    @FXML
    private Label appointmentLabel;

    private Appointment appointment;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setData( Appointment appointment){
        this.appointment = appointment;
        this.init();
    }

    private void init(){
        appointmentContainer.setStyle("-fx-background-color: white;");
        appointmentContainer.setLayoutY(this.getLayoutY());
        appointmentContainer.setPrefHeight(this.getHeight());
        appointmentLabel.setText(appointment.getDescription());
    }


    public void setColor(String color){
        appointmentContainer.setStyle("-fx-background-color: "+color+";");
    }


    public Double getHeight(){
        Double height;

        Integer minutes = appointment.getLengthInMinutes();
        if( minutes > (AppointmentController.hourHeight / 2)){
            height = (Double) Double.parseDouble(minutes.toString()) * AppointmentController.hourHeight / 60;
        }
        else {
            height = 50.0;
        }

        return height;
    }

    private Double getLayoutY(){
        Integer minutes = appointment.getStartTimeInMinutes();
        Double layoutY = (Double) Double.parseDouble(minutes.toString()) * AppointmentController.hourHeight / 60;
        return layoutY;
    }

    public void setLayoutX(Double layoutX){
        appointmentContainer.setLayoutX(layoutX);
    }

    public void setWidth(Double width){
        appointmentContainer.setPrefWidth(width);
    }
}
