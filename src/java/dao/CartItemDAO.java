/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectionBuilder;
import model.ProductItem;
import model.ShoppingCart;
import model.ShoppingCartItem;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ASUS
 */
public class CartItemDAO {

    public void save(ShoppingCartItem shoppingCartItem) throws Exception {
        Transaction tr = null;
        Session session = null;
        try {
            session = ConnectionBuilder.hibSession();
            tr = session.beginTransaction();
            session.save(shoppingCartItem);
            tr.commit();
        } catch (Exception e) {
            if (tr != null && tr.isActive()) {
                tr.rollback();
            }
            throw e;
        }finally{
        }
    }

    public void delete(ShoppingCartItem sci) throws Exception {
        System.out.println("[In DELETE] : Method called !");
        System.out.println("[In DELETE] : Shopping cart item id : "+ sci.getId());
        Transaction tr = null;
        Session session = null;
        try {
            session = ConnectionBuilder.hibSession();
            tr = session.beginTransaction();
 
            if (sci != null) {
                session.delete(sci);
            }
            tr.commit();
            System.out.println("[In DELETE] : Deleted !");
        } catch (Exception e) {
            if (tr != null && tr.isActive()) {
                System.out.println("[In DELETE] : Rolled back !");
                tr.rollback();
            }
            throw e;
        } finally{
 
        }
    }

    public void updateQty(int cartItemId, int qty) {
        Transaction tr = null;
        Session session = ConnectionBuilder.hibSession();
        try {
            tr = session.beginTransaction();
            String hql = "UPDATE ShoppingCartItem SET qty = :newQty WHERE id = :cartItemId";
            Query query = session.createQuery(hql);
            query.setParameter("newQty", qty);
            query.setParameter("cartItemId", cartItemId);
            query.executeUpdate();
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }finally{
          
        }
    }

    public ShoppingCartItem searchByProductItemAndCustomer(ProductItem productItem, ShoppingCart shoppingCart) {
        ShoppingCartItem shoppingCartItem = null;
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(ShoppingCartItem.class);
        criteria.add(Restrictions.eq("productItem", productItem));
        criteria.add(Restrictions.eq("shoppingCart", shoppingCart));
        criteria.setCacheable(false);
        shoppingCartItem = (ShoppingCartItem) criteria.uniqueResult();
    
        return shoppingCartItem;
    }

}
