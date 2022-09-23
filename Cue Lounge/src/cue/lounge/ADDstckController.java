/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cue.lounge;

import java.io.File;
import java.io.FileInputStream;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Talha
 */
public class ADDstckController implements Initializable {

    @FXML
    private TextField tf1_adst;
    @FXML
    private TextField tf2_adst;
    @FXML
    private TextField tf3_adst;
    @FXML
    private DatePicker dp1_adst;
    @FXML
    private TextField tf4_adst;
    @FXML
    private Button b1_savesu_adstck;
    @FXML
    private DatePicker dp2_adst;
    @FXML
    private TextField tf5_adst;
    @FXML
    private ComboBox<String> comb1_adst;
    @FXML
    private ComboBox<String> comb2_adst;
    @FXML
    private TextField tf6_adst1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            ObservableList<String> stocktype = FXCollections.observableArrayList("Eatable","Furniture","Game_Equipment","Accessories");
            comb1_adst.setItems(stocktype);
            ObservableList<String> supplierID = FXCollections.observableArrayList();
        try {
            // TODO
            //now suppliers could only be supplier which we have in database
            //First getting data from our database
            CueLounge cl=new CueLounge();
            cl.DBMSConnection();
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root","");
            //Now we have made connection Now it's time to retrieve data from database
            ResultSet rs=cn.createStatement().executeQuery("SELECT *from suppliers");//Table was named due to mistake
            while(rs.next()){
                supplierID.add(rs.getString("Supplier_ID"));
                
            }
            //Addding to combobox
            comb2_adst.setItems(supplierID);
        } catch (SQLException ex) {
            Logger.getLogger(ADDstckController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void f_keytf_Su1(KeyEvent event) {
    }

    @FXML
    private void f_keytf_Su2(KeyEvent event) {
    }

    @FXML
    private void f_lstnamsu(ActionEvent event) {
    }

    @FXML
    private void f_keytf_Su3(KeyEvent event) {
    }

    @FXML
    private void f_keytf_Su5(KeyEvent event) {
    }

    @FXML
    private void f_savesu(ActionEvent event) {
        //Now stock has been reached to us so making or saving it to our database
        boolean ch=false;//i.e ID given by user does not exists
        try {
            
            CueLounge cl=new CueLounge();
            cl.DBMSConnection();
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root","");
            //calling for buiding up connection
            PreparedStatement psmt0 = cn.prepareStatement("Select * from `cuelounge`.stocks`");
            ResultSet Rs=psmt0.executeQuery();
            while(Rs.next()){
                if(Rs.getString("P_ID").equals(tf6_adst1.getText())){
                    ch=true;//Yes exists
                }
            }
            //Now it's time to insert new data
            if(ch==true){
                JOptionPane.showMessageDialog(null,"Product already exists!!! Update quantity by editing it");
            }else{  
            PreparedStatement psmt = cn.prepareStatement("INSERT INTO `cuelounge`.`stocks` ( `P_ID`,`Employee_ID`, `Supplier_ID`, `P_Name`, `Unit Buying cost`, `Unit cost S`, `B_Date`, `Exp_Date`, `Q_Avail`, `Stock_Type`, `Manufacturer`, `Time`, `Date`) VALUES ( ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            psmt.setString(1, tf6_adst1.getText());
            psmt.setString(2, CueLounge.emp_ID);
            psmt.setString(3, comb2_adst.getSelectionModel().getSelectedItem());
            psmt.setString(4, tf1_adst.getText());
            psmt.setString(5, tf2_adst.getText());
            psmt.setString(6, tf3_adst.getText());
            psmt.setString(7, dp1_adst.getEditor().getText());
            psmt.setString(8, dp2_adst.getEditor().getText());
            psmt.setString(9, tf5_adst.getText());
            psmt.setString(10, comb1_adst.getSelectionModel().getSelectedItem());
            psmt.setString(11, comb2_adst.getSelectionModel().getSelectedItem());
            psmt.setString(12, (java.time.LocalTime.now().toString()));
             long millis=System.currentTimeMillis();  
            java.sql.Date date=new java.sql.Date(millis);  
            psmt.setString(13,date.toString() );
            psmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Product was successfully added to stocks");
            }
        }
         catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error in updating stock table: "+ex);
        
        }
        
        }
    
}

