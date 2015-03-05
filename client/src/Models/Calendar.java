package Models;

import java.util.ArrayList;

/**
 * Created by Dag Frode on 04.03.2015.
 */
public class Calendar extends Model{

    private ArrayList<Appointment> appointments;

    public Calendar( ArrayList<Appointment> appointments){
        this.appointments = appointments;
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

    public static Calendar getCalendarByUser(User user){

        return new Calendar(null);

    }
}
