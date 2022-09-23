/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cue.lounge;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author zabi khan
 */
public class ResetPasswordController implements Initializable {

    @FXML
    private AnchorPane anc_rp;
    @FXML
    private JFXButton b_Reset_rp;
    @FXML
    private JFXButton b_cancel_rp;
    @FXML
    private JFXTextField tf_ID_rp;
    @FXML
    private JFXTextField tf_ps1_rp;
    @FXML
    private JFXTextField tf_ps2_rp;
    @FXML
    private Label lb2_rp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void f_Reset_rp(ActionEvent event) {
        if(tf_ps1_rp.getText().equals(tf_ps2_rp.getText())){
            try {
                lb2_rp.setVisible(false);
                //Now making connection to DBMS so that we can change passowrd

                Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root","");
                //Above is our connection URL or string which is generated when we establish connection in first place
                CueLounge cl=new CueLounge();//Making CueLounge class object
                cl.DBMSConnection(); //Building connection by calling connection function
                //Which is defined in CueLounge class
                PreparedStatement psmt=null;
                //Now we have been succssfully connected to DBMS it's time to update password in employees table
               psmt=cn.prepareStatement("UPDATE `cuelounge`.`employees` SET `Password` = ? WHERE `employees`.`Employee_ID` =? ");
               psmt.setString(1, tf_ps1_rp.getText());
               psmt.setString(2, tf_ID_rp.getText());
               psmt.executeUpdate();//For executing new update in database if simple execute then our fxml will stuck
               //Now password is updated successfully
               lb2_rp.setText("Password was updated successfully!!!");
               lb2_rp.setTextFill(Color.GREEN);
               lb2_rp.setVisible(true);
            } catch (SQLException ex) {
               JOptionPane.showMessageDialog(null,"Error is" +ex);
            }
        }
        else{
            
            lb2_rp.setText("Password do not match! Please enter same password");
            lb2_rp.setTextFill(Color.RED);
            lb2_rp.setVisible(true);
            
        }
    }

    @FXML
    private void f_cancel_rp(ActionEvent event) {
        //For closing current scene
          Stage s=(Stage) anc_rp.getScene().getWindow();
                  s.close();
    }

    @FXML
    private void f_tf_rp1(KeyEvent event) {
        //for user ID it must be integer and it must be less or equal to 4 in lenght
        tf_ID_rp.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,4}?")) {
                    tf_ID_rp.setText(oldValue);
                }
                // In this code we have made key typed event in our fxml file in scene builder, so this will activate when 
                //user types any character in tf_su9 text field which is for 4 digit security code now we have in text 
                //property of tf_su9 text field that when text is changed in it this changed() function will check that
                //whether text is a digit shown in if(!newValue.matches("\\d(0,4)?")) now when users enters data it
                //can enter only digit due to 'd' in instruction and size can be from 0 to 4 if any of this is ignored then
                //text is set to old text
            }
        });
    }
    
}
