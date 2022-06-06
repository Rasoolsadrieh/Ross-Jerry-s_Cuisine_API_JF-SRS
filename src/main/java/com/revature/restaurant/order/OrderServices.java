package com.revature.restaurant.order;

import com.revature.restaurant.customer.CustomerDao;
import com.revature.restaurant.menu.MenuDao;
import com.revature.restaurant.util.interfaces.Serviceable;
import java.util.List;

public class OrderServices implements Serviceable<Order> {

    private final OrderDao orderDao;

    private final CustomerDao customerDao;

    private final MenuDao menuDao;

    public OrderServices(OrderDao orderDao, CustomerDao customerDao, MenuDao menuDao) {
        this.orderDao = orderDao;
        this.customerDao = customerDao;
        this.menuDao = menuDao;
    }

    @Override
    public Order create(Order newOrder) {return orderDao.create(newOrder);}

    @Override
    public List<Order> readAll(){ return orderDao.findAll();}

    @Override
    public Order readById(String orderDate) {return orderDao.findById(orderDate);}

    @Override
    public Order update(Order updatedOrder) { return null;}

    @Override
    public boolean delete(String comment) {return false;}

    @Override
    public boolean delete1(Order deletedOrder) {return false;}

}
