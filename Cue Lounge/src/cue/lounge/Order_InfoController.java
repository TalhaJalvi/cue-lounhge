/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cue.lounge;

import com.gluonhq.charm.glisten.control.TextField;
import cue.lounge.ModelTableClasses.ModelTable;
import cue.lounge.ModelTableClasses.ModelTableOrder;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author zabi khan
 */
public class Order_InfoController implements Initializable {

    @FXML
    private TableView<ModelTableOrder> tb_Order_list;
    @FXML
    private TableColumn<ModelTableOrder, String> col_Order_ID_order_list;
    @FXML
    private TableColumn<ModelTableOrder, String> col_Product_ID_list_Order_list;
    @FXML
    private TableColumn<ModelTableOrder, String> col_P_Name_list_Order_list;
    @FXML
    private TableColumn<ModelTableOrder, String> col_Quantity_each_Order_list;
    @FXML
    private TableColumn<ModelTableOrder, String> col_Each_unit_cost_Order_list;
    @FXML
    private TableColumn<ModelTableOrder, String> col_Total_Bill_Order_list;
    @FXML
    private TableColumn<ModelTableOrder, String> col_Supplier_ID_Order_list;
    @FXML
    private TableColumn<ModelTableOrder, String> col_Stock_type_Order_list;
    @FXML
    private TableColumn<ModelTableOrder, String> col_Date_Order_list;
    private TableColumn<ModelTableOrder, String> col_Time_Order_list;

        ObservableList<ModelTableOrder> orderlist=FXCollections.observableArrayList();
    @FXML
    private TableColumn<ModelTableOrder, String> col_Status_Order_list;
    @FXML
    private TextField tf1_orderlist;
    @FXML
    private Button b2_print_orderlist;
    @FXML
    private Button b4_remove_orderlist;
    @FXML
    private Button b3_neworder_orderlist;
    @FXML
    private Button b5_edit_orderlist;
    @FXML
    private Label lb1_orderlist;
    public static String g0,g1,g2,g3,g4,g5,g6,g7,g8,g9;
    
    //Creating filtered list for searching purpose
    FilteredList flist;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
        // TODO
      

    @FXML
    private void f_autoup7(KeyEvent event) {
         try {
             lb1_orderlist.setVisible(false);
             //Deleting old entriies before adding new ones  to avoid duplication
              //Clearing previos data to avoi duplication
              for ( int i = 0; i<tb_Order_list.getItems().size(); i++) {
             tb_Order_list.getItems().clear();
               }
            //Now it's time to connect to database and get our data
            CueLounge cl=new CueLounge();
            cl.DBMSConnection();
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root","");
            //Now we have made connection Now it's time to retrieve data from database
            ResultSet rs=cn.createStatement().executeQuery("Select  *from order_info");
            while(rs.next()){
                orderlist.add(new ModelTableOrder(rs.getString("Order_ID"),rs.getString("Product_ID_list"),rs.getString("Product_N_list"),rs.getString("Quantity_Each"),rs.getString("Each_unit_cost"),rs.getString("T_cost"),rs.getString("Supplier_ID"),rs.getString("Stock_type"),rs.getString("Order_status"),rs.getString("Date")));
            }           
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null,"Error is"+ex);
        }
        
        
        //Setting cell values
            col_Order_ID_order_list.setCellValueFactory(new PropertyValueFactory<>("Order_ID"));
            col_Product_ID_list_Order_list.setCellValueFactory(new PropertyValueFactory<>("Product_ID_list"));
            col_P_Name_list_Order_list.setCellValueFactory(new PropertyValueFactory<>("P_Name_list"));
            col_Quantity_each_Order_list.setCellValueFactory(new PropertyValueFactory<>("Quantity_each"));
            col_Each_unit_cost_Order_list.setCellValueFactory(new PropertyValueFactory<>("Each_unit_cost"));
            col_Total_Bill_Order_list.setCellValueFactory(new PropertyValueFactory<>("Total_Bill")); 
            col_Supplier_ID_Order_list.setCellValueFactory(new PropertyValueFactory<>("Supplier_ID"));
            col_Stock_type_Order_list.setCellValueFactory(new PropertyValueFactory<>("Stock_type"));
             col_Status_Order_list.setCellValueFactory(new PropertyValueFactory<>("Order_Status"));
            col_Date_Order_list.setCellValueFactory(new PropertyValueFactory<>("Date")); 
           //We are not showing time as it is not much important entity

