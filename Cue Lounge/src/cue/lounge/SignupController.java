/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cue.lounge;

import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * FXML Controller class
 *
 * @author zabi khan
 */
public class SignupController implements Initializable {

    @FXML
    private TextField tf_su1;
    @FXML
    private TextField tf_su2;
    @FXML
    private TextField tf_su3;
    @FXML
    private DatePicker dp_su1;
    @FXML
    private TextField tf_su4;
    @FXML
    private TextField tf_su5;
    
    @FXML
    private TextField tf_su6;
    @FXML
    private Button b_savesu;
    @FXML
    private TextField tf_su7;
    @FXML
    private RadioButton rb_su1;
    @FXML
    private RadioButton rb_su2;
    @FXML
    private TextArea ta_su1;
    @FXML
    private TextField tf_su8;
    @FXML
    private Button b_Uploadpic;
    public JFXTextField tf_su9;
    @FXML
    private Label l_inchar;
    private Object cn;
    @FXML
    private PasswordField ps_su1;       @FXML
    private ToggleGroup Gender;
    @FXML
    private RadioButton rb_su3;
    @FXML
    private RadioButton rb_su4;
    @FXML
    private ImageView img_su1;
    public Label fileselected;
    public String imageFile;
    public File selectedFile ;
    
    //For resume
    public String resumFile;
    
    //For CV
    public String cvFile;
        public Image image;
    @FXML
    private TextField tf_su10;
    @FXML
    private TextField tf_su11;
    @FXML
    private RadioButton rb_su5;
    @FXML
    private TextField tf_su12;
    @FXML
    private AnchorPane sc_su;
    @FXML
    private ToggleGroup maririage;
    @FXML
    private Button b_Check;
    @FXML
    private Button b_Reset;
    @FXML
    private Button b_upres_su1;
    @FXML
    private Button b_upcv_su1;



 
     
    /**
     * Initializes the controller class.
     */
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

