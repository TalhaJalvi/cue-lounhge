
package cue.lounge;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class LoadingWinController implements Initializable {

    @FXML
    public StackPane st_loadwin;//Making it public so it can be acessed in Cue Lounge

    @Override
    public void initialize(URL url, ResourceBundle rb) {
     new splashScreen().start();
     
    }    

    class splashScreen extends Thread{
        @Override 
        public void run(){
            try {
                Thread.sleep(7000);// Making 5 secounds wait before screen loads
                Platform.runLater(new Runnable() {//Making this as FX frames are loaded with one thread only and not other
                                                 
                    @Override
                    public void run() {
                        Parent root=null;
                        try{
                           
                            root=FXMLLoader.load(getClass().getResource("Login.fxml"));
                        }catch(Exception ex){
                            Logger.getLogger(LoadingWinController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                       Scene scene=new Scene(root);
                       Stage stage=new Stage();
                       stage.setScene(scene);
                       stage.show();
                       //Now it's time to hide splash Screen
                       st_loadwin.getScene().getWindow().hide();
                    }
                });
            } catch (InterruptedException ex) {
                Logger.getLogger(LoadingWinController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
}
    
   


