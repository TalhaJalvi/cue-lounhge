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
public class ModelTableCafe {
private String Customer_ID_cafe,C_Name_cafe,P_ID_list_Cafe,P_Name_List_cafe,Quantity_cafe,Itrm_unit_C_cafe,T_Bill_cafe,Emp_ID_cafe,Date_cafe,Time_Cafe;

    public ModelTableCafe(String Customer_ID_cafe, String C_Name_cafe, String P_ID_list_Cafe, String P_Name_List_cafe, String Quantity_cafe, String Itrm_unit_C_cafe, String T_Bill_cafe, String Emp_ID_cafe, String Date_cafe, String Time_Cafe) {
        this.Customer_ID_cafe = Customer_ID_cafe;
        this.C_Name_cafe = C_Name_cafe;
        this.P_ID_list_Cafe = P_ID_list_Cafe;
        this.P_Name_List_cafe = P_Name_List_cafe;
        this.Quantity_cafe = Quantity_cafe;
        this.Itrm_unit_C_cafe = Itrm_unit_C_cafe;
        this.T_Bill_cafe = T_Bill_cafe;
        this.Emp_ID_cafe = Emp_ID_cafe;
        this.Date_cafe = Date_cafe;
        this.Time_Cafe = Time_Cafe;
    }

    public String getCustomer_ID_cafe() {
        return Customer_ID_cafe;
    }

    public void setCustomer_ID_cafe(String Customer_ID_cafe) {
        this.Customer_ID_cafe = Customer_ID_cafe;
    }

    public String getC_Name_cafe() {
        return C_Name_cafe;
    }

    public void setC_Name_cafe(String C_Name_cafe) {
        this.C_Name_cafe = C_Name_cafe;
    }

    public String getP_ID_list_Cafe() {
        return P_ID_list_Cafe;
    }

    public void setP_ID_list_Cafe(String P_ID_list_Cafe) {
        this.P_ID_list_Cafe = P_ID_list_Cafe;
    }

    public String getP_Name_List_cafe() {
        return P_Name_List_cafe;
    }

    public void setP_Name_List_cafe(String P_Name_List_cafe) {
        this.P_Name_List_cafe = P_Name_List_cafe;
    }

    public String getQuantity_cafe() {
        return Quantity_cafe;
    }

    public void setQuantity_cafe(String Quantity_cafe) {
        this.Quantity_cafe = Quantity_cafe;
    }

    public String getItrm_unit_C_cafe() {
        return Itrm_unit_C_cafe;
    }

    public void setItrm_unit_C_cafe(String Itrm_unit_C_cafe) {
        this.Itrm_unit_C_cafe = Itrm_unit_C_cafe;
    }

    public String getT_Bill_cafe() {
        return T_Bill_cafe;
    }

    public void setT_Bill_cafe(String T_Bill_cafe) {
        this.T_Bill_cafe = T_Bill_cafe;
    }

    public String getEmp_ID_cafe() {
        return Emp_ID_cafe;
    }

    public void setEmp_ID_cafe(String Emp_ID_cafe) {
        this.Emp_ID_cafe = Emp_ID_cafe;
    }

    public String getDate_cafe() {
        return Date_cafe;
    }

    public void setDate_cafe(String Date_cafe) {
        this.Date_cafe = Date_cafe;
    }

    public String getTime_Cafe() {
        return Time_Cafe;
    }

    public void setTime_Cafe(String Time_Cafe) {
        this.Time_Cafe = Time_Cafe;
    }
    

}