                   //disabling till security code is entered
           //Textfields
        tf_su1.setDisable(true);
        tf_su2.setDisable(true);;
        tf_su3.setDisable(true);
        tf_su4.setDisable(true);
        tf_su5.setDisable(true);
        tf_su6.setDisable(true);
        tf_su7.setDisable(true);
        tf_su8.setDisable(true);
        tf_su10.setDisable(true);
        tf_su11.setDisable(true);
        tf_su12.setDisable(true);
        //Now for date picker
        dp_su1.setDisable(true);
        //Now for text area
        ta_su1.setDisable(true);
        //Now for radio buttons
        rb_su1.setDisable(true);
        rb_su2.setDisable(true);
        rb_su5.setDisable(true);
        //Now for  buttons
        b_savesu.setDisable(true);
        b_Uploadpic.setDisable(true);
        b_upres_su1.setDisable(true);
        b_upcv_su1.setDisable(true);
        //Now for checkbox
        rb_su3.setDisable(true);
        rb_su4.setDisable(true);
        //Now for text area
        ta_su1.setDisable(true);
        //Now making invalid charcter lable not visible
        l_inchar.setVisible(false);
        ps_su1.setDisable(true);
               
        
        
        

    }
    @FXML
    private void f_savesu(ActionEvent event) throws FileNotFoundException {
       
        try {
            CueLounge cl=new CueLounge();
            cl.DBMSConnection();
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root","");
            //calling for buiding up connection
            
            //Now it's time to insert new data
            PreparedStatement psmt = cn.prepareStatement("INSERT INTO `cuelounge`.`employees` (`Password`, `First_Name`, `LAST_NAME`, `CNIC`, `DOB`, `EMAIL`, `PHONE_NO`, `GENDER`, `CITY`, `Qualification`, `MARITUAL_ST`, `Post`, `Picture`, `Address`, `Salary`, `DOJ`, `Time`, `RESUME`, `CV`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            psmt.setString(1, ps_su1.getText());//getting password
            psmt.setString(2, tf_su1.getText()); //First name
            psmt.setString(3, tf_su2.getText());//Last name
            psmt.setString(4, tf_su3.getText()); //CNIC
            psmt.setString(5, dp_su1.getEditor().getText());//This how we will get date from date picker
                                                            //First we will use getEditor() returns the 
                                                            //text editor of the date picker
                                                            //then getText() function for getting text in
                                                            //our database variable from that text editor
            psmt.setString(6,tf_su4.getText() );//email
            psmt.setString(7,tf_su5.getText());//phone no
            
            
                        if(rb_su3.isSelected()){
                psmt.setString(8, rb_su3.getText());
            }
            else if(rb_su4.isSelected()){
                psmt.setString(8, rb_su4.getText());
            }
            else{
                psmt.setString(8, rb_su5.getText());
            } //Setting gender
                        
            psmt.setString(9, tf_su6.getText());//Getting city
            psmt.setString(10, tf_su7.getText());//Getting qualification
            
            //Now for selecting maritual status for which we have used radio buttons
            //We have grouped them in netbeans by using radio button property togggle 
            //group and given both radio buttons same value"marriage" Now we can select
            //one radio button at a time.
            
            if(rb_su1.isSelected()){
                psmt.setString(11, rb_su1.getText());
            }
            else if(rb_su2.isSelected()){
                psmt.setString(11, rb_su2.getText());
            }
            else{
                JOptionPane.showMessageDialog(null, "Please select one of each marritual status");
            }//maritual status
            if(tf_su10.getText().equalsIgnoreCase("ADMIN")){
            psmt.setString(12,tf_su10.getText());//Entering post
            //Now it's time to send image as BLOB (Binary Large Object) to our xamp database}
            }else{
                JOptionPane.showMessageDialog(null, "Post can only be admin");
            }
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
            
            psmt.setString(14,ta_su1.getText());//getting address
            psmt.setString(15, tf_su8.getText());//getting salary
            
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
            JOptionPane.showMessageDialog(null,"Sign up was successful!");
            
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error is"+ex);
        }
    }


    @FXML
    private void f_Uploadpic(ActionEvent event) throws MalformedURLException {

        FileChooser fileChooser = new FileChooser();//Opening filechooser
        fileChooser.setTitle("Select Image File");//Setting filehoser title
        fileChooser.getExtensionFilters().addAll( //Adding filters so images witth given extensions can only be selected
                new FileChooser.ExtensionFilter("Image Files",
                        "*.bmp", "*.png", "*.jpg", "*.gif")); // limit fileChooser options to image files
        selectedFile = fileChooser.showOpenDialog(fileselected.getScene().getWindow());//Getting selected file

        if (selectedFile != null) {//checking do we have file or not

            imageFile = selectedFile.toURI().toURL().toString();//converting to string so we can use it as 
                                                                //address in our image viewer

            image = new Image(imageFile);  
            img_su1.setImage(image); //setting image in image view
            imageFile=selectedFile.getAbsolutePath();//overwriting variable and storing absolute path 
                                                     //which is required during convertion to binary
            
            
          /*  The Image class requires a URL and does not have a direct way of taking a path as 
             anargument. However, there is help. You could do something with your code along the lines of:
              String imagepath = file.toURI().toURL().toString();Which will convert your File file into a workable format
              which can be passed into the setImage() method. You will have to do some exception handling as the above can 
              throw a MalformedURLException, but that is easy enough, right?
            
             Normally file chooser gets our image in file format but image view is unable to use it            
            */
          
        } 
        

    }
 

            
            
        
        
    

    @FXML
    private void f_Check(ActionEvent event) {
        if((tf_su9.getText().isEmpty())){
            JOptionPane.showMessageDialog(null, "Please enter eny data", "Error Message:", JOptionPane.ERROR_MESSAGE);  
        }
        else{
            try {
                Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root","");
                //Above is our connection URL or string which is generated when we establish connection in first place
                CueLounge cl=new CueLounge();//Making CueLounge class object
                cl.DBMSConnection(); //Building connection by calling connection function
                                     //Which is defined in CueLounge class
                PreparedStatement pstmt=null;
                pstmt=cn.prepareStatement("Select code from security_code");//Preparing SQL statement to be executed when button is 
                                                                            //clicked
                ResultSet rs = pstmt.executeQuery();//Executing our query
                /*When we execute certain SQL queries (SELECT query in general) they return tabular data (IN THE FORM OF TABLE).
                The java.sql.ResultSet
                interface represents such tabular data returned by the SQL statements.i.e. the ResultSet object holds the 
                tabular data returned by the methods that execute the statements which quires the database (executeQuery()
                method of the Statement interface in general).The ResultSet object has a cursor/pointer which points to the
                current row. Initially this cursor is positioned before first row.
                i.e., on calling the next() method for the first time the result set pointer/cursor 
                will be moved to the 1st row (from default position). And on calling the next() method
                for the second time the result set cursor will be moved to the 2nd row.in dbms*/
                
                /*     COL 1 || COL2 || COL3
                ROW1         ||      ||
                ROW2         ||      ||
                */
                while(rs.next()){//rs.next() function for 
                    
       if(tf_su9.getText().equals(rs.getString("Code"))){//Checking whether security code entered by user matches orignal
         
        //If yes then enabling all fields so data can be entered   
        tf_su1.setDisable(false);
        tf_su2.setDisable(false);;
        tf_su3.setDisable(false);
        tf_su4.setDisable(false);
        tf_su5.setDisable(false);
        tf_su6.setDisable(false);
        tf_su7.setDisable(false);
        tf_su8.setDisable(false);
        tf_su9.setDisable(true);
        tf_su10.setDisable(false);
        tf_su11.setDisable(false);
        tf_su12.setDisable(false);
        //Now for date picker
        dp_su1.setDisable(false);
        //Now for text area
        ta_su1.setDisable(false);
        //Now for radio buttons
        rb_su1.setDisable(false);
        rb_su2.setDisable(false);
        rb_su3.setDisable(false);
        rb_su4.setDisable(false);
         rb_su5.setDisable(false);
        //Now for  buttons
        b_savesu.setDisable(false);
        b_Uploadpic.setDisable(false);
        b_upres_su1.setDisable(false);
        b_upcv_su1.setDisable(false);
        //Now for text area
        ta_su1.setDisable(false);
        //Now for password field
        ps_su1.setDisable(false);
        
        

                    }
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    


    @FXML
    private void f_Reset(ActionEvent event) {
        try {
            //Now first of all we will generate random 4 digit code to be sended to user
            //Add liberary :import java.util.Random
            //Now generating random 4 - digit code
            Random rand = new Random();
            String resRandom = Integer.toString(rand.nextInt((9999 - 100) + 1) + 10);
            //Random number has been successfully generated
            
            //Deleting old value in table
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cuelounge?zeroDateTimeBehavior=convertToNull","root","");
            //Above is our connection URL or string which is generated when we establish connection in first place
            CueLounge cl=new CueLounge();//Making CueLounge class object
            cl.DBMSConnection(); //Building connection by calling connection function
            //Which is defined in CueLounge class
            PreparedStatement pstmt=null;
            pstmt=cn.prepareStatement("DELETE FROM `cuelounge`.`security_code` WHERE `security_code`.`code_id`=1");
            pstmt.execute();
            
            //Now it's time to insert new code database
            pstmt=cn.prepareStatement("INSERT INTO `cuelounge`.`security_code` (`code_id`, `Code`) VALUES (?,?)");
            pstmt.setString(1, "1");
            pstmt.setString(2, resRandom);
            pstmt.executeUpdate();
            
            
            JavaMailUtil.send("talhajalvi321@gmail.com","T1a2r3z4a5n6","Zabik1144@gmail.com","code is:",resRandom);
        } catch (SQLException ex) {
            Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private void f_digonl(KeyEvent event) {
        tf_su9.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,4}?")) {
                    tf_su9.setText(oldValue);
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
    private void f_keytf_Su1(KeyEvent event) {
        tf_su1.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\sa-zA-Z*")) {
            tf_su1.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
        }
    });
        //Allowing user to enter only character and space and backspace and TAB
    }

    @FXML
    private void f_keytf_Su2(KeyEvent event) {
         tf_su2.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\sa-zA-Z*")) {
            tf_su2.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
        }
    });
    }


    @FXML
    private void f_keytf_Su3(KeyEvent event) {
         tf_su3.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\d")) {
            tf_su3.setText(newValue.replaceAll("[^\\d]", ""));
        }
    });
    }


    @FXML
    private void f_keytf_Su5(KeyEvent event) {
         tf_su5.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\d")) {
            tf_su5.setText(newValue.replaceAll("[^\\d]", ""));
        }
    });
         //Allowing digit only
    }

    @FXML
    private void f_keytf_Su6(KeyEvent event) {
                tf_su6.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\sa-zA-Z*")) {
            tf_su6.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
        }
    });
    }


    @FXML
    private void f_keytf_Su8(KeyEvent event) {
             tf_su8.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("[\\d\\.]+")) {
            tf_su8.setText(newValue.replaceAll("[^\\d\\.]", ""));
        }
    });
             //Allowing . and digits only for this
    }

    @FXML
    private void f_upres_su1(ActionEvent event) throws MalformedURLException {
         FileChooser fileChooser = new FileChooser();//Opening filechooser
        fileChooser.setTitle("Select document File");//Setting filehoser title
        fileChooser.getExtensionFilters().addAll( //Adding filters so images witth given extensions can only be selected
                new FileChooser.ExtensionFilter("Document Files",
                        "*.pdf", "*.docx")); // limit fileChooser options to image files
        selectedFile = fileChooser.showOpenDialog(fileselected.getScene().getWindow());//Getting selected file

        if (selectedFile != null) {//checking do we have file or not

            resumFile = selectedFile.toURI().toURL().toString();//converting to string so we can use it as 
                                                                //address in our image viewer

            tf_su11.setText(resumFile);
           resumFile=selectedFile.getAbsolutePath();//overwriting variable and storing absolute path 
                                                     //which is required during convertion to binary
            
            
          /*  The Image class requires a URL and does not have a direct way of taking a path as 
             anargument. However, there is help. You could do something with your code along the lines of:
              String imagepath = file.toURI().toURL().toString();Which will convert your File file into a workable format
              which can be passed into the setImage() method. You will have to do some exception handling as the above can 
              throw a MalformedURLException, but that is easy enough, right?
            
             Normally file chooser gets our image in file format but image view is unable to use it            
            */
          
        }

    }

    @FXML
    private void f_upcv_su1(ActionEvent event) {
         FileChooser fileChooser = new FileChooser();//Opening filechooser
        fileChooser.setTitle("Select document File");//Setting filehoser title
        fileChooser.getExtensionFilters().addAll( //Adding filters so images witth given extensions can only be selected
                new FileChooser.ExtensionFilter("Document Files",
                        "*.pdf", "*.docx")); // limit fileChooser options to image files
        selectedFile = fileChooser.showOpenDialog(fileselected.getScene().getWindow());//Getting selected file

        if (selectedFile != null) {try {
            //checking do we have file or not
            
            cvFile = selectedFile.toURI().toURL().toString();//converting to string so we can use it as
            //address in our image viewer
            
            tf_su12.setText(cvFile);
            cvFile=selectedFile.getAbsolutePath();//overwriting variable and storing absolute path
            //which is required during convertion to binary
            
            
            /*  The Image class requires a URL and does not have a direct way of taking a path as
            anargument. However, there is help. You could do something with your code along the lines of:
            String imagepath = file.toURI().toURL().toString();Which will convert your File file into a workable format
            which can be passed into the setImage() method. You will have to do some exception handling as the above can
            throw a MalformedURLException, but that is easy enough, right?
            
            Normally file chooser gets our image in file format but image view is unable to use it            
            */
             } catch (MalformedURLException ex) {
                 Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
             }
          
        }
    }

    @FXML
    private void f_lstnamsu(ActionEvent event) {
    }

    @FXML
    private void f_keytf_Su4(KeyEvent event) {
    }

    @FXML
    private void f_keytf_Su7(KeyEvent event) {
    }

    @FXML
    private void f_keyta_su1(KeyEvent event) {
    }

    @FXML
    private void f_tf_su10(KeyEvent event) {
        //For post
         tf_su10.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\sa-zA-Z*")) {
            tf_su10.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
        }
    });
        
    }

  


    
}

