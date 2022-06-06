package com.revature.restaurant.creditcard;

import com.revature.restaurant.customer.CustomerDao;
import com.revature.restaurant.util.interfaces.Serviceable;
import java.util.List;

public class CreditCardServices implements Serviceable<CreditCard> {

    private final CreditCardDao creditCardDao;

    private final CustomerDao customerDao;



    public CreditCardServices(CreditCardDao creditCardDao, CustomerDao customerDao) {
        this.creditCardDao = creditCardDao;
        this.customerDao = customerDao;
    }

    @Override
    public CreditCard create(CreditCard newCreditCard) {
        return creditCardDao.create(newCreditCard);
    }

    @Override
    public List<CreditCard> readAll() {return null;}

    @Override
    public CreditCard readById(String id) {return null;}

    @Override
    public CreditCard update(CreditCard updatedCreditCard) {
        if(!creditCardDao.update(updatedCreditCard)) {
            return null;
        }
        return updatedCreditCard;
    }

    @Override
    public boolean delete(String ccNumber) {
        return creditCardDao.delete(ccNumber);
        // return false;
    }

    @Override
    public boolean delete1(CreditCard deletedCreditCard) {
        if(!creditCardDao.delete1(deletedCreditCard)) {
            return false;
        }
        return creditCardDao.delete1(deletedCreditCard);
    }

}
