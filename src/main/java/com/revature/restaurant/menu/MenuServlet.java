package com.revature.restaurant.menu;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.restaurant.util.exceptions.ResourcePersistanceException;
import com.revature.restaurant.util.interfaces.Authable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MenuServlet extends HttpServlet implements Authable {

    private final MenuServices menuServices;

    private final ObjectMapper mapper;

    public MenuServlet(MenuServices menuServices, ObjectMapper mapper) {
        this.menuServices = menuServices;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
//        if(req.getParameter("itemName") != null) {
//            Menu allItems = menuServices.readById(req.getParameter("itemName"));
//            String payload = mapper.writeValueAsString(allItems);
//            resp.getWriter().write(payload);
//            return;
//        }


        List<Menu> menus = menuServices.readAll();
        String payload = mapper.writeValueAsString(menus);

        resp.getWriter().write(payload);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

      //  if (!Authable.checkAuth(req, resp)) return;

        Menu newMenu = mapper.readValue(req.getInputStream(), Menu.class);
        Menu menu = menuServices.create(newMenu);

        String payload = mapper.writeValueAsString(menu);
        resp.getWriter().write("You have successfully added an item to the Menu ");
        resp.getWriter().write(payload);
        resp.setStatus(201);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

      //  if (!Authable.checkAuth(req, resp)) return;

        Menu updatedMenu = mapper.readValue(req.getInputStream(), Menu.class);
        Menu newMenu = menuServices.update(updatedMenu);

        String payload = mapper.writeValueAsString(newMenu);
        resp.getWriter().write("You have successfully updated an item on the Menu ");
        resp.getWriter().write(payload);
        resp.setStatus(201);

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
     //  if(!Authable.checkAuth(req,resp)) return;
//        if(req.getParameter("password") == null){
//            resp.getWriter().write("In order to delete, please provide your Admin Password as a verification into the url with ?password=password");
//            resp.setStatus(401);
//            return;
//        }

        String itemName = req.getParameter("itemName");



        try {
            menuServices.delete(itemName);
            resp.getWriter().write("Deleted an item from the Menu ");
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
