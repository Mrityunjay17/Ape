package Utility;

import HttpClient.HttpGet;
import HttpClient.HttpPost;
import Main.ConnectionCheck;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class Tokencheck {

    public static String read(){
        String token;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            File file = new File("token.ddf");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
            fileReader.close();

        } catch (IOException e) {

            if(e.getMessage().equals("token.ddf (The system cannot find the file specified)")){
                return "Login";
            }
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
        token=stringBuffer.toString();
        return token;
    }

/*    public static JSONObject getUserdetails(){
        URL url= null;
        try {
            url = new URL("http://localhost/token");
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("token",read());
            HttpPost httpPost=new HttpPost(url,jsonObject);
            JSONObject details=new JSONObject(httpPost.getStringbuffer().toString());
            return details;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/

    public static boolean check(){
        Tokencheck tokencheck=new Tokencheck();
        if(read()!=null){
            try {
                HttpGet httpGet=new HttpGet("http://localhost/checkconnection");

            }
            catch (Exception e){
                return false;
            }

            if(read().equals("Login")){
                ConnectionCheck.setTitle("Login");
                try {
                    ConnectionCheck.setRoot( tokencheck.setroot("Login"));
                } catch (Exception e) {
                   return false;
                }
            }
            else {
                try {
                    URL url=new URL("http://localhost/tokenverify");
                    JSONObject jsonObject=new JSONObject();
                    jsonObject.put("token",read());
                    HttpPost httpPost=new HttpPost(url,jsonObject);
                    JSONObject get=new JSONObject(httpPost.getStringbuffer().toString());
                    tokencheck.setroot(get.getString("Post"));
                    ConnectionCheck.setRoot(tokencheck.setroot(get.getString("Post")));
                    ConnectionCheck.setTitle("Accounts");
                }
                catch (Exception e){
                    ConnectionCheck.setTitle("Login");
                    try {
                        ConnectionCheck.setRoot( tokencheck.setroot("Login"));
                    } catch (Exception ex) {
                        return false;
                    }

                }
            }
            return true;
        }
        return false;
    }

private Parent setroot(String fxml) throws Exception{
    Parent root;

    switch (fxml){
        case ("account"):root=FXMLLoader.load(getClass().getResource("/Account/AccountMain.fxml"));
                            break;
        default:root=FXMLLoader.load(getClass().getResource("/Login/Login.fxml"));
    }
    return root;
 }

}
