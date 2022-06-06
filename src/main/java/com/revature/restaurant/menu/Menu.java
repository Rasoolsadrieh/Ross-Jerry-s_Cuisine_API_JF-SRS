package com.revature.restaurant.menu;

import javax.persistence.*;

 @Entity
 @Table(name = "menu")
public class Menu {
     @Id
     @Column(name = "item_name")
    private String itemName;
    @Column(name = "cost")
    private int itemCost;
    @Column(name = "protein")
    private String itemProtein;
    @Column(name = "is_substitutable")
    private boolean itemIs_substitutable;

    public Menu(String itemName, int itemCost, String itemProtein, boolean itemIs_substitutable) {
        super();
        this.itemName = itemName;
        this.itemCost = itemCost;
        this.itemProtein = itemProtein;
        this.itemIs_substitutable =  itemIs_substitutable;
    }

    public Menu() {
    }

    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemCost() {
        return itemCost;
    }
    public void setItemCost(int itemCost) { this.itemCost = itemCost; }

    public String getItemProtein() {
        return itemProtein;
    }
    public void setItemProtein(String itemProtein) {
        this.itemProtein = itemProtein;
    }

    public boolean getItemIs_substitutable() {
        return itemIs_substitutable;
    }
    public void setItemIs_substitutable(boolean itemIs_substitutable) {
        this.itemIs_substitutable = itemIs_substitutable;
    }

    @Override
    public String toString(){
        return "Menu{" +
                "Item Name: " + itemName + '\'' +
                "Item Cost: " + itemCost + '\'' +
                "Item Protein: " + itemProtein + '\'' +
                "Is It Substitutable: " + itemIs_substitutable + '\'' +
                '}';
    }

}
