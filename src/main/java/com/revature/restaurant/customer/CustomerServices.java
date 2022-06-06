package com.revature.restaurant.customer;

import com.revature.restaurant.util.exceptions.AuthenticationException;
import com.revature.restaurant.util.exceptions.InvalidRequestException;
import com.revature.restaurant.util.interfaces.Serviceable;
import java.util.List;

public class CustomerServices implements Serviceable<Customer> {

    private final CustomerDao customerDao;

    public CustomerServices(CustomerDao customerDao) { this.customerDao = customerDao;}

    @Override
    public Customer create(Customer newCustomer){
        Customer persistedCustomer = customerDao.create(newCustomer);
        return persistedCustomer;
    }

    @Override
    public List<Customer> readAll() { return null;}

    @Override
    public Customer readById(String username) {return customerDao.findById(username);}



    @Override
    public Customer update(Customer updatedCustomer) {
        if (!customerDao.update(updatedCustomer)){
            return null;
        }

        return updatedCustomer;
    }

    @Override
    public boolean delete(String username) {
        return customerDao.delete(username);
    }

    @Override
    public boolean delete1(Customer deletedCustomer) {return false;}

    public Customer authenticateCustomer (String username, String password) {
        if (username == null || username.trim().equals("") || password == null || password.trim().equals("")){
            throw new InvalidRequestException("Either username or password is an invalid entry. Please try logging in again");
        }

        Customer authenticatedCustomer = customerDao.authenticatedCustomer(username, password);

        if (authenticatedCustomer == null) {
            throw new AuthenticationException("Unauthenticated user, information provided was not consistent with our database.");
        }

        return authenticatedCustomer;
    }




}
