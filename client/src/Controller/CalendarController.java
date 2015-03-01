package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CalendarController implements Initializable {
    @FXML
    private AnchorPane calendarMonth;
    @FXML
    private AnchorPane calendarWeek;
    @FXML
    private AnchorPane calendarDay;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO Initialize
    }    

    @FXML
    private void onCreate(ActionEvent event) {
        //TODO CreateEvent
    }
    
}
