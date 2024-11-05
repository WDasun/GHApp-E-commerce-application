/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectionBuilder;
import java.util.List;
import model.Address;
import model.Customer;
import model.ProductItem;
import model.WishList;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ASUS
 */
public class WishListDAO {

    public void save(WishList wishList) throws Exception {
        Transaction tr = null;
        Session session = ConnectionBuilder.hibSession();
        try {
            tr = session.beginTransaction();
            session.save(wishList);
            session.flush();
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
            throw e;
        }
        
    }

    public WishList searchByCustomerAndProductItem(Customer customer, ProductItem productItem) throws Exception {
        WishList wishList = null;
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(WishList.class);
        criteria.add(Restrictions.eq("customer", customer));
        criteria.add(Restrictions.eq("productItem", productItem));
        criteria.setCacheable(false);
        wishList = (WishList) criteria.uniqueResult();
        
        return wishList;
    }

    public WishList searchByid(int wishListId, Session session) throws Exception {
        WishList wishList = null;
        Criteria criteria = session.createCriteria(WishList.class);
        criteria.add(Restrictions.eq("id", wishListId));
        criteria.setCacheable(false);
        wishList = (WishList) criteria.uniqueResult();
        return wishList;
    }
    
    public List<WishList> wishListbyCustomer(Customer customer, Session session) throws Exception {
        List<WishList> wishList = null;
        Criteria criteria = session.createCriteria(WishList.class);
        criteria.add(Restrictions.eq("customer", customer));
        criteria.setCacheable(false);
        wishList = (List<WishList>) criteria.list();
        return wishList;
    }

    public void delete(int wishListId) throws Exception {
        Transaction tr = null;
        Session session = ConnectionBuilder.hibSession();
        WishList wishList = searchByid(wishListId, session);
        try {
            tr = session.beginTransaction();
            session.delete(wishList);
            tr.commit();
        } catch (Exception e) {
            if (tr != null) {
                tr.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            
        }
    }

}
