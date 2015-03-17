package Models;

import Controller.AppointmentController;
import Controller.MainController;
import Interfaces.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Dag Frode on 04.03.2015.
 */
public class KVOMMDCalendar extends Model{

    private ArrayList<Appointment> appointments = new ArrayList<Appointment>();
    private ArrayList<AppointmentController> appointmentControllers = new ArrayList<AppointmentController>();
    private ArrayList<AnchorPane> appointmentAnchorPanes = new ArrayList<AnchorPane>();
    private User user;
    private Calendar calendar = new GregorianCalendar();
    private Controller parentController;

    public KVOMMDCalendar(User user, Calendar calendar, Controller parentController){
        this.parentController = parentController;
        this.user = user;
        this.calendar = calendar;
        System.out.println(this.calendar.get(Calendar.WEEK_OF_YEAR));


        ArrayList<Appointment> tmpAppointments = Appointment.getAppointmentsByUser(user);


        for( Appointment appointment : tmpAppointments ){
            Calendar date = appointment.getFrom();
            if (
                    date.get(Calendar.YEAR) == calendar.get(Calendar.YEAR) &&
                    date.get(Calendar.WEEK_OF_YEAR) == calendar.get(Calendar.WEEK_OF_YEAR)

                    ) {
                appointments.add(appointment);

                try{
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/Appointment.fxml"));
                    AnchorPane root = fxmlLoader.load();
                    AppointmentController appointmentController = fxmlLoader.getController();
                    appointmentController.setData(appointment);
                    appointmentControllers.add(appointmentController);
                    appointmentController.setParentController(this.parentController);


                    appointmentAnchorPanes.add(root);
                }
                catch (Exception e){
                    System.out.println(e);
                    System.out.println(e.getMessage());
                }
            }
            for (Integer i = 0; i < appointments.size(); i++) {
                for (Integer j = i + 1; j < appointments.size(); j++) {
                    if (appointments.get(i).isSiblings(appointments.get(j))) {
                        appointmentControllers.get(i).addSibling();
                        appointmentControllers.get(j).addSibling();
                        appointmentControllers.get(j).addSiblingNumber();
                    }
                }
            }
        }
    }



    public ArrayList<AnchorPane> getMondayAppointments(){
        return  getDaysAppointmentsByDayOfWeek(Calendar.MONDAY);
    }

    public ArrayList<AnchorPane> getDaysAppointmentsByDayOfWeek( Integer dayOfWeek ){

        ArrayList<AnchorPane> ap = new ArrayList<AnchorPane>();
        for( Integer i = 0; i < appointments.size(); i++ ){
            if( appointments.get(i).getFrom().get(Calendar.DAY_OF_WEEK) == dayOfWeek){
                ap.add(appointmentAnchorPanes.get(i));
            }
        }
        return ap;
    }

    public ArrayList<AnchorPane> getTuesdayAppointments(){
        return  getDaysAppointmentsByDayOfWeek(Calendar.TUESDAY);

    }
    public ArrayList<AnchorPane> getWednesdayAppointments(){
        return  getDaysAppointmentsByDayOfWeek(Calendar.WEDNESDAY);

    }
    public ArrayList<AnchorPane> getThursdayAppointments(){
        return  getDaysAppointmentsByDayOfWeek(Calendar.THURSDAY);

    }

    public void setParentController(Controller parentController) {
        this.parentController = parentController;
    }

    public ArrayList<AnchorPane> getFridayAppointments(){
        return  getDaysAppointmentsByDayOfWeek(Calendar.FRIDAY);

    }
    public ArrayList<AnchorPane> getSaturdayAppointments(){
        return  getDaysAppointmentsByDayOfWeek(Calendar.SATURDAY);

    }
    public ArrayList<AnchorPane> getSundayAppointments(){
        return  getDaysAppointmentsByDayOfWeek(Calendar.SUNDAY);

    }





    public void redraw( Double dayWidth){
        for( AppointmentController appointmentController : this.appointmentControllers){
            appointmentController.setWidth(dayWidth);
        }
    }
}
