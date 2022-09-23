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
public class ModelTableOrder {
String Order_ID,Product_ID_list,P_Name_list,Quantity_each,Each_unit_cost,Total_Bill,Supplier_ID,Stock_type,Order_Status,Date;

    public ModelTableOrder(String Order_ID, String Product_ID_list, String P_Name_list, String Quantity_each, String Each_unit_cost, String Total_Bill, String Supplier_ID, String Stock_type, String Order_Status, String Date) {
        this.Order_ID = Order_ID;
        this.Product_ID_list = Product_ID_list;
        this.P_Name_list = P_Name_list;
        this.Quantity_each = Quantity_each;
        this.Each_unit_cost = Each_unit_cost;
        this.Total_Bill = Total_Bill;
        this.Supplier_ID = Supplier_ID;
        this.Stock_type = Stock_type;
        this.Order_Status = Order_Status;
        this.Date = Date;
    }



    public String getOrder_ID() {
        return Order_ID;
    }

    public void setOrder_ID(String Order_ID) {
        this.Order_ID = Order_ID;
    }

    public String getProduct_ID_list() {
        return Product_ID_list;
    }

    public void setProduct_ID_list(String Product_ID_list) {
        this.Product_ID_list = Product_ID_list;
    }

    public String getP_Name_list() {
        return P_Name_list;
    }

    public void setP_Name_list(String P_Name_list) {
        this.P_Name_list = P_Name_list;
    }

    public String getQuantity_each() {
        return Quantity_each;
    }

    public void setQuantity_each(String Quantity_each) {
        this.Quantity_each = Quantity_each;
    }

    public String getEach_unit_cost() {
        return Each_unit_cost;
    }

    public void setEach_unit_cost(String Each_unit_cost) {
        this.Each_unit_cost = Each_unit_cost;
    }

    public String getTotal_Bill() {
        return Total_Bill;
    }

    public void setTotal_Bill(String Total_Bill) {
        this.Total_Bill = Total_Bill;
    }

    public String getSupplier_ID() {
        return Supplier_ID;
    }

    public void setSupplier_ID(String Supplier_ID) {
        this.Supplier_ID = Supplier_ID;
    }

    public String getStock_type() {
        return Stock_type;
    }

    public void setStock_type(String Stock_type) {
        this.Stock_type = Stock_type;
    }

    public String getOrder_Status() {
        return Order_Status;
    }

    public void setDate(String Order_Status) {
        this.Order_Status = Order_Status;
    }

    public String getDate() {
        return Date;
    }

    public void setTime(String Date) {
        this.Date = Date;
    }
    

}
