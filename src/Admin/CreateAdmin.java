package Admin;

import HttpClient.HttpPost;
import controls.NumberField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.json.JSONObject;

import java.net.URL;


public class CreateAdmin{

    String Add_border ="-fx-border-width: 1,1,1,1;-fx-border-color: red";
    String Remove_border ="-fx-border-width: 1,1,1,1";
    String Email_Pattern="(^[a-zA-Z0-9._]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$)";
    String Mobile_Pattern="[0-9]{10}";
    ObservableList<String> gender= FXCollections.observableArrayList("Male","Female","Other");

    @FXML
    ComboBox<String> Gender;
    @FXML
    TextField F_name,Mname,Lname,Gfname,Gmname,Glname, Email_id;
    @FXML
    DatePicker Date_of_birth;
    @FXML
    NumberField Mobile;
    @FXML
    PasswordField password,Confirm_Password;

    @FXML
    void CreateAccount(ActionEvent event) {
        try {
            if (getData().length()!=0){
                register(getData());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void createAccount(KeyEvent keyEvent){
        if(keyEvent.getCode()== KeyCode.ENTER){
            try {
                if (getData().length()!=0){
                    register(getData());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    JSONObject getData() throws Exception{

        JSONObject Create_account=new JSONObject();

        if(!F_name.getText().equals("")&&!Gfname.getText().equals("")&&!Gender.getValue().equals(null)
                && Email_id.getText().matches(Email_Pattern)&&Mobile.getText().matches(Mobile_Pattern)&&
                password.getText().length()<=6 &&password.getText().equals(Confirm_Password.getText())){

            Create_account.put("First_Name", F_name.getText());
            Create_account.put("Middle_Name",Mname.getText());
            Create_account.put("Last_Name",Lname.getText());
            Create_account.put("Guardian_First_name",Gfname.getText());
            Create_account.put("Guardian_Middle_name",Gmname.getText());
            Create_account.put("Guardian_Last_name",Glname.getText());
            Create_account.put("Date_Of_Birth", Date_of_birth.getValue());
            Create_account.put("Sex",Gender.getValue());
            Create_account.put("EmailId", Email_id.getText());
            Create_account.put("Mobile",Mobile.getText());
            Create_account.put("Password",password.getText());
        }

        if(F_name.getText().equals("")){
            F_name.setStyle(Add_border);
        }
        if(Gfname.getText().equals("")){
            Gfname.setStyle(Add_border);
        }
        if(Email_id.getText().equals("")){
            Email_id.setStyle(Add_border);
        }
        if(Mobile.getText().equals("")){
            Mobile.setStyle(Add_border);
        }
        if(password.getText().length()<6){
            password.setStyle(Add_border);
            Confirm_Password.setStyle(Add_border);
        }
        if(Gender.getSelectionModel().isEmpty()){
            Gender.setStyle(Add_border);
        }
        if(Date_of_birth.getValue()==null){
            Date_of_birth.setStyle(Add_border);
        }
        return Create_account;
    }


    private void register(JSONObject jsonObject) throws Exception {
        URL url = new URL("http://localhost/create_admin");

        HttpPost httpPost=new HttpPost(url,jsonObject);
        System.out.println(httpPost.getStringbuffer().toString());
    }

    @FXML
    public void initialize(){

        Email_id.textProperty().addListener(((observable, oldValue, newValue) -> {
            if(newValue.matches(Email_Pattern)){
                Email_id.setStyle(Remove_border);
            }
            else {
                Email_id.setStyle(Add_border);
            }
        }));

        F_name.textProperty().addListener(((observable, oldValue, newValue) -> {
            if(newValue.length()==0){
                F_name.setStyle(Add_border);
            }
            else {
                F_name.setStyle(Remove_border);
            }
        }));

        Gfname.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.length()==0){
                Gfname.setStyle(Add_border);
            }
            else {
                Gfname.setStyle(Remove_border);
            }
        });

        Mobile.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.length()>10){
                Mobile.setText(oldValue);
                Mobile.setStyle(Remove_border);
            }
            else if(newValue.matches(Mobile_Pattern)){
                Mobile.setStyle(Remove_border);
            }
            else {
                Mobile.setStyle(Add_border);
            }
        });

        password.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.length()<6){
                password.setStyle(Add_border);
            }
            else {
                password.setStyle(Remove_border);
            }
        });
        Confirm_Password.textProperty().addListener((observable, oldValue, newValue) -> {
            if(password.getText().equals(Confirm_Password.getText())){
                Confirm_Password.setStyle(Remove_border);
            }
            else {
                Confirm_Password.setStyle(Add_border);
            }
        });

        Date_of_birth.valueProperty().addListener((observable, oldValue, newValue) -> {
           Date_of_birth.setStyle(Remove_border);
        });

        Gender.valueProperty().addListener((observable, oldValue, newValue) -> {
            Gender.setStyle(Remove_border);

        });

        Gender.setItems(gender);
    }
}
