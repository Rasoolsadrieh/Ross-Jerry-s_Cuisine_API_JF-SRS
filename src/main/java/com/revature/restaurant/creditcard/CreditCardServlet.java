package com.revature.restaurant.creditcard;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.restaurant.customer.Customer;
import com.revature.restaurant.customer.CustomerServices;
import com.revature.restaurant.util.exceptions.ResourcePersistanceException;
import com.revature.restaurant.util.interfaces.Authable;
import com.revature.restaurant.util.web.dto.CreditCardInitializer;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;

public class CreditCardServlet extends HttpServlet implements Authable {

    private final CreditCardServices creditCardServices;

    private final ObjectMapper mapper;

    private final CustomerServices customerServices;

    public CreditCardServlet(CreditCardServices creditCardServices, ObjectMapper mapper, CustomerServices customerServices) {
        this.creditCardServices = creditCardServices;
        this.mapper = mapper;
        this.customerServices = customerServices;
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

       // if (!Authable.checkAuth(req, resp)) return;

        CreditCard newCreditCard = new CreditCard();
        CreditCardInitializer initCreditCard = mapper.readValue(req.getInputStream(), CreditCardInitializer.class);
        try{
            Customer customerUsername = customerServices.readById(initCreditCard.getCustomerUsername());

            newCreditCard.setCcNumber(initCreditCard.getCcNumber());
            newCreditCard.setCcName(initCreditCard.getCcName());
            newCreditCard.setCvv(initCreditCard.getCvv());
            newCreditCard.setExpDate(initCreditCard.getExpDate());
            newCreditCard.setZip(initCreditCard.getZip());
            newCreditCard.setLimit(initCreditCard.getLimit());
            newCreditCard.setCustomerUsername(customerUsername);
        }catch (Exception e){
            resp.getWriter().write(e.getMessage());
        }
        System.out.println(newCreditCard);
        CreditCard persistedCreditCard = creditCardServices.create(newCreditCard);
        String payload = mapper.writeValueAsString(persistedCreditCard);

        resp.getWriter().write("You have successfully added your credit card to your account ");
        resp.getWriter().write(payload);
        resp.setStatus(201);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

       // if (!Authable.checkAuth(req, resp)) return;

        CreditCard newCreditCard = new CreditCard();
        CreditCardInitializer initCreditCard = mapper.readValue(req.getInputStream(), CreditCardInitializer.class);
        try{
            Customer customerUsername = customerServices.readById(initCreditCard.getCustomerUsername());

            newCreditCard.setCcNumber(initCreditCard.getCcNumber());
            newCreditCard.setCcName(initCreditCard.getCcName());
            newCreditCard.setCvv(initCreditCard.getCvv());
            newCreditCard.setExpDate(initCreditCard.getExpDate());
            newCreditCard.setZip(initCreditCard.getZip());
            newCreditCard.setLimit(initCreditCard.getLimit());
            newCreditCard.setCustomerUsername(customerUsername);
        }catch (Exception e){
            resp.getWriter().write(e.getMessage());
        }
        System.out.println(newCreditCard);
        CreditCard persistedCreditCard = creditCardServices.update(newCreditCard);
        String payload = mapper.writeValueAsString(persistedCreditCard);

        resp.getWriter().write("You have successfully updated your credit card information  ");
        resp.getWriter().write(payload);
        resp.setStatus(201);

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
      //  if(!Authable.checkAuth(req,resp)) return;
//        if(req.getParameter("ccNumber") == null){
//            resp.getWriter().write("In order to delete, please provide your credit card number as a verification into the url with ?ccNumber= ");
//            resp.setStatus(401);
//            return;
//        }

        String ccNumber = req.getParameter("ccNumber");



        try {
            creditCardServices.delete(ccNumber);
            resp.getWriter().write("Deleted credit card from the database");
        } catch (ResourcePersistanceException e){
            resp.getWriter().write(e.getMessage());
            resp.setStatus(404);
        } catch (Exception e){
            resp.getWriter().write(e.getMessage());
            resp.setStatus(500);
        }
//        CreditCard newCreditCard = new CreditCard();
//        CreditCardInitializer initCreditCard = mapper.readValue(req.getInputStream(), CreditCardInitializer.class);
//        try{
//            Customer customerUsername = customerServices.readById(initCreditCard.getCustomerUsername());
//
//            newCreditCard.setCcNumber(initCreditCard.getCcNumber());
//            newCreditCard.setCcName(initCreditCard.getCcName());
//            newCreditCard.setCvv(initCreditCard.getCvv());
//            newCreditCard.setExpDate(initCreditCard.getExpDate());
//            newCreditCard.setZip(initCreditCard.getZip());
//            newCreditCard.setLimit(initCreditCard.getLimit());
//            newCreditCard.setCustomerUsername(customerUsername);
//
//            creditCardServices.delete1(newCreditCard);
//            resp.getWriter().write("Deleted credit card from the database");
//        }catch (Exception e){
//            resp.getWriter().write(e.getMessage());
//        }
    }


}
