/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cue.lounge;

import com.jfoenix.controls.JFXTextField;
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
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Talha
 */
public class GamesTable4 implements Initializable {

    @FXML
    private AnchorPane anc_gametable4;
    @FXML
    private Button b3_add_gametable4;
    @FXML
    private ChoiceBox<String> ch1_gametyp_gametable4;
    @FXML
    private Button b4_cancel_gametable4;
    @FXML
    private JFXTextField tf1_P1name_gametable4;
    @FXML
    private JFXTextField tf2_P2name_gametable4;
    @FXML
    private JFXTextField tf4_starttime_gametable4;
    @FXML
    private JFXTextField tf1_endtime_gametable4;
    @FXML
    private Button b1_start_gametable4;
    @FXML
    private Button b2_end_gametable4;
    @FXML
    private JFXTextField tf6_bill_gametable4;
    @FXML
    private JFXTextField tf3_quant_gametable4;
    @FXML
    private JFXTextField tf7_bill_gametable4;
    
     //Declaring variables
    public static int i=1;//for calculating calculating quantity of each game played
    public static float bill=0;
    String prevgame;
    String nextgame;
    float prevgamebill;//variable which will store previous game type bill
    public static float amountpaid;
    String listDB="";
    public static String p1n,p2n;
    
    static ArrayList <String> gamelist=new ArrayList<>();
    static ArrayList <String> quantitylist=new ArrayList<>();
    public double bHeight=0.0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         // TODO
              //Now for secound one which is order status 
          ObservableList<String> dbOrderStatus = FXCollections.observableArrayList("Solids and Stripes","Straight Pool","Carom","Snooker","The Queen’s Game ");
          ch1_gametyp_gametable4.setItems(dbOrderStatus);
    }    

    @FXML
    private void f_b3_add_gametable4(ActionEvent event) {
            //It's time to add 
        //Bill is calculated base on number of games
     
        nextgame=ch1_gametyp_gametable4.getSelectionModel().getSelectedItem();
        if(nextgame.equals(prevgame)){
        i=i+1;
        bill=bill+(prevgamebill);
        //Only adding here when repetition occurs
        gamelist.add(ch1_gametyp_gametable4.getSelectionModel().getSelectedItem());
        quantitylist.add(tf3_quant_gametable4.getText());
        tf3_quant_gametable4.setText(String.valueOf(i));
        tf6_bill_gametable4.setText(String.valueOf(bill));
                      
    }

        else{        
   
            //now if game is different from previous games then 
            //Making game counter =1
            i=1;
            switch(nextgame){
                case "Solids and Stripes":
                 prevgame=nextgame;
                 //So we can se it if user selects this game other time
                 bill=bill+(100);
                tf3_quant_gametable4.setText(String.valueOf(i));
        tf6_bill_gametable4.setText(String.valueOf(bill));
        gamelist.add(nextgame);
        quantitylist.add(String.valueOf(i));
                 prevgamebill=100;
                 break;
                 
                case "Straight Pool":
                 prevgame=nextgame;
                 //So we can se it if user selects this game other time
                 bill=bill+(120);
                  tf3_quant_gametable4.setText(String.valueOf(i));
        tf6_bill_gametable4.setText(String.valueOf(bill));
           gamelist.add(nextgame);
        quantitylist.add(String.valueOf(i));
 
                 prevgamebill=120;
                 break;
                 
                   case "Carom":
                 prevgame=nextgame;
                 //So we can se it if user selects this game other time
                 bill=bill+(140);
                    tf3_quant_gametable4.setText(String.valueOf(i));
        tf6_bill_gametable4.setText(String.valueOf(bill));
        gamelist.add(nextgame);
        quantitylist.add(String.valueOf(i));
 
                 prevgamebill=140;
                 break;
                 
                  case "Snooker":
                 prevgame=nextgame;
                 //So we can se it if user selects this game other time
                 bill=bill+(160);
                                 tf3_quant_gametable4.setText(String.valueOf(i));
        tf6_bill_gametable4.setText(String.valueOf(bill));
       gamelist.add(nextgame);
        quantitylist.add(String.valueOf(i));
 
       
                 prevgamebill=160;
                 break;
                 
                 case "The Queen’s Game ":
                 prevgame=nextgame;
                 //So we can se it if user selects this game other time
                 bill=bill+(120);
                                 tf3_quant_gametable4.setText(String.valueOf(i));
        tf6_bill_gametable4.setText(String.valueOf(bill));
               gamelist.add(nextgame);
        quantitylist.add(String.valueOf(i));
 
                 prevgamebill=120;
                 break;
            }
        }
    }

    @FXML
    private void f_b4_cancel_gametable4(ActionEvent event) {
          //If user exits in between game then closing without saving all work
        //if user cancel his decision for update then
        //if user name and password is correct then close login window and open main menu window
        //Before that asking or prompting user he will lost all his saved work
        int reply = JOptionPane.showConfirmDialog(null, "Do you want to exit ? All your work will be lost", "", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
                  Stage s=(Stage) anc_gametable4.getScene().getWindow();
                  s.close();
        }
        else {
           JOptionPane.showMessageDialog(null, "Thank you for continuing");
        }
    }

    @FXML
    private void f_b1_start_gametable4(ActionEvent event) {
          String sttarttime = (java.time.LocalTime.now().toString());// Starting counting time when player started to play
        //Time counting has been started
        //We have stored current time
        //Setting start time text field
        tf4_starttime_gametable4.setText(sttarttime);
        //Storing name of players in variables
        p1n=tf1_P1name_gametable4.getText();
        p2n=tf2_P2name_gametable4.getText();
    }

    @FXML
    private void f_b2_end_gametable4(ActionEvent event) {
        if(tf7_bill_gametable4.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please Enter Amount paid to proceed");
        }
        else{
           
        
           String totalendtime = (java.time.LocalTime.now().toString());
        tf1_endtime_gametable4.setText(totalendtime);
        
        //Now it's time to do printing
        //Creating print data what kind od data do you want to print
        //Geetting image;
        
         bHeight = Double.valueOf(gamelist.size());
        //JOptionPane.showMessageDialog(rootPane, bHeight);
        amountpaid=Float.parseFloat(tf7_bill_gametable4.getText());
        PrinterJob pj = PrinterJob.getPrinterJob();
                
        pj.setPrintable(new G().new H(),new G().getPageFormat(pj));
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
         try{
              CueLounge cl=new CueLounge();
            cl.DBMSConnection();
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root","");
            //calling for buiding up connection
            
            //Now it's time to insert new data
            PreparedStatement psmt = cn.prepareStatement("INSERT INTO `cuelounge`.`players_info` (`Table_ID`, `P2_Name`, `P1_Name`, `Time_Started`, `Time_Ended`, `Game_Type`, `Bill`, `Paid`, `Date`, `Time`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            psmt.setString(1, "0004");//getting password
            psmt.setString(2, tf2_P2name_gametable4.getText()); 
            psmt.setString(3, tf1_P1name_gametable4.getText());
            psmt.setString(4, tf4_starttime_gametable4.getText()); 
            psmt.setString(5, tf1_endtime_gametable4.getText());
            //For adding game types which players have played using arraylist which we have generated                                                 
            for(int count=0;count<gamelist.size();count++){
            listDB=listDB+gamelist.get(count)+",";
         }                              
             psmt.setString(6,listDB); //Setting games
            psmt.setString(7,tf6_bill_gametable4.getText());//phone no
            psmt.setString(8, "Yes");

            long millis=System.currentTimeMillis();  
            java.sql.Date date=new java.sql.Date(millis);               
            psmt.setString(9, date.toString());//Setting date
            psmt.setString(10, (java.time.LocalTime.now().toString()));
            //Getting system time 
            psmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Players data was entered was successful!");
            //Now updating our account
            //Updating total amount
            CueLounge.t_amount=CueLounge.t_amount+bill;
            PreparedStatement psmt1 = cn.prepareStatement("INSERT INTO `cuelounge`.`account` (`Employee_ID`, `Amount_deducted`, `Amount_added`, `Reason`, `Total_Remaining`, `Date`, `Time`) VALUES (?, ?, ?, ?, ?, ?, ?);");
            psmt1.setString(1,  CueLounge.emp_ID);//Seetiing employee ID of dealing person
            psmt1.setString(2, "0");
            psmt1.setString(3, String.valueOf(bill));
            psmt1.setString(4,"Games were played on table 0004 by "+p1n+" and "+p2n);
            psmt1.setString(5, String.valueOf(CueLounge.t_amount));              
            psmt1.setString(6, date.toString());//Setting date
            psmt1.setString(7, (java.time.LocalTime.now().toString()));
            psmt1.executeUpdate();
        } 
         catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
    }
    }
}
   //Now it's time to do printer work
