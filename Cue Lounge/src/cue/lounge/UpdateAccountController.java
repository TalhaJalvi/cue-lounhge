/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cue.lounge;

import com.gluonhq.charm.glisten.control.TextField;
import static cue.lounge.MainMenuController.e1;
import static cue.lounge.MainMenuController.e2;
import static cue.lounge.MainMenuController.e3;
import static cue.lounge.MainMenuController.e4;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Talha
 */
public class UpdateAccountController implements Initializable {

    @FXML
    private TextArea ta1_upAccount;
    @FXML
    private TextField tf1_upAccount;
    @FXML
    private TextField tf2_upAcount;
    @FXML
    private Button b1_upAccount;
    @FXML
    private TextField tf3_upAccount;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Passing all values from previous frame to this frame
        tf1_upAccount.setText(e1);
        tf2_upAcount.setText(e2);
        ta1_upAccount.setText(e3);
        tf3_upAccount.setText(e4);
        
        //As Account values cannot be altered so this is why disabling them
        tf1_upAccount.setDisable(true);
        tf1_upAccount.setStyle("-fx-opacity: 1;");
        
        tf2_upAcount.setDisable(true);
        tf2_upAcount.setStyle("-fx-opacity: 1;");
        
        tf3_upAccount.setDisable(true);
        tf3_upAccount.setStyle("-fx-opacity: 1;");
    }    

    @FXML
    private void f_b1_upAccount(ActionEvent event) {
        //It's time for updating account
        
        //But before doing this we have to calculate total remaining again 
        //First getting previous total remaining value
        float prevtrem=Float.parseFloat(e4);
        //Getting new value from textfield
        //New remaining
        float newtrem=Float.parseFloat(tf3_upAccount.getText());
        //It is not finalized whrther we should update values or not so pending till all other updation frames and
        //search filters are applied
        
    }
    
}
