/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cue.lounge;

import com.gluonhq.charm.glisten.control.TextField;
import static cue.lounge.MainMenuController.f0;
import static cue.lounge.MainMenuController.f1;
import static cue.lounge.MainMenuController.f2;
import static cue.lounge.MainMenuController.f3;
import static cue.lounge.MainMenuController.f4;
import static cue.lounge.MainMenuController.f5;
import static cue.lounge.MainMenuController.f6;
import static cue.lounge.MainMenuController.f8;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Talha
 */
public class UpdateSupliersController implements Initializable {

    @FXML
    private TextField tf1_upSuppliers;
    @FXML
    private TextField tf2_upSuppliers;
    @FXML
    private ComboBox<String> combx1_upSuppliers;
    @FXML
    private TextField tf3_upSuppliers;
    @FXML
    private TextField tf4_upSuppliers;
    @FXML
    private TextField tf5_upSuppliers;
    @FXML
    private TextArea ta1_upSuppliers;
    @FXML
    private Button b_save_upSuppliers;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Time for passing values from previous frame to this frame
        tf1_upSuppliers.setText(f1);
        tf2_upSuppliers.setText(f2);
        combx1_upSuppliers.setValue(f3);
        tf3_upSuppliers.setText(f4);
        tf4_upSuppliers.setText(f5);
        tf5_upSuppliers.setText(f8);
        ta1_upSuppliers.setText(f6);
        
    }    

    @FXML
    private void f_b_save_upSuppliers(ActionEvent event) {
        try {
            //Now it's time for making update query
            //First getting connection from database
            CueLounge cl=new CueLounge();
            cl.DBMSConnection();
            //Now writing connection String
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root","");
            PreparedStatement psmt=cn.prepareStatement("UPDATE `cuelounge`.`suppliers` SET `Supplier_ID` = ?,\n" +
"`Employee_ID` = ?,\n" +
"`Supplier_Name` = ?,\n" +
"`Product_type` = ?,\n" +
"`Email` = ?,\n" +
"`Phone_no` = ?,\n" +
"`Address` = ?,\n" +
"`timings` = ?,\n" +
"`Date` = ?,\n" +
"`Time` = ? WHERE `suppliers`.`Sr_no` =? ");
            //Now it's time to give values to this query of ours
            psmt.setString(1, tf1_upSuppliers.getText());//Supplier ID
            psmt.setString(2, CueLounge.emp_ID);//Emp_ID updating(new admin maybe who is modifying our database)
            psmt.setString(3, tf2_upSuppliers.getText());//Supplier name
            psmt.setString(4, combx1_upSuppliers.getSelectionModel().getSelectedItem());//Product type
            psmt.setString(5, tf3_upSuppliers.getText());//Email
            psmt.setString(6, tf4_upSuppliers.getText());//Phone no
            psmt.setString(7, ta1_upSuppliers.getText());//Address
            psmt.setString(8, tf5_upSuppliers.getText());//timings
            long millis=System.currentTimeMillis();  
            java.sql.Date date=new java.sql.Date(millis);   
            psmt.setString(9, date.toString());//Date (Getting from system local date ;date at which system was modified)
            psmt.setString(10, (java.time.LocalTime.now().toString()));//Time (Giving local time)
            psmt.setString(11, f0);//Sr_no
            //Now it's time for executing query
            psmt.executeUpdate();
            //showing update successful message
            JOptionPane.showMessageDialog(null, "Suppliers database was successfully updated!! Please refresh your table");

        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null, "Error in udating suppliers database and is: "+ex);
        }
    }
    
}
