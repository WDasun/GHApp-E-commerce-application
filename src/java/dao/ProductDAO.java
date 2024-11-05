/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectionBuilder;
import java.util.List;
import model.Product;
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
public class ProductDAO {

    public void save(Product product) {
        Transaction tr = null;
        Session session = ConnectionBuilder.hibSession();
        try {
            tr = session.beginTransaction();
            session.save(product);
            tr.commit();
            session.flush();
            session.refresh(product);
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        } finally {
           
        }
    }

    public long GetRowCount() {
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(Product.class);
        criteria.setProjection(org.hibernate.criterion.Projections.rowCount());
        long rowCount = (long) criteria.uniqueResult();
       
        return rowCount;

    }

    public List<Product> getProductList(Session session) {
        List<Product> productList = null;
        Query query = null;
        try {
            query = session.createQuery("FROM Product");
        } catch (Exception e) {
            e.printStackTrace();
        }
        productList = query.list();
        return productList;
    }

    public Product searchById(String id) {
        Product product = null;
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(Product.class);
        criteria.add(Restrictions.eq("id", id));
        product = (Product) criteria.uniqueResult();
     
        return product;
    }

    public void Update(String id,
            ProductCategory newproductCategory,
            String name,
            String newdescription,
            String newimagePathOne,
            String newimagePathTwo) {
        Transaction tr = null;
        Session session = ConnectionBuilder.hibSession();
        
        try {
            Product product = searchById(id);
            tr = session.beginTransaction();
            String hql = "";

            if ((newimagePathOne != null) && (newimagePathTwo != null)) {
                hql = "UPDATE Product SET "
                        + "productCategory = :newproductCategory,"
                        + "name = :newName,"
                        + "description = :newdescription,"
                        + "imagePathOne = :newimagePathOne,"
                        + "imagePathTwo = :newimagePathTwo WHERE id = :productId";
                System.out.println("Here 1");
            } else if ((newimagePathTwo == null) && (newimagePathOne == null)) {
                hql = "UPDATE Product SET "
                        + "productCategory = :newproductCategory,"
                        + "name = :newName,"
                        + "description = :newdescription WHERE id = :productId";
                System.out.println("Here 2");
            } else if (newimagePathOne == null) {
                hql = "UPDATE Product SET "
                        + "productCategory = :newproductCategory,"
                        + "name = :newName,"
                        + "description = :newdescription,"
                        + "imagePathTwo = :newimagePathTwo WHERE id = :productId";
                System.out.println("Here 3");
            } else if (newimagePathTwo == null) {
                hql = "UPDATE Product SET "
                        + "productCategory = :newproductCategory,"
                        + "name = :newName,"
                        + "description = :newdescription,"
                        + "imagePathOne = :newimagePathOne WHERE id = :productId";
                System.out.println("Here 4");
            }

            Query query = session.createQuery(hql);

            query.setParameter("newproductCategory", newproductCategory);
            query.setParameter("newName", name);
            query.setParameter("newdescription", newdescription);
            query.setParameter("productId", id);
            if (newimagePathOne != null) {
                System.out.println("Here second 1");
                query.setParameter("newimagePathOne", newimagePathOne);
            }
            if (newimagePathTwo != null) {
                System.out.println("Here second 2");
                query.setParameter("newimagePathTwo", newimagePathTwo);
            }

            query.executeUpdate();
            tr.commit();
            session.flush();
            session.refresh(product);
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
            Product product = searchById(id);
            tr = session.beginTransaction();
            String hql = "UPDATE Product SET status = :newValue WHERE id = :productId";
            Query query = session.createQuery(hql);
            query.setParameter("newValue", value);
            query.setParameter("productId", id);
            query.executeUpdate();
            tr.commit();
            session.flush();
            session.refresh(product);
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        } finally{
           
        }
    }

}
