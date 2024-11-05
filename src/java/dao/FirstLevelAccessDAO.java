/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectionBuilder;
import java.util.List;
import model.FirstLevelAccess;
import model.Role;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ASUS
 */
public class FirstLevelAccessDAO {

    public void save(FirstLevelAccess fla) {
        Transaction tr = null;
        Session session = ConnectionBuilder.hibSession();
        try {
            tr = session.beginTransaction();
            session.save(fla);
            tr.commit();
            session.flush();
            session.refresh(fla);

        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }
    }

    public void update(FirstLevelAccess fla) {
        Transaction tr = null;
        Session session = ConnectionBuilder.hibSession();
        try {
            tr = session.beginTransaction();
            String hql = "UPDATE FirstLevelAccess SET"
                    + " f1 = :f1,"
                    + " f2 = :f2,"
                    + " f3 = :f3,"
                    + " f4 = :f4,"
                    + " f5 = :f5,"
                    + " f6 = :f6"
                    + " WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("f1", fla.isF1());
            query.setParameter("f2", fla.isF2());
            query.setParameter("f3", fla.isF3());
            query.setParameter("f4", fla.isF4());
            query.setParameter("f5", fla.isF5());
            query.setParameter("f6", fla.isF6());
            query.setParameter("id", fla.getId());
            query.executeUpdate();
            tr.commit();
            session.flush();
            session.refresh(fla);
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }
    }

    public FirstLevelAccess searchByRole(Role role) {
        FirstLevelAccess fla = null;
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(FirstLevelAccess.class);
        criteria.add(Restrictions.eq("role", role));
        fla = (FirstLevelAccess) criteria.uniqueResult();

        return fla;
    }
    
        public FirstLevelAccess searchByRoleAndLevel(Role role, String level) {
        FirstLevelAccess fla = null;
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(FirstLevelAccess.class);
        criteria.add(Restrictions.eq("role", role));
        criteria.add(Restrictions.eq(level, true));
        fla = (FirstLevelAccess) criteria.uniqueResult();

        return fla;
    }
}
