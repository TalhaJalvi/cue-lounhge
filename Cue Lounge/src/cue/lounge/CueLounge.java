/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cue.lounge;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 *
 * @author Talha
 */
public class CueLounge extends Application {
    //DECLARING GLOBAL VARIABLES WHICH WOULD BE USED FOR ACCOUNTS ETC
    public static float t_amount=0; //Making it static so that only one copy of it exists
    public static String emp_ID;
     public static float t_expenditure=0; 
     public static float t_earnings=0; 
     Timer timer;
     //Simple constructor
     public CueLounge(){
         //no parameters
     }
    public void DBMSConnection(){
                       try{
        Class.forName("com.mysql.jdbc.Driver");//Our driver name
        Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root","");
        //Building up our connection by using connection URL or connection link
        Statement st = cn.createStatement();
        
        JOptionPane.showMessageDialog(null,"Connection was successful");
        //Showing connection successful message if connection building was successful
    }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Connection was not successful"+e, "Error Message:", JOptionPane.ERROR_MESSAGE);
        }
    
    

    }
    
    
    @Override
    public void start(Stage stage) throws Exception {;
        Parent root = FXMLLoader.load(getClass().getResource("LoadingWin.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
    }
        
        
     //Now closing this and getting another scene
    
    //Now this window is closed opening new frame
     
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
}

  

