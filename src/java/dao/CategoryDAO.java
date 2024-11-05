/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectionBuilder;
import java.util.List;
import model.ProductCategory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ASUS
 */
public class CategoryDAO {
    
    public List<ProductCategory> getCategoryList() {
        List<ProductCategory> productCategoryList = null;
        Query query = null;
        Session session = ConnectionBuilder.hibSession();
        try {
            query = session.createQuery("FROM ProductCategory");
        } catch (Exception e) {
            e.printStackTrace();
        }
        productCategoryList = query.list();
        return productCategoryList;
    }

    public List<ProductCategory> getParentCategoryList() {
        List<ProductCategory> ParentProductCategoryList = null;
        Query query = null;
        Session session = ConnectionBuilder.hibSession();
        try {
            query = session.createQuery("FROM ProductCategory WHERE productCategory is NULL");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ParentProductCategoryList = query.list();
        return ParentProductCategoryList;
    }

    public List<ProductCategory> getSubCategoryList(Session session) {
        List<ProductCategory> subProductCategoryList = null;
        Query query = null;
        try {
            query = session.createQuery("FROM ProductCategory WHERE productCategory is NOT NULL");
        } catch (Exception e) {
            e.printStackTrace();
        }
        subProductCategoryList = query.list();
        return subProductCategoryList;
    }

    public List<ProductCategory> getSubCategoryListOfParent(ProductCategory parentCategory) {
        List<ProductCategory> subCategoryListOfParent = null;
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(ProductCategory.class);
        criteria.add(Restrictions.eq("productCategory", parentCategory));
        criteria.setCacheable(false);
        subCategoryListOfParent = (List<ProductCategory>) criteria.list();

        return subCategoryListOfParent;
    }

    public void save(ProductCategory productcategory) {
        Transaction tr = null;
        Session session = ConnectionBuilder.hibSession();
        try {
            tr = session.beginTransaction();
            session.save(productcategory);
            tr.commit();
            session.flush();
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }finally{
      
        }
    }

    public void update(String name, String id, ProductCategory pc) {
        Transaction tr = null;
        Session session = ConnectionBuilder.hibSession();
        try {
            tr = session.beginTransaction();
            ProductCategory category = searchById(id, session);
            if (pc == null) {
                // Update parent
                String hql = "UPDATE ProductCategory SET name = :newName, productCategory = NULL WHERE id = :categoryId";
                Query query = session.createQuery(hql);
                query.setParameter("newName", name);
                query.setParameter("categoryId", category.getId());
                query.executeUpdate();
            } else {
                // Update sub
                String hql = "UPDATE ProductCategory SET name = :newName, productCategory = :newPc WHERE id = :categoryId";
                Query query = session.createQuery(hql);
                query.setParameter("newName", name);
                query.setParameter("categoryId", category.getId());
                query.setParameter("newPc", pc);
                query.executeUpdate();
            }

            tr.commit();
            session.flush();
            session.refresh(category);
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }finally{
   
        }
    }

    public void updateStatus(boolean value, String id) {
        Transaction tr = null;
        Session session = ConnectionBuilder.hibSession();
        try {
            ProductCategory pc = searchById(id, session);
            tr = session.beginTransaction();
            String hql = "UPDATE ProductCategory SET status = :newValue WHERE id = :categoryId";
            Query query = session.createQuery(hql);
            query.setParameter("newValue", value);
            query.setParameter("categoryId", pc.getId());
            query.executeUpdate();
            tr.commit();
            session.flush();
            session.refresh(pc);
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }finally{
           
        }
    }

    public long GetRowCount() {
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(ProductCategory.class);
        criteria.setProjection(org.hibernate.criterion.Projections.rowCount());
        long rowCount = (long) criteria.uniqueResult();   
        return rowCount;

    }

    public ProductCategory searchById(String id, Session session) {
        ProductCategory productCategory = null;

        Criteria criteria = session.createCriteria(ProductCategory.class);
        criteria.add(Restrictions.eq("id", id));
        criteria.setCacheable(false);
        productCategory = (ProductCategory) criteria.uniqueResult();

        return productCategory;
    }

    public boolean checkCategoryIsParent(String id) {
        boolean status = false;
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(ProductCategory.class);
        criteria.add(Restrictions.eq("id", id));
        criteria.add(Restrictions.isNull("productCategory"));
        criteria.setCacheable(false);
        status = criteria.uniqueResult() != null;
      
        return status;
    }
}
