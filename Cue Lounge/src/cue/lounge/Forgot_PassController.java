/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cue.lounge;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author zabi khan
 */
public class Forgot_PassController implements Initializable {

    @FXML
    private JFXButton b_check_fg;
    @FXML
    private TextField tf_fp1;
    @FXML
    private JFXButton b_send_fg;
    @FXML
    private AnchorPane anc_fp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void f_fg1(KeyEvent event) {
                tf_fp1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,4}?")) {
                    tf_fp1.setText(oldValue);
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

    @FXML
    private void f_send_fg(ActionEvent event) {
        try {
            //Now first of all we will generate random 4 digit code to be sended to user
            //Add liberary :import java.util.Random
            //Now generating random 4 - digit code
            Random rand = new Random();
            String resRandom = Integer.toString(rand.nextInt((9999 - 100) + 1) + 10);
            //Random number has been successfully generated
            
            //Deleting old value in table
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root","");
            //Above is our connection URL or string which is generated when we establish connection in first place
            CueLounge cl=new CueLounge();//Making CueLounge class object
            cl.DBMSConnection(); //Building connection by calling connection function
            //Which is defined in CueLounge class
            PreparedStatement pstmt=null;
            pstmt=cn.prepareStatement("DELETE FROM `cuelounge`.`security_code` WHERE `security_code`.`code_id`=1");
            pstmt.execute();
            
            //Now it's time to insert new code database
            pstmt=cn.prepareStatement("INSERT INTO `cuelounge`.`security_code` (`code_id`, `Code`) VALUES (?,?)");
            pstmt.setString(1, "1");
            pstmt.setString(2, resRandom);
            pstmt.executeUpdate();
            
            
            JavaMailUtil.send("talhajalvi321@gmail.com","T1a2r3z4a5n6","Zabik1144@gmail.com","code is:",resRandom);
        } catch (SQLException ex) {
            Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void f_check_fg(ActionEvent event) {
         if((tf_fp1.getText().isEmpty())){
            JOptionPane.showMessageDialog(null, "Please enter eny data", "Error Message:", JOptionPane.ERROR_MESSAGE);  
        }
        else{
            try {
                Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root","");
                //Above is our connection URL or string which is generated when we establish connection in first place
                CueLounge cl=new CueLounge();//Making CueLounge class object
                cl.DBMSConnection(); //Building connection by calling connection function
                                     //Which is defined in CueLounge class
                PreparedStatement pstmt=null;
                pstmt=cn.prepareStatement("Select code from security_code");//Preparing SQL statement to be executed when button is 
                                                                            //clicked
                ResultSet rs = pstmt.executeQuery();//Executing our query
                /*When we execute certain SQL queries (SELECT query in general) they return tabular data (IN THE FORM OF TABLE).
                The java.sql.ResultSet
                interface represents such tabular data returned by the SQL statements.i.e. the ResultSet object holds the 
                tabular data returned by the methods that execute the statements which quires the database (executeQuery()
                method of the Statement interface in general).The ResultSet object has a cursor/pointer which points to the
                current row. Initially this cursor is positioned before first row.
                i.e., on calling the next() method for the first time the result set pointer/cursor 
                will be moved to the 1st row (from default position). And on calling the next() method
                for the second time the result set cursor will be moved to the 2nd row.in dbms*/
                
                /*     COL 1 || COL2 || COL3
                ROW1         ||      ||
                ROW2         ||      ||
                */
                while(rs.next()){//rs.next() function for 
                    
       if(tf_fp1.getText().equals(rs.getString("Code"))){//Checking whether security code entered by user matches orignal
           try{
                Stage s=(Stage) anc_fp.getScene().getWindow();
                  s.close();
                  //For closing previous window
                  FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("reset password.fxml"));//FXML loader class will load 2nd window
                  Parent root1 = (Parent) fxmlLoader.load();//It will be stored in root1 of parent type
                  Stage stage = new Stage();//Setting a new stage
                  stage.setScene(new Scene(root1));
                  stage.show();//displaying our new window
              }
              catch(Exception e){
                  System.out.println("Error in loading signup window"+e);
              };
       }
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
