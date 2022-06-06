package com.revature.restaurant.customer;

import com.revature.restaurant.util.interfaces.Crudable;
import com.revature.restaurant.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class CustomerDao implements Crudable<Customer> {

    @Override
    public Customer create(Customer newCustomer) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.save(newCustomer);
            transaction.commit();
            return newCustomer;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public List<Customer> findAll() {return null;}

    @Override
    public Customer findById(String username) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Customer customer = session.get(Customer.class, username);
            transaction.commit();
            return customer;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public boolean update(Customer updatedCustomer) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.merge(updatedCustomer);
            transaction.commit();
            return true;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public boolean delete(String username) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Customer customer = session.load(Customer.class, username);
            session.remove(customer);
            transaction.commit();
            return true;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public boolean delete1(Customer deletedCustomer) {return false;}

    public Customer authenticatedCustomer (String username, String password){
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Customer where username= :username and password= :password");
            query.setParameter("username", username);
            query.setParameter("password", password);
            Customer customer = (Customer) query.uniqueResult();
            transaction.commit();
            return customer;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }

    }


}
