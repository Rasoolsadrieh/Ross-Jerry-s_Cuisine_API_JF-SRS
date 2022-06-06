package com.revature.restaurant.menu;

import com.revature.restaurant.util.interfaces.Crudable;
import com.revature.restaurant.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.Session;

import java.io.IOException;
import java.util.List;

public class MenuDao implements Crudable<Menu> {

    @Override
    public Menu create(Menu newMenu) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.save(newMenu);
            transaction.commit();
            return newMenu;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public List<Menu> findAll() {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            List<Menu> menus = session.createQuery("FROM Menu").list();
            transaction.commit();
            return menus;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public Menu findById(String itemName) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Menu menu = session.get(Menu.class, itemName);
            transaction.commit();
            return menu;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }




    @Override
    public boolean update(Menu updatedMenu) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.merge(updatedMenu);
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
    public boolean delete(String itemName) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Menu menu = session.load(Menu.class, itemName);
            session.remove(menu);
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
    public boolean delete1(Menu deletedMenu) {return false;}

}
