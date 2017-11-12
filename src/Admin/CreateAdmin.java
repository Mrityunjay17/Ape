package Admin;

import controls.NumberField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class CreateAdmin{

    ObservableList<String> gender= FXCollections.observableArrayList("Male","Female","Other");

    @FXML
    ComboBox Gender;
    @FXML
    TextField Fname,Mname,Lname,Gfname,Gmname,Glname,Emailid;
    @FXML
    DatePicker Dateofbirth;
    @FXML
    NumberField Mobile;
    @FXML
    PasswordField Createpassword,Confirmpassword;

    @FXML
    void CreateAccount(ActionEvent event) throws Exception {
        register(event);
    }

    @FXML
    void CreateAccount(KeyEvent keyEvent){
        if(keyEvent.getCode()== KeyCode.ENTER){
            register(keyEvent);
        }
    }

    private void register(Event event){

    }

    @FXML
    public void initialize(){
        Gender.setItems(gender);
    }
}
