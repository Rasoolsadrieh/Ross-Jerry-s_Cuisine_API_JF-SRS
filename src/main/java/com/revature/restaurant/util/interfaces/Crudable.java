package com.revature.restaurant.util.interfaces;

import java.io.IOException;
import java.util.List;

public interface Crudable<C> {

    // Create
    C create(C newObject);

    //Read
    List<C> findAll() throws IOException;
    C findById(String id);

    //Update
    public boolean update(C updatedObject);

    //Delete
     boolean delete( String id);
     boolean delete1(C deletedObject);


}
