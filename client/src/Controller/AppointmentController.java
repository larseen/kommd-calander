package Controller;

import Interfaces.Controller;
import Models.Appointment;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;



/**
 * Created by Dag Frode on 04.03.2015.
 */
public class AppointmentController implements Initializable, Controller {

    private static Integer hourHeight = 30;
    @FXML
    private AnchorPane appointmentContainer;
    @FXML
    private Label appointmentLabel;

    private Appointment appointment;

    private Integer siblings = 0;
    private Integer siblingNumber = 0;
    private Controller parentController;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void addSibling(){
        this.siblings++;
    }

    public void addSiblingNumber(){
        this.siblingNumber++;
    }

    public void setData( Appointment appointment){
        this.appointment = appointment;
        this.update();
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
            height = 1.0*(AppointmentController.hourHeight / 2);
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
        Double siblingWidth = width / ( this.siblings + 1);
        appointmentContainer.setPrefWidth(siblingWidth);
        appointmentContainer.setMaxWidth(siblingWidth);
        this.setLayoutX( siblingWidth * this.siblingNumber);
    }

    @FXML
    private void onClicked(MouseEvent mouseEvent)throws Exception{
        if( mouseEvent.getClickCount() > 1){
            showEditAppointmentDialog();
        }
    }

    private Controller showEditAppointmentDialog(  )throws Exception{

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/EditAppointment.fxml"));
        Parent root = fxmlLoader.load();
        EditAppointmentController editAppointmentController = fxmlLoader.getController();
        editAppointmentController.setMainController(parentController);
        editAppointmentController.setData(appointment);
        Stage stage = new Stage();
        stage.setTitle("Edit Appointment");
        stage.setScene(new Scene(root));
        stage.show();
        return editAppointmentController;
    }

    public void setParentController( Controller parentController ){
        this.parentController = parentController;
    }

    @Override
    public void update() {

        appointmentContainer.setStyle("-fx-background-color: white;");
        appointmentContainer.setLayoutY(this.getLayoutY());
        appointmentContainer.setPrefHeight(this.getHeight());
        appointmentLabel.setText(appointment.getTitle());
    }


}
