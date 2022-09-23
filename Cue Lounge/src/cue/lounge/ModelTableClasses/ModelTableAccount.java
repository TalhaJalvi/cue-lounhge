/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cue.lounge.ModelTableClasses;

/**
 *
 * @author Talha
 */
public class ModelTableAccount {
  String  Employee_ID,Amount_deducted,Amount_added,Reason,Total_Remaining,Date,Time;

    public ModelTableAccount(String Employee_ID, String Amount_deducted, String Amount_added, String Reason, String Total_Remaining, String Date, String Time) {
        this.Employee_ID = Employee_ID;
        this.Amount_deducted = Amount_deducted;
        this.Amount_added = Amount_added;
        this.Reason = Reason;
        this.Total_Remaining = Total_Remaining;
        this.Date = Date;
        this.Time = Time;
    }

    public String getEmployee_ID() {
        return Employee_ID;
    }

    public void setEmployee_ID(String Employee_ID) {
        this.Employee_ID = Employee_ID;
    }

    public String getAmount_deducted() {
        return Amount_deducted;
    }

    public void setAmount_deducted(String Amount_deducted) {
        this.Amount_deducted = Amount_deducted;
    }

    public String getAmount_added() {
        return Amount_added;
    }

    public void setAmount_added(String Amount_added) {
        this.Amount_added = Amount_added;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String Reason) {
        this.Reason = Reason;
    }

    public String getTotal_Remaining() {
        return Total_Remaining;
    }

    public void setTotal_Remaining(String Total_Remaining) {
        this.Total_Remaining = Total_Remaining;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }
  
}
