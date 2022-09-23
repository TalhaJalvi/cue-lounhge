/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cue.lounge;


import com.gluonhq.charm.glisten.control.TextField;
import com.jfoenix.controls.JFXTextField;
import cue.lounge.ModelTableClasses.ModelTableSupplier;
import cue.lounge.ModelTableClasses.ModelTableStocks;
import cue.lounge.ModelTableClasses.ModelTableGames;
import cue.lounge.ModelTableClasses.ModelTableCafe;
import cue.lounge.ModelTableClasses.ModelTableAccount;
import cue.lounge.ModelTableClasses.ModelTable;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
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
 * @author Talha
 */
public class MainMenuController implements Initializable {

    //TABLE VIEW AND DATA
    @FXML
    public TableView<ModelTable> tb_mm1;
    @FXML
    private TableColumn<ModelTable,String> col_Emp_ID;
    @FXML
    private TableColumn<ModelTable,String> col_Name;
    @FXML
    private TableColumn<ModelTable,String> col_Post;
    @FXML
    private TableColumn<ModelTable,String> col_Salary;
    @FXML
    private TableColumn<ModelTable,String> col_DOJ;
    @FXML
    private TableColumn<ModelTable,String> Col_Gender;
    @FXML
    private TableColumn<ModelTable,String> Col_Maritual_status;
    @FXML
    private TableColumn<ModelTable,String> Col_Address;
    @FXML
    private TableColumn<ModelTable,String> Col_Paid;
    @FXML
    private TableColumn<ModelTable,String> col_Time;
    @FXML
    private TableColumn<ModelTable,String> col_Date;
    
    ObservableList<ModelTable> employeelist=FXCollections.observableArrayList();
    @FXML
        private TableView<ModelTableStocks> tb_stocks;
    @FXML
    private TableColumn<ModelTableStocks, String> col_P_ID;
       @FXML
    private TableColumn<ModelTableStocks, String> col_P_Name;
    @FXML
    private TableColumn<ModelTableStocks, String> col_Unit_cost_B;
    @FXML
    private TableColumn<ModelTableStocks, String> col_Unit_cost_S;
    @FXML
    private TableColumn<ModelTableStocks, String> col_B_Date;
    @FXML
    private TableColumn<ModelTableStocks, String> col_Exp_Date;
    @FXML
    private TableColumn<ModelTableStocks, String> col_Q_Avail;
    private TableColumn<ModelTableStocks, String> col_Q_Req;
    @FXML
    private TableColumn<ModelTableStocks, String> col_Stock_type;
    @FXML
    private TableColumn<ModelTableStocks, String> col_Manufacturer;   
    private TableColumn<ModelTableStocks, String> col_Timestocks;    
     //Now making secound coloumn observable list  
     ObservableList<ModelTableStocks> stocklist=FXCollections.observableArrayList();       
    @FXML
    private TableView<ModelTableGames> tb_games;
    @FXML
    private TableColumn<ModelTableGames, String> col_Table_ID;
    @FXML
    private TableColumn<ModelTableGames, String> col_P1_Name;
    @FXML
    private TableColumn<ModelTableGames, String> col_P2_Name;
    @FXML
    private TableColumn<ModelTableGames, String> col_Time_Started;
    @FXML
    private TableColumn<ModelTableGames, String> col_Time_Ended;
    private TableColumn<ModelTableGames, String> col_Time_Allowed;
    @FXML
    private TableColumn<ModelTableGames, String> col_Game_Type;
    @FXML
    private TableColumn<ModelTableGames, String> col_Bill;
    @FXML
     private TableColumn<ModelTableGames, String> col_Paidgames;
    @FXML
    private TableColumn<ModelTableGames, String> col_Dategames;
    @FXML
    private TableColumn<ModelTableGames, String> col_Timegames;
    //Observable list
    ObservableList<ModelTableGames> gamelist=FXCollections.observableArrayList();
    @FXML
    private TableView<ModelTableCafe> tb_Cafe;
    @FXML
    private TableColumn<ModelTableCafe, String> col_Customer_ID_cafe;
    @FXML
    private TableColumn<ModelTableCafe, String> col_C_Name_cafe;
    @FXML
    private TableColumn<ModelTableCafe, String> col_P_ID_list_Cafe;
    @FXML
    private TableColumn<ModelTableCafe, String> col_P_Name_List_cafe;
    @FXML
    private TableColumn<ModelTableCafe, String> col_Quantity_cafe;
    @FXML
    private TableColumn<ModelTableCafe, String> col_Itrm_unit_C_cafe;
    @FXML
    private TableColumn<ModelTableCafe, String> col_T_Bill_cafe;
    @FXML
    private TableColumn<ModelTableCafe, String> col_Emp_ID_cafe;
    @FXML
    private TableColumn<ModelTableCafe, String> col_Date_cafe;
    @FXML
    private TableColumn<ModelTableCafe, String> col_Time_Cafe;
    
    //Now making list
     ObservableList<ModelTableCafe> cafelist=FXCollections.observableArrayList();
    @FXML
    private TableView<ModelTableAccount> tb_account;
    @FXML
    private TableColumn<ModelTableAccount, String> col_Employe_ID_account;
    @FXML
    private TableColumn<ModelTableAccount, String> col_Amount_deducted_account;
    @FXML
    private TableColumn<ModelTableAccount, String> col_Amount_added_account;
    @FXML
    private TableColumn<ModelTableAccount, String> co_Reason_account;
    @FXML
    private TableColumn<ModelTableAccount, String> col_Total_Remaining_account;
    @FXML
    private TableColumn<ModelTableAccount, String> col_Date_account;
    @FXML
    private TableColumn<ModelTableAccount, String> col_Time_account;

    ObservableList<ModelTableAccount> accountlist=FXCollections.observableArrayList();
    @FXML
    private TableView<ModelTableSupplier> tb_Supplier;
    @FXML
    private TableColumn<ModelTableSupplier, String> col_Supplier_ID_supplier;
    @FXML
    private TableColumn<ModelTableSupplier, String> col_Supplier_Name_supplier;
    @FXML
    private TableColumn<ModelTableSupplier, String> col_Product_type_supplier;
    @FXML
    private TableColumn<ModelTableSupplier, String> col_Email_supplier;
    @FXML
    private TableColumn<ModelTableSupplier, String> col_Phone_no_supplier;
    @FXML
    private TableColumn<ModelTableSupplier, String> col_Address_supplier;
    private TableColumn<ModelTableSupplier, String> col_Focal_P_supplier;
    @FXML
    private TableColumn<ModelTableSupplier, String> col_Timings_supplier;
    @FXML
    private TableColumn<ModelTableSupplier, String> col_Date_supplier;
    @FXML
    private TableColumn<ModelTableSupplier, String> col_Tijme_supplier;
    
    ObservableList<ModelTableSupplier> supplierlist=FXCollections.observableArrayList();
    @FXML
    private Button b_print_mmempl;
    @FXML
    private Button b_add_mmempl;
    @FXML
    private Button b_remove_mmempl;
    @FXML
    private Button b_update_mmempl;
    @FXML
    private Label lb2_mm1_emp; //Making it static so can be accessed in other classes and alos making it public
    @FXML
    private TextField tf1_mm2_stocks;
    @FXML
    private Button b1_print_mm2_stocks;
    @FXML
    private Button b2_order_mm2_stocks;
    @FXML
    private Button b3_remove_mm2_stocks;
    @FXML
    private Button b4_add_mm2_stocks;
    @FXML
    private Button b5_update_mm2_stocks;
    @FXML
    private Label lb1_tamount_account;
    @FXML
    private Label lb6_tloanstopay_account;
    @FXML
    private Label lb2_tDeptstoget_account;
    @FXML
    public Label lb3_texpenditure_account;
    @FXML
    private Label lb4_earnedgames_account;
    @FXML
    private Label lb5_tearnedcafe_account;
    @FXML
    private Label lb7_tprofit_account;
    @FXML
    private Label lb8_empID_account;
    @FXML
    public  Button b1_tablebooking_mm3;
    @FXML
    private Button b2_tablebooking_mm3;
    @FXML
    private Button b3_tablebooking_mm3;
    @FXML
    private Button b4_tablebooking_mm3;
    @FXML
    private Button b5_tablebooking_mm3;
    @FXML
    private TableColumn<?, ?> col_Emp_ID_supplier;
    @FXML
    private TableColumn<?, ?> col_Datestocks;
    @FXML
    private AnchorPane col_time_stocs;
    @FXML
    private TableColumn<?, ?> col_Employee_ID;
    @FXML
    private Button b2_suppliers_add;
    @FXML
    private Button b1_suppliers_up;
    @FXML
    private Button b3_suppliers_rem;
    @FXML
    private Button button_print_games;
    @FXML
    private Button b2_bill;
    @FXML
    public  JFXTextField txt_cname;
    public  JFXTextField txt_amtpaid;
    @FXML
    private Button b_button;
    public JFXTextField txt_pname;
    @FXML
    public  JFXTextField txt_quant;
    @FXML
    public ComboBox<String> comb1_stocktype;
    @FXML
    public JFXTextField Amount_paid;
    @FXML
    public ImageView lb_logout_bt;
    public static JFXTextField txt_ID;
    @FXML
    private JFXTextField txt_scost;
    //Variables which are used to pass values to next frame
    public static String a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12;
    public static String b1,b2,b3,b4,b5,b6,b7,b8,b9,b10;
    public static String c0,c1,c2,c3,c4,c5,c6,c7,c8;
    public static String d1,d2,d3,d4,d5,d6,d7,d8,d9,d10;
    public static String e1,e2,e3,e4,e5,e6;
    public static String f0,f1,f2,f3,f4,f5,f6,f7,f8,f9,f10;
    
