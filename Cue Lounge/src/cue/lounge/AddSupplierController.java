/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cue.lounge;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Talha
 */
public class AddSupplierController implements Initializable {

    @FXML
    private TextField tf1_addSupp;
    @FXML
    private TextField tf3_addSupp;
    @FXML
    private TextField tf2_addSupp;
    @FXML
    private Button b1_savesu_adSupp;
    @FXML
    private TextField tf5_addSupp;
    @FXML
    private TextField tf8_addSupp;
    @FXML
    private ComboBox<String> comb1_addSupp;
    @FXML
    private TextField tf4_addSupp;
    @FXML
    private TextArea ta1_addSupp;
    @FXML
    private TextField tf6_addSupp;
    @FXML
    private TextField tf7_addSupp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            ObservableList<String> stocktype = FXCollections.observableArrayList("Eatable","Furniture","Game_Equipment","Accessories");
            comb1_addSupp.setItems(stocktype);
    }    

    @FXML
    private void f_keytf_Su1(KeyEvent event) {
    }


    @FXML
    private void f_keytf_Su3(KeyEvent event) {
    }

    @FXML
    private void f_keytf_Su5(KeyEvent event) {
    }

    @FXML
    private void f_savesu(ActionEvent event) {
        //Now it's time to add supplier info to database
         //Now stock has been reached to us so making or saving it to our database
        boolean ch=false;//i.e ID given by user does not exists
        try {
            
            CueLounge cl=new CueLounge();
            cl.DBMSConnection();
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root","");
            //calling for buiding up connection
            PreparedStatement psmt0 = cn.prepareStatement("Select * from `cuelounge`.`suppliers`");
            ResultSet Rs=psmt0.executeQuery();
            while(Rs.next()){
                if(Rs.getString("Supplier_ID").equals(tf2_addSupp.getText())){
                    ch=true;//Yes exists
                }
            }
            //Now it's time to insert new data
            if(ch==true){
                JOptionPane.showMessageDialog(null,"Supplier already exists");
            }else{
                
            
            PreparedStatement psmt = cn.prepareStatement("INSERT INTO `cuelounge`.`suppliers` (`Supplier_ID`, `Employee_ID`, `Supplier_Name`, `Product_type`, `Email`, `Phone_no`, `Address`, `Focal_P`, `timings`, `CEO`, `Date`, `Time`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            psmt.setString(1, tf1_addSupp.getText());
            psmt.setString(2, CueLounge.emp_ID);
            psmt.setString(3, tf3_addSupp.getText());
            psmt.setString(4, comb1_addSupp.getSelectionModel().getSelectedItem());
            psmt.setString(5, tf4_addSupp.getText());
            psmt.setString(6, tf5_addSupp.getText());
            psmt.setString(7, ta1_addSupp.getText());
            psmt.setString(8, tf6_addSupp.getText());
            psmt.setString(9, tf7_addSupp.getText());
            psmt.setString(10, tf8_addSupp.getText());
            long millis=System.currentTimeMillis();  
            java.sql.Date date=new java.sql.Date(millis);  
            psmt.setString(11, date.toString());
            psmt.setString(12, (java.time.LocalTime.now().toString()));
            psmt.executeUpdate();}
        }
         catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error is"+ex);
        
        }
        
    }
    
}
