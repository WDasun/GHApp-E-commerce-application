/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.StatelessSession;

/**
 *
 * @author ASUS
 */
public class ConnectionBuilder {

    private static Session session;
    public static Session hibSession() {
        if (session == null || !session.isOpen()) {
            session = connection.NewHibernateUtil.getSessionFactory().openSession();
        }
        return session;
    }
}
