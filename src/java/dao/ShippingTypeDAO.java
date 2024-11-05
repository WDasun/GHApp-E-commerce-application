/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectionBuilder;
import java.util.List;
import model.ShippingType;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ASUS
 */
public class ShippingTypeDAO {

    public ShippingType searchById(int id) throws Exception {
        ShippingType shippingType = null;
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(ShippingType.class);
        criteria.add(Restrictions.eq("id", id));
        criteria.setCacheable(false);
        shippingType = (ShippingType) criteria.uniqueResult();
        
        if (shippingType != null) {
            return shippingType;
        } else {
            throw new NullPointerException();
        }
    }

    public List<ShippingType> ShippingTypeList() throws Exception {
        List<ShippingType> shippingTypeList = null;
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(ShippingType.class);
        criteria.setCacheable(false);
        shippingTypeList = (List<ShippingType>) criteria.list();
     
        if (shippingTypeList != null) {
            return shippingTypeList;
        } else {
            throw new NullPointerException();
        }
    }
}
