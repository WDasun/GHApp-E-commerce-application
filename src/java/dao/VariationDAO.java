/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectionBuilder;
import java.util.List;
import model.ProductCategory;
import model.Variation;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ASUS
 */
public class VariationDAO {

    public void save(Variation variation) {
        Transaction tr = null;
        Session session = ConnectionBuilder.hibSession();
        try {
            tr = session.beginTransaction();
            session.save(variation);
            tr.commit();
            session.flush();
            session.refresh(variation);  
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }
    }

    public long GetRowCount() {
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(Variation.class);
        criteria.setProjection(org.hibernate.criterion.Projections.rowCount());
        long rowCount = (long) criteria.uniqueResult();
       
        return rowCount;

    }

    public List<Variation> getVariationList(Session session) {
        List<Variation> variationList = null;
        Query query = null;
        try {
            query = session.createQuery("FROM Variation");
        } catch (Exception e) {
            e.printStackTrace();
        }
        variationList = query.list();
        return variationList;
    }

    public List<Variation> getVariationListByCategory(ProductCategory productCategory) {
        List<Variation> variationList = null;
        Query query = null;
        Session session = ConnectionBuilder.hibSession();
        try {
            query = session.createQuery("FROM Variation WHERE productCategory = :productCategory");
            query.setParameter("productCategory", productCategory);
        } catch (Exception e) {
            e.printStackTrace();
        }
        variationList = query.list();
        
        return variationList;
    }

    public boolean checkAvailability(ProductCategory productCategory, String name) {
        boolean status = false;
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(Variation.class);
        criteria.add(Restrictions.eq("productCategory", productCategory));
        criteria.add(Restrictions.eq("name", name));
        status = criteria.uniqueResult() != null;
        
        return status;
    }

    public Variation searchById(int id) {
        Variation variation = null;
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(Variation.class);
        criteria.add(Restrictions.eq("id", id));
        variation =(Variation) criteria.uniqueResult();
        
        return variation;
    }

    public void update(int id, String name, ProductCategory ProductCategory) {
        Transaction tr = null;
        Session session = ConnectionBuilder.hibSession();
        try {
            Variation variation = searchById(id);
            tr = session.beginTransaction();
            String hql = "UPDATE Variation SET name = :newName, productCategory = :newCategory WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("newName", name);
            query.setParameter("newCategory", ProductCategory);
            query.setParameter("id", id);
            query.executeUpdate();
            tr.commit();
            session.flush();
            session.refresh(variation);
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }
    }
}
