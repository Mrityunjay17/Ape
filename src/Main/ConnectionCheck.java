package Main;

import Utility.Utility;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ConnectionCheck implements Initializable {

    static String Title;

    static Parent root ;

    @FXML
    ProgressBar progressBar;
    @FXML
    AnchorPane ancher;



    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        booleanService.start();
        booleanService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
           @Override
           public void handle(WorkerStateEvent event) {

               if (!booleanService.getValue()){
                   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                   alert.setTitle("Server Error");
                   alert.setHeaderText("Server Is Not Responding");
                   alert.setContentText("Select One");
                   alert.setGraphic(new ImageView(this.getClass().getResource("/Resources/images/tumblr_inline_nj4nxkE0C21qersu1.png").toString()));
                   Stage alertbox = (Stage) alert.getDialogPane().getScene().getWindow();
                   alertbox.getIcons().add(new Image("/Resources/images/ApeLogo.png"));
                   ButtonType buttonTypeOne = new ButtonType("Retry");
                   ButtonType buttonTypeCancel = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);
                   alert.getButtonTypes().setAll(buttonTypeOne,buttonTypeCancel);

                   Optional<ButtonType> result = alert.showAndWait();

                   if (result.get() == buttonTypeOne){
                       booleanService.restart();
                   } else {
                       System.exit(0);
                   }

               }
               else {

                    Stage main=(Stage) ancher.getScene().getWindow();
                    main.hide();
                    Stage stage=new Stage();
                    stage.setTitle(Title);
                    stage.getIcons().add(new Image("/Resources/images/ApeLogo.png"));
                    stage.setScene(new Scene(root));
                    stage.setMaximized(true);
                    stage.show();
               }
           }
       });
    }

    Service<Boolean> booleanService=new Service<>() {
        @Override
        protected Task<Boolean> createTask() {
            return new Task<>() {
                @Override
                protected Boolean call() throws Exception {
                    for (int i=0;i<=100;i++) {
                        double set = i;
                        progressBar.setProgress(set / 100);
                        Thread.sleep(100);
                    }
                   return Utility.check();
                }
            };
        }
    };


    public static void setRoot(Parent root) {
        ConnectionCheck.root = root;
    }

    public static void setTitle(String title) {
        Title = title;
    }

}
