/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectionBuilder;
import model.OrderLine;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ASUS
 */
public class OrderLineDAO {

    public void Save(OrderLine orderLine) throws Exception {
        Transaction tr = null;
        Session session = ConnectionBuilder.hibSession();
        try {
            tr = session.beginTransaction();
            session.save(orderLine);
            tr.commit();
            session.flush();
            session.refresh(orderLine);
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
            throw e;
        } finally{
 
        }
    }
    
}
