/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectionBuilder;
import model.Customer;
import model.ShoppingCart;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ASUS
 */
public class CartDAO {

    public void save(ShoppingCart shoppingCart) throws Exception {
        Transaction tr = null;
        Session session = null;
        try {
            session = ConnectionBuilder.hibSession();
            tr = session.beginTransaction();
            session.save(shoppingCart);
            tr.commit();
        } catch (Exception e) {
            if (tr != null && tr.isActive()) {
                tr.rollback();
            }
            throw e;
        }finally{
        }
    }

    public ShoppingCart searchByCustomer(Customer customer) {
        ShoppingCart shoppingCart = null;
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(ShoppingCart.class);
        criteria.add(Restrictions.eq("customer", customer));
        criteria.setCacheable(false);
        shoppingCart = (ShoppingCart) criteria.uniqueResult();
        return shoppingCart;
    }
}
