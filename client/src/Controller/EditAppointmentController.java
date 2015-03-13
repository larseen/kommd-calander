package Controller;

import Interfaces.ManageUser;
import Interfaces.Controller;
import Models.Appointment;
import Models.Room;
import Models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;


/**
 * Created by Dag Frode on 04.03.2015.
 */
public class EditAppointmentController implements Initializable, Controller, ManageUser {

    @FXML
    private TextField title;
    @FXML
    private TextArea description;
    @FXML
    private DatePicker date;
    @FXML
    private TextField fromHour;
    @FXML
    private TextField toHour;
    @FXML
    private TextField fromMin;
    @FXML
    private TextField toMin;
    @FXML
    private TextField location;
    @FXML
    private ComboBox rooms;
    private ArrayList<Room> roomList;

    private Appointment appointment;
    private Controller parentController;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void setData( Appointment appointment){
        this.appointment = appointment;
        this.update();
    }





 public void setMainController( Controller controller){
     parentController = controller;
 }

    @FXML
    private void onManageInvites(ActionEvent event) throws Exception{
        this.showManageUserDialog();
    }

    @FXML
    private void onCancel(ActionEvent event) {
        //TODO Cancel

        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void onSave(ActionEvent event) {
        appointment.setTitle(title.getText());
        appointment.setDescription(description.getText());
        appointment.setAdmin(MainController.getCurrentUser());
        appointment.getFrom().set(Calendar.HOUR_OF_DAY, Integer.parseInt(fromHour.getText()));
        appointment.getFrom().set(Calendar.MINUTE, Integer.parseInt(fromMin.getText()));

        appointment.getTo().set(Calendar.HOUR_OF_DAY, Integer.parseInt(toHour.getText()));
        appointment.getTo().set(Calendar.MINUTE, Integer.parseInt(toMin.getText()));

        appointment.getFrom().set(Calendar.DAY_OF_MONTH, date.getValue().getDayOfMonth());
        appointment.getFrom().set(Calendar.YEAR, date.getValue().getYear());
        appointment.getFrom().set(Calendar.MONTH, (date.getValue().getMonth().getValue()-1));

        appointment.getTo().set(Calendar.DAY_OF_MONTH, date.getValue().getDayOfMonth());
        appointment.getTo().set(Calendar.YEAR, date.getValue().getYear());
        appointment.getTo().set(Calendar.MONTH, (date.getValue().getMonth().getValue()-1));


        if( appointment.getId() == null){
            appointment.setAdmin(MainController.getCurrentUser());
        }
        appointment.save();
        parentController.update();

        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @Override
    public void update() {
        if( appointment.getId() != null){
            title.setText(appointment.getTitle());
            description.setText(appointment.getDescription());
            roomList = Room.getRooms();
            date.setValue(appointment.getFrom().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            rooms.getItems().removeAll(rooms.getItems());
            for( Room room : roomList){
                rooms.getItems().add(room.getName() + " : " + room.getSize().toString());
            }
            fromHour.setText(String.valueOf(appointment.getFrom().get(Calendar.HOUR_OF_DAY)));
            fromMin.setText(String.valueOf(appointment.getFrom().get(Calendar.MINUTE)));
            toHour.setText(String.valueOf(appointment.getTo().get(Calendar.HOUR_OF_DAY)));
            toMin.setText(String.valueOf(appointment.getTo().get(Calendar.MINUTE)));
            location.setText(appointment.getLocation());
        }
    }

    @Override
    public void addUser(User user) {
        if( appointment != null){
            appointment.inviteUser( user );
        }
    }

    @Override
    public void removeUser(User user) {
        if( appointment != null){
            appointment.uninviteUser(user);
        }
    }

    private Controller showManageUserDialog(  )throws Exception{

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/ManageUser.fxml"));
        Parent root = fxmlLoader.load();
        ManageUserController manageUserController = fxmlLoader.getController();
        manageUserController.setMainController(this);
        Stage stage = new Stage();
        stage.setTitle("Manage Users");
        stage.setScene(new Scene(root));
        stage.show();
        return manageUserController;
    }
}
