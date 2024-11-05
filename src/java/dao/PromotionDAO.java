/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectionBuilder;
import java.util.List;
import model.ProductCategory;
import model.Promotion;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ASUS
 */
public class PromotionDAO {
    
    public void save(ProductCategory category, Promotion promotion) throws Exception {
        Transaction tr = null;
        Session session = null;
        try {
            session = ConnectionBuilder.hibSession();
            tr = session.beginTransaction();

            promotion.getProductCategories().add(category);

            session.save(promotion);
                    
            tr.commit();
            session.flush();
            session.refresh(promotion);
            session.refresh(category);
        } catch (Exception e) {
            if (tr != null && tr.isActive()) {
                tr.rollback();
            }
            throw e;
        }finally{
        }
    }
    
    public void delete(ProductCategory category, Promotion promotion) throws Exception {
        Transaction tr = null;
        Session session = null;
        try {
            session = ConnectionBuilder.hibSession();
            tr = session.beginTransaction();

            promotion.getProductCategories().remove(category);

            session.delete(promotion);
                    
            tr.commit();
            session.flush();
            session.refresh(category);
        } catch (Exception e) {
            if (tr != null && tr.isActive()) {
                tr.rollback();
            }
            throw e;
        }finally{
        }
    }

    public void update(Promotion promotion) throws Exception {
        Transaction tr = null;
        try {
            Session session = ConnectionBuilder.hibSession();
            tr = session.beginTransaction();
            String hql = "UPDATE Promotion SET"
                    + " name = :name,"
                    + " description = :description,"
                    + " discountRate = :discountRate,"
                    + " startDate = :startDate,"
                    + " endDate = :endDate"
                    + " WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("name", promotion.getName());
            query.setParameter("description", promotion.getDescription());
            query.setParameter("discountRate", promotion.getDiscountRate());
            query.setParameter("startDate", promotion.getStartDate());
            query.setParameter("endDate", promotion.getEndDate());
            query.setParameter("id", promotion.getId());
            query.executeUpdate();
            tr.commit();
            session.flush();
            session.refresh(promotion);
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
            throw e;
        }
    }
    
    public Promotion getRelatedPromotion(int id) throws Exception {
        Promotion promotion = null;
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(Promotion.class);
        criteria.add(Restrictions.eq("id", id));
        promotion = (Promotion) criteria.uniqueResult();
    
        if (promotion != null) {
            return promotion;
        } else {
            throw new NullPointerException("Promotion not found !");
        }
    }
    
    public List<Promotion> getPromotionList() {
        List<Promotion> promotionList = null;
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(Promotion.class);
        promotionList = (List<Promotion>) criteria.list();
        
        return promotionList;
    }
}
