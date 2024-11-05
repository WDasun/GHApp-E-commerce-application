/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectionBuilder;
import java.util.List;
import model.CardDetails;
import model.CreditOrDebitCardType;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ASUS
 */
public class CardTypeDAO {

    public CreditOrDebitCardType searchById(int id) throws Exception {
        CreditOrDebitCardType cardType = null;
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(CreditOrDebitCardType.class);
        criteria.add(Restrictions.eq("id", id));
        criteria.setCacheable(false);
        cardType = (CreditOrDebitCardType) criteria.uniqueResult();
        if (cardType != null) {
            return cardType;
        } else {
            throw new NullPointerException();
        }
    }
    
    public List<CreditOrDebitCardType> getTypeList() throws Exception {
        List<CreditOrDebitCardType> cardType = null;
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(CreditOrDebitCardType.class);
        criteria.setCacheable(false);
        cardType = (List<CreditOrDebitCardType>) criteria.list();
        if (cardType != null) {
            return cardType;
        } else {
            throw new NullPointerException();
        }
    }

}
