/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cue.lounge.ModelTableClasses;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

/**
 *
 * @author Talha
 */
public class ModelTableGames {
    
   private String sr_no_var,Table_ID, P1_Name, P2_Name,Time_Started, Time_Ended, Game_Type,  Bill, G_Paid, Dategames, Timegames;

    public ModelTableGames(String sr_no_var,String Table_ID, String P1_Name, String P2_Name, String Time_Started, String Time_Ended,  String Game_Type, String Bill, String G_Paid, String Dategames, String Timegames) {
        this.sr_no_var=sr_no_var;
        this.Table_ID = Table_ID;
        this.P1_Name = P1_Name;
        this.P2_Name = P2_Name;
        this.Time_Started = Time_Started;
        this.Time_Ended = Time_Ended;
        this.Game_Type = Game_Type;
        this.Bill = Bill;
        this.G_Paid = G_Paid;
        this.Dategames = Dategames;
        this.Timegames = Timegames;
    }

    public String getSr_no_var() {
        return sr_no_var;
    }

    public void setSr_no_var(String sr_no_var) {
        this.sr_no_var = sr_no_var;
    }

    public String getTable_ID() {
        return Table_ID;
    }

    public void setTable_ID(String Table_ID) {
        this.Table_ID = Table_ID;
    }

    public String getP1_Name() {
        return P1_Name;
    }

    public void setP1_Name(String P1_Name) {
        this.P1_Name = P1_Name;
    }

    public String getP2_Name() {
        return P2_Name;
    }

    public void setP2_Name(String P2_Name) {
        this.P2_Name = P2_Name;
    }

    public String getTime_Started() {
        return Time_Started;
    }

    public void setTime_Started(String Time_Started) {
        this.Time_Started = Time_Started;
    }

    public String getTime_Ended() {
        return Time_Ended;
    }

    public void setTime_Ended(String Time_Ended) {
        this.Time_Ended = Time_Ended;
    }

    public String getGame_Type() {
        return Game_Type;
    }

    public void setGame_Type(String Game_Type) {
        this.Game_Type = Game_Type;
    }

    public String getBill() {
        return Bill;
    }

    public void setBill(String Bill) {
        this.Bill = Bill;
    }

    public String getG_Paid() {
        return G_Paid;
    }

    public void setG_Paid(String G_Paid) {
        this.G_Paid = G_Paid;
    }

    public String getDategames() {
        return Dategames;
    }

    public void setDategames(String Dategames) {
        this.Dategames = Dategames;
    }

    public String getTimegames() {
        return Timegames;
    }

    public void setTimegames(String Timegames) {
        this.Timegames = Timegames;
    }

}