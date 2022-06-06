package com.revature.restaurant.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.restaurant.menu.Menu;
import com.revature.restaurant.util.exceptions.ResourcePersistanceException;
import com.revature.restaurant.util.interfaces.Authable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomerServlet extends HttpServlet implements Authable {

    private final CustomerServices customerServices;

    private final ObjectMapper mapper;

    public CustomerServlet(CustomerServices customerServices, ObjectMapper mapper) {
        this.customerServices = customerServices;
        this.mapper = mapper;
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

        Customer newCustomer = mapper.readValue(req.getInputStream(), Customer.class);
        Customer persistedCustomer = customerServices.create(newCustomer);

        String payload = mapper.writeValueAsString(persistedCustomer);
        resp.getWriter().write("You have successfully registered an account at RossandJerry's Restaurant");
        resp.getWriter().write(payload);
        resp.setStatus(201);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

       // if (!Authable.checkAuth(req, resp)) return;
      //  Customer authCustomer = (Customer) req.getSession().getAttribute("authCustomer");
        Customer updatedCustomer = mapper.readValue(req.getInputStream(), Customer.class);

            Customer newCustomer = customerServices.update(updatedCustomer);

            String payload = mapper.writeValueAsString(newCustomer);
            resp.getWriter().write("You have successfully updated your customer information ");
            resp.getWriter().write(payload);
            resp.setStatus(201);


    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
       // if(!Authable.checkAuth(req,resp)) return;
//        if(req.getParameter("username") == null){
//            resp.getWriter().write("In order to delete, please provide your username as a verification into the url with ?username=username");
//            resp.setStatus(401);
//            return;
//        }

        String username = req.getParameter("username");
//        Customer authCustomer = (Customer) req.getSession().getAttribute("authCustomer");

//        if(!authCustomer.getUsername().equals(username)){
//            resp.getWriter().write("Username provided does not match the user logged in, double check for confirmation of deletion");
//            return;
//        }

        try {
            customerServices.delete(username);
            resp.getWriter().write("Deleted user from Ross and Jerry's Cuisine");
            req.getSession().invalidate();
        } catch (ResourcePersistanceException e){
            resp.getWriter().write(e.getMessage());
            resp.setStatus(404);
        } catch (Exception e){
            resp.getWriter().write(e.getMessage());
            resp.setStatus(500);
        }
    }

}
