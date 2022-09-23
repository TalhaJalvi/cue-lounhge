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
public class ModelTableStocks {
      //Parametrized constructor for stocks table
    private String P_ID,Emp_ID,P_Name,Unit_cost_B,Unit_cost_S,B_Date,Exp_Date,Q_Avail,Stock_type, Manufacturer,timestocks;

    public ModelTableStocks(String P_ID, String Emp_ID, String P_Name, String Unit_cost_B, String Unit_cost_S, String B_Date, String Exp_Date, String Q_Avail, String Stock_type, String Manufacturer, String timestocks) {
        this.P_ID = P_ID;
        this.Emp_ID = Emp_ID;
        this.P_Name = P_Name;
        this.Unit_cost_B = Unit_cost_B;
        this.Unit_cost_S = Unit_cost_S;
        this.B_Date = B_Date;
        this.Exp_Date = Exp_Date;
        this.Q_Avail = Q_Avail;
        this.Stock_type = Stock_type;
        this.Manufacturer = Manufacturer;
        this.timestocks = timestocks;
    }

    public String getP_ID() {
        return P_ID;
    }

    public void setP_ID(String P_ID) {
        this.P_ID = P_ID;
    }

    public String getEmp_ID() {
        return Emp_ID;
    }

    public void setEmp_ID(String Emp_ID) {
        this.Emp_ID = Emp_ID;
    }

    public String getP_Name() {
        return P_Name;
    }

    public void setP_Name(String P_Name) {
        this.P_Name = P_Name;
    }

    public String getUnit_cost_B() {
        return Unit_cost_B;
    }

    public void setUnit_cost_B(String Unit_cost_B) {
        this.Unit_cost_B = Unit_cost_B;
    }

    public String getUnit_cost_S() {
        return Unit_cost_S;
    }

    public void setUnit_cost_S(String Unit_cost_S) {
        this.Unit_cost_S = Unit_cost_S;
    }

    public String getB_Date() {
        return B_Date;
    }

    public void setB_Date(String B_Date) {
        this.B_Date = B_Date;
    }

    public String getExp_Date() {
        return Exp_Date;
    }

    public void setExp_Date(String Exp_Date) {
        this.Exp_Date = Exp_Date;
    }

    public String getQ_Avail() {
        return Q_Avail;
    }

    public void setQ_Avail(String Q_Avail) {
        this.Q_Avail = Q_Avail;
    }

    public String getStock_type() {
        return Stock_type;
    }

    public void setStock_type(String Stock_type) {
        this.Stock_type = Stock_type;
    }

    public String getManufacturer() {
        return Manufacturer;
    }

    public void setManufacturer(String Manufacturer) {
        this.Manufacturer = Manufacturer;
    }

    public String getTimestocks() {
        return timestocks;
    }

    public void setTimestocks(String timestocks) {
        this.timestocks = timestocks;
    }
    

}