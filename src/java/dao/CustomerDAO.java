/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectionBuilder;
import java.util.Date;
import java.util.List;
import model.Customer;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Dasun Wimukthi
 */
public class CustomerDAO {

    public void save(Customer customer) throws Exception {
        Transaction tr = null;
        Session session = ConnectionBuilder.hibSession();
        try {
            tr = session.beginTransaction();
            session.save(customer);
            session.flush();
            tr.commit();
        } catch (Exception e) {
            if (tr != null && tr.isActive()) {
                tr.rollback();
            }
            throw e;
        } finally {

        }
    }

    public List<Customer> getCustomerList() {
        List<Customer> customerList = null;
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(Customer.class);
        customerList = (List<Customer>) criteria.list();

        return customerList;
    }

    public boolean checkEmailAvailability(String email) {
        boolean status = false;
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.eq("email", email));
        criteria.setCacheable(false);
        status = criteria.uniqueResult() == null;

        return status;
    }

    public Customer searchByEmail(String email) {
        Customer customer = null;
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.eq("email", email));
        customer = (Customer) criteria.uniqueResult();

        return customer;
    }

    public Customer searchByEmail(String email, Session session) {
        Customer customer = null;
        Criteria criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.eq("email", email));
        criteria.setCacheable(false);
        customer = (Customer) criteria.uniqueResult();
        return customer;
    }

    public Customer getCustomerIfAvailable(String email, String password) {
        Customer customer = null;
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.eq("email", email));
        criteria.add(Restrictions.eq("password", password));
        criteria.setCacheable(false);
        customer = (Customer) criteria.uniqueResult();

        return customer;
    }

    public void update(Customer customer) throws Exception {
        Transaction tr = null;
        Session session = ConnectionBuilder.hibSession();
        try {
            tr = session.beginTransaction();
            String hql = "UPDATE Customer SET "
                    + "fname = :fname,"
                    + "lname = :lname,"
                    + "email = :email,"
                    + "cntNumber = :cntNumber,"
                    + "dob = :dob,"
                    + "updatedAt = :updatedAt"
                    + " WHERE customerId = :customerId";

            Query query = session.createQuery(hql);
            query.setParameter("customerId", customer.getCustomerId());
            query.setParameter("fname", customer.getFname());
            query.setParameter("lname", customer.getLname());
            query.setParameter("email", customer.getEmail());
            query.setParameter("dob", customer.getDob());
            query.setParameter("updatedAt", customer.getUpdatedAt());
            query.setParameter("cntNumber", customer.getCntNumber());

            query.executeUpdate();
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
            throw e;
        } finally {

        }
    }

    public void ChangeStatus(Customer customer, boolean status) throws Exception {
        Transaction tr = null;
        Session session = ConnectionBuilder.hibSession();
        try {
            tr = session.beginTransaction();
            String hql = "UPDATE Customer SET "
                    + "status = :status"
                    + " WHERE customerId = :customerId";

            Query query = session.createQuery(hql);
            query.setParameter("customerId", customer.getCustomerId());
            query.setParameter("status", status);

            query.executeUpdate();
            tr.commit();
            session.flush();
            session.refresh(customer);
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
            throw e;
        }
    }

    public void updatePassword(String newPassword, int customerId) throws Exception {
        Transaction tr = null;
        Session session = ConnectionBuilder.hibSession();
        try {
            tr = session.beginTransaction();
            String hql = "UPDATE Customer SET "
                    + "password = :password,"
                    + "updatedAt = :updatedAt"
                    + " WHERE customerId = :customerId";

            Query query = session.createQuery(hql);
            query.setParameter("customerId", customerId);
            query.setParameter("password", newPassword);
            query.setParameter("updatedAt", new Date());

            query.executeUpdate();
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
            throw e;
        } finally {

        }
    }

    public Customer searchById(int customerId) throws Exception {
        Customer customer = null;
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.eq("customerId", customerId));
        customer = (Customer) criteria.uniqueResult();

        if (customer != null) {
            return customer;
        } else {
            throw new NullPointerException();
        }
    }

    public Customer searchById(int customerId, Session session) {
        Customer customer = null;
        Criteria criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.eq("customerId", customerId));
        customer = (Customer) criteria.uniqueResult();
        return customer;

    }
}
