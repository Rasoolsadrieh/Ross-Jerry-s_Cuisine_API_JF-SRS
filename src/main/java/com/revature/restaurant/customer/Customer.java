package com.revature.restaurant.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;

 @Entity
 @Table(name = "customer")
public class Customer {

     @Id
     @Column(name = "username")
    private String username;
    @Column(name = "fname")
    private String fName;
    @Column(name = "lname")
    private String lName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password")
    private String password;
    @Column(name =  "balance")
    private int balance;
    @Column(name = "is_admin")
    private boolean isAdmin;

    public Customer(String username, String fName, String lName, String password, int balance, boolean isAdmin){
        super();
        this.username =  username;
        this.fName = fName;
        this.lName = lName;
        this.password = password;
        this.balance = balance;
        this.isAdmin = isAdmin;
    }

    public Customer(String password){
        this.password = password;
    }

    public Customer() {

    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getFname() {
        return fName;
    }
    public void setFname(String fname) {
        this.fName = fname;
    }

    public String getLname() {
        return lName;
    }
    public void setLname(String lname) {
        this.lName = lname;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public int getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }
    public void setIsAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString(){
        return "Customer{" +
                "Username: " + username +'\'' +
                "First Name: " + fName + '\'' +
                "Last Name: " + lName + '\'' +
                "Password: " + password + '\'' +
                "Balance: " + balance + '\'' +
                "isAdmin: " + isAdmin + '\'' +
                '}';

    }
}

