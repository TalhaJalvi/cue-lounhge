/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cue.lounge;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Talha
 */
public class LoginController implements Initializable {

    @FXML
    private AnchorPane anc_login;
    @FXML
    private JFXTextField tf_sn1;
    @FXML
    private JFXPasswordField ps_sp1;
    @FXML
    private JFXButton b_fgpass;
    @FXML
    private Button b_SignIn;
    @FXML
    private JFXButton lb_ab;
    @FXML
    public  Button b_sgnup;
    @FXML
    private JFXHamburger hamburger_login;
    @FXML
    private JFXDrawer drawer_login;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            // TODO
            //Setting VBOX in or drawer
            VBox box=FXMLLoader.load(getClass().getResource("drawercontent.fxml"));
            drawer_login.setSidePane(box);
            //Now for hamburger scene
            HamburgerBackArrowBasicTransition burgerTask2=new HamburgerBackArrowBasicTransition(hamburger_login);
            burgerTask2.setRate(-1);
            hamburger_login.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                burgerTask2.setRate(burgerTask2.getRate() * -1);
                burgerTask2.play();
                
                if (drawer_login.isOpened()) {
                    drawer_login.close(); //Closing drawer
                } else {
                    drawer_login.open();//Opening drawer
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        
    }    

    @FXML
    private void f_tf_sn1(KeyEvent event) {
        //only numeric value is allowed for ID
          tf_sn1.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("[\\d]+")) {
            tf_sn1.setText(newValue.replaceAll("[^\\d]", ""));
        }
    });
    }

    @FXML
    private void f_fgpass(ActionEvent event) {
        //Going to forget password window
        try{
          FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Forgot_Pass.fxml"));//FXML loader class will load 2nd window
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
    private void f_sigin(ActionEvent event) {
        //Now going to Main menu page
        //checking ID and password and ADMIN post
        //Getting DBMS connection
        boolean ck=false;
        try{
            CueLounge cl=new CueLounge();
            cl.DBMSConnection();
            //Now connection String
             Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root","");
             //Above is our connection URL or string which is generated when we establish connection in first place
             //Now writing query for getting values from database
             PreparedStatement psmt=cn.prepareStatement("SELECT * FROM `employees`");
             
             ResultSet rs=psmt.executeQuery();
             while(rs.next()){
             if(rs.getString("Employee_ID").equalsIgnoreCase(tf_sn1.getText()) && rs.getString("Password").equalsIgnoreCase(ps_sp1.getText()) && rs.getString("Post").equalsIgnoreCase("ADMIN")){    
             ck=true;
             //Disposing previous pane
             CueLounge.emp_ID=tf_sn1.getText();
             Stage s=(Stage) anc_login.getScene().getWindow();
             s.close();
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Main menu.fxml"));//FXML loader class will load 2nd window
                  Parent root1 = (Parent) fxmlLoader.load();//It will be stored in root1 of parent type
                  Stage stage = new Stage();//Setting a new stage
                  stage.setScene(new Scene(root1));
                  stage.show();//displaying our new window
                  //order frame has been successfully called
             }
            
              }
             if(ck==false){
                 JOptionPane.showMessageDialog(null, "Make sure ID or Password are correct");
             }
        }
              catch(Exception e){
                  JOptionPane.showMessageDialog(null,"Error in loading main window" +e);
              };
        
    }

    @FXML
    private void f_absn(ActionEvent event) {
        //Going to About Page
        try{
          FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("About.fxml"));//FXML loader class will load 2nd window
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
    private void f_signup(ActionEvent event) {
        //Going to signup Page
        try{
          FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("signup.fxml"));//FXML loader class will load 2nd window
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
