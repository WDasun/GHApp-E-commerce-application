package dao;

import connection.ConnectionBuilder;
import java.util.List;
import model.ShoppingCart;
import model.ShoppingCartItem;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Dasun Wimukthi
 */
public class ShoppingCartItemDAO {

    public void changeSelectedStatus(int shoppingCartItemId, boolean status) throws Exception {
        Transaction tr = null;
        Session session = ConnectionBuilder.hibSession();
        try {
            tr = session.beginTransaction();
            String hql = "UPDATE ShoppingCartItem SET "
                    + "selected = :selected"
                    + " WHERE id = :id";

            Query query = session.createQuery(hql);
            query.setParameter("id", shoppingCartItemId);
            query.setParameter("selected", status);

            query.executeUpdate();
            tr.commit();
        
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
            throw e;
        }
    }

    public ShoppingCartItem searchById(int id) throws Exception {
        ShoppingCartItem shoppingCartItem = null;
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(ShoppingCartItem.class);
        criteria.add(Restrictions.eq("id", id));
        criteria.setCacheable(false);
        shoppingCartItem = (ShoppingCartItem) criteria.uniqueResult();
     
        if (shoppingCartItem != null) {
            return shoppingCartItem;
        } else {
            throw new NullPointerException();
        }
    }
    
    public List<ShoppingCartItem> searchByCart(ShoppingCart shoppingCart) throws Exception {
        List<ShoppingCartItem> shoppingCartItemList = null;
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(ShoppingCartItem.class);
        criteria.add(Restrictions.eq("shoppingCart", shoppingCart));
        criteria.setCacheable(false);
        shoppingCartItemList = (List<ShoppingCartItem>) criteria.list();
     
        if (shoppingCartItemList != null) {
            return shoppingCartItemList;
        } else {
            throw new NullPointerException();
        }
    }

    public void delete(ShoppingCartItem shoppingCartItem) throws Exception {
        Transaction tr = null;
        Session session = ConnectionBuilder.hibSession();
        try {
            tr = session.beginTransaction();
            session.delete(shoppingCartItem);
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
