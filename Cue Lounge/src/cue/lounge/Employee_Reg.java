/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cue.lounge;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author zabi khan
 */
public class Employee_Reg implements Initializable {

    @FXML
    private TextField tf1_er;
    @FXML
    private TextField tf2_er;
    @FXML
    private TextField tf3_er;
    @FXML
    private DatePicker dp1_er;
    @FXML
    private TextField tf4_er;
    @FXML
    private TextField tf5_er;
    @FXML
    private TextField tf6_er;
    @FXML
    private Button b3_er;
    @FXML
    private Button b4_er;
    @FXML
    private TextArea ta1_er;
    @FXML
    private TextField tf7_er;
    @FXML
    private TextField tf8_er;
    @FXML
    private TextField tf10_er;
    @FXML
    private TextField tf9_er;
    @FXML
    private TextField tf11_er;
    @FXML
    private Button b1_er;
    @FXML
    private Button b2_er;
    @FXML
    private RadioButton rb1_er;
    @FXML
    private ToggleGroup Gender;
    @FXML
    private RadioButton rb2_er;
    @FXML
    private RadioButton rb3_er;
    @FXML
    private ImageView img1_er;
    @FXML
    private Label lbup_er;
    @FXML
    private Button b5_er;
    @FXML
    private RadioButton rb4_er;
    @FXML
    private ToggleGroup marriage;
    @FXML
    private RadioButton rb5_er;
    
    public Label fileselected;
    public String imageFile;
    public File selectedFile ;
    
    //For resume
    public String resumFile;
    