    @FXML
    private Button b1_print_cafe;
    @FXML
    private Button b1_report_account;
    @FXML
    private Button b1_print_suppliers;
    @FXML
    private AnchorPane anc_mainmenu;
    @FXML
    private Button button_update_games;
    @FXML
    private Button button_remove_games;
    @FXML
    private TableColumn<?, ?> col_srNo;
    @FXML
    private Button b3_remove_cafe;
    @FXML
    private Button b2_update_cafe;
    @FXML
    private Button b2_update_Account;
    @FXML
    private TableColumn<?, ?> col_Sr_no_supplier;
    @FXML
    private TextField tf_filter;
    FilteredList filteredDataempl;
    FilteredList filteredDataStocks;
    FilteredList filteredDataGames;
    FilteredList filteredDataCafe;
    FilteredList filteredDataAccount;
    FilteredList filteredDataSuppliers;
    
    @FXML
    private TextField tf1_search_gametb;
    @FXML
    private TextField tf1_search_cafetb;
    @FXML
    private TextField tf1_search_accoynttb;
    @FXML
    private TextField tf1_search_suppliers;
    ObservableList<String> productsListCafe =FXCollections.observableArrayList();
    
     
     //User cafe items
     public static ObservableList<String> items =FXCollections.observableArrayList();
      public static ArrayList<String> quantitylist_cafe =new ArrayList();
    @FXML
    private AnchorPane txt_totalbill;
    @FXML
    public  JFXTextField txt_scost1;
    public static float bill_cafe=0;
    @FXML
    private JFXTextField txt_PID;
     ObservableList<String> PIDcafe =FXCollections.observableArrayList();
     
    public static ObservableList<String> unitbillscafe =FXCollections.observableArrayList();
         public double bHeight=0.0;
         public static float amount_paid_cafe;
         public static String cnamecafe;
    @FXML
    private Button b6_tablebooking_mm3;
     //because of this
    public void initialize(URL url, ResourceBundle rb) {
        //Setting accounts labels in intializers
         
            
            
            //Adding to account some details which are to be present there
                           try {
                   
                    lb8_empID_account.setText(CueLounge.emp_ID);
            
            //Now it's time to connect to database and get our data
            CueLounge cl=new CueLounge();
            cl.DBMSConnection();
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root","");
            //Now we have made connection Now it's time to retrieve data from database
            ResultSet rs=cn.createStatement().executeQuery("SELECT *from account");
            while(rs.next()){
                
           CueLounge.t_amount=Float.parseFloat(rs.getString("Total_Remaining"));
           CueLounge.t_expenditure=CueLounge.t_expenditure+Float.parseFloat(rs.getString("Amount_deducted"));
           CueLounge.t_earnings=CueLounge.t_earnings+Float.parseFloat(rs.getString("Amount_added"));
          
            }
           lb1_tamount_account.setText(String.valueOf(CueLounge.t_amount));
           lb3_texpenditure_account.setText(String.valueOf(CueLounge.t_expenditure));
           lb7_tprofit_account.setText(String.valueOf(CueLounge.t_earnings-CueLounge.t_expenditure));
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null,"Error is"+ex);
        }

           //For cafe buyable items
           try {
            CueLounge cl=new CueLounge();
            cl.DBMSConnection();
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root","");
            //calling for buiding up connection
            PreparedStatement psmt0 = cn.prepareStatement("Select * from `cuelounge`.stocks`");
            ResultSet Rs=psmt0.executeQuery();
            while(Rs.next()){
                if(Rs.getString("Stock_Type").equalsIgnoreCase("Eatable")){
                   productsListCafe.add(Rs.getString("P_Name"));
                }
            }
            comb1_stocktype.setItems(productsListCafe);
            //Now it's time to insert new data
          
        } catch (SQLException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
                 

    }    
    @FXML
    private void f_upauto1(KeyEvent event) {
         try {  
                    
            lb2_mm1_emp.setVisible(false);
            //Deleting old table values son as to make or show fresh data only in table
            for ( int i = 0; i<tb_mm1.getItems().size(); i++) {
            tb_mm1.getItems().clear();
               }
            //Now it's time to connect to database and get our data
            CueLounge cl=new CueLounge();
            cl.DBMSConnection();
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root","");
            //Now we have made connection Now it's time to retrieve data from database
            ResultSet rs=cn.createStatement().executeQuery("Select  *from employees");
            while(rs.next()){
                employeelist.add(new ModelTable(rs.getString("Employee_ID"),rs.getString("First_Name"),rs.getString("Post"),rs.getString("Salary"),rs.getString("DOJ"),rs.getString("Gender"),rs.getString("MARITUAL_ST"),rs.getString("Address"),rs.getString("Paid"),rs.getString("Time"),rs.getString("DOJ")));          
            }          
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null,"Error is"+ex);
        }      
        //Setting cell values
            col_Emp_ID.setCellValueFactory(new PropertyValueFactory<>("Emp_ID"));
            col_Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
            col_Post.setCellValueFactory(new PropertyValueFactory<>("Post"));
            col_Salary.setCellValueFactory(new PropertyValueFactory<>("Salary"));
            col_DOJ.setCellValueFactory(new PropertyValueFactory<>("DOJ"));
            Col_Gender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
            Col_Maritual_status.setCellValueFactory(new PropertyValueFactory<>("Maritual_status"));
            Col_Address.setCellValueFactory(new PropertyValueFactory<>("Address"));
            Col_Paid.setCellValueFactory(new PropertyValueFactory<>("Paid"));
            col_Time.setCellValueFactory(new PropertyValueFactory<>("Time"));
            col_Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
      
