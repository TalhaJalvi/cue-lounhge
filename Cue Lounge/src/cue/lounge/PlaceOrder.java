/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cue.lounge;

import com.gluonhq.charm.glisten.control.TextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Talha
 */
public class PlaceOrder implements Initializable {

    @FXML
    private AnchorPane anc_newOrder;
    @FXML
    private Button b2_newOrder;
    @FXML
    private Button b3_newOrder;
    @FXML
    private TextField tf1_newOrder;
    @FXML
    private TextField tf2_newOrder;
    @FXML
    private TextField tf3_newOrder;
    @FXML
    private TextField tf4_newOrder;
     @FXML
    private TextField tf5_newOrder;
    private TextField tf6_newOrder;
    @FXML
    private ComboBox<String> combx1_newOrder;
    @FXML
    private ComboBox<String> combx2_newOrder;
    @FXML
    private DatePicker dp1_newOrder;
    @FXML
    private Button b1_newOrder;
    String msg2="" ;

    @FXML
    private TextField tf0_newOrder;
    String[] Each_Unit_Costvar;
     String[] Product_ID_listvar;
    @FXML
    private ComboBox<String> combx3_newOrder;
        //Making our list as global
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Getting values in combo boxes
         ObservableList<String> dbStockType = FXCollections.observableArrayList("Eatable","Furniture","Game_Equipment","Accessories");
          //Accessory means equipments as bulbs, and other neccessary material computer cables etc
          combx1_newOrder.setItems(dbStockType);
          ObservableList<String> dbOrderStatus = FXCollections.observableArrayList("not recieved","Recieved not paid","Recieved and paid");
          combx2_newOrder.setItems(dbOrderStatus);
        //Now for secound one which is order status 
         
          //Making total bill textfield uneditable
          //Adding and getting supplier ID in combo box 3
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
            combx3_newOrder.setItems(supplierID);
        } catch (SQLException ex) {
            Logger.getLogger(ADDstckController.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }    

    @FXML
    private void f_b2_newOrder(ActionEvent event) {
        String RecipientMailvar=tf0_newOrder.getText();
         String Product_ID_listvar[]=tf1_newOrder.getText().split(",");
        String[] PrdoductNamelistvar=tf2_newOrder.getText().split(",");
        String[] Quantity_eachvar=tf3_newOrder.getText().split(",");
        Each_Unit_Costvar=tf4_newOrder.getText().split(",");
        String TotalBillVar=tf5_newOrder.getText();
        String SupplIDvar=combx3_newOrder.getSelectionModel().getSelectedItem();
        String stctypvar=combx1_newOrder.getSelectionModel().getSelectedItem();
        String ordstsvar=combx2_newOrder.getSelectionModel().getSelectedItem();
        String date=dp1_newOrder.getEditor().getText();
        //Before sending mail writing mail in specific format 
        String msg1="It is hereby notified that our "+"CUE LOUNGE SNOOKER CLUB"+" requires following things as listed:"+"\n"
                    + "\n";
        int length=Product_ID_listvar.length;
        String msg1_5= "     Names:                                    Quantities";             
                    for(int i=0;i<length;i++){
              //Using observable or linked list 
                msg2=msg2+(PrdoductNamelistvar[i]+"               "+Quantity_eachvar[i])+"\n";
              
                    }
        

        String msg3="Order items Type: "+stctypvar+"\n";
        String msg4="\n"+" "+"Waiting for your reply."+"\n";
        String msg5="                                                 Dated:"+date+"\n";
        String msg6="                                               CUE LOUNGE SNOOKER CLUB";
                   
        //Now final message
        String finalMSG=msg1+msg1_5+msg2+msg3+msg4+msg5+msg6;
        //Now for placing order it's time to send mail using above data
      //  JavaMailUtil.send("talhajalvi321@gmail.com", "T1a2r3z4a5n6",RecipientMailvar,"ORDER PLACED BY CUE LOUNGE, Sahiwal",finalMSG );
        //Now it's time to store this all data in database
         try {
                //Now user has filled all field it's time to update table of database
                //First connecting to database
                CueLounge cl=new CueLounge();
                cl.DBMSConnection();
                //Now if connection was successful this part of code will execute
                //Now it's time to make query
                Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root","");
                        PreparedStatement psmt1=cn.prepareStatement("INSERT INTO `cuelounge`.`order_info` (`Employee_ID`, `Product_ID_list`, `Product_N_list`, `Quantity_Each`, `Each_unit_cost`, `T_cost`, `Stock_type`, `Supplier_ID`, `Order_status`, `Date`, `Time`) VALUES (?, ?, ?, ? , ?, ?, ?, ?, ?, ?, ?)");
                        //Now giving these question marks user values
                        psmt1.setString(1, CueLounge.emp_ID);
                        psmt1.setString(2, tf1_newOrder.getText());
                        psmt1.setString(3, tf2_newOrder.getText());
                        psmt1.setString(4, tf3_newOrder.getText());
                        psmt1.setString(5, tf4_newOrder.getText());
                        psmt1.setString(6, tf5_newOrder.getText());
                        psmt1.setString(7, combx1_newOrder.getSelectionModel().getSelectedItem());                                                                                                                                 
                        psmt1.setString(8, combx3_newOrder.getSelectionModel().getSelectedItem());
                        psmt1.setString(9, combx2_newOrder.getSelectionModel().getSelectedItem());
                        psmt1.setString(10, dp1_newOrder.getEditor().getText());
                        //Getting system time
                        psmt1.setString(11,(java.time.LocalTime.now().toString()) );//getting ID
                        psmt1.executeUpdate();
                       
                        //Now this statement is exeted and database is updated now user has to refresh table
                        System.out.println(CueLounge.emp_ID);
                        JOptionPane.showMessageDialog(null, "Data updated successfully!! Refresh your table"); 
                    
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error in updating table entry and is:"+ex);
            }
    }

    @FXML
    private void f_b3_up_order_list(ActionEvent event) {
    }

    @FXML
    private void f_tf5_newOrder(KeyEvent event) {
  
    }

    @FXML
    private void f_b1_newOrder(ActionEvent event) {
          //if user cancel his decision for update then
             //if user name and password is correct then close login window and open main menu window
             //Before that asking or prompting user he will lost all his saved work
        int reply = JOptionPane.showConfirmDialog(null, "Do you want to exit ? All your work will be lost", "", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
                  Stage s=(Stage) anc_newOrder.getScene().getWindow();
                  s.close();
        }
        else {
           JOptionPane.showMessageDialog(null, "Thank you for continuing");
        }

    }
    
}
