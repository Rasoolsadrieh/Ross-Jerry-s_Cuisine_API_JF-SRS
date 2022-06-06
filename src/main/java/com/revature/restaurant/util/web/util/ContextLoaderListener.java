package com.revature.restaurant.util.web.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.restaurant.creditcard.CreditCardDao;
import com.revature.restaurant.creditcard.CreditCardServices;
import com.revature.restaurant.creditcard.CreditCardServlet;
import com.revature.restaurant.customer.CustomerDao;
import com.revature.restaurant.customer.CustomerServices;
import com.revature.restaurant.customer.CustomerServlet;
import com.revature.restaurant.menu.MenuDao;
import com.revature.restaurant.menu.MenuServices;
import com.revature.restaurant.menu.MenuServlet;
import com.revature.restaurant.order.OrderDao;
import com.revature.restaurant.order.OrderServices;
import com.revature.restaurant.order.OrderServlet;
import com.revature.restaurant.util.web.servlets.AuthServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce){
        ObjectMapper mapper = new ObjectMapper();

        CustomerDao customerDao = new CustomerDao();
        CreditCardDao creditCardDao = new CreditCardDao();
        MenuDao menuDao = new MenuDao();
        OrderDao orderDao = new OrderDao();

        CustomerServices customerServices = new CustomerServices(customerDao);
        CreditCardServices creditCardServices = new CreditCardServices(creditCardDao, customerDao);
        MenuServices menuServices = new MenuServices(menuDao);
        OrderServices orderServices = new OrderServices(orderDao, customerDao, menuDao);

        AuthServlet authServlet = new AuthServlet(customerServices, mapper);
        CustomerServlet customerServlet = new CustomerServlet(customerServices, mapper);
        CreditCardServlet creditCardServlet =  new CreditCardServlet(creditCardServices, mapper, customerServices);
        MenuServlet menuServlet =  new MenuServlet(menuServices, mapper);
        OrderServlet orderServlet =  new OrderServlet(orderServices, mapper, customerServices, menuServices );

        ServletContext context = sce.getServletContext();
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");
        context.addServlet("CustomerServlet", customerServlet).addMapping("/customers/*");
        context.addServlet("CreditCardServlet", creditCardServlet).addMapping("/creditcards/*");
        context.addServlet("MenuServlet", menuServlet).addMapping("/menus/*");
        context.addServlet("OrderServlet", orderServlet).addMapping("/orders/*");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
