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
public class ModelTableSupplier {
String Sr_no,Supplier_ID,Supplier_Name,Product_type,Email,Phone_no,Address,Emp_ID,Timings, Date,Tijme;

    public ModelTableSupplier(String Sr_no, String Supplier_ID, String Supplier_Name, String Product_type, String Email, String Phone_no, String Address, String Emp_ID, String Timings, String Date, String Tijme) {
        this.Sr_no = Sr_no;
        this.Supplier_ID = Supplier_ID;
        this.Supplier_Name = Supplier_Name;
        this.Product_type = Product_type;
        this.Email = Email;
        this.Phone_no = Phone_no;
        this.Address = Address;
        this.Emp_ID = Emp_ID;
        this.Timings = Timings;
        this.Date = Date;
        this.Tijme = Tijme;
    }

    public String getSr_no() {
        return Sr_no;
    }

    public void setSr_no(String Sr_no) {
        this.Sr_no = Sr_no;
    }

    public String getSupplier_ID() {
        return Supplier_ID;
    }

    public void setSupplier_ID(String Supplier_ID) {
        this.Supplier_ID = Supplier_ID;
    }

    public String getSupplier_Name() {
        return Supplier_Name;
    }

    public void setSupplier_Name(String Supplier_Name) {
        this.Supplier_Name = Supplier_Name;
    }

    public String getProduct_type() {
        return Product_type;
    }

    public void setProduct_type(String Product_type) {
        this.Product_type = Product_type;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPhone_no() {
        return Phone_no;
    }

    public void setPhone_no(String Phone_no) {
        this.Phone_no = Phone_no;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getEmp_ID() {
        return Emp_ID;
    }

    public void setEmp_ID(String Emp_ID) {
        this.Emp_ID = Emp_ID;
    }

    public String getTimings() {
        return Timings;
    }

    public void setTimings(String Timings) {
        this.Timings = Timings;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getTijme() {
        return Tijme;
    }

    public void setTijme(String Tijme) {
        this.Tijme = Tijme;
    }
    

}