/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cue.lounge;

import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Talha
 */
public class UpdateStockControllers implements Initializable {

    @FXML
    private TextField tf1_upstck;
    @FXML
    private TextField tf2_upstck;
    @FXML
    private TextField tf3_uptck;
    @FXML
    private DatePicker dp1_upstck;
    @FXML
    private TextField tf4_upstck;
    @FXML
    private Button b1_savesu_upstck;
    @FXML
    private DatePicker dp2_upstck;
    @FXML
    private TextField tf5_upstck;
    @FXML
    private ComboBox<String> comb1_upstck;
    @FXML
    private ComboBox<String> comb2_upstck;
    @FXML
    private JFXTextField tf7_upStocks;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
  ObservableList<String> stocktype = FXCollections.observableArrayList("Eatable","Furniture","Game_Equipment","Accessories");
            comb1_upstck.setItems(stocktype);
            ObservableList<String> supplierID = FXCollections.observableArrayList();
        try {
            // TODO
            //Getting values from previous frames
            tf7_upStocks.setText(MainMenuController.b1);
            tf1_upstck.setText(MainMenuController.b2);
            tf2_upstck.setText(MainMenuController.b3);
            tf3_uptck.setText(MainMenuController.b4);
            dp1_upstck.getEditor().setText(MainMenuController.b5);
            dp2_upstck.getEditor().setText(MainMenuController.b6);
            tf4_upstck.setText(MainMenuController.b7);
            tf5_upstck.setText(MainMenuController.b8);
            comb1_upstck.setValue(MainMenuController.b9);
            comb2_upstck.setValue(MainMenuController.b10);
            //All values have been setted
            
            //now suppliers could only be supplier which we have in database
            //First getting data from our database
            boolean up=false;
            CueLounge cl=new CueLounge();
            cl.DBMSConnection();
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root","");
            //Now we have made connection Now it's time to retrieve data from database
            ResultSet rs=cn.createStatement().executeQuery("SELECT *from suppliers");//Table was named due to mistake
            while(rs.next()){
                supplierID.add(rs.getString("Supplier_ID"));
                
            }
            //Addding to combobox
            comb2_upstck.setItems(supplierID);
            //Now it's time to update database
            
            
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
        //Now time for updating database
        //Now time for updating stocks database
        try{
            //First making connection with database
            CueLounge cl=new CueLounge();
            cl.DBMSConnection();
            //Now for connection string
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root","");
            //Now making update statement
            PreparedStatement psmt=cn.prepareStatement("UPDATE `cuelounge`.`stocks` SET \n" +
"`Employee_ID` = ?,\n" +
"`Supplier_ID` = ?,\n" +
"`P_Name` = ?,\n" +
"`Unit Buying cost` = ?,\n" +
"`Unit cost S` = ?,\n" +
"`B_Date` = ?,\n" +
"`Exp_Date` = ?,\n" +
"`Q_Avail` = ?,\n" +
"`Stock_Type` = ?,\n" +
"`Manufacturer` = ?,\n" +
"`Time` = ?,\n" +
"`Date` = ? WHERE `stocks`.`P_ID` =?");
          //Now giving value to above query
          psmt.setString(1,  CueLounge.emp_ID);//Employee who is updating 
          psmt.setString(2,  comb2_upstck.getSelectionModel().getSelectedItem());
          psmt.setString(3,  tf1_upstck.getText());
          psmt.setString(4,  tf2_upstck.getText());
          psmt.setString(5,  tf3_uptck.getText());
          psmt.setString(6,  dp1_upstck.getEditor().getText());
          psmt.setString(7,  dp2_upstck.getEditor().getText());
          psmt.setString(8,  tf5_upstck.getText());
          psmt.setString(9,  comb1_upstck.getSelectionModel().getSelectedItem());
          psmt.setString(10, comb2_upstck.getSelectionModel().getSelectedItem());
          psmt.setString(11, (java.time.LocalTime.now().toString()));
          long millis=System.currentTimeMillis();  
          java.sql.Date date=new java.sql.Date(millis);   
          psmt.setString(12, date.toString());
          psmt.setString(13, tf7_upStocks.getText());
          psmt.executeUpdate();
          JOptionPane.showMessageDialog(null, "Stocks Database was successfully updated ");
            
        }
        catch(NullPointerException ex){
            JOptionPane.showMessageDialog(null,"One of your field may be empty!");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error during updating stock database"); //Database updation error
        }
    }
    
}
