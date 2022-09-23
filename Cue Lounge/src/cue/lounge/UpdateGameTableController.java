/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cue.lounge;

import com.jfoenix.controls.JFXTimePicker;
import static cue.lounge.MainMenuController.c0;
import static cue.lounge.MainMenuController.c1;
import static cue.lounge.MainMenuController.c2;
import static cue.lounge.MainMenuController.c3;
import static cue.lounge.MainMenuController.c4;
import static cue.lounge.MainMenuController.c5;
import static cue.lounge.MainMenuController.c6;
import static cue.lounge.MainMenuController.c7;
import static cue.lounge.MainMenuController.c8;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Talha
 */
public class UpdateGameTableController implements Initializable {

    @FXML
    private Button button_updategames;
    @FXML
    private TextField tf1_upgames;
    @FXML
    private TextField tf2_upgames;
    @FXML
    private JFXTimePicker tp1_upgames;
    @FXML
    private JFXTimePicker tp2_upgames;
    @FXML
    private TextArea ta1_upgames;
    @FXML
    private TextField tf3_upgames;
    @FXML
    private TextField tf4_upgames;
    @FXML
    private DatePicker dp1_upgames;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Passing values from previous frames
        tf1_upgames.setText(c1);
        tf2_upgames.setText(c2);
        tp1_upgames.getEditor().setText(c3);
        tp2_upgames.getEditor().setText(c4);
        ta1_upgames.setText(c5);
        tf3_upgames.setText(c6);
        tf4_upgames.setText(c7);
        dp1_upgames.getEditor().setText(c8);
    }    

    @FXML
    private void f_button_updategames(ActionEvent event) {
        //It's time to update database witrh new values
        try{
            //Getting database connection
             CueLounge cl=new CueLounge();
             cl.DBMSConnection();
            //Now for connection string
             Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root","");
             PreparedStatement psmt=cn.prepareStatement("UPDATE `cuelounge`.`players_info` SET \n" +
"`P2_Name` = ?,\n" +
"`P1_Name` = ?,\n" +
"`Time_Started` = ?,\n" +
"`Time_Ended` = ?,\n" +
"`Game_Type` = ?,\n" +
"`Bill` = ?,\n" +
"`Paid` = ?,\n" +
"`Date` = ?,\n" +
"`Time` = ? WHERE `players_info`.`sr_no` =?");
             
             psmt.setString(1, tf1_upgames.getText());     
             psmt.setString(2, tf2_upgames.getText());
             psmt.setString(3, tp1_upgames.getEditor().getText());
             psmt.setString(4, tp2_upgames.getEditor().getText());
             psmt.setString(5, ta1_upgames.getText());
             psmt.setString(6, tf3_upgames.getText());
             psmt.setString(7, tf4_upgames.getText());
             //Automatic update time and date #Last modified date and time  
             psmt.setString(8,  dp1_upgames.getEditor().getText());
             psmt.setString(9, (java.time.LocalTime.now().toString()));
             //Getting table ID so that we can update data at that position
             psmt.setString(10, c0);
             //Now its time for executing query
             psmt.executeUpdate();
             //Showing message that update was successful
             JOptionPane.showMessageDialog(null, "Update of players info table was successful!! Please refresh your table");
             
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error in udating database of games details: "+e);
        }
    }
    
}
