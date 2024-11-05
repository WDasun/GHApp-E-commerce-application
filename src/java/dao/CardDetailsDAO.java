/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectionBuilder;
import java.util.List;
import model.CardDetails;
import model.Customer;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ASUS
 */
public class CardDetailsDAO {

    public CardDetails searchById(int id) throws Exception {
        CardDetails cardDetails = null;
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(CardDetails.class);
        criteria.add(Restrictions.eq("id", id));
        criteria.setCacheable(false);
        cardDetails = (CardDetails) criteria.uniqueResult();
        if (cardDetails != null) {
            return cardDetails;
        } else {
            throw new NullPointerException();
        }
    }

    public CardDetails SearchByCardNumber(String cardNumber) {
        CardDetails cardDetails = null;
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(CardDetails.class);
        criteria.add(Restrictions.eq("cardNumber", cardNumber));
        criteria.setCacheable(false);
        cardDetails = (CardDetails) criteria.uniqueResult();
        return cardDetails;
    }

    public List<CardDetails> searchByCustomer(Customer customer) throws Exception {
        List<CardDetails> cardDetailsList = null;
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(CardDetails.class);
        criteria.add(Restrictions.eq("customer", customer));
        criteria.setCacheable(false);
        cardDetailsList = (List<CardDetails>) criteria.list();
        if (cardDetailsList != null) {
            return cardDetailsList;
        } else {
            throw new NullPointerException();
        }
    }

    public void save(CardDetails cardDetails) throws Exception {
        Transaction tr = null;
        Session session = null;
        try {
            session = ConnectionBuilder.hibSession();
            tr = session.beginTransaction();
            session.save(cardDetails);
            session.flush();
            tr.commit();
        } catch (Exception e) {
            if (tr != null && tr.isActive()) {
                tr.rollback();
            }
            throw e;
        }finally{
        }
    }
    
    public void delete(CardDetails cardDetails) throws Exception {
        Transaction tr = null;
        Session session = null;
        try {
            session = ConnectionBuilder.hibSession();
            tr = session.beginTransaction();
            session.delete(cardDetails);
            session.flush();
            tr.commit();
        } catch (Exception e) {
            if (tr != null && tr.isActive()) {
                tr.rollback();
            }
            throw e;
        }finally{
        }
    }
    
        public void update(CardDetails cardDetails, Session session) throws Exception {
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            String hql = "UPDATE CardDetails SET"
                    + " creditOrDebitCardType = :creditOrDebitCardType,"
                    + " cardNumber = :cardNumber,"
                    + " cardName = :cardName,"
                    + " expYear = :expYear,"
                    + " expMonth = :expMonth"
                    + " WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("creditOrDebitCardType", cardDetails.getCreditOrDebitCardType());
            query.setParameter("cardNumber", cardDetails.getCardNumber());
            query.setParameter("cardName", cardDetails.getCardNumber());
            query.setParameter("expYear", cardDetails.getExpYear());
            query.setParameter("expMonth", cardDetails.getExpMonth());
            query.setParameter("id", cardDetails.getId());
            query.executeUpdate();
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
            throw e;
        }
    }
}