class G extends javax.swing.JFrame{
   //Only extending JFRAME
      GamesTable4  gmp=new GamesTable4();
      public PageFormat getPageFormat(PrinterJob pj)
{
    
    PageFormat pf = pj.defaultPage();
    Paper paper = pf.getPaper();    

    double bodyHeight = gmp.bHeight;  
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
    class H implements Printable{
      long millis=System.currentTimeMillis();  
            java.sql.Date date=new java.sql.Date(millis);
        @Override
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {

           int r=GamesTable4.gamelist.size();
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
             g2d.drawString("Player 1: "+GamesTable4.p1n+" Player 2: "+GamesTable4.p2n+"",12,y);y+=yShift;
            g2d.drawString("-------------------------------------",10,y);y+=headerRectHeight;
            g2d.drawString(" Games                   ",10,y);y+=yShift;
            g2d.drawString("-------------------------------------",10,y);y+=headerRectHeight;
          
           for(int i=0;i<r;i++){
              g2d.drawString(""+GamesTable4.gamelist.get(i)+" "+"                            ",10,y);y+=yShift;
            g2d.drawString("                          ",10,y); g2d.drawString("",160,y);y+=yShift;

           }   
            g2d.drawString("-------------------------------------",10,y);y+=yShift;
            g2d.drawString(" Total amount:               "+String.valueOf(GamesTable4.bill)+"   ",10,y);y+=yShift;
            g2d.drawString("-------------------------------------",10,y);y+=yShift;
            g2d.drawString(" Cash      :                 "+String.valueOf(GamesTable4.amountpaid)+"   ",10,y);y+=yShift;
            g2d.drawString("-------------------------------------",10,y);y+=yShift;
            g2d.drawString(" Cashback   :                 "+String.valueOf(GamesTable4.amountpaid-GamesTable4.bill)+"   ",10,y);y+=yShift;
  
            g2d.drawString("*************************************",10,y);y+=yShift;
            g2d.drawString("   THANK YOU COME AGAIN            ",10,y);y+=yShift;
            g2d.drawString("*************************************",10,y);y+=yShift;
            g2d.drawString("      SOFTWARE BY:AAHT          ",10,y);y+=yShift;
            g2d.drawString("   CONTACT: aaht@gmail.com       ",10,y);y+=yShift;       
            g2d.drawString("*************************************",10,y);y+=yShift;
            g2d.drawString("Dated: "+ date.toString() ,10,y);y+=yShift;
           
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


