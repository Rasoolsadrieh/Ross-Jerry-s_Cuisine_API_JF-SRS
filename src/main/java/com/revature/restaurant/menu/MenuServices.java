package com.revature.restaurant.menu;

import com.revature.restaurant.util.interfaces.Serviceable;

import java.util.List;

public class MenuServices implements Serviceable<Menu> {

    private final MenuDao menuDao;

    public MenuServices(MenuDao menuDao) {this.menuDao = menuDao;}

    @Override
    public Menu create(Menu newMenu){
         return menuDao.create(newMenu);
    }

    @Override
    public List<Menu> readAll() {return menuDao.findAll();}

    @Override
    public Menu readById(String itemName) {return menuDao.findById(itemName);}



    @Override
    public Menu update(Menu updatedMenu) {
        if (!menuDao.update(updatedMenu)){
            return null;
        }

        return updatedMenu;
    }

    @Override
    public boolean delete(String itemName) {
        return menuDao.delete(itemName);
    }

    @Override
    public boolean delete1(Menu deletedMenu) {return false;}
}
