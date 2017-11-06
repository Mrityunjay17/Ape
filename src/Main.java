
import HttpClient.HttpPost;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root;

        if(usercheck(read())){
            root = FXMLLoader.load(getClass().getResource("/Account/AccountMain.fxml"));
            primaryStage.setMaximized(true);
        }
        else {
            root = FXMLLoader.load(getClass().getResource("/Login/Login.fxml"));
        }
        primaryStage.setTitle("Ape");
        primaryStage.getIcons().add(new Image("/Resources/images/ApeLogo.png"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
      launch(args);
    }

    public String read(){
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
            e.printStackTrace();
        }

        return stringBuffer.toString();
    }


    private boolean usercheck(String token){

        try {
            URL url = new URL("http://localhost/token");
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("token",token);
            HttpPost httpPost=new HttpPost(url,jsonObject);
            System.out.println(httpPost.getStringbuffer().toString());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