            //String P_ID, String Unit_cost_B, String Unit_cost_S, String B_Date, String Exp_Date, String Q_Avail, String Q_Req, String Stock_type, String Manufacturer, String time;
      //Now it's time to set ITEMS in the table
      tb_Order_list.setItems(orderlist);
      //Passing list to filtered list for search function
       flist=new FilteredList(orderlist,e->true);
    }

    @FXML
    private void f_b2_print_orderlist(ActionEvent event) {
        //Time for printing all details
         try{
              // TODO
            //now suppliers could only be supplier which we have in database
            //First getting data from our database
            CueLounge cl=new CueLounge();
            cl.DBMSConnection();
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root","");
            //Now we have made connection Now it's time to retrieve data from database
            //It's time for opening jasper file
            JasperDesign jdesign=JRXmlLoader.load("H:\\Comsats 5th semester PC\\SDM\\Cue\\Cue Lounge\\src\\Reports\\OrderDetails.jrxml");
            String query="SELECT\n" +
"     order_info.`Employee_ID` AS E_ID,\n" +
"     order_info.`Order_ID` AS Order_ID,\n" +
"     order_info.`Product_ID_list` AS Products_ID,\n" +
"     order_info.`Product_N_list` AS Products_name,\n" +
"     order_info.`Quantity_Each` AS Quantity,\n" +
"     order_info.`Each_unit_cost` AS unit_cost,\n" +
"     order_info.`T_cost` AS   T_cost,\n" +
"     order_info.`Stock_type` AS Stock_type,\n" +
"     order_info.`Supplier_ID` AS Supplier_ID,\n" +
"     order_info.`Order_status` AS Order_status,\n" +
"     order_info.`Date` AS Date,\n" +
"     order_info.`Time` AS  Time\n" +
"\n" +
"FROM\n" +
"     `order_info` order_info";
            JRDesignQuery updateQuery=new JRDesignQuery();
            updateQuery.setText(query);
            jdesign.setQuery(updateQuery);
            JasperReport jreport=JasperCompileManager.compileReport(jdesign);
            JasperPrint jprint=JasperFillManager.fillReport(jreport,null,cn);
            //Now it's time to view report
            JasperViewer.viewReport(jprint);
           
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error in generating report is: "+e);
        }
    }

    @FXML
    private void f_b4_remove_orderlist(ActionEvent event) {
        //Now if a user wants to remove an order from order list i.e he has cancelled that order if pending or any other person if is not required
        //warning will e shown to him Deleting only selected portion or part
          try {
            //First of all making connection with DBMS
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root",""); 
            CueLounge cl=new CueLounge();
            cl.DBMSConnection();
            //  Now that connection was successful it's time to remove employee or simply data from database
            //First getting id from table so we can match it with our database 
            //Creating new emply observale list
            ObservableList<ModelTableOrder> remlist; //list of values to be removed
            //Getting values of selected rows in observable list
            remlist=tb_Order_list.getSelectionModel().getSelectedItems();
            //selecting employee ID and storing it in variable
            String a=remlist.get(0).getOrder_ID();
            PreparedStatement psmt=cn.prepareStatement("DELETE FROM `cuelounge`.`order_info` WHERE `order_info`.`Order_ID`=?");
            psmt.setString(1, a);//giving ? employee ID
            psmt.execute();
           lb1_orderlist.setText("Please refresh table!! database was modified");
            lb1_orderlist.setTextFill(Color.RED);
            lb1_orderlist.setVisible(true);
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null,"Error during removing data from database"+ex);
        }
        
    }

    @FXML
    private void f_b3_neworder_orderlist(ActionEvent event) {
        //Now if user or ADMIN want to place new order openeining new frame for it placing it to new suppliers
           try{
                  FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Place_Order.fxml"));//FXML loader class will load 2nd window
                  Parent root1 = (Parent) fxmlLoader.load();//It will be stored in root1 of parent type
                  Stage stage = new Stage();//Setting a new stage
                  stage.setScene(new Scene(root1));
                  stage.show();//displaying our new window
              }
              catch(Exception e){
                  System.out.println("Error in loading signup window"+e);
              };
    }

    @FXML
    private void f_b5_edit_orderlist(ActionEvent event) {
       //Opening a new frame for update
       //Before updating order table we need to pass selected values to nexr frame
        ObservableList<ModelTableOrder> uplist;
            //Getting values of selected rows in observable list
            uplist=tb_Order_list.getSelectionModel().getSelectedItems();
            g0=uplist.get(0).getOrder_ID();
            g1=uplist.get(0).getProduct_ID_list();
            g2=uplist.get(0).getP_Name_list();
            g3=uplist.get(0).getQuantity_each();
            g4=uplist.get(0).getEach_unit_cost();
            g5=uplist.get(0).getTotal_Bill();
            g6=uplist.get(0).getSupplier_ID();
            g7=uplist.get(0).getStock_type();//stckt
            g8=uplist.get(0).getOrder_Status();//ordr
            g9=uplist.get(0).getDate();//date
         try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("order_tb_up.fxml"));//FXML loader class will load 2nd window
            Parent root1 = (Parent) fxmlLoader.load();//It will be stored in root1 of parent type
            Stage stage = new Stage();//Setting a new stage
            stage.setScene(new Scene(root1));  
            stage.show();//displaying our new window
    }
        catch(Exception e){
        JOptionPane.showMessageDialog(null,"Error in loading signup window " +e);
    };
       
       
        }

    @FXML
    private void f_tf1_orderlist(KeyEvent event) {
        //Applying search function 
           try{
        tf1_orderlist.textProperty().addListener((observable, oldValue,newValue)-> {
           flist.setPredicate((Predicate<? super ModelTableOrder>)(ModelTableOrder mto)->{
              
                
              if(newValue.isEmpty() || newValue==null){
                  return true;
              }     
              else if(mto.getOrder_ID().contains(newValue)){
                  return true;
              }
              else if(mto.getProduct_ID_list().contains(newValue)){
                  return true;
              }
              else if(mto.getP_Name_list().contains(newValue)){
                  return true;
              }
              else if(mto.getDate().contains(newValue)){
                  return true;
              }
              else if(mto.getEach_unit_cost().contains(newValue)){
                  return true;
              }
              else if(mto.getOrder_Status().contains(newValue)){
                  return true;
              }
              else if(mto.getQuantity_each().contains(newValue)){
                  return true;
              }
              else if(mto.getStock_type().contains(newValue)){
                  return true;
              }
              else if(mto.getSupplier_ID().contains(newValue)){
                  return true;
              }
              else if(mto.getTotal_Bill().contains(newValue)){
                  return true;
              }
                   
                   
                   
              return  false;
              
        }); 
        });
        //Now time for Sorted list to come into action
        SortedList sort=new SortedList(flist);
        sort.comparatorProperty().bind(tb_Order_list.comparatorProperty());
        tb_Order_list.setItems(sort);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Table is empty!!! Search not possible");
        }
        
    }
       
         
    }
    

