/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cue.lounge;

import com.gluonhq.charm.glisten.control.TextField;
import static cue.lounge.MainMenuController.d1;
import static cue.lounge.MainMenuController.d2;
import static cue.lounge.MainMenuController.d3;
import static cue.lounge.MainMenuController.d4;
import static cue.lounge.MainMenuController.d5;
import static cue.lounge.MainMenuController.d6;
import static cue.lounge.MainMenuController.d7;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Talha
 */
public class UpdateCafeController implements Initializable {

    @FXML
    private Button b1_save_upCafe;
    @FXML
    private TextField tf1_upCafe;
    @FXML
    private TextField tf2_upCafe;
    @FXML
    private TextArea ta1_upCafe;
    @FXML
    private TextField tf3_upCafe;
    @FXML
    private TextField tf4_upCafe;
    @FXML
    private TextField tf5_upCafe;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //this is intializer of update cafe frame
        //Passing all values from previous frame to this frame
          
            tf1_upCafe.setText(d2);
            tf2_upCafe.setText(d3);
            ta1_upCafe.setText(d4);
            tf3_upCafe.setText(d5);
            tf4_upCafe.setText(d6);
            tf5_upCafe.setText(d7);
    }    

    @FXML
    private void f_b1_save_upCafe(ActionEvent event) {
        try {
            //Now it's time to update cafe if any mistake was made
            //First making connection with database
            CueLounge cl=new CueLounge();
            cl.DBMSConnection();
            //Connection has been made
            //Now for connection String
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root","");
            //Now preparing update statement
            PreparedStatement psmt=cn.prepareStatement("UPDATE `cuelounge`.`cafe_c_inf` SET `C_Name` = ?,\n" +
"`P_ID_list` = ?,\n" +
"`P_Name_List` = ?,\n" +
"`Quantity` = ?,\n" +
"`Item Bill` = ?,\n" +
"`T_Bill` = ?,\n" +
"`C_Date` = ?,\n" +
"`C_Time` = ? WHERE `cafe_c_inf`.`Customer_ID` =?");
            psmt.setString(1,  tf1_upCafe.getText());
            psmt.setString(2,  tf2_upCafe.getText());
            psmt.setString(3,  ta1_upCafe.getText());
            psmt.setString(4,  tf3_upCafe.getText());
            psmt.setString(5,  tf4_upCafe.getText());
            psmt.setString(6,  tf5_upCafe.getText() );
            //new modified date and time
            long millis=System.currentTimeMillis();  
            java.sql.Date date=new java.sql.Date(millis);
            psmt.setString(7,  date.toString());
            psmt.setString(8, (java.time.LocalTime.now().toString()));
            psmt.setString(9, d1);
            //Now it's time to execute query
            psmt.executeUpdate();
            //Showing message if update was successful
            JOptionPane.showMessageDialog(null,"Database was of cafe was modified!! Please refresh your table.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error in updating database: "+ex);
        }
        
    }
    
}
