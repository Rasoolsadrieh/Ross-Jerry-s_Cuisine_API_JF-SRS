package com.revature.restaurant.util.web.dto;

public class OrderInitializer {

    private String menuItem;

    private String comment;

    private boolean isFavorite;

    private String orderDate;

    private String customerUsername;

    public OrderInitializer() {
    }

    public OrderInitializer (String menuItem, String comment, boolean isFavorite, String orderDate, String customerUsername){

        this.menuItem = menuItem;
        this.comment = comment;
        this.isFavorite = isFavorite;
        this.orderDate = orderDate;
        this.customerUsername = customerUsername;

    }

    public String getMenuItem(){ return menuItem;}
    public void setMenuItem(String menuItem) {this.menuItem = menuItem;}

    public String getComment() {return comment;}
    public void setComment(String comment) {this.comment = comment;}

    public boolean getFavorite() {return isFavorite;}
    public void setFavorite(boolean isFavorite) {this.isFavorite = isFavorite;}

    public String getOrderDate() {return orderDate;}
    public void setOrderDate(String orderDate) {this.orderDate = orderDate;}

    public String getCustomerUsername(){return customerUsername;}
    public void setCustomerUsername(String customerUsername) {this.customerUsername = customerUsername;}

}
