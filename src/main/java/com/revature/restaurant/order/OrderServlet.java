package com.revature.restaurant.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.restaurant.creditcard.CreditCard;
import com.revature.restaurant.customer.Customer;
import com.revature.restaurant.customer.CustomerServices;
import com.revature.restaurant.menu.Menu;
import com.revature.restaurant.menu.MenuServices;
import com.revature.restaurant.util.interfaces.Authable;
import com.revature.restaurant.util.web.dto.CreditCardInitializer;
import com.revature.restaurant.util.web.dto.OrderInitializer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends HttpServlet implements Authable {

    private final OrderServices orderServices;

    private final ObjectMapper mapper;

    private final CustomerServices customerServices;

    private final MenuServices menuServices;

    public OrderServlet(OrderServices orderServices, ObjectMapper mapper, CustomerServices customerServices, MenuServices menuServices){
        this.orderServices = orderServices;
        this.mapper = mapper;
        this.customerServices = customerServices;
        this.menuServices = menuServices;
    }
    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doOptions(req, resp);
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
      //  if (!Authable.checkAuth(req, resp)) return;
        Order newOrder = new Order();
        OrderInitializer initOrder = mapper.readValue(req.getInputStream(), OrderInitializer.class); // from JSON to Java Object (Pokemon)
        try {
            Menu menu = menuServices.readById(initOrder.getMenuItem());
            Customer customer = customerServices.readById(initOrder.getCustomerUsername());

            newOrder.setMenuItem(menu);
            newOrder.setComment(initOrder.getComment());
            newOrder.setFavorite(initOrder.getFavorite());
            newOrder.setOrderDate(initOrder.getOrderDate());
            newOrder.setCustomerUsername(customer);

        } catch (Exception e) {
            resp.getWriter().write(e.getMessage());
        }

        System.out.println(newOrder);
        Order persistedOrder = orderServices.create(newOrder);

        String payload = mapper.writeValueAsString(persistedOrder);

        resp.getWriter().write("Added the order as shown below \n");
        resp.getWriter().write(payload);
        resp.setStatus(201);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        if(req.getParameter("orderDate") != null){
            Order order = orderServices.readById(req.getParameter("orderDate"));
            String payload = mapper.writeValueAsString(order);
            resp.getWriter().write(payload);
            return;
        }
        List<Order> orders = orderServices.readAll();
        String payload = mapper.writeValueAsString(orders);

        resp.getWriter().write(payload);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
     //   if (!Authable.checkAuth(req, resp)) return;
        Order newOrder = new Order();
        OrderInitializer initOrder= mapper.readValue(req.getInputStream(), OrderInitializer.class);
        try{
            Customer customer = customerServices.readById(initOrder.getCustomerUsername());
            Menu menu = menuServices.readById(initOrder.getMenuItem());

            newOrder.setMenuItem(menu);
            newOrder.setComment(initOrder.getComment());
            newOrder.setFavorite(initOrder.getFavorite());
            newOrder.setOrderDate(initOrder.getOrderDate());
            newOrder.setCustomerUsername(customer);

        }catch (Exception e){
            resp.getWriter().write(e.getMessage());
        }
        System.out.println(newOrder);
        Order persistedOrder = orderServices.create(newOrder);
        String payload = mapper.writeValueAsString(persistedOrder);

        resp.getWriter().write("You have successfully updated your Order information  ");
        resp.getWriter().write(payload);
        resp.setStatus(201);
    }

}
