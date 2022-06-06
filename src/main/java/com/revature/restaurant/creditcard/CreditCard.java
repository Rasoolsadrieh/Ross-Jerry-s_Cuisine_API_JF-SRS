package com.revature.restaurant.creditcard;

import com.revature.restaurant.customer.Customer;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "credit_card")

public class CreditCard {

    @Id
    @Column(name = "cc_number")
    private String ccNumber;
    @Column(name = "cc_name")
    private String ccName;
    @Column(name = "cvv")
    private int cvv;
    @Column(name = "exp_date")
    private String expDate;
    @Column(name = "zip")
    private int zip;

    @Column(name = "credit_limit")
    private int limit;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_username", referencedColumnName = "username")
    private Customer customerUsername;

    public CreditCard(String ccNumber, String ccName, int cvv, String expDate, int zip, int limit, Customer customerUsername ){
        super();
        this.ccNumber = ccNumber;
        this.ccName = ccName;
        this.cvv = cvv;
        this.expDate = expDate;
        this.zip = zip;
        this.limit = limit;
        this.customerUsername = customerUsername;
    }
    public CreditCard(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return cvv == that.cvv && zip == that.zip && limit == that.limit && Objects.equals(ccNumber, that.ccNumber) && Objects.equals(ccName, that.ccName) && Objects.equals(expDate, that.expDate) && Objects.equals(customerUsername, that.customerUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ccNumber, ccName, cvv, expDate, zip, limit, customerUsername);
    }

    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof CreditCard)) return false;
//        CreditCard creditCard = (CreditCard) o;
//        return getCvv() == creditCard.getCvv() && getExpDate() == creditCard.getExpDate() && getZip() == creditCard.getZip() && getLimit() == creditCard.getLimit() && Objects.equals(getCcName(), creditCard.getCcName()) && Objects.equals(getCcNumber(), creditCard.getCcNumber()) && Objects.equals(getCustomerUsername(), creditCard.getCustomerUsername());
//    }
//    @Override
//    public int hashCode() {
//        return Objects.hash(getCcNumber(), getCcName(), getCvv(), getExpDate(), getZip(), getLimit(), getCustomerUsername());
//    }


    public String getCcNumber() { return ccNumber;}
    public void setCcNumber(String ccNumber) {this.ccNumber = ccNumber;}

    public String getCcName() {return ccName;}
    public void setCcName(String ccName) {this.ccName = ccName;}

    public int getCvv() {return cvv;}
    public void setCvv(int cvv) {this.cvv = cvv;}

    public String getExpDate() {return expDate;}
    public void setExpDate(String expDate) {this.expDate = expDate;}

    public int getZip() {return zip;}
    public void setZip(int zip) {this.zip = zip;}

    public int getLimit() {return limit;}
    public void setLimit(int limit) {this.limit = limit;}

    public Customer getCustomerUsername() {return customerUsername;}
    public void setCustomerUsername(Customer customerUsername) {this.customerUsername = customerUsername;}


    @Override
    public String toString(){
        return "CreditCard{" +
                "Credit Card Number: " + ccNumber + '\'' +
                "Credit Card Name: " + ccName + '\'' +
                "CVV: " + cvv + '\'' +
                "Expiration Date: " + expDate + '\'' +
                "Zip Code: " + zip + '\'' +
                "Limit: " + limit + '\'' +
                "Customer Username: " + customerUsername + '\'' +
                '}';
    }

}

