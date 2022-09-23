/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cue.lounge;

import com.gluonhq.charm.glisten.control.TextField;
import com.jfoenix.controls.JFXTextField;
import static cue.lounge.GamesTable1.bill;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
public class Order_tb_upController implements Initializable {

    @FXML
    private AnchorPane anc_order_up_list;
    @FXML
    private Button b1_up_order_list;
    @FXML
    private Button b2_up_order_list;
    @FXML
    private TextField tf2_up_order_list;
    @FXML
    private TextField tf3_up_order_list;
    @FXML
    private TextField tf4_up_order_list;
    @FXML
    public TextField tf5_up_order_list;
    @FXML
    private TextField tf6_up_order_list;
    private TextField tf7_up_order_list;
    @FXML
    private ComboBox<String> combx1_tup_order_list;
    @FXML
    private ComboBox<String> combx2_tup_order_list;
    @FXML
    private DatePicker dp1_tup_order_list;
    @FXML
    private JFXTextField tf1_up_order_list;
    @FXML
    private ComboBox<String> combx3_tup_order_list1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Adding values to combo boxes
        //Adding values to first one "STOCK TYPE"
          ObservableList<String> dbStockType = FXCollections.observableArrayList("Eatable","Furniture","Game_Equipment","Accessories");
          //Accessory means equipments as bulbs, and other neccessary material computer cables etc
          combx1_tup_order_list.setItems(dbStockType);
        //Now for secound one which is order status 
          ObservableList<String> dbOrderStatus = FXCollections.observableArrayList("not recieved","Recieved not paid","Recieved and paid");
          combx2_tup_order_list.setItems(dbOrderStatus);
          //Adding supplier
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
            combx3_tup_order_list1.setItems(supplierID);
        } catch (SQLException ex) {
            Logger.getLogger(ADDstckController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Getting value in this frame from previous frame
        tf1_up_order_list.setText(Order_InfoController.g0);
        tf2_up_order_list.setText(Order_InfoController.g1);
        tf3_up_order_list.setText(Order_InfoController.g2);
        tf4_up_order_list.setText(Order_InfoController.g3);
        tf5_up_order_list.setText(Order_InfoController.g4);
        tf6_up_order_list.setText(Order_InfoController.g5);
        
        combx3_tup_order_list1.setValue(Order_InfoController.g6);
        combx1_tup_order_list.setValue(Order_InfoController.g7);
        combx2_tup_order_list.setValue(Order_InfoController.g8);
        dp1_tup_order_list.getEditor().setText(Order_InfoController.g9);
        
    }    

    @FXML
    private void f_b1_up_order_list(ActionEvent event) {
           //Save buttion
        //Now it's time to do changing and updation in database
          
           boolean ch=false;
            try {
                //Now user has filled all field it's time to update table of database
                //First connecting to database
                CueLounge cl=new CueLounge();
                cl.DBMSConnection();
                //Now if connection was successful this part of code will execute
                //Now it's time to make query
                Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root","");
                PreparedStatement psmt=cn.prepareStatement("Select *from order_info");
                //Preparingf secound statement which is of update
                
                ch=false;
                //Now it's time time to execute this query
                ResultSet rs=psmt.executeQuery();
                while(rs.next()){
                    if(rs.getString("Order_ID").equals(tf1_up_order_list.getText())){
                        //We will come here if required Employee ID is found
                        //Preparing update statement
                        //Now changing that entry
                        ch=true;
                        //Order ID which is tobe updated is found
                        PreparedStatement psmt1=cn.prepareStatement("UPDATE `cuelounge`.`order_info` SET `Product_ID_list` =?, `Product_N_list` = ?,`Quantity_Each`=?, `Each_unit_cost`=?,`T_cost`=?, `Supplier_ID`=?,`Stock_type`=?,`Order_status`=?, `Date`=? WHERE `Order_ID`=?");
                        //Now giving these question marks user values
                        psmt1.setString(1, tf2_up_order_list.getText());
                        psmt1.setString(2, tf3_up_order_list.getText());
                        psmt1.setString(3, tf4_up_order_list.getText());
                        psmt1.setString(4, tf5_up_order_list.getText());
                        psmt1.setString(5, tf6_up_order_list.getText());
                        psmt1.setString(6, combx3_tup_order_list1.getSelectionModel().getSelectedItem());                                                                                                                                 
                        psmt1.setString(7, combx1_tup_order_list.getSelectionModel().getSelectedItem());
                        psmt1.setString(8, combx2_tup_order_list.getSelectionModel().getSelectedItem());
                        psmt1.setString(9, dp1_tup_order_list.getEditor().getText());
                        psmt1.setString(10,tf1_up_order_list.getText() );//getting ID
                        psmt1.executeUpdate();
                         //Making it true i.e data is updated successfully
                        //Now this statement is exeted and database is updated now user has to refresh table
                        if(combx2_tup_order_list.getSelectionModel().getSelectedItem().equals("Recieved and paid")){
                            
                        CueLounge.t_amount=CueLounge.t_amount-( Integer.parseInt(tf6_up_order_list.getText()));
                         //Now updating database of account in this
                         CueLounge.t_amount=CueLounge.t_amount+bill;
            PreparedStatement psmt2 = cn.prepareStatement("INSERT INTO `cuelounge`.`account` (`Employee_ID`, `Amount_deducted`, `Amount_added`, `Reason`, `Total_Remaining`, `Date`, `Time`) VALUES (?, ?, ?, ?, ?, ?, ?);");
            psmt2.setString(1,  CueLounge.emp_ID);//Seetiing employee ID of dealing person
            psmt2.setString(2, tf6_up_order_list.getText());
            psmt2.setString(3, "0");
            psmt2.setString(4,"Stock was bought having order ID"+tf1_up_order_list.getText()+"and type was"+combx1_tup_order_list.getSelectionModel().getSelectedItem());
            psmt2.setString(5, String.valueOf(CueLounge.t_amount));   
            long millis=System.currentTimeMillis();  
            java.sql.Date date=new java.sql.Date(millis);
            psmt2.setString(6, date.toString());//Setting date
            psmt2.setString(7, (java.time.LocalTime.now().toString()));
            psmt2.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data updated successfully!! Refresh your table");
                        }   
                           break;//breaking loop after updation   
                    }
                    
                   
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error in updating table entry and is:"+ex);
            }
              if(ch==false){
                    JOptionPane.showMessageDialog(null,"Please make sure you have entered correct Order ID");
                }

        }
    

    @FXML
    private void f_b2_up_order_list(ActionEvent event) {
        //if user cancel his decision for update then
             //if user name and password is correct then close login window and open main menu window
             //Before that asking or prompting user he will lost all his saved work
        int reply = JOptionPane.showConfirmDialog(null, "Do you want to exit ? All your work will be lost", "", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
                  Stage s=(Stage) anc_order_up_list.getScene().getWindow();
                  s.close();
        }
        else {
           JOptionPane.showMessageDialog(null, "Thank you for continuing");
        }

    }

    @FXML
    private void f_tf6_up_order_list(KeyEvent event) {
        //Total Bill
         tf6_up_order_list.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d")) {
                    tf6_up_order_list.setText(oldValue);
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

    private void tf7_up_order_list(KeyEvent event) {
        //Supplier ID
          tf7_up_order_list.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,4}?")) {
                    tf7_up_order_list.setText(oldValue);
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
    private void f_tf1_up_order_list(KeyEvent event) {
        //Order ID
                  tf1_up_order_list.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,4}?")) {
                    tf1_up_order_list.setText(oldValue);
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
