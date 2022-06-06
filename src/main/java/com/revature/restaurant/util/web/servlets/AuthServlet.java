package com.revature.restaurant.util.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.restaurant.customer.Customer;
import com.revature.restaurant.customer.CustomerServices;
import com.revature.restaurant.util.exceptions.AuthenticationException;
import com.revature.restaurant.util.exceptions.InvalidRequestException;
import com.revature.restaurant.util.interfaces.Authable;
import com.revature.restaurant.util.web.dto.LoginCreds;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthServlet extends HttpServlet implements Authable {

    private final CustomerServices customerServices;

    private final ObjectMapper mapper;

    public AuthServlet(CustomerServices customerServices, ObjectMapper mapper){
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        try {

            LoginCreds loginCreds = mapper.readValue(req.getInputStream(), LoginCreds.class);

            Customer authCustomer = customerServices.authenticateCustomer(loginCreds.getUsername(), loginCreds.getPassword());

            HttpSession httpSession = req.getSession(true);
            httpSession.setAttribute("authCostomer", authCustomer);
            String payload = mapper.writeValueAsString(authCustomer);

            resp.getWriter().write("You have Successfully logged in! ");
            resp.getWriter().write("Welcome to Ross and Jerry's Cuisine! \n");
            resp.getWriter().write(payload);
            resp.setStatus(200);
        } catch (AuthenticationException | InvalidRequestException e){
            resp.setStatus(404);
            resp.getWriter().write(e.getMessage());
        } catch (Exception e){
            resp.setStatus(500);
            resp.getWriter().write(e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        req.getSession().invalidate();
        resp.getWriter().write("You have successfully logged out!");
    }
}
