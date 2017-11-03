package Login;



import HttpClient.HttpPost;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.json.JSONObject;

import java.net.URL;


public class Login {

    @FXML
    TextField EmailID;
    @FXML
    PasswordField Password;


    @FXML
    void Login(ActionEvent event) throws Exception {
       login();
        }

    private void login() {
        try {
            URL url = new URL("http://localhost/loginn");
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("EmailId",EmailID.getText());
            jsonObject.put("Password",Password.getText());
            HttpPost httpPost=new HttpPost(url,jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
