package dao;

import connection.ConnectionBuilder;
import java.util.List;
import model.Address;
import model.Customer;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class AddressDAO {

    public void save(Address address, Session session) throws Exception {
        Transaction tr = null;
        try {
            tr = session.getTransaction();
            if (tr == null || !tr.isActive()) {
                tr = session.beginTransaction();
            }
            session.save(address);
            session.flush();  
            tr.commit();
        } catch (Exception e) {
            if (tr != null && tr.isActive()) {
                tr.rollback();
            }
            e.printStackTrace();
            throw e;
        }
    }

    public List<Address> getSpecificCustomerAddressList(Customer customer, Session session) {
        List<Address> addressList = null;
        Criteria criteria = session.createCriteria(Address.class);
        criteria.add(Restrictions.eq("customer", customer));
        addressList = (List<Address>) criteria.list();
        return addressList;
    }

    public Address searchById(int id) throws Exception {
        Address address = null;

        Session session = ConnectionBuilder.hibSession();

        Criteria criteria = session.createCriteria(Address.class);
        criteria.add(Restrictions.eq("id", id));
        criteria.setCacheable(false);
        address = (Address) criteria.uniqueResult();
        if (address != null) {
            return address;
        } else {
            throw new NullPointerException();
        }
    }

    public void delete(Address address) throws Exception {
        Transaction tr = null;
        Session session = ConnectionBuilder.hibSession();
        try {
            tr = session.beginTransaction();
            session.delete(address);
            tr.commit();
        } catch (Exception e) {
            if (tr != null) {
                tr.rollback();
            }
            e.printStackTrace();
            throw e;
        }finally{

        }
    }

    public void update(Address address, Session session) throws Exception {
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            String hql = "UPDATE Address SET"
                    + " country = :country,"
                    + " addressLine1 = :addressLine1,"
                    + " addressLine2 = :addressLine2,"
                    + " city = :city,"
                    + " stateOrDistrict = :stateOrDistrict,"
                    + " postalCode = :postalCode,"
                    + " updatedAt = :updatedAt"
                    + " WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("country", address.getCountry());
            query.setParameter("addressLine1", address.getAddressLine1());
            query.setParameter("addressLine2", address.getAddressLine2());
            query.setParameter("city", address.getCity());
            query.setParameter("stateOrDistrict", address.getStateOrDistrict());
            query.setParameter("postalCode", address.getPostalCode());
            query.setParameter("updatedAt", address.getUpdatedAt());
            query.setParameter("id", address.getId());
            query.executeUpdate();
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
            throw e;
        }
    }

}
