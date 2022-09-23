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
//This class is for table of main menus
public class ModelTable {
    String Emp_ID,Name,Post,Salary,DOJ,Gender,Maritual_status,Address,Paid,Time,Date;
//Parametrized constructor for employees table
    public ModelTable(String Emp_ID, String Name, String Post, String Salary, String DOJ, String Gender, String Maritual_status, String Address, String Paid, String Time, String Date) {
        this.Emp_ID = Emp_ID;
        this.Name = Name;
        this.Post = Post;
        this.Salary = Salary;
        this.DOJ = DOJ;
        this.Gender = Gender;
        this.Maritual_status = Maritual_status;
        this.Address = Address;
        this.Paid = Paid;
        this.Time = Time;
        this.Date = Date;
    }

    //getters and setters for this parametrized constructor
    public String getEmp_ID() {
        return Emp_ID;
    }

    public void setEmp_ID(String Emp_ID) {
        this.Emp_ID = Emp_ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getPost() {
        return Post;
    }

    public void setPost(String Post) {
        this.Post = Post;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String Salary) {
        this.Salary = Salary;
    }

    public String getDOJ() {
        return DOJ;
    }

    public void setDOJ(String DOJ) {
        this.DOJ = DOJ;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getMaritual_status() {
        return Maritual_status;
    }

    public void setMaritual_status(String Maritual_status) {
        this.Maritual_status = Maritual_status;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getPaid() {
        return Paid;
    }

    public void setPaid(String Paid) {
        this.Paid = Paid;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }
        
    
}