      //Now it's time to set ITEMS in the table
      tb_mm1.setItems(employeelist);
      //Passing list to filtered list for search function
       filteredDataempl=new FilteredList(employeelist,e->true);
       
      
    }

    @FXML
    private void f_upauto2(KeyEvent event) {
        
         try {
             //Clearing previos data to avoi duplication
              for ( int i = 0; i<tb_stocks.getItems().size(); i++) {
             tb_stocks.getItems().clear();
               }
            //Now it's time to connect to database and get our data
            CueLounge cl=new CueLounge();
            cl.DBMSConnection();
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root","");
            //Now we have made connection Now it's time to retrieve data from database
            ResultSet rs=cn.createStatement().executeQuery("Select  *from stocks");
            while(rs.next()){
                stocklist.add(new ModelTableStocks(rs.getString("P_ID"),rs.getString("Employee_ID"),rs.getString("P_Name"),rs.getString("Unit Buying cost"),rs.getString("Unit cost S"),rs.getString("B_Date"),rs.getString("Exp_Date"),rs.getString("Q_Avail"),rs.getString("Stock_Type"),rs.getString("Manufacturer"),rs.getString("Time")));
            }
            
            
            
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null,"Error is"+ex);
        }
        
        
        //Setting cell values
            col_P_ID.setCellValueFactory(new PropertyValueFactory<>("P_ID"));
            col_P_Name.setCellValueFactory(new PropertyValueFactory<>("P_Name"));
            col_Unit_cost_B.setCellValueFactory(new PropertyValueFactory<>("Unit_cost_B"));
            col_Unit_cost_S.setCellValueFactory(new PropertyValueFactory<>("Unit_cost_S"));
            col_B_Date.setCellValueFactory(new PropertyValueFactory<>("B_Date"));
            col_Exp_Date.setCellValueFactory(new PropertyValueFactory<>("Exp_Date"));
            col_Q_Avail.setCellValueFactory(new PropertyValueFactory<>("Q_Avail"));
            col_Stock_type.setCellValueFactory(new PropertyValueFactory<>("Stock_type"));
            col_Employee_ID.setCellValueFactory(new PropertyValueFactory<>("Emp_ID"));
            col_Datestocks.setCellValueFactory(new PropertyValueFactory<>("timestocks"));
            col_Manufacturer.setCellValueFactory(new PropertyValueFactory<>("Manufacturer"));
            
            
      
            //String P_ID, String Unit_cost_B, String Unit_cost_S, String B_Date, String Exp_Date, String Q_Avail, String Q_Req, String Stock_type, String Manufacturer, String time;
      //Now it's time to set ITEMS in the table
      tb_stocks.setItems(stocklist);
      //Passing list to filtered list for search function
       filteredDataStocks=new FilteredList(stocklist,e->true);
    }

    @FXML
    private void f_upauto3(KeyEvent event) {
                 try {
             //Deleting old table values son as to make or show fresh data only in table
             for ( int i = 0; i<tb_games.getItems().size(); i++) {
             tb_games.getItems().clear();
               }        
            //Now it's time to connect to database and get our data
            CueLounge cl=new CueLounge();
            cl.DBMSConnection();
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root","");
            //Now we have made connection Now it's time to retrieve data from database
            ResultSet rs=cn.createStatement().executeQuery("SELECT *FROM `players_info`");
            while(rs.next()){
            gamelist.add(new ModelTableGames(rs.getString("sr_no"),rs.getString("Table_ID"),rs.getString("P1_Name"),rs.getString("P2_Name"),rs.getString("Time_Started"),rs.getString("Time_Ended"),rs.getString("Game_Type"),rs.getString("Bill"),rs.getString("Paid"),rs.getString("Date"),rs.getString("Time")));
            }
            
            
            
            
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null,"Error is"+ex);
        }
        
        
        //Setting cell values
            col_srNo.setCellValueFactory(new PropertyValueFactory<>("sr_no_var"));
            col_Table_ID.setCellValueFactory(new PropertyValueFactory<>("Table_ID"));
            col_P1_Name.setCellValueFactory(new PropertyValueFactory<>("P1_Name"));
            col_P2_Name.setCellValueFactory(new PropertyValueFactory<>("P2_Name"));
            col_Time_Started.setCellValueFactory(new PropertyValueFactory<>("Time_Started"));
            col_Time_Ended.setCellValueFactory(new PropertyValueFactory<>("Time_Ended"));
            col_Game_Type.setCellValueFactory(new PropertyValueFactory<>("Game_Type"));
            col_Bill.setCellValueFactory(new PropertyValueFactory<>("Bill"));
            col_Paidgames.setCellValueFactory(new PropertyValueFactory<>("G_Paid"));
            col_Dategames.setCellValueFactory(new PropertyValueFactory<>("Dategames"));
            col_Timegames.setCellValueFactory(new PropertyValueFactory<>("Timegames"));  
            //String P_ID, String Unit_cost_B, String Unit_cost_S, String B_Date, String Exp_Date, String Q_Avail, String Q_Req, String Stock_type, String Manufacturer, String time;
      //Now it's time to set ITEMS in the table
      tb_games.setItems(gamelist);
      
       //Passing list to filtered list for search function
       filteredDataGames=new FilteredList(gamelist,e->true);
    }

    @FXML
    private void f_upauto4(KeyEvent event) {
                try {
                    //Deleting old table values son as to make or show fresh data only in table
                    for ( int i = 0; i<tb_Cafe.getItems().size(); i++) {
             tb_Cafe.getItems().clear();
               }
            //Now        
            //Now it's time to connect to database and get our data
            CueLounge cl=new CueLounge();
            cl.DBMSConnection();
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root","");
            //Now we have made connection Now it's time to retrieve data from database
            ResultSet rs=cn.createStatement().executeQuery("SELECT *\n" +"FROM `cafe_c_inf`");
            while(rs.next()){
            cafelist.add(new ModelTableCafe(rs.getString("Customer_ID"),rs.getString("C_Name"),rs.getString("P_ID_list"),rs.getString("P_Name_List"),rs.getString("Quantity"),rs.getString("Item Bill"),rs.getString("T_Bill"),rs.getString("Emp_ID_Cafe"),rs.getString("C_Date"),rs.getString("C_Time")));
            }
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null,"Error is"+ex);
        }
        
        
        //Setting cell values
           col_Customer_ID_cafe.setCellValueFactory(new PropertyValueFactory<>("Customer_ID_cafe"));
           col_C_Name_cafe.setCellValueFactory(new PropertyValueFactory<>("C_Name_cafe"));
           col_P_ID_list_Cafe.setCellValueFactory(new PropertyValueFactory<>("P_ID_list_Cafe;"));
           col_P_Name_List_cafe.setCellValueFactory(new PropertyValueFactory<>("P_Name_List_cafe"));
           col_Quantity_cafe.setCellValueFactory(new PropertyValueFactory<>("Quantity_cafe"));
           col_Itrm_unit_C_cafe.setCellValueFactory(new PropertyValueFactory<>("Itrm_unit_C_cafe"));
           col_T_Bill_cafe.setCellValueFactory(new PropertyValueFactory<>("T_Bill_cafe")); 
           col_Emp_ID_cafe.setCellValueFactory(new PropertyValueFactory<>("Emp_ID_cafe")); 
           col_Date_cafe.setCellValueFactory(new PropertyValueFactory<>("Date_cafe")); 
           col_Time_Cafe.setCellValueFactory(new PropertyValueFactory<>("Time_Cafe")); 
            //String P_ID, String Unit_cost_B, String Unit_cost_S, String B_Date, String Exp_Date, String Q_Avail, String Q_Req, String Stock_type, String Manufacturer, String time;
      //Now it's time to set ITEMS in the table
      tb_Cafe.setItems(cafelist);
      
      //Passing list to filtered list for search function
       filteredDataCafe=new FilteredList(cafelist,e->true);
        
    }

    @FXML
    private void f_upauto5(KeyEvent event) {

         //Deleting old table values son as to make or show fresh data only in table
             for ( int i = 0; i<tb_account.getItems().size(); i++) {
             tb_account.getItems().clear();
               }
             
                          try {
                   
                    
            
            //Now it's time to connect to database and get our data
            CueLounge cl=new CueLounge();
            cl.DBMSConnection();
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root","");
            //Now we have made connection Now it's time to retrieve data from database
            ResultSet rs=cn.createStatement().executeQuery("SELECT *from account");
            while(rs.next()){
                
            accountlist.add(new ModelTableAccount( CueLounge.emp_ID,rs.getString("Amount_deducted"),rs.getString("Amount_added"),rs.getString("Reason"),rs.getString("Total_Remaining"),rs.getString("Date"),rs.getString("Time")));
         
            }
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null,"Error is"+ex);
        }

        //Setting cell values
           col_Employe_ID_account.setCellValueFactory(new PropertyValueFactory<>("Employee_ID"));
           col_Amount_deducted_account.setCellValueFactory(new PropertyValueFactory<>("Amount_deducted"));
           col_Amount_added_account.setCellValueFactory(new PropertyValueFactory<>("Amount_added"));
           co_Reason_account.setCellValueFactory(new PropertyValueFactory<>("Reason"));
           col_Total_Remaining_account.setCellValueFactory(new PropertyValueFactory<>("Total_Remaining"));
           col_Date_account.setCellValueFactory(new PropertyValueFactory<>("Date"));
           col_Time_account.setCellValueFactory(new PropertyValueFactory<>("Time"));
            
           ;
            //String P_ID, String Unit_cost_B, String Unit_cost_S, String B_Date, String Exp_Date, String Q_Avail, String Q_Req, String Stock_type, String Manufacturer, String time;
      //Now it's time to set ITEMS in the table
      tb_account.setItems(accountlist);
      
      //Passing list to filtered list for search function
       filteredDataAccount=new FilteredList(accountlist,e->true);
    }

    @FXML
    private void f_upauto6(KeyEvent event) {
                      try {
             //Deleting old table values son as to make or show fresh data only in table
             for ( int i = 0; i<tb_Supplier.getItems().size(); i++) {
             tb_Supplier.getItems().clear();
               }              
            //Now it's time to connect to database and get our data
            CueLounge cl=new CueLounge();
            cl.DBMSConnection();
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root","");
            //Now we have made connection Now it's time to retrieve data from database
            ResultSet rs=cn.createStatement().executeQuery("SELECT *from suppliers");//Table was named due to mistake
            while(rs.next()){
                
            supplierlist.add(new ModelTableSupplier(rs.getString("Sr_no"),rs.getString("Supplier_ID"),rs.getString("Supplier_Name"),rs.getString("Product_type"),rs.getString("Email"),rs.getString("Phone_no"),rs.getString("Address"),rs.getString("Employee_ID"),rs.getString("timings"),rs.getString("Date"),rs.getString("Time")));
            }
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null,"Error is"+ex);
        }
        

           //Setting cell values
           col_Sr_no_supplier.setCellValueFactory(new PropertyValueFactory<>("Sr_no"));
           col_Supplier_ID_supplier.setCellValueFactory(new PropertyValueFactory<>("Supplier_ID"));
           col_Supplier_Name_supplier.setCellValueFactory(new PropertyValueFactory<>("Supplier_Name"));
           col_Product_type_supplier.setCellValueFactory(new PropertyValueFactory<>("Product_type"));
           col_Email_supplier.setCellValueFactory(new PropertyValueFactory<>("Email"));
           col_Phone_no_supplier.setCellValueFactory(new PropertyValueFactory<>("Phone_no"));
           col_Address_supplier.setCellValueFactory(new PropertyValueFactory<>("Address"));
           col_Emp_ID_supplier.setCellValueFactory(new PropertyValueFactory<>("Emp_ID"));
           col_Timings_supplier.setCellValueFactory(new PropertyValueFactory<>("Timings"));
           col_Date_supplier.setCellValueFactory(new PropertyValueFactory<>("Date"));
           col_Tijme_supplier.setCellValueFactory(new PropertyValueFactory<>("Tijme"));
           
            //String P_ID, String Unit_cost_B, String Unit_cost_S, String B_Date, String Exp_Date, String Q_Avail, String Q_Req, String Stock_type, String Manufacturer, String time;
      //Now it's time to set ITEMS in the table
      tb_Supplier.setItems(supplierlist); 
      
      //Passing list to filtered list for search function
       filteredDataSuppliers=new FilteredList(supplierlist,e->true);
    }



    @FXML
    private void f_print_mmempl(ActionEvent event) {        
        //Now generating report
        //First getting database connection
        
        try{
              // TODO
            //now suppliers could only be supplier which we have in database
            //First getting data from our database
            CueLounge cl=new CueLounge();
            cl.DBMSConnection();
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root","");
            //Now we have made connection Now it's time to retrieve data from database
            //It's time for opening jasper file
            JasperDesign jdesign=JRXmlLoader.load("H:\\Comsats 5th semester PC\\SDM\\Cue\\Cue Lounge\\src\\Reports\\report1.jrxml");
            String query="SELECT\n" +
"     employees.`Employee_ID` AS ID,\n" +
"     employees.`First_Name` AS Name,\n" +
"     employees.`CNIC` AS CNIC,\n" +
"     employees.`PHONE_NO` AS PHONE_NO,\n" +
"     employees.`CITY` AS CITY,\n" +
"     employees.`Qualification` AS Qualification,\n" +
"     employees.`Salary` AS Salary,\n" +
"     employees.`DOJ` AS DOJ,\n" +
"     employees.`Paid` AS Paid,\n" +
"     employees.`Time` AS Time,\n" +
"     employees.`GENDER` AS GENDER,\n" +
"     employees.`Post` AS Post\n" +
"FROM\n" +
"     `employees` employees";
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
    private void f_add_mmempl(ActionEvent event) {
        //It's time to add new employee
        //Calling or opening frame used for inserting employee data or for employee registration
          try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Employee_Reg.fxml"));//FXML loader class will load 2nd window
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
    private void f_remove_mmempl(ActionEvent event) {
        try {
            //First of all making connection with DBMS
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root",""); 
            CueLounge cl=new CueLounge();
            cl.DBMSConnection();
            //  Now that connection was successful it's time to remove employee or simply data from database
            //First getting id from table so we can match it with our database 
            //Creating new emply observale list
            ObservableList<ModelTable> remlist;
            //Getting values of selected rows in observable list
            remlist=tb_mm1.getSelectionModel().getSelectedItems();
            //selecting employee ID and storing it in variable
            String a=remlist.get(0).getEmp_ID();
            PreparedStatement psmt=cn.prepareStatement("DELETE FROM `cuelounge`.`employees` WHERE `employees`.`Employee_ID`=?");
            psmt.setString(1, a);//giving ? employee ID
            psmt.execute();
            lb2_mm1_emp.setText("Please refresh table!! database was modified");
            lb2_mm1_emp.setTextFill(Color.RED);
            lb2_mm1_emp.setVisible(true);
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null,"Error during removing data from database"+ex);
        }
    }

    @FXML
    private void f_update_mmempl(ActionEvent event) {
        //Now if we had to modify some table in our database i.e we had entered incorect data in our database
        //For this first opening a panel in which user enters newdata for which he want to put in table
         
        try{
             ObservableList<ModelTable> uplist;
            //Getting values of selected rows in observable list
            uplist=tb_mm1.getSelectionModel().getSelectedItems();
            // Storing data from table view so that we can show it in next frame
            a1=uplist.get(0).getEmp_ID();
            a2=uplist.get(0).getName();
            a3=uplist.get(0).getPost();
            a4=uplist.get(0).getSalary();
            a5=uplist.get(0).getDOJ();
            a6=uplist.get(0).getGender();
            a7=uplist.get(0).getMaritual_status();
            a8=uplist.get(0).getAddress();
            a9=uplist.get(0).getPaid();
            a10=uplist.get(0).getTime();
            a11=uplist.get(0).getDate();
            //For phone no getting value from database
             CueLounge cl=new CueLounge();
            cl.DBMSConnection();
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root","");
            //Now we have made connection Now it's time to retrieve data from database
            PreparedStatement ps=cn.prepareStatement("Select  *from employees WHERE Employee_ID="+a1);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                    a12=rs.getString("PHONE_NO");
            }
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("emp_tb_up.fxml"));//FXML loader class will load 2nd window
            Parent root1 = (Parent) fxmlLoader.load();//It will be stored in page root1 of parent type
            Stage stage = new Stage();//Setting a new stage
            stage.setScene(new Scene(root1));  
            stage.show();//displaying our new window
            
    }
        catch(Exception e){
        JOptionPane.showMessageDialog(null,"Error in loading signup window " +e);
    };
    
    //Now that new page has been opened values will be updated their
        
    }

   

    @FXML
    private void f_b1_print_mm2_stocks(ActionEvent event) {
        //It's time to print stocks information       
        
            //Now generating report
        //First getting database connection
        
        try{
              // TODO
            //now suppliers could only be supplier which we have in database
            //First getting data from our database
            CueLounge cl=new CueLounge();
            cl.DBMSConnection();
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root","");
            //Now we have made connection Now it's time to retrieve data from database
            //It's time for opening jasper file
            JasperDesign jdesign=JRXmlLoader.load("H:\\Comsats 5th semester PC\\SDM\\Cue\\Cue Lounge\\src\\Reports\\stocks.jrxml");
            String query="SELECT\n" +
"     stocks.`P_ID` AS P_ID,\n" +
"     stocks.`Employee_ID` AS Employee_ID,\n" +
"     stocks.`Supplier_ID` AS Supplier_ID,\n" +
"     stocks.`P_Name` AS P_Name,\n" +
"     stocks.`Unit Buying cost` AS Unit_Buying_cost,\n" +
"     stocks.`Unit cost S` AS Unit_cost_S,\n" +
"     stocks.`B_Date` AS B_Date,\n" +
"     stocks.`Exp_Date` AS Exp_Date,\n" +
"     stocks.`Q_Avail` AS Q_Avail,\n" +
"     stocks.`Stock_Type` AS Stock_Type,\n" +
"     stocks.`Manufacturer` AS Manufacturer,\n" +
"     stocks.`Time` AS Time,\n" +
"     stocks.`Date` ADate\n" +
"FROM\n" +
"     `stocks` stocks";
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
    private void f_b2_order_mm2_stocks(ActionEvent event) {
        //It's time to check previous order lists and making new order and opening our order pan
          try{
                  FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Order_Info.fxml"));//FXML loader class will load 2nd window
                  Parent root1 = (Parent) fxmlLoader.load();//It will be stored in root1 of parent type
                  Stage stage = new Stage();//Setting a new stage
                  stage.setScene(new Scene(root1));
                  stage.show();//displaying our new window
                  //order frame has been successfully called
              }
              catch(Exception e){
                  System.out.println("Error in loading signup window"+e);
              };
    }

    @FXML
    private void f_b3_remove_mm2_stocks(ActionEvent event) {
        //Now it's time to remove any stock if needed
         try {
            //First of all making connection with DBMS
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root",""); 
            CueLounge cl=new CueLounge();
            cl.DBMSConnection();
            //  Now that connection was successful it's time to remove employee or simply data from database
            //First getting id from table so we can match it with our database 
            //Creating new emply observale list
            ObservableList<ModelTableStocks> remlist;
            //Getting values of selected rows in observable list
            remlist=tb_stocks.getSelectionModel().getSelectedItems();
            //selecting employee ID and storing it in variable
            String a=remlist.get(0).getP_ID();
            PreparedStatement psmt=cn.prepareStatement("DELETE FROM `cuelounge`.`stocks` WHERE `stocks`.`P_ID`=?");
            psmt.setString(1, a);//giving ? employee ID
            psmt.execute();
            JOptionPane.showMessageDialog(null, "Table was modified!!! please refresh your table");
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null,"Error during removing data from database"+ex);
        }
    }

    @FXML
    private void f_b4_add_mm2_stocks(ActionEvent event) {
           try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ADDstck.fxml"));//FXML loader class will load 2nd window
            Parent root1 = (Parent) fxmlLoader.load();//It will be stored in root1 of parent type
            Stage stage = new Stage();//Setting a new stage
            stage.setScene(new Scene(root1));  
            stage.show();//displaying our new window
    }
        catch(Exception e){
        JOptionPane.showMessageDialog(null,"Error in loading add stocks window " +e);
    };
    }

    @FXML
    private void f_b5_update_mm2_stocks(ActionEvent event) {
           try{
               //Before opening update fxml document we want to pass previous values to that fxml document
                ObservableList<ModelTableStocks> uplist;
            //Getting values of selected rows in observable list
            uplist=tb_stocks.getSelectionModel().getSelectedItems();
            // Storing data from table view so that we can show it in next frame
            b1=uplist.get(0).getP_ID();
            b2=uplist.get(0).getP_Name();
            b3=uplist.get(0).getUnit_cost_B();
            b4=uplist.get(0).getUnit_cost_S();
            b5=uplist.get(0).getB_Date();
            b6=uplist.get(0).getExp_Date();
            b7=uplist.get(0).getEmp_ID();
            b8=uplist.get(0).getQ_Avail();
            b9=uplist.get(0).getStock_type();
            b10=uplist.get(0).getManufacturer();
            //Now loading fxml document
                  FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UpdateStock.fxml"));//FXML loader class will load 2nd window
                  Parent root1 = (Parent) fxmlLoader.load();//It will be stored in root1 of parent type
                  Stage stage = new Stage();//Setting a new stage
                  stage.setScene(new Scene(root1));
                  stage.show();//displaying our new window
                  //order frame has been successfully called
              }
              catch(Exception e){
                  System.out.println("Error in loading update stocks window"+e);
              };
        
    }

    @FXML
    private void f_b1_tablebooking_mm3(ActionEvent event) {
        //Booking table 1
          try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Games table1.fxml"));//FXML loader class will load 2nd window
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
    private void f_b2_tablebooking_mm3(ActionEvent event) {
        //Booking table 2
         try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Games table2.fxml"));//FXML loader class will load 2nd window
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
    private void f_b3_tablebooking_mm3(ActionEvent event) {
        //Booking table 3
         try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Games table3.fxml"));//FXML loader class will load 2nd window
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
    private void f_b4_tablebooking_mm3(ActionEvent event) {
        //Booking table 4
         try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Games table4.fxml"));//FXML loader class will load 2nd window
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
    private void f_b5_tablebooking_mm3(ActionEvent event) {
        //Booking table 5
         try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Games table5.fxml"));//FXML loader class will load 2nd window
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
    private void f_b2_suppliers_add(ActionEvent event) {
         try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddSupplier.fxml"));//FXML loader class will load 2nd window
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
    private void f_b1_suppliers_up(ActionEvent event) {
        //Updating supplier
           try{
               //Before opening update fxml document we want to pass previous values to that fxml document
                ObservableList<ModelTableSupplier> uplist;
            //Getting values of selected rows in observable list
            uplist=tb_Supplier.getSelectionModel().getSelectedItems();
            // Storing data from table view so that we can show it in next frame
            f0=uplist.get(0).getSr_no();
            f1=uplist.get(0).getSupplier_ID();
            f2=uplist.get(0).getSupplier_Name();
            f3=uplist.get(0).getProduct_type();
            f4=uplist.get(0).getEmail();
            f5=uplist.get(0).getPhone_no();
            f6=uplist.get(0).getAddress();
            f7=uplist.get(0).getEmp_ID();
            f8=uplist.get(0).getTimings();
            f9=uplist.get(0).getDate();
            f10=uplist.get(0).getTijme();
             //Now loading fxml document
                  FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("updateSupliers.fxml"));//FXML loader class will load 2nd window
                  Parent root1 = (Parent) fxmlLoader.load();//It will be stored in root1 of parent type
                  Stage stage = new Stage();//Setting a new stage
                  stage.setScene(new Scene(root1));
                  stage.show();//displaying our new window
                  //order frame has been successfully called
              }
              catch(Exception e){
                  System.out.println("Error in loading signup window"+e);
              };
        
    }

    @FXML
    private void b3_suppliers_rem(ActionEvent event) {
        //Now it's time to remove any stock if needed
         try {
            //First of all making connection with DBMS
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root",""); 
            CueLounge cl=new CueLounge();
            cl.DBMSConnection();
            //  Now that connection was successful it's time to remove employee or simply data from database
            //First getting id from table so we can match it with our database 
            //Creating new emply observale list
            ObservableList<ModelTableSupplier> remlist;
            //Getting values of selected rows in observable list
            remlist=tb_Supplier.getSelectionModel().getSelectedItems();
            //selecting employee ID and storing it in variable
            String a=remlist.get(0).getSupplier_ID();
            PreparedStatement psmt=cn.prepareStatement("DELETE FROM `cuelounge`.`suppliers` WHERE `suppliers`.`Supplier_ID`=?");
            psmt.setString(1, a);//giving ? employee ID
            psmt.execute();
            lb2_mm1_emp.setText("Please refresh table!! database was modified");
            lb2_mm1_emp.setTextFill(Color.RED);
            lb2_mm1_emp.setVisible(true);
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null,"Error during removing data from database"+ex);
        }
    }

    @FXML
    private void button_print_game1(ActionEvent event) {
        try{
              // TODO
            //now suppliers could only be supplier which we have in database
            //First getting data from our database
            CueLounge cl=new CueLounge();
            cl.DBMSConnection();
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root","");
            //Now we have made connection Now it's time to retrieve data from database
            //It's time for opening jasper file
            JasperDesign jdesign=JRXmlLoader.load("H:\\Comsats 5th semester PC\\SDM\\Cue\\Cue Lounge\\src\\Reports\\games.jrxml");
            String query="SELECT\n" +
"     players_info.`Table_ID` AS T_ID,\n" +
"     players_info.`P1_Name` AS P1_Name,\n" +
"     players_info.`P2_Name` AS P2_Name,\n" +
"     players_info.`Time_Started` AS Time_Started,\n" +
"     players_info.`Time_Ended` AS Time_Ended,\n" +
"     players_info.`Game_Type` AS Game_Type,\n" +
"     players_info.`Bill` AS Bill,\n" +
"     players_info.`Paid` AS Paid,\n" +
"     players_info.`Date` AS Date,\n" +
"     players_info.`Time` AS Time\n" +
"FROM\n" +
"     `players_info` players_info";
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
    private void f_b2_bill(ActionEvent event) {
        //it's time to print bill for cafe
          if(Amount_paid.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please Enter Amount paid to proceed");
        }
        else{
        String timeCafe = (java.time.LocalTime.now().toString());
        cnamecafe=txt_cname.getText();
        //Now it's time to do printing
        //Creating print data what kind od data do you want to print
        //Geetting image;
        
         bHeight = Double.valueOf(items.size());
        //JOptionPane.showMessageDialog(rootPane, bHeight);
        amount_paid_cafe=Integer.parseInt(Amount_paid.getText());
        PrinterJob pj = PrinterJob.getPrinterJob();
                
        pj.setPrintable(new L().new M(),new L().getPageFormat(pj));
        try {
             pj.print();
              
              
        }
         catch (PrinterException ex) {
                 ex.printStackTrace();
        }
        
        //Beforing clearing now we have to send data to database
         CueLounge cue=new CueLounge();
         cue.DBMSConnection();
         //Connection was successfule //time to insert
         //Creating local variable
         String local1,local2,local3,local4;
         local1="";local2="";local3="";local4="";
         try{
              CueLounge cl=new CueLounge();
            cl.DBMSConnection();
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root","");
            //calling for buiding up connection
            
            //Now it's time to insert new data
            PreparedStatement psmt = cn.prepareStatement("INSERT INTO `cuelounge`.`cafe_c_inf` ( `C_Name`, `P_ID_list`, `P_Name_List`, `Quantity`, `Item Bill`, `T_Bill`, `Emp_ID_Cafe`, `C_Date`, `C_Time`) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            psmt.setString(1, txt_cname.getText());//getting password
            for(int i=0;i<PIDcafe.size();i++){
                local1=local1+PIDcafe.get(i)+",";
            }
            psmt.setString(2, local1); 
            for(int i=0;i<items.size();i++){
                local2=local2+items.get(i)+",";
            }
            psmt.setString(3, local2);
             for(int i=0;i<quantitylist_cafe.size();i++){
                local3=local3+quantitylist_cafe.get(i)+",";
            }
            psmt.setString(4, local3);
            for(int i=0;i<unitbillscafe.size();i++){
                local4=local4+unitbillscafe.get(i)+",";
            }
            psmt.setString(5, local4);
            //For adding game types which players have played using arraylist which we have generated                                                 
            
             psmt.setString(6,txt_scost1.getText()); //Setting games
            psmt.setString(7, CueLounge.emp_ID);//phone no
            long millis=System.currentTimeMillis();  
            java.sql.Date date=new java.sql.Date(millis);               
            psmt.setString(8, date.toString());//Setting date
            psmt.setString(9, (java.time.LocalTime.now().toString()));
            //Getting system time 
            psmt.executeUpdate();
            CueLounge.t_amount=CueLounge.t_amount+Float.parseFloat(txt_scost1.getText());
        } 
         catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
         try{
              JOptionPane.showMessageDialog(null, "Customer data was entered was successful!");
            //Now updating our account
            //Updating total amount
             CueLounge cl=new CueLounge();
            cl.DBMSConnection();
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root","");
           
            PreparedStatement psmt1 = cn.prepareStatement("INSERT INTO `cuelounge`.`account` (`Employee_ID`, `Amount_deducted`, `Amount_added`, `Reason`, `Total_Remaining`, `Date`, `Time`) VALUES (?, ?, ?, ?, ?, ?, ?)");
            psmt1.setString(1,  CueLounge.emp_ID);//Seetiing employee ID of dealing person
            psmt1.setString(2, "0");
            psmt1.setString(3, txt_scost1.getText());
            psmt1.setString(4, "Items were bought from cafe");
            psmt1.setString(5, String.valueOf(CueLounge.t_amount)); 
             long millis=System.currentTimeMillis();  
            java.sql.Date date=new java.sql.Date(millis); 
            psmt1.setString(6, date.toString());//Setting date
            psmt1.setString(7, (java.time.LocalTime.now().toString()));
            psmt1.executeUpdate();
            JOptionPane.showMessageDialog(null, "Account table was updated");
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
        }
          //now it's time to clear all data
          txt_cname.setText("");
          txt_quant.setText("");
          txt_scost.setText("");
          txt_scost1.setText("");
          txt_PID.setText("");
          Amount_paid.setText("");
          items.clear();
          quantitylist_cafe.clear();
          PIDcafe.clear();
          unitbillscafe.clear();
    }

    @FXML
    private void f_b_button(ActionEvent event) {
        //Cafe items addition
        items.add(comb1_stocktype.getSelectionModel().getSelectedItem());//Getting item name
        quantitylist_cafe.add(txt_quant.getText());//Getting item quantity
        
        //Now it's time to calculate bill
            //Getting connection from database
            try {
            CueLounge cl=new CueLounge();
            cl.DBMSConnection();
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root","");
            //calling for buiding up connection
            PreparedStatement psmt0 = cn.prepareStatement("Select * from `cuelounge`.stocks`");
            
            ResultSet Rs=psmt0.executeQuery();
            while(Rs.next()){
                if(Rs.getString("P_Name").equalsIgnoreCase(comb1_stocktype.getSelectionModel().getSelectedItem())){
                   PreparedStatement psmt1=cn.prepareStatement("UPDATE `cuelounge`.`stocks` SET `Q_Avail` = ? WHERE `stocks`.`P_ID` =?");
                   psmt1.setString(1, String.valueOf(Integer.parseInt(Rs.getString("Q_Avail"))-(Integer.parseInt(txt_quant.getText()))));
                   psmt1.setString(2, Rs.getString("P_ID"));
                   psmt1.executeUpdate();
                   
                   //Now setting price
                   txt_PID.setText(Rs.getString("P_ID"));
                   txt_scost.setText(Rs.getString("Unit cost S"));
                   unitbillscafe.add(Rs.getString("Unit cost S")+",");
                }
            }
            PIDcafe.add(txt_PID.getText());
            bill_cafe=bill_cafe+Integer.parseInt(txt_scost.getText())*Integer.parseInt(txt_quant.getText());
            txt_scost1.setText(String.valueOf(bill_cafe));
          
        } catch (SQLException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        }
    

    @FXML
    private void f_b1_print_cafe(ActionEvent event) {
        //Print button for cafe table
           try{
              // TODO
            //now suppliers could only be supplier which we have in database
            //First getting data from our database
            CueLounge cl=new CueLounge();
            cl.DBMSConnection();
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root","");
            //Now we have made connection Now it's time to retrieve data from database
            //It's time for opening jasper file
            JasperDesign jdesign=JRXmlLoader.load("H:\\Comsats 5th semester PC\\SDM\\Cue\\Cue Lounge\\src\\Reports\\CafeDetails.jrxml");
            String query="SELECT\n" +
"     cafe_c_inf.`Customer_ID` AS cafe_c_inf_Customer_ID,\n" +
"     cafe_c_inf.`C_Name` AS cafe_c_inf_C_Name,\n" +
"     cafe_c_inf.`P_ID_list` AS cafe_c_inf_P_ID_list,\n" +
"     cafe_c_inf.`P_Name_List` AS cafe_c_inf_P_Name_List,\n" +
"     cafe_c_inf.`Quantity` AS cafe_c_inf_Quantity,\n" +
"     cafe_c_inf.`Item Bill` AS cafe_c_inf_Item_Bill,\n" +
"     cafe_c_inf.`T_Bill` AS cafe_c_inf_T_Bill,\n" +
"     cafe_c_inf.`Emp_ID_Cafe` AS cafe_c_inf_Emp_ID_Cafe,\n" +
"     cafe_c_inf.`C_Date` AS cafe_c_inf_C_Date,\n" +
"     cafe_c_inf.`C_Time` AS cafe_c_inf_C_Time\n" +
"FROM\n" +
"     `cafe_c_inf` cafe_c_inf";
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
    private void f_b1_report_account(ActionEvent event) {
        //Generating account report
         try{
              // TODO
            //now suppliers could only be supplier which we have in database
            //First getting data from our database
            CueLounge cl=new CueLounge();
            cl.DBMSConnection();
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root","");
            //Now we have made connection Now it's time to retrieve data from database
            //It's time for opening jasper file
            JasperDesign jdesign=JRXmlLoader.load("H:\\Comsats 5th semester PC\\SDM\\Cue\\Cue Lounge\\src\\Reports\\AccountDetails.jrxml");
            String query="SELECT\n" +
"     account.`Account_ID` AS Account_ID,\n" +
"     account.`Employee_ID` AS Emp_ID,\n" +
"     account.`Amount_deducted` AS Amount_deducted,\n" +
"     account.`Amount_added` AS Amount_added,\n" +
"     account.`Reason` AS Reason,\n" +
"     account.`Total_Remaining` AS Total_Remaining,\n" +
"     account.`Date` AS Date,\n" +
"     account.`Time` AS Time\n" +
"FROM\n" +
"     `account` account";
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
    private void f_b1_print_suppliers(ActionEvent event) {
        //printing suppliers information
          try{
              // TODO
            //now suppliers could only be supplier which we have in database
            //First getting data from our database
            CueLounge cl=new CueLounge();
            cl.DBMSConnection();
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root","");
            //Now we have made connection Now it's time to retrieve data from database
            //It's time for opening jasper file
            JasperDesign jdesign=JRXmlLoader.load("H:\\Comsats 5th semester PC\\SDM\\Cue\\Cue Lounge\\src\\Reports\\supplier_information.jrxml");
            String query="SELECT\n" +
"     suppliers.`Supplier_ID` AS ID,\n" +
"     suppliers.`Employee_ID` AS Emp_ID,\n" +
"     suppliers.`Supplier_Name` AS S_Name,\n" +
"     suppliers.`Product_type` AS  Product_type,\n" +
"     suppliers.`Email` AS Email,\n" +
"     suppliers.`Phone_no` AS Ph,\n" +
"     suppliers.`Address` AS Address,\n" +
"     suppliers.`timings` AS timings,\n" +
"     suppliers.`Date` AS  Date,\n" +
"     suppliers.`Time` AS Time,\n" +
"     suppliers.`CEO` AS CEO,\n" +
"     suppliers.`Focal_P` AS Focal_P\n" +
"FROM\n" +
"     `suppliers` suppliers";
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
    private void f_lb_logout_bt(MouseEvent event) {
        //This button is for logging out from main menu to login screen
            try{
                  Stage s=(Stage) anc_mainmenu.getScene().getWindow();
                  s.close();
                  FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));//FXML loader class will load 2nd window
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
    private void f_button_update_games(ActionEvent event) {
        //this function is for calling update games frame
        try{
               //Before opening update fxml document we want to pass previous values to that fxml document
                ObservableList<ModelTableGames> uplist;
            //Getting values of selected rows in observable list
            uplist=tb_games.getSelectionModel().getSelectedItems();
            // Storing data from table view so that we can show it in next frame
            c0=uplist.get(0).getSr_no_var();
            c1=uplist.get(0).getP1_Name();
            c2=uplist.get(0).getP2_Name();
            c3=uplist.get(0).getTime_Started();
            c4=uplist.get(0).getTime_Ended();
            c5=uplist.get(0).getGame_Type();
            c6=uplist.get(0).getBill();
            c7=uplist.get(0).getG_Paid();
            c8=uplist.get(0).getDategames();
            //Now loading fxml document
                  FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UpdateGameTable.fxml"));//FXML loader class will load 2nd window
                  Parent root1 = (Parent) fxmlLoader.load();//It will be stored in root1 of parent type
                  Stage stage = new Stage();//Setting a new stage
                  stage.setScene(new Scene(root1));
                  stage.show();//displaying our new window
                  //order frame has been successfully called
              }
              catch(Exception e){
                  System.out.println("Error in loading signup window"+e);
              };
    }

    @FXML
    private void f_button_remove_games(ActionEvent event) {
        //Removing any game detail from database if required
        try {
            //First of all making connection with DBMS
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root",""); 
            CueLounge cl=new CueLounge();
            cl.DBMSConnection();
            //  Now that connection was successful it's time to remove employee or simply data from database
            //First getting id from table so we can match it with our database 
            //Creating new emply observale list
            ObservableList<ModelTableGames> remlist;
            //Getting values of selected rows in observable list
            remlist=tb_games.getSelectionModel().getSelectedItems();
            //selecting employee ID and storing it in variable
            String a=remlist.get(0).getSr_no_var();
            PreparedStatement psmt=cn.prepareStatement("DELETE FROM `cuelounge`.`players_info` WHERE `players_info`.`sr_no`=?");
            psmt.setString(1, a);//giving ? employee ID
            psmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Please refresh game table!! Database was modified");
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null,"Error during removing data from database"+ex);
        }
    }

    @FXML
    private void f_b3_remove_cafe(ActionEvent event) {
    }

    @FXML
    private void f_b2_update_cafe(ActionEvent event) {
        //this function is for updating cafe values if any mistake was made
        //First setting values which is to be tranferred from this frame to next frame
           try{
               //Before opening update fxml document we want to pass previous values to that fxml document
                ObservableList<ModelTableCafe> uplist;
            //Getting values of selected rows in observable list
            uplist=tb_Cafe.getSelectionModel().getSelectedItems();
            // Storing data from table view so that we can show it in next frame
            d1=uplist.get(0).getCustomer_ID_cafe();
            d2=uplist.get(0).getC_Name_cafe();
            d3=uplist.get(0).getP_ID_list_Cafe();
            d4=uplist.get(0).getP_Name_List_cafe();
            d5=uplist.get(0).getQuantity_cafe();
            d6=uplist.get(0).getItrm_unit_C_cafe();
            d7=uplist.get(0).getT_Bill_cafe();
            d8=uplist.get(0).getEmp_ID_cafe();
            d9=uplist.get(0).getDate_cafe();
            d10=uplist.get(0).getTime_Cafe();
            //Now loading fxml document
                  FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("updateCafe.fxml"));//FXML loader class will load 2nd window
                  Parent root1 = (Parent) fxmlLoader.load();//It will be stored in root1 of parent type
                  Stage stage = new Stage();//Setting a new stage
                  stage.setScene(new Scene(root1));
                  stage.show();//displaying our new window
                  //order frame has been successfully called
              }
              catch(Exception e){
                  System.out.println("Error in loading signup window"+e);
              };
        
    }

    @FXML
    private void f_b2_update_Account(ActionEvent event) {
         //Opening FXML frame wich would be used to update account if any mistake was made 
        //First preparing values to be passed  from this frame to next frame
          try{
               //Before opening update fxml document we want to pass previous values to that fxml document
                ObservableList<ModelTableAccount> uplist;
            //Getting values of selected rows in observable list
            uplist=tb_account.getSelectionModel().getSelectedItems();
            // Storing data from table view so that we can show it in next frame
            e1=uplist.get(0).getAmount_deducted();
            e2=uplist.get(0).getAmount_added();
            e3=uplist.get(0).getReason();
            e4=uplist.get(0).getTotal_Remaining();
            e5=uplist.get(0).getDate();
            e6=uplist.get(0).getTime();

            //Now loading fxml document
                  FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("updateAccount.fxml"));//FXML loader class will load 2nd window
                  Parent root1 = (Parent) fxmlLoader.load();//It will be stored in root1 of parent type
                  Stage stage = new Stage();//Setting a new stage
                  stage.setScene(new Scene(root1));
                  stage.show();//displaying our new window
                  //order frame has been successfully called
              }
              catch(Exception e){
                  System.out.println("Error in loading signup window"+e);
              };
    }
    //now applying search filters to all search related textfields
    //Employee Table
    @FXML
    private void tf1_search_emp_tb(KeyEvent event) {
  //Doing some error handling table maya be empty so value may not be searched in this case
        try{
        tf_filter.textProperty().addListener((observable, oldValue,newValue)-> {
           filteredDataempl.setPredicate((Predicate<? super ModelTable>)(ModelTable mt)->{
              
                
              if(newValue.isEmpty() || newValue==null){
                  return true;
              }     
              else if(mt.getEmp_ID().contains(newValue)){
                  return true;
              }
              else if(mt.getName().contains(newValue)){
                  return true;
              }
              else if(mt.getPost().contains(newValue)){
                  return true;
              }
              else if(mt.getSalary().contains(newValue)){
                  return true;
              }
              else if(mt.getDOJ().contains(newValue)){
                  return true;
              }
              else if(mt.getMaritual_status().contains(newValue)){
                  return true;
              }
              else if(mt.getAddress().contains(newValue)){
                  return true;
              }
              else if(mt.getPaid().contains(newValue)){
                  return true;
              }
              else if(mt.getTime().contains(newValue)){
                  return true;
              }
              else if(mt.getDate().contains(newValue)){
                  return true;
              }
                   
                   
                   
              return  false;
              
        }); 
        });
        //Now time for Sorted list to come into action
        SortedList sort=new SortedList(filteredDataempl);
        sort.comparatorProperty().bind(tb_mm1.comparatorProperty());
        tb_mm1.setItems(sort);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Table is empty!!! Search not possible");
        }
}
    //Stocks table
     @FXML
    private void f_tf1_mm2_stocks(KeyEvent event) {
        try{
         tf1_mm2_stocks.textProperty().addListener((observable, oldValue,newValue)-> {
           filteredDataStocks.setPredicate((Predicate<? super ModelTableStocks>)(ModelTableStocks mts)->{
              if(newValue.isEmpty() || newValue==null){
                  return true;
              }     
              else if(mts.getP_ID().contains(newValue)){
                  return true;
              }
              else if(mts.getP_Name().contains(newValue)){
                  return true;
              }
              else if(mts.getUnit_cost_B().contains(newValue)){
                  return true;
              }
              else if(mts.getUnit_cost_S().contains(newValue)){
                  return true;
              }
              else if(mts.getB_Date().contains(newValue)){
                  return true;
              }
              else if(mts.getExp_Date().contains(newValue)){
                  return true;
              }
              else if(mts.getQ_Avail().contains(newValue)){
                  return true;
              }
              else if(mts.getStock_type().contains(newValue)){
                  return true;
              }
              else if(mts.getEmp_ID().contains(newValue)){
                  return true;
              }
              else if(mts.getManufacturer().contains(newValue)){
                  return true;
              }
              else if(mts.getTimestocks().contains(newValue)){
                  return true;
              }
                   
                   
                   
              return  false;     
        }); 
        });
        //Now time for Sorted list to come into action
        SortedList sort=new SortedList(filteredDataStocks);
        sort.comparatorProperty().bind(tb_stocks.comparatorProperty());
        tb_stocks.setItems(sort);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Table is empty!!! Search not possible");
        }
    }
    //Games table
    @FXML
    private void f_tf1_search_gametb(KeyEvent event) {
        try{
         tf1_search_gametb.textProperty().addListener((observable, oldValue,newValue)-> {
           filteredDataGames.setPredicate((Predicate<? super ModelTableGames>)(ModelTableGames mtg)->{
              if(newValue.isEmpty() || newValue==null){
                  return true;
              }     
              else if(mtg.getSr_no_var().contains(newValue)){
                  return true;
              }
              else if(mtg.getP1_Name().contains(newValue)){
                  return true;
              }
              else if(mtg.getP2_Name().contains(newValue)){
                  return true;
              }
              else if(mtg.getTable_ID().contains(newValue)){
                  return true;
              }
              else if(mtg.getTime_Started().contains(newValue)){
                  return true;
              }
              else if(mtg.getTime_Ended().contains(newValue)){
                  return true;
              }
              else if(mtg.getGame_Type().contains(newValue)){
                  return true;//Above is gamelist
              }
              else if(mtg.getBill().contains(newValue)){
                  return true;
              }
              else if(mtg.getG_Paid().contains(newValue)){
                  return true;
              }
              else if(mtg.getDategames().contains(newValue)){
                  return true;
              }
              else if(mtg.getTimegames().contains(newValue)){
                  return true;
              }
                   
                   
                   
              return  false;     
        }); 
        });
        //Now time for Sorted list to come into action
        SortedList sort=new SortedList(filteredDataGames);
        sort.comparatorProperty().bind(tb_games.comparatorProperty());
        tb_games.setItems(sort);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Table is empty!!! Search not possible");
        }
    }
        
         
    
   
    //Cafe table
    @FXML
    private void f_tf1_search_cafetb(KeyEvent event) {
        try{
         tf1_search_cafetb.textProperty().addListener((observable, oldValue,newValue)-> {
           filteredDataCafe.setPredicate((Predicate<? super ModelTableCafe>)(ModelTableCafe mtc)->{
              if(newValue.isEmpty() || newValue==null){
                  return true;
              }     
              else if(mtc.getCustomer_ID_cafe().contains(newValue)){
                  return true;
              }
              else if(mtc.getC_Name_cafe().contains(newValue)){
                  return true;
              }
              else if(mtc.getP_ID_list_Cafe().contains(newValue)){
                  return true;
              }
              else if(mtc.getP_Name_List_cafe().contains(newValue)){
                  return true;
              }
              else if(mtc.getQuantity_cafe().contains(newValue)){
                  return true;
              }
              else if(mtc.getItrm_unit_C_cafe().contains(newValue)){
                  return true;
              }
              else if(mtc.getT_Bill_cafe().contains(newValue)){
                  return true;//Above is gamelist
              }
              else if(mtc.getEmp_ID_cafe().contains(newValue)){
                  return true;
              }
              else if(mtc.getDate_cafe().contains(newValue)){
                  return true;
              }
              else if(mtc.getTime_Cafe().contains(newValue)){
                  return true;
              }
             
                   
                   
                   
              return  false;     
        }); 
        });
        //Now time for Sorted list to come into action
        SortedList sort=new SortedList(filteredDataCafe);
        sort.comparatorProperty().bind(tb_Cafe.comparatorProperty());
        tb_Cafe.setItems(sort);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Table is empty!!! Search not possible");
        }
    }
    
    //Account
    @FXML
    private void f_tf1_search_accoynttb(KeyEvent event) {
        try{
       tf1_search_accoynttb.textProperty().addListener((observable, oldValue,newValue)-> {
           filteredDataAccount.setPredicate((Predicate<? super ModelTableAccount>)(ModelTableAccount mta)->{
              if(newValue.isEmpty() || newValue==null){
                  return true;
              }     
              else if(mta.getEmployee_ID().contains(newValue)){
                  return true;
              }
              else if(mta.getAmount_deducted().contains(newValue)){
                  return true;
              }
              else if(mta.getAmount_added().contains(newValue)){
                  return true;
              }
              else if(mta.getReason().contains(newValue)){
                  return true;
              }
              else if(mta.getTotal_Remaining().contains(newValue)){
                  return true;
              }
              else if(mta.getDate().contains(newValue)){
                  return true;
              }
              else if(mta.getTime().contains(newValue)){
                  return true;//Above is gamelist
              }
             
                   
              return  false;     
        }); 
        });
        //Now time for Sorted list to come into action
        SortedList sort=new SortedList(filteredDataAccount);
        sort.comparatorProperty().bind(tb_account.comparatorProperty());
        tb_account.setItems(sort);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Table is empty!!! Search not possible");
        }
    }
   
    //Suppliers
    @FXML
    private void tf1_search_suppliers(KeyEvent event) {
        try{
         tf1_search_suppliers.textProperty().addListener((observable, oldValue,newValue)-> {
           filteredDataSuppliers.setPredicate((Predicate<? super ModelTableSupplier>)(ModelTableSupplier mts)->{
              if(newValue.isEmpty() || newValue==null){
                  return true;
              }     
              else if(mts.getSr_no().contains(newValue)){
                  return true;
              }
              else if(mts.getSupplier_ID().contains(newValue)){
                  return true;
              }
              else if(mts.getSupplier_Name().contains(newValue)){
                  return true;
              }
              else if(mts.getProduct_type().contains(newValue)){
                  return true;
              }
              else if(mts.getPhone_no().contains(newValue)){
                  return true;
              }
              else if(mts.getEmail().contains(newValue)){
                  return true;
              }
              else if(mts.getAddress().contains(newValue)){
                  return true;//Above is gamelist
              }
              else if(mts.getEmp_ID().contains(newValue)){
                  return true;
              }
              else if(mts.getTimings().contains(newValue)){
                  return true;
              }
              else if(mts.getDate().contains(newValue)){
                  return true;
              }
                 else if(mts.getTijme().contains(newValue)){
                  return true;
              }
             
                   
                   
                   
              return  false;     
        }); 
        });
        //Now time for Sorted list to come into action
        SortedList sort=new SortedList(filteredDataSuppliers);
        sort.comparatorProperty().bind(tb_Supplier.comparatorProperty());
        tb_Supplier.setItems(sort);
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, "Table is empty!!! Search not possible");
    }
    
}

    @FXML
    private void f_b6_tablebooking_mm3(ActionEvent event) {
        //this is for fooseball table booking
        try{
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Fooseball.fxml"));//FXML loader class will load 2nd window
                  Parent root1 = (Parent) fxmlLoader.load();//It will be stored in root1 of parent type
                  Stage stage = new Stage();//Setting a new stage
                  stage.setScene(new Scene(root1));
                  stage.show();//displaying our new window
                  //order frame has been successfully called
              }
              catch(Exception e){
                  System.out.println("Error in loading signup window"+e);
              };
    }
}
 

//Now it's time to do printer work
class L extends javax.swing.JFrame{
   //Only extending JFRAME
      MainMenuController  mmc=new MainMenuController();
      public PageFormat getPageFormat(PrinterJob pj)
{
    
    PageFormat pf = pj.defaultPage();
    Paper paper = pf.getPaper();    

    double bodyHeight = mmc.bHeight;  
    double headerHeight = 10;                  
    double footerHeight = 10;        
    double width = cm_to_pp(15); 
    double height = cm_to_pp(headerHeight+bodyHeight+footerHeight); 
    paper.setSize(width, height);
    paper.setImageableArea(-5,10,width,height - cm_to_pp(1));  
            
    pf.setOrientation(PageFormat.PORTRAIT);  
    pf.setPaper(paper);    

    return pf;
}
   
    
    
    protected static double cm_to_pp(double cm)
    {            
	        return toPPI(cm * 0.393600787);            
    }
 
protected static double toPPI(double inch)
    {            
	        return inch * 72d;            
    }
    class M implements Printable{
      long millis=System.currentTimeMillis();  
            java.sql.Date date=new java.sql.Date(millis);
        @Override
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {

           int r=MainMenuController.items.size();
      ImageIcon icon=new ImageIcon("H:\\Comsats 5th semester PC\\SDM\\Cue\\Cue Lounge\\src\\cue\\WhatsApp Image 2019-12-04 at 10.16.28 AM.jpeg"); 
      int result = NO_SUCH_PAGE;    
        if (pageIndex == 0) {                    
        
            Graphics2D g2d = (Graphics2D) graphics;                    
            double width = pageFormat.getImageableWidth();                               
            g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY()); 



          //  FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));
        
        try{
            int y=20;
            int yShift = 10;
            int headerRectHeight=15;
           // int headerRectHeighta=40;
            
                
            g2d.setFont(new Font("Monospaced",Font.PLAIN,9));
            g2d.drawImage(icon.getImage(), 50, 20, 90, 30, rootPane);y+=yShift+30;
            g2d.drawString("-------------------------------------",12,y);y+=yShift;
            g2d.drawString("             CUE LOUNGE        ",12,y);y+=yShift;
            g2d.drawString("    Cue lounge snooker Club near ",12,y);y+=yShift;
            g2d.drawString(" COMSATS university, Sahiwal campus ",12,y);y+=yShift;
            g2d.drawString("   www.facebook.com/cuelounge ",12,y);y+=yShift;
            g2d.drawString("         +923056896696      ",12,y);y+=yShift;
            g2d.drawString("-------------------------------------",12,y);y+=headerRectHeight;
             g2d.drawString("Customer Name:"+MainMenuController.cnamecafe,12,y);y+=yShift;
            g2d.drawString("-------------------------------------",10,y);y+=headerRectHeight;
            g2d.drawString(" Products               Cost   ",10,y);y+=yShift;
            g2d.drawString("-------------------------------------",10,y);y+=headerRectHeight;
          
           for(int i=0;i<r;i++){
              g2d.drawString(""+MainMenuController.items.get(i)+"("+MainMenuController.quantitylist_cafe.get(i)+")"+"                 "+MainMenuController.unitbillscafe.get(i)                                 ,10,y);y+=yShift;
            g2d.drawString("                          ",10,y); g2d.drawString("",160,y);y+=yShift;

           }   
            g2d.drawString("-------------------------------------",10,y);y+=yShift;
            g2d.drawString(" Total amount:               "+MainMenuController.bill_cafe+"   ",10,y);y+=yShift;
            g2d.drawString("-------------------------------------",10,y);y+=yShift;
            g2d.drawString(" Cash      :                 "+MainMenuController.amount_paid_cafe+"   ",10,y);y+=yShift;
            g2d.drawString("-------------------------------------",10,y);y+=yShift;
            g2d.drawString(" Cashback   :                 "+String.valueOf(MainMenuController.amount_paid_cafe-MainMenuController.bill_cafe)+"   ",10,y);y+=yShift;
  
            g2d.drawString("*************************************",10,y);y+=yShift;
            g2d.drawString("   THANK YOU COME AGAIN            ",10,y);y+=yShift;
            g2d.drawString("*************************************",10,y);y+=yShift;
            g2d.drawString("      SOFTWARE BY:AAHT          ",10,y);y+=yShift;
            g2d.drawString("   CONTACT: aaht@gmail.com       ",10,y);y+=yShift;       
            g2d.drawString("*************************************",10,y);y+=yShift;
            g2d.drawString("Dated"+ date.toString() ,10,y);y+=yShift;
           
    }
    catch(Exception e){
    e.printStackTrace();
    }

              result = PAGE_EXISTS;    
          }    
          return result;    
      }  
        }

}


   

