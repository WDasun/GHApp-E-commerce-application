/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectionBuilder;
import model.CustomerOrder;
import model.Invoice;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ASUS
 */
public class InvoiceDAO {

    public void Save(Invoice invoice) throws Exception {
        Transaction tr = null;
        Session session = ConnectionBuilder.hibSession();
        try {
            tr = session.beginTransaction();
            session.save(invoice);
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
            throw e;
        }finally{
          
        }
    }

    public Invoice searchByInvoiceId(String invoiceId, Session session) {
        Invoice invoice = null;
        Criteria criteria = session.createCriteria(Invoice.class);
        criteria.add(Restrictions.eq("id", invoiceId));
        criteria.setCacheable(false);
        invoice = (Invoice) criteria.uniqueResult();
        return invoice;
    }

    public Invoice getInvoiceByCustomerOrder(CustomerOrder customerOrder) {
        Invoice invoice = null;
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(Invoice.class);
        criteria.add(Restrictions.eq("customerOrder", customerOrder));
        criteria.setCacheable(false);
        invoice = (Invoice) criteria.uniqueResult();
    
        return invoice;
    }
}
