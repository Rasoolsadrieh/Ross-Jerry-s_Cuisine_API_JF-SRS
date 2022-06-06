package com.revature.restaurant.util.web.dto;

import com.revature.restaurant.customer.Customer;
import javax.persistence.*;

public class CreditCardInitializer {
    private String ccNumber;
    private String ccName;
    private int cvv;
    private String expDate;
    private int zip;
    private int limit;
    private String customerUsername;

    public CreditCardInitializer() {
    }

    public CreditCardInitializer(String ccNumber, String ccName, int cvv, String expDate, int zip, int limit, String customerUsername) {
        this.ccNumber = ccNumber;
        this.ccName = ccName;
        this.cvv = cvv;
        this.expDate = expDate;
        this.zip = zip;
        this.limit = limit;
        this.customerUsername = customerUsername;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public String getCcName() {
        return ccName;
    }

    public void setCcName(String ccName) {
        this.ccName = ccName;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }
}
