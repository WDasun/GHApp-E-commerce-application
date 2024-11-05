/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectionBuilder;
import java.util.List;
import model.Variation;
import model.VariationOption;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ASUS
 */
public class VariationOptionDAO {

    public void save(VariationOption variationOption) {
        Transaction tr = null;
        Session session = ConnectionBuilder.hibSession();
        try {
            tr = session.beginTransaction();
            session.save(variationOption);
            tr.commit();
            session.flush();
            session.refresh(variationOption);
            session.refresh(variationOption.getVariation());
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }
    }

    public List<VariationOption> getVariationOptionList(Session session) {
        List<VariationOption> variationOptionList = null;
        Query query = null;
        try {
            query = session.createQuery("FROM VariationOption");
        } catch (Exception e) {
            e.printStackTrace();
        }
        variationOptionList = query.list();
        return variationOptionList;
    }

    public List<VariationOption> getVariationOptionListByVariation(Variation variation) {
        List<VariationOption> variationOptionList = null;
        Query query = null;
        Session session = ConnectionBuilder.hibSession();
        try {
            query = session.createQuery("FROM VariationOption WHERE variation = :variation");
            query.setParameter("variation", variation);
        } catch (Exception e) {
            e.printStackTrace();
        }
        variationOptionList = query.list();

        return variationOptionList;
    }

    public boolean checkAvailability(Variation variation, String value) {
        boolean status = false;
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(VariationOption.class);
        criteria.add(Restrictions.eq("variation", variation));
        criteria.add(Restrictions.eq("value", value));
        status = criteria.uniqueResult() != null;

        return status;
    }

    public VariationOption searchById(int id) {
        VariationOption variationOption = null;
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(VariationOption.class);
        criteria.add(Restrictions.eq("id", id));
        variationOption = (VariationOption) criteria.uniqueResult();

        return variationOption;
    }

    public VariationOption searchById(int id, Session session) {
        VariationOption variationOption = null;
        Criteria criteria = session.createCriteria(VariationOption.class);
        criteria.add(Restrictions.eq("id", id));
        variationOption = (VariationOption) criteria.uniqueResult();
        return variationOption;
    }

    public void update(int id, String value, Variation variation) {
        Transaction tr = null;
        Session session = ConnectionBuilder.hibSession();
        try {
            VariationOption vo = searchById(id);
            tr = session.beginTransaction();
            String hql = "UPDATE VariationOption SET variation = :newVariation, value = :newValue WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("newValue", value);
            query.setParameter("newVariation", variation);
            query.setParameter("id", id);
            query.executeUpdate();
            tr.commit();
            session.flush();
            session.refresh(vo);
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }
    }
}
