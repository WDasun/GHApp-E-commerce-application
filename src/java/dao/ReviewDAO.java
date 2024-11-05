package dao;

import connection.ConnectionBuilder;
import java.util.List;
import model.Customer;
import model.ProductItem;
import model.Review;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Dasun Wimukthi
 */
public class ReviewDAO {

    public void save(Review review) {
        Transaction tr = null;
        Session session = ConnectionBuilder.hibSession();
        try {
            tr = session.beginTransaction();
            session.save(review);
            session.flush();
            tr.commit();
            System.out.println("Review Id : " + review.getId());
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }
    }

    public void update(Review review, Session session) {
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            String hql = "UPDATE Review SET"
                    + " commentTitle = :commentTitle,"
                    + " comment = :comment,"
                    + " date = :date,"
                    + " rate = :rate"
                    + " WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("commentTitle", review.getCommentTitle());
            query.setParameter("comment", review.getComment());
            query.setParameter("rate", review.getRate());
            query.setParameter("date", review.getDate());
            query.setParameter("id", review.getId());
            query.executeUpdate();
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }
    }

    public List<Review> getRiviewListForProduct(ProductItem productItem, Session session) throws Exception {

        List<Review> reviewList = null;
        Criteria criteria = session.createCriteria(Review.class);
        criteria.add(Restrictions.eq("productItem", productItem));
        reviewList = (List<Review>) criteria.list();

        return reviewList;
    }

    public Review getReviewByCustomerAndProductItem(Customer customer, ProductItem productItem, Session session) throws Exception {
        Review customerReviewForProductItem = null;
        Criteria criteria = session.createCriteria(Review.class);
        criteria.add(Restrictions.eq("customer", customer));
        criteria.add(Restrictions.eq("productItem", productItem));
        criteria.setCacheable(false);
        customerReviewForProductItem = (Review) criteria.uniqueResult();

        return customerReviewForProductItem;
    }

}
