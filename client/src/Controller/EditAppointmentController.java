package Controller;

import Interfaces.Controller;
import Models.Appointment;
import Models.Room;
import Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class EditAppointmentController implements Initializable, Controller {

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
    private ArrayList<User> users;
    @FXML
    private ComboBox usersCombo;
    @FXML
    private ListView userList;

    private Appointment appointment;
    private Controller parentController;
    private ArrayList<User> usersToInvite = new ArrayList<User>();
    private ArrayList<User> usersToRemove = new ArrayList<User>();
    private ArrayList<User> usersInList = new ArrayList<User>();


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
        appointment.inviteUsers(usersToInvite);
        appointment.uninviteUsers(usersToRemove);
        parentController.update();

        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @Override
    public void update() {
        if( appointment.getId() != null){
            title.setText(appointment.getTitle());
            description.setText(appointment.getDescription());
            date.setValue(appointment.getFrom().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            fromHour.setText(String.valueOf(appointment.getFrom().get(Calendar.HOUR_OF_DAY)));
            fromMin.setText(String.valueOf(appointment.getFrom().get(Calendar.MINUTE)));
            toHour.setText(String.valueOf(appointment.getTo().get(Calendar.HOUR_OF_DAY)));
            toMin.setText(String.valueOf(appointment.getTo().get(Calendar.MINUTE)));
            location.setText(appointment.getLocation());
            for( User u : appointment.getInvitedUsers()){

            }
        }
        else{
            Calendar cal = Calendar.getInstance();
            date.setValue(cal.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            fromHour.setText(String.valueOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)));
            fromMin.setText(String.valueOf(Calendar.getInstance().get(Calendar.MINUTE)));
            cal.add(Calendar.MINUTE, 30);
            toHour.setText(String.valueOf(cal.get(Calendar.HOUR_OF_DAY)));
            toMin.setText(String.valueOf(cal.get(Calendar.MINUTE)));

        }


        users = User.getUsers();
        usersCombo.getItems().removeAll(usersCombo.getItems());
        for( User user : users){
            usersCombo.getItems().add(user.getName().toString());
        }

        roomList = Room.getRooms();
        rooms.getItems().removeAll(rooms.getItems());
        for( Room room : roomList){
            rooms.getItems().add(room.getName() + " : " + room.getSize().toString());
        }

    }

    @FXML
    private void onAddUser(ActionEvent event)throws Exception {
        Integer selectedUserID = usersCombo.getSelectionModel().getSelectedIndex();
        User user = users.get(selectedUserID);

        this.usersToInvite.add(user);
        this.usersInList.add(user);

        ObservableList ul = userList.getItems();
        ul.add(user.getName());
        userList.setItems(ul);
    }

    @FXML
    private void onRemoveUser(ActionEvent event) {
        Integer selectedUserID = userList.getSelectionModel().getSelectedIndex();
        User user = usersInList.get(selectedUserID);

        usersInList.remove(user);
        usersToInvite.remove(user);
        usersToRemove.add(user);
        ArrayList<String> ul = new ArrayList<String>();

        for( User u : this.usersInList){
            ul.add(u.getName().toString());
        }

        ObservableList obListTime = FXCollections.observableList(ul);
        userList.getItems().clear();
        userList.setItems(obListTime);
    }



}
