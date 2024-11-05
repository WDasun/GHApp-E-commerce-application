/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectionBuilder;
import java.util.List;
import model.OrderStatus;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ASUS
 */
public class OrderStatusDAO {

    public OrderStatus searchById(int id) throws Exception {
        OrderStatus orderStatus = null;
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(OrderStatus.class);
        criteria.add(Restrictions.eq("id", id));
        criteria.setCacheable(false);
        orderStatus = (OrderStatus) criteria.uniqueResult();
 
        return orderStatus;
    }

    public OrderStatus searchById(int id, Session session) throws Exception {
        OrderStatus orderStatus = null;
        Criteria criteria = session.createCriteria(OrderStatus.class);
        criteria.add(Restrictions.eq("id", id));
        criteria.setCacheable(false);
        orderStatus = (OrderStatus) criteria.uniqueResult();
        return orderStatus;
    }

    public List<OrderStatus> getOrderStatusList(Session session) throws Exception {
        List<OrderStatus> orderStatusList = null;
        Criteria criteria = session.createCriteria(OrderStatus.class);
        criteria.setCacheable(false);
        orderStatusList = (List<OrderStatus>) criteria.list();
        return orderStatusList;
    }
}
