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
public class RoleDAO {

    public void save(Role role, FirstLevelAccess fla) throws Exception {
        Transaction tr = null;
        Session session = ConnectionBuilder.hibSession();
        try {
            tr = session.beginTransaction();
            // Role save
            session.save(role);
            //First level accesses save
            fla.setRole(role);
            session.save(fla);

            tr.commit();
            session.flush();
            session.refresh(role);
            session.refresh(fla);

        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
            throw e;
        }
    }

    public List<Role> getRoleList() {
        List<Role> roleList = null;
        Query query = null;
        Session session = ConnectionBuilder.hibSession();
        try {
            query = session.createQuery("FROM Role");
        } catch (Exception e) {
            e.printStackTrace();
        }
        roleList = query.list();

        return roleList;
    }

    public boolean checkAvailability(String roleId) {
        boolean status = false;
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(Role.class);;
        criteria.add(Restrictions.eq("roleId", roleId));
        criteria.setCacheable(false);
        status = criteria.uniqueResult() == null;

        return status;
    }

    public Role searchById(String id) {
        Role role = null;
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(Role.class);
        criteria.add(Restrictions.eq("roleId", id));
        role = (Role) criteria.uniqueResult();

        return role;
    }

    public Role searchById(String id, Session session) {
        Role role = null;
        Criteria criteria = session.createCriteria(Role.class);
        criteria.add(Restrictions.eq("roleId", id));
        criteria.setCacheable(false);
        role = (Role) criteria.uniqueResult();
        return role;
    }

    public void update(Role role, FirstLevelAccess fla) throws Exception{
        Transaction tr = null;
        Session session = ConnectionBuilder.hibSession();
        try {
            tr = session.beginTransaction();

            String hqlRole = "UPDATE Role SET "
                    + "roleName = :roleName,"
                    + " roleDescription = :roleDescription "
                    + "WHERE roleId = :roleId";
            Query queryRole = session.createQuery(hqlRole);
            queryRole.setParameter("roleName", role.getRoleName());
            queryRole.setParameter("roleDescription", role.getRoleDescription());
            queryRole.setParameter("roleId", role.getRoleId());
            queryRole.executeUpdate();

            String hqlFLA = "UPDATE FirstLevelAccess SET "
                    + "f1 = :f1,"
                    + "f2 = :f2,"
                    + "f3 = :f3,"
                    + "f4 = :f4,"
                    + "f5 = :f5,"
                    + " f6 = :f6"
                    + " WHERE id = :id";
            Query queryFLA = session.createQuery(hqlFLA);
            queryFLA.setParameter("f1", fla.isF1());
            queryFLA.setParameter("f2", fla.isF2());
            queryFLA.setParameter("f3", fla.isF3());
            queryFLA.setParameter("f4", fla.isF4());
            queryFLA.setParameter("f5", fla.isF5());
            queryFLA.setParameter("f6", fla.isF6());
            queryFLA.setParameter("id", fla.getId());
            queryFLA.executeUpdate();

            tr.commit();
            session.flush();
            session.refresh(role);
            session.refresh(fla);
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
            throw e;
        }
    }
}
