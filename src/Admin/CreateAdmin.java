package Admin;

import HttpClient.HttpPost;
import controls.NumberField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.json.JSONObject;
import Utility.Verification;

import java.net.URL;


public class CreateAdmin{
    Verification verification =new Verification();
   //  boolean nullAdmin;
    ObservableList<String> gender= FXCollections.observableArrayList("Male","Female","Other");
    private Stage Admin_Stage;
    @FXML
    ComboBox<String> Gender;
    @FXML
    TextField fName,Mname,Lname, gFName,Gmname,Glname, Email_id;
    @FXML
    DatePicker Date_of_birth;
    @FXML
    NumberField Mobile;
    @FXML
    PasswordField password,Confirm_Password;



    @FXML
    void CreateAccount(ActionEvent event) {
        data_check(event);

    }

    @FXML
    void createAccount(KeyEvent keyEvent){
        if(keyEvent.getCode()== KeyCode.ENTER) {
            data_check(keyEvent);
        }
    }

    private void data_check( Event event){
        try {
            JSONObject data=getData();
            if (data.length()!=0){
                send_to_server(data,event);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    JSONObject getData() throws Exception{

        JSONObject Create_account=new JSONObject();

        if(verification.textFieldNotNull(fName,gFName)&& verification.datePickerNotNull(Date_of_birth)
                &&verification.comboBoxNotNull(Gender)&&verification.mobileVerify(Mobile)&&
                verification.emailVerify(Email_id)&& verification.passwordVerify(password,Confirm_Password)){
            Create_account.put("First_Name", fName.getText());
            Create_account.put("Middle_Name",Mname.getText());
            Create_account.put("Last_Name",Lname.getText());
            Create_account.put("Guardian_First_name", gFName.getText());
            Create_account.put("Guardian_Middle_name",Gmname.getText());
            Create_account.put("Guardian_Last_name",Glname.getText());
            Create_account.put("Date_Of_Birth", Date_of_birth.getValue());
            Create_account.put("Sex",Gender.getValue());
            Create_account.put("EmailId", Email_id.getText());
            Create_account.put("Mobile",Mobile.getText());
            Create_account.put("Password",password.getText());
        }
        return Create_account;
    }


    private void send_to_server(JSONObject jsonObject, Event event) throws Exception {
        URL url = new URL("http://localhost/create_admin");
        HttpPost httpPost=new HttpPost(url,jsonObject);
        System.out.println(httpPost.getStringbuffer().toString());

        Parent Admin_Parent = FXMLLoader.load(getClass().getResource("Admin_home.fxml"));
        Scene AdminLoginScene = new Scene(Admin_Parent);
        Admin_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Admin_Stage.setScene(AdminLoginScene);
        Admin_Stage.setResizable(true);
        Admin_Stage.setMaximized(true);
        Admin_Stage.show();
    }


    @FXML
    private void initialize(){
        verification.emailVerifyOnTextChange(Email_id);
        verification.mobileVerifyOnNumberChange(Mobile);
        verification.textFieldVerifyOnTextChange(fName,gFName);
        verification.passwordFieldVerifyOnTextChange(password);
        verification.confirmPasswordFieldOnTextChange(password,Confirm_Password);
        verification.datePickerOnDateChange(Date_of_birth);
        verification.comboBoxOnValueChange(Gender);
        Gender.setItems(gender);

       /* try {
            HttpGet httpGet=new HttpGet("http://localhost");
            if(httpGet.getStringbuffer().toString().equals("0")){
                nullAdmin=true;
            }
        }
        catch (Exception e){

        }*/
    }
}
