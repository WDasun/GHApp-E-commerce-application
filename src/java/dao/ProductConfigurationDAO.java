/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectionBuilder;
import model.ProductConfiguration;
import model.ProductItem;
import model.VariationOption;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ASUS
 */
public class ProductConfigurationDAO {

    public void save(ProductConfiguration pc) {
        Transaction tr = null;
        Session session = ConnectionBuilder.hibSession();
        session.clear();
        try {
            tr = session.beginTransaction();
            session.save(pc);
            tr.commit();
            session.flush();
            session.refresh(pc);
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }
    }

    public boolean checkAvailability(ProductItem pi, VariationOption vo) {
        boolean status = false;
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(ProductConfiguration.class);;
        criteria.add(Restrictions.eq("productItem", pi));
        criteria.add(Restrictions.eq("variationOption", vo));
        status = criteria.uniqueResult() != null;
     
        return status;
    }

    public void deleteByProductItem(ProductItem newProductItem) {
        Transaction tr = null;
        Session session = ConnectionBuilder.hibSession();
        try {
            tr = session.beginTransaction();
            String hql = "DELETE FROM ProductConfiguration WHERE productItem = :newProductItem";
            Query query = session.createQuery(hql);
            query.setParameter("newProductItem", newProductItem);
            query.executeUpdate();
            tr.commit();
            session.flush();
        } catch (Exception e) {
            if (tr != null) {
                tr.rollback();
            }
            e.printStackTrace();
        }
    }

    public ProductConfiguration searchById(ProductItem pi, VariationOption vo) {
        ProductConfiguration productConfiguration = null;
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(ProductConfiguration.class);
        criteria.add(Restrictions.eq("productItem", pi));
        criteria.add(Restrictions.eq("variationOption", vo));
        productConfiguration = (ProductConfiguration) criteria.uniqueResult();
   
        return productConfiguration;
    }

}
