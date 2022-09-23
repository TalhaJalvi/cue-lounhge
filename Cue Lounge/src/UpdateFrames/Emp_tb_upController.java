/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UpdateFrames;

import com.gluonhq.charm.glisten.control.TextField;
import com.jfoenix.controls.JFXTextField;
import cue.lounge.CueLounge;
import cue.lounge.MainMenuController;
import static cue.lounge.MainMenuController.a1;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Talha
 */
public class Emp_tb_upController implements Initializable {

    @FXML
    private ToggleGroup gender;
    @FXML
    private Button b2_up_emp_tb;
    @FXML
    private AnchorPane anc_up_emp_tb;
    @FXML
    private TextField tf1_up_emp_tb;
    @FXML
    private TextField tf2_up_emp_tb;
     
    @FXML
    private TextField tf3_up_emp_tb;
    @FXML
    private DatePicker dp1_up_emp_tb;
    @FXML
    private RadioButton rb1_up_emp_tb;
    @FXML
    private RadioButton rb2_up_emp_tb;
    @FXML
    private TextField tf4_up_emp_tb;
    @FXML
    private Button b1_up_emp_tb;
    @FXML
    private ComboBox<String> combb_up_emp_tb;//Values are added in intializer
    @FXML
    private TextField tf6_up_emp_tb;
    @FXML
    private RadioButton rb3_up_emp_tb;
    @FXML
    private ToggleGroup paid;
    @FXML
    private RadioButton rb4_up_emp_tb;
    @FXML
    private ComboBox<String> combb2_up_emp_tb1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Adding menu items in combo box
        ObservableList<String> dbTypeList = FXCollections.observableArrayList("Male","Female","other");
        ObservableList<String> List = FXCollections.observableArrayList("yes","no");
        combb_up_emp_tb.setItems(dbTypeList);
        combb2_up_emp_tb1.setItems(List);
        //Making object of employee main menu frame so that it shows data in this frame
        
        tf1_up_emp_tb.setText(MainMenuController.a2);//name
        tf2_up_emp_tb.setText(MainMenuController.a3);//post
        tf3_up_emp_tb.setText(MainMenuController.a4);//salary
        tf6_up_emp_tb.setText(MainMenuController.a12);//phone no
        //Now setting others
        combb_up_emp_tb.setValue(MainMenuController.a6);//Gender
        if((MainMenuController.a7).equalsIgnoreCase("Single")){
            rb1_up_emp_tb.selectedProperty().set(true);
        }                                                       //Maritual status
        else{
            rb2_up_emp_tb.selectedProperty().set(true);
        }
        if(MainMenuController.a9.equalsIgnoreCase("yes")){
            rb3_up_emp_tb.selectedProperty().set(true);
        }                                                  //Paid status
        else{
            rb4_up_emp_tb.selectedProperty().set(true);
        }
        //now for date
       dp1_up_emp_tb.getEditor().setText(MainMenuController.a11);
       
       //Now for address
       tf4_up_emp_tb.setText(MainMenuController.a8);
       
    }    

    @FXML
    private void f_b2_up_emp_tb(ActionEvent event) {
          //if user cancel his decision for update then
             //if user name and password is correct then close login window and open main menu window
             //Before that asking or prompting user he will lost all his saved work
        int reply = JOptionPane.showConfirmDialog(null, "Do you want to exit ? All your work will be lost", "", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
                  Stage s=(Stage) anc_up_emp_tb.getScene().getWindow();
                  s.close();
        }
        else {
           JOptionPane.showMessageDialog(null, "Thank you for continuing");
        }

    }

    @FXML
    private void f_tf1_up_emp_tb(KeyEvent event) {
        //Name
        tf1_up_emp_tb.textProperty().addListener((observable,oldeValue,newValue)->{
            if(!newValue.matches("\\sa-zA-Z*")){
                tf1_up_emp_tb.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
             if(!oldeValue.matches("\\sa-zA-Z*")){
                tf1_up_emp_tb.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
    }

    @FXML
    private void f_tf2_up_emp_tb(KeyEvent event) {
        //Post

    }


    @FXML
    private void f_tf3_up_emp_tb(KeyEvent event) {
        //Salary

    }

    @FXML
    private void tf4_up_emp_tb(KeyEvent event) {
        //Address
    }

    @FXML
    private void f_b1_up_emp_tb(ActionEvent event) {
        try {
            //First establishing connection with database
            CueLounge cl=new CueLounge();
            cl.DBMSConnection();
            //Now  giving conenection string
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root","");
            //now making update query
            PreparedStatement psmt=cn.prepareStatement("UPDATE `cuelounge`.`employees` SET `First_Name` = ?,\n" +
"`PHONE_NO` = ?,\n" +
"`GENDER` = ?,\n" +
"`MARITUAL_ST` = ?,\n" +
"`Post` = ?,\n" +
"`Address` = ?,\n" +
"`Salary` = ?,\n" +
"`DOJ` = ?,\n" +
"`Paid` = ?\n" +
" WHERE `employees`.`Employee_ID` =? ");
            //Now it's time to give values 
            psmt.setString(1, tf1_up_emp_tb.getText());
            psmt.setString(2, tf6_up_emp_tb.getText());
            psmt.setString(3, combb_up_emp_tb.getSelectionModel().getSelectedItem());
            if(rb1_up_emp_tb.isSelected()){
                psmt.setString(4, rb1_up_emp_tb.getText());
            }
            else if(rb2_up_emp_tb.isSelected()){
                psmt.setString(4, rb2_up_emp_tb.getText());
            }
            else{
                JOptionPane.showMessageDialog(null, "Please select any value for maritual status");
            }
            
            psmt.setString(5, tf2_up_emp_tb.getText());
            psmt.setString(6, tf4_up_emp_tb.getText());
            psmt.setString(7, tf3_up_emp_tb.getText());
            psmt.setString(8, dp1_up_emp_tb.getEditor().getText());
            if(rb3_up_emp_tb.isSelected()){
                psmt.setString(9, rb3_up_emp_tb.getText());
            }
            else{
                psmt.setString(9, rb4_up_emp_tb.getText());
            }
            psmt.setString(10, a1);
            psmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data successfully updated refresh your table");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error in updating table: "+ex);
        }
     
     
        }
    


       
                            
    

  

    @FXML
    private void f_tf6_up_emp_dp(KeyEvent event) {
 
    }
}

      
    
    

