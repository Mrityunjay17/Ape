package Admin;

import HttpClient.HttpPost;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

public class CreateAdmin{

    ObservableList<String> gender= FXCollections.observableArrayList("Male","Female","Other");

    @FXML
    ComboBox<String> Gender;
    @FXML
    TextField Fname,Mname,Lname,Gfname,Gmname,Glname,Emailid;
    @FXML
    DatePicker Dateofbirth;
    @FXML
    NumberField Mobile;
    @FXML
    PasswordField password,Confirmpassword;

    @FXML
    void CreateAccount(ActionEvent event) throws Exception {

         register(getData());
    }

    @FXML
    void CreateAccount(KeyEvent keyEvent){
        if(keyEvent.getCode()== KeyCode.ENTER){
            try {
                register(getData());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    JSONObject getData() throws Exception{
        JSONObject Create_account=new JSONObject();
        Create_account.put("First_Name",Fname.getText());
        Create_account.put("Middle_Name",Mname.getText());
        Create_account.put("Last_Name",Lname.getText());
        Create_account.put("Guardian_First_name",Gfname.getText());
        Create_account.put("Guardian_Middle_name",Gmname.getText());
        Create_account.put("Guardian_Last_name",Glname.getText());
        Create_account.put("Date_Of_Birth",Dateofbirth.getValue());
        Create_account.put("Sex","M");
        Create_account.put("EmailId",Emailid.getText());
        Create_account.put("Mobile",Mobile.getText());
        Create_account.put("Password",password.getText());
        return Create_account;
    }

    private void register(JSONObject jsonObject) throws Exception {
       URL url = new URL("http://localhost/create_admin");

        HttpPost httpPost=new HttpPost(url,jsonObject);
        System.out.println(httpPost.getStringbuffer().toString());
    }

    @FXML
    public void initialize(){
        Gender.setItems(gender);
    }
}
