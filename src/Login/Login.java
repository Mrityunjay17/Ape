package Login;


import HttpClient.HttpPost;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;


public class Login {
    @FXML
    TextField EmailID;
    @FXML
    PasswordField Password;
    @FXML
    Label  errormessage;
    @FXML
     CheckBox Staysignedin;

    public static Stage AdminLoginStage;

    @FXML
    void Login(ActionEvent event) throws Exception {

        login(event);
    }

    private void login(ActionEvent event) {
        try {
            URL url = new URL("http://localhost/login");
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("EmailId",EmailID.getText());
            jsonObject.put("Password",Password.getText());
            HttpPost httpPost=new HttpPost(url,jsonObject);

          if (httpPost.getStringbuffer().toString().equals("User Not Found")){
              errormessage.setText(httpPost.getStringbuffer().toString());
          }
          else if(httpPost.getStringbuffer().toString().equals("Please check your password"))
          {
              errormessage.setText(httpPost.getStringbuffer().toString());
          }
          else {
              if (Staysignedin.isSelected()){
                  writeNewUser(httpPost.getStringbuffer().toString());
              }
              Parent AdminLoginParent = FXMLLoader.load(getClass().getResource("/Account/AccountMain.fxml"));
              Scene AdminLoginScene = new Scene(AdminLoginParent);
              AdminLoginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              AdminLoginStage.setScene(AdminLoginScene);
              AdminLoginStage.show();

          }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void writeNewUser(String token) {

        PrintWriter writer;
        try {
            writer = new PrintWriter("token.ddf", "UTF-8");
            writer.println(token);
            writer.close();
        } catch (Exception e){

        }
    }
}
