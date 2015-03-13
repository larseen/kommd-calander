package Controller;

import Interfaces.Controller;
import Models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class PasswordController implements Initializable, Controller {
    @FXML
    private TextField oldPasswordTextArea;
    @FXML
    private TextField newPasswordTextArea;
    @FXML
    private TextField reNewPasswordTextArea;
    @FXML
    private Button save;
    @FXML
    private Button cancel;
    @FXML
    private Label errorLabel;

    private User user;
    private Controller parentController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO Initialize
        errorLabel.setText("");
    }



    public void setUser(User user){
        this.user = user;
        if( user.getId() == null){
            oldPasswordTextArea.setDisable(true);
        }
    }

    public void setMainController( Controller parentController ){
        this.parentController = parentController;
    }



    @FXML
    private void onCancel(ActionEvent event) {
        //TODO Cancel
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
    @FXML
    private void onSave(ActionEvent event) {
        //TODO Register
        String oldPassword = oldPasswordTextArea.getText();

        String newPassword = newPasswordTextArea.getText();
        String reNewPassword = reNewPasswordTextArea.getText();
        if( newPassword.equals( reNewPassword )){
            if( user.getId() == null  ){
                user.setPassword(newPassword);

                ((Node) event.getSource()).getScene().getWindow().hide();

            }
            else {
                if( user.isPasswordCorrect( oldPassword )){
                    user.updatePassword(oldPassword, newPassword);

                        ((Node) event.getSource()).getScene().getWindow().hide();

                    errorLabel.setText("Old password is incorrect");
;
                }

                else{
                    errorLabel.setText("Old password is incorrect");
                }
            }
        }
        else {
            errorLabel.setText("Passwords does not match");
        }


}

    @Override
    public void update() {};

}