    //For CV
    public String cvFile;
        public Image image;
    @FXML
    private AnchorPane anc_rmpReg;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void f_tf1_er(KeyEvent event) {
         tf1_er.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\sa-zA-Z*")) {
            tf1_er.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
        }
    });
        //Allowing user to enter only character and space and backspace and TAB
    }

    @FXML
    private void f_tf2_er(KeyEvent event) {
         tf2_er.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\sa-zA-Z*")) {
            tf2_er.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
        }
    });
        //Allowing user to enter only character and space and backspace and TAB
    }

    @FXML
    private void f_tf3_er(KeyEvent event) {
                 tf3_er.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\d")) {
            tf3_er.setText(newValue.replaceAll("[^\\d]", ""));
        }
    });
         //Allowing digit only
    }

    @FXML
    private void f_tf5_er(KeyEvent event) {
                         tf5_er.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\d")) {
            tf5_er.setText(newValue.replaceAll("[^\\d]", ""));
        }
    });
    }

    @FXML
    private void f_save_er(ActionEvent event) throws FileNotFoundException  {
        //Now saving data in database
          try {
            CueLounge cl=new CueLounge();
            cl.DBMSConnection();
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root","");
            //calling for buiding up connection
            
            //Now it's time to insert new data
            PreparedStatement psmt = cn.prepareStatement("INSERT INTO `cuelounge`.`employees` (`Password`, `First_Name`, `LAST_NAME`, `CNIC`, `DOB`, `EMAIL`, `PHONE_NO`, `GENDER`, `CITY`, `Qualification`, `MARITUAL_ST`, `Post`, `Picture`, `Address`, `Salary`, `DOJ`, `Time`, `RESUME`, `CV`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            psmt.setString(1, "");//getting password
            psmt.setString(2, tf1_er.getText());
            psmt.setString(3, tf2_er.getText());
            psmt.setString(4, tf3_er.getText());
            psmt.setString(5, dp1_er.getEditor().getText());
            psmt.setString(6, tf4_er.getText());
            psmt.setString(7, tf5_er.getText());
               
            if(rb1_er.isSelected()){
                psmt.setString(8, rb1_er.getText());
            }
            else if(rb2_er.isSelected()){
                psmt.setString(8, rb2_er.getText());
            }
            else{
                psmt.setString(8, rb3_er.getText());
            } //Setting gender
                        
            psmt.setString(9, tf6_er.getText());//Getting city
            psmt.setString(10, tf7_er.getText());//Getting qualification
            
            //Now for selecting maritual status for which we have used radio buttons
            //We have grouped them in netbeans by using radio button property togggle 
            //group and given both radio buttons same value"marriage" Now we can select
            //one radio button at a time.
            
            if(rb4_er.isSelected()){
                psmt.setString(11, rb4_er.getText());
            }
            else if(rb5_er.isSelected()){
                psmt.setString(11, rb5_er.getText());
            }
            else{
                JOptionPane.showMessageDialog(null, "Please select one of each marritual status");
            }//maritual status
            if(tf8_er.getText().equalsIgnoreCase("ADMIN")){
                
            JOptionPane.showMessageDialog(null, "Post cannot be admin");
            
            }else{
                psmt.setString(12,tf8_er.getText());//Entering post
            }
            //Now it's time to send image as BLOB (Binary Large Object) to our xamp database
            if(imageFile==null){
                              Alert alert = new Alert(Alert.AlertType.INFORMATION);
                              alert.setTitle("Information Dialog");
                              alert.setHeaderText("Please Select a File");
                              alert.setContentText("You didn't select a file for profile!");
                              alert.showAndWait();
               
            }
            FileInputStream inputstream = new FileInputStream(new File(imageFile));
            psmt.setBlob(13, inputstream);
            //First we have converted our image to input stream of binary so it can be uploaded as blob
            
            psmt.setString(14,ta1_er.getText());//getting address
            psmt.setString(15, tf10_er.getText());//getting salary
            
            //Getting date from system
            long millis=System.currentTimeMillis();  
            java.sql.Date date=new java.sql.Date(millis);   
            
            psmt.setString(16, date.toString());//storing date
            
            //Getting system time 
            psmt.setString(17, (java.time.LocalTime.now().toString()));
            FileInputStream inputstream2=new FileInputStream(new File(resumFile));
            psmt.setBlob(18, inputstream2);
            
            FileInputStream inputstream3=new FileInputStream(new File(cvFile));
            psmt.setBlob(19, inputstream3);
            
            //now it's time to execute all above statements and upload our data to the database
            psmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Employee Registration was successful!");
            
        } catch (SQLException ex) {
            error:
             JOptionPane.showMessageDialog(null, "Error is"+ex);
        }
    }

    @FXML
    private void f_cancel_er(ActionEvent event) {
         //if user cancel his decision for update then
             //if user name and password is correct then close login window and open main menu window
             //Before that asking or prompting user he will lost all his saved work
        int reply = JOptionPane.showConfirmDialog(null, "Do you want to exit ? All your work will be lost", "", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
                //  Stage s=(Stage) anc_.getScene().getWindow();
               //   s.close();
        }
        else {
           JOptionPane.showMessageDialog(null, "Thank you for continuing");
        }

    }

    @FXML
    private void f_tf10_er(KeyEvent event) {
                                 tf10_er.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\d")) {
            tf10_er.setText(newValue.replaceAll("[^\\d]", ""));
        }
    });
    }

    @FXML
    private void f_up1resume_er(ActionEvent event) throws MalformedURLException {
      FileChooser file=new FileChooser();
              file.setTitle("Select Docuumente File");//Setting filehoser title
              file.getExtensionFilters().addAll( //Adding filters so images witth given extensions can only be selected
                new FileChooser.ExtensionFilter("Document Files",
                        "*.docx", "*.pdf")); // limit fileChooser options to image files
              selectedFile=file.showOpenDialog(null);
             if(selectedFile!=null){
                 resumFile = selectedFile.toURI().toURL().toString();//converting to string so we can use it as 
                                                                //address in our image viewer

            tf9_er.setText(resumFile);
            resumFile=selectedFile.getAbsolutePath();//overwriting variable and storing absolute path 
                                                     //which is required during convertion to binary
            
             }
             else{
                 JOptionPane.showMessageDialog(null, "Error! no file was selected");
             }
    }

    @FXML
    private void f_up2CV_er(ActionEvent event) throws MalformedURLException  {
         FileChooser file=new FileChooser();
              file.setTitle("Select Document File");//Setting filehoser title
              file.getExtensionFilters().addAll( //Adding filters so images witth given extensions can only be selected
                new FileChooser.ExtensionFilter("Image Files",
                        "*.docx", "*.pdf")); // limit fileChooser options to image files
              selectedFile=file.showOpenDialog(null);
             if(selectedFile!=null){
                 cvFile = selectedFile.toURI().toURL().toString();//converting to string so we can use it as 
                                                                //address in our image viewer
            tf11_er.setText(cvFile);
             
            cvFile=selectedFile.getAbsolutePath();//overwriting variable and storing absolute path 
                                                     //which is required during convertion to binary
            
             }
             else{
                 JOptionPane.showMessageDialog(null, "Error! no file was selected");
             }
             } 
          
    
    
        
    @FXML
    private void f_up3pic_er(ActionEvent event) throws MalformedURLException  {
              FileChooser file=new FileChooser();
              file.setTitle("Select Image File");//Setting filehoser title
              file.getExtensionFilters().addAll( //Adding filters so images witth given extensions can only be selected
                new FileChooser.ExtensionFilter("Image Files",
                        "*.bmp", "*.png", "*.jpg", "*.gif")); // limit fileChooser options to image files
               selectedFile=file.showOpenDialog(null);
             if(selectedFile!=null){
                 imageFile = selectedFile.toURI().toURL().toString();//converting to string so we can use it as 
                                                                //address in our image viewer

            image = new Image(imageFile);  
            img1_er.setImage(image); //setting image in image view
            imageFile=selectedFile.getAbsolutePath();//overwriting variable and storing absolute path 
                                                     //which is required during convertion to binary
            
             }
             else{
                 JOptionPane.showMessageDialog(null, "Error! no file was selected");
             }
             
    }

    @FXML
    private void f_tf6_er(KeyEvent event) {
            tf6_er.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\sa-zA-Z*")) {
            tf6_er.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
        }
    });
        //Allowing user to enter only character and space and backspace and TAB
    }

    @FXML
    private void f_tf8_er(KeyEvent event) {
            tf8_er.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\sa-zA-Z*")) {
            tf8_er.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
        }
    });
        //Allowing user to enter only character and space and backspace and TAB
    }
    
}
