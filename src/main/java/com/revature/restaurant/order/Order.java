package com.revature.restaurant.order;
import com.revature.restaurant.customer.Customer;
import com.revature.restaurant.menu.Menu;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "menu_item", referencedColumnName = "item_name")
    private Menu menuItem;
    @Column(name = "comment")
    private String comment;
    @Column(name = "is_favorite")
    private boolean isFavorite;
    @Column(name = "order_date")
    private String orderDate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_username", referencedColumnName = "username")
    private Customer customerUsername;

    public Order() {
    }

    public Order(int id, Menu menuItem, String comment, boolean isFavorite, String orderDate, Customer customerUsername) {
        this.id = id;
        this.menuItem = menuItem;
        this.comment = comment;
        this.isFavorite = isFavorite;
        this.orderDate = orderDate;
        this.customerUsername = customerUsername;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Menu getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(Menu menuItem) {
        this.menuItem = menuItem;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Customer getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(Customer customerUsername) {
        this.customerUsername = customerUsername;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", menuItem=" + menuItem +
                ", comment='" + comment + '\'' +
                ", isFavorite=" + isFavorite +
                ", orderDate='" + orderDate + '\'' +
                ", customerUsername=" + customerUsername +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && isFavorite == order.isFavorite && Objects.equals(menuItem, order.menuItem) && Objects.equals(comment, order.comment) && Objects.equals(orderDate, order.orderDate) && Objects.equals(customerUsername, order.customerUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, menuItem, comment, isFavorite, orderDate, customerUsername);
    }
}

