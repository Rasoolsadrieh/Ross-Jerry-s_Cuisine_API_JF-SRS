package com.revature.restaurant.creditcard;

import com.revature.restaurant.customer.Customer;
import com.revature.restaurant.util.interfaces.Crudable;
import com.revature.restaurant.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.Session;

import javax.persistence.OneToOne;
import java.io.IOException;
import java.util.List;

public class CreditCardDao implements Crudable<CreditCard> {

    @Override
    public CreditCard create(CreditCard newCreditCard) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.save(newCreditCard);
            transaction.commit();
            return newCreditCard;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public List<CreditCard> findAll(){return null;}

    @Override
    public CreditCard findById(String id) {return null;}

    @Override
    public boolean update(CreditCard updatedCreditCard) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.merge(updatedCreditCard);
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
    public boolean delete1(CreditCard deletedCreditCard) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            //CreditCard creditCard = session.load(CreditCard.class, ccNumber);
            session.remove(deletedCreditCard);
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
    public boolean delete(String ccNumber) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            CreditCard creditCard = session.load(CreditCard.class, ccNumber);
            session.remove(creditCard);
            transaction.commit();
            return true;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            HibernateUtil.closeSession();
        }
    }
}