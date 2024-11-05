/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectionBuilder;
import java.util.Date;
import java.util.List;
import model.Employee;
import model.SystemUser;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ASUS
 */
public class SystemUserDAO {

    public void update(SystemUser systemUser, Session session) throws Exception {
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            String hql = "UPDATE SystemUser SET"
                    + " role = :role,"
                    + " lastUpdate = :lastUpdate"
                    + " WHERE userName = :userName";
            Query query = session.createQuery(hql);
            query.setParameter("role", systemUser.getRole());
            query.setParameter("lastUpdate", new Date());
            query.setParameter("userName", systemUser.getUserName());
            query.executeUpdate();
            tr.commit();
            session.refresh(systemUser);
            session.flush();
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
            throw e;
        }
    }

    public void updateStatus(SystemUser systemUser) throws Exception {
        Transaction tr = null;
        try {
            Session session = ConnectionBuilder.hibSession();
            tr = session.beginTransaction();
            String hql = "UPDATE SystemUser SET"
                    + " status = :status"
                    + " WHERE userName = :userName";
            Query query = session.createQuery(hql);
            query.setParameter("status", systemUser.isStatus());
            query.setParameter("userName", systemUser.getUserName());
            query.executeUpdate();
            tr.commit();
            session.flush();
            session.refresh(systemUser);

        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
            throw e;
        }
    }
    
    public void updatePassword(SystemUser systemUser) throws Exception {
        Transaction tr = null;
        try {
            Session session = ConnectionBuilder.hibSession();
            tr = session.beginTransaction();
            String hql = "UPDATE SystemUser SET"
                    + " userPassword = :userPassword"
                    + " WHERE userName = :userName";
            Query query = session.createQuery(hql);
            query.setParameter("userPassword", systemUser.getUserPassword());
            query.setParameter("userName", systemUser.getUserName());
            query.executeUpdate();
            tr.commit();
            session.flush();
            session.refresh(systemUser);

        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
            throw e;
        }
    }

    public void save(SystemUser systemUser) {
        Transaction tr = null;
        Session session = ConnectionBuilder.hibSession();
        try {
            tr = session.beginTransaction();
            session.save(systemUser);
            tr.commit();
            session.flush();
            session.refresh(systemUser);
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }
    }

    public List<SystemUser> getSystemUserList(Session session) {
        List<SystemUser> systemUserList = null;
        Query query = null;
        try {
            query = session.createQuery("FROM SystemUser");
        } catch (Exception e) {
            e.printStackTrace();
        }
        systemUserList = query.list();
        return systemUserList;
    }

    public boolean checkAvailability(String userName) {
        boolean status = false;
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(SystemUser.class);
        criteria.add(Restrictions.eq("userName", userName));
        status = criteria.uniqueResult() == null;

        return status;
    }

    public SystemUser searchSystemUserByUserNameAndPassword(String userName, String userPassword, Session session) {
        SystemUser systemUser;
        Criteria criteria = session.createCriteria(SystemUser.class);
        criteria.add(Restrictions.eq("userName", userName));
        criteria.add(Restrictions.eq("userPassword", userPassword));
        criteria.setCacheable(false);
        systemUser = (SystemUser) criteria.uniqueResult();
        return systemUser;
    }

    public boolean checkAvailabilityEmplyeeHasUserAccount(Employee employee) {
        boolean status = false;
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(SystemUser.class);
        criteria.add(Restrictions.eq("employee", employee));
        status = criteria.uniqueResult() == null;

        return status;
    }

    public SystemUser searchById(String id) {
        SystemUser systemUser = null;
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(SystemUser.class);
        criteria.add(Restrictions.eq("userName", id));
        criteria.setCacheable(false);
        systemUser = (SystemUser) criteria.uniqueResult();

        return systemUser;
    }

    public SystemUser searchById(String id, Session session) {
        SystemUser systemUser = null;
        Criteria criteria = session.createCriteria(SystemUser.class);
        criteria.add(Restrictions.eq("userName", id));
        criteria.setCacheable(false);
        systemUser = (SystemUser) criteria.uniqueResult();
        if (systemUser != null) {
            session.refresh(systemUser);
        }
        return systemUser;
    }

}
