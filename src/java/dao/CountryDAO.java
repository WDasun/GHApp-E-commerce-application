/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectionBuilder;
import java.util.List;
import model.Country;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ASUS
 */
public class CountryDAO {

    public Country searchById(String id) {
        Country country = null;
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(Country.class);
        criteria.add(Restrictions.eq("id", id));
        criteria.setCacheable(false);
        country = (Country) criteria.uniqueResult();

        return country;
    }

    public Country searchById(String id, Session session) {
        Country country = null;
        Criteria criteria = session.createCriteria(Country.class);
        criteria.add(Restrictions.eq("id", id));
        criteria.setCacheable(false);
        country = (Country) criteria.uniqueResult();
        return country;
    }

    public List<Country> getCoutryList() {
        List<Country> countryList = null;
        Query query = null;
        Session session = ConnectionBuilder.hibSession();
        try {
            query = session.createQuery("FROM Country");
        } catch (Exception e) {
            e.printStackTrace();
        }
        countryList = query.list();
  
        return countryList;
    }
}
