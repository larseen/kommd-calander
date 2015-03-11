package Models;

import Controller.AppointmentController;
import Controller.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Created by Dag Frode on 04.03.2015.
 */
public class KVOMMDCalendar extends Model{

    private ArrayList<Appointment> appointments = new ArrayList<Appointment>();
    private ArrayList<AppointmentController> appointmentControllers = new ArrayList<AppointmentController>();
    private User user;

    public KVOMMDCalendar(User user){
        this.appointments = appointments;
    }

    public ArrayList<AnchorPane> getDayAppointmentsByDate( ){

        ArrayList<AnchorPane> dayAppointments = new ArrayList<AnchorPane>();

        //appointments.add(new Appointment( new GregorianCalendar(2015,03,03,14,00,00), new GregorianCalendar(2015,03,03,16,45,00), "Møte", "bes", "der", new Room(), User.getUserById(62)));
        //appointments.add(new Appointment( new GregorianCalendar(2015,03,03,16,50,00), new GregorianCalendar(2015,03,03,18,00,00), "Møte 1 ", "bes", "der", new Room(), new User()));
        //appointments.add(new Appointment( new GregorianCalendar(2015,03,03,17,30,00), new GregorianCalendar(2015,03,03,18,00,00), "Møte 2 ", "bes", "der", new Room(), new User()));
        //appointments.add(new Appointment( new GregorianCalendar(2015,03,03,17,30,00), new GregorianCalendar(2015,03,03,19,00,00), "Møte 3", "bes", "der", new Room(), new User()));

       // System.out.println(appointments.get(0).toJSON());
        //System.out.println(appointments.get(0).save());
        //appointments.get(0).inviteUser(MainController.getCurrentUser());
        //ArrayList<Appointment> a = Appointment.getAppointmentsByUser(User.getUserById(62));
        //System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        //System.out.println(a.get(0).toJSON());
        //appointments.get(0).uinviteUser(User.getUserById(60));

        //System.out.println(Appointment.getAppointmentsByUser(MainController.getCurrentUser()));

        appointments.addAll(Appointment.getAppointmentsByUser(MainController.getCurrentUser()));


        for( Appointment appointment : appointments ){
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/Appointment.fxml"));
                AnchorPane root = fxmlLoader.load();
                AppointmentController appointmentController = fxmlLoader.getController();
                appointmentController.setData(appointment);
                appointmentControllers.add(appointmentController);


                dayAppointments.add(root);
            }
            catch (Exception e){
                System.out.println(e);
            }
        }
        for( Integer i = 0; i < appointments.size(); i++){
            for( Integer j = i+1; j < appointments.size(); j++){
                if( appointments.get(i).isSiblings(appointments.get(j)) ){
                    appointmentControllers.get(i).addSibling();
                    appointmentControllers.get(j).addSibling();
                    appointmentControllers.get(j).addSiblingNumber();
                }
            }
        }
        return dayAppointments;
    }



    public void addAppointment(Appointment appointment){
        this.appointments.add(appointment);
    }

    public ArrayList<Appointment> getDaysAppointments(){
        //TODO
        return appointments;
    }

    public ArrayList<Appointment> getWeeksAppointments(){
        //TODO
        return appointments;
    }

    public ArrayList<Appointment> getMonthsAppointments(){
        //TODO
        return appointments;
    }

    public static KVOMMDCalendar getCalendarByUser(User user){

        return new KVOMMDCalendar(null);

    }

    public void redraw( Double dayWidth){
        for( AppointmentController appointmentController : this.appointmentControllers){
            appointmentController.setWidth(dayWidth);
        }
    }
}
