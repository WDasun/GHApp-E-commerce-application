/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectionBuilder;
import model.Customer;
import model.Recovery;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ASUS
 */
public class RecoveryDAO {

    public void save(Recovery recovery, Session session) throws Exception {
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            session.save(recovery);
            tr.commit();
            session.flush();
            session.refresh(recovery);
        } catch (Exception e) {
            if (tr != null && tr.isActive()) {
                tr.rollback();
            }
            throw e;
        }
    }

    public Recovery searchByCustomer(Customer customer, Session session) throws Exception {
        Recovery recovery = null;
        Criteria criteria = session.createCriteria(Recovery.class);
        criteria.add(Restrictions.eq("customer", customer));
        criteria.setCacheable(false);
        recovery = (Recovery) criteria.uniqueResult();
        return recovery;
    }

    public Recovery searchByRecoveryCode(String recoveryCode, Session session) throws Exception {
        Recovery recovery = null;
        Criteria criteria = session.createCriteria(Recovery.class);
        criteria.add(Restrictions.eq("recoveryCode", recoveryCode));
        criteria.setCacheable(false);
        recovery = (Recovery) criteria.uniqueResult();
        return recovery;
    }

    public void delete(Recovery recovery, Session session) throws Exception {
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            session.delete(recovery);
            session.flush();
            tr.commit();
        } catch (Exception e) {
            if (tr != null) {
                tr.rollback();
            }
            e.printStackTrace();
            throw e;
        }
    }
}
