package com.revature.restaurant.order;

import com.revature.restaurant.util.HibernateUtil;
import com.revature.restaurant.util.interfaces.Crudable;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.List;

public class OrderDao implements Crudable<Order> {

    public Order create(Order newOrder) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.save(newOrder);
            transaction.commit();
            return newOrder;
        } catch (HibernateException | IOException e){
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }


    }

    @Override
    public List<Order> findAll() {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            List<Order> orders = session.createQuery("FROM Order").list();
            transaction.commit();
            return orders;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public Order findById(String orderDate) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Order order = session.load(Order.class, orderDate);
            transaction.commit();
            return order;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public boolean update(Order updatedOrder) {return false;}

    @Override
    public boolean delete(String comment ) {return false;}

    @Override
    public boolean delete1(Order deletedOrder) {return false;}






}
