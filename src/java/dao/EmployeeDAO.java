/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectionBuilder;
import java.util.List;
import model.Employee;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ASUS
 */
public class EmployeeDAO {

    public void update(Employee employee) {
        Transaction tr = null;
        Session session = ConnectionBuilder.hibSession();
        try {
            tr = session.beginTransaction();
            String hql = "";
            if (employee.getProfilePicturePath() != null) {
                hql = "UPDATE Employee SET "
                        + "fname = :fname,"
                        + "lname = :lname,"
                        + "nic = :nic,"
                        + "dob = :dob,"
                        + "gender = :gender,"
                        + "email = :email,"
                        + "address = :address,"
                        + "cntOne = :cntOne,"
                        + "cntTwo = :cntTwo,"
                        + "profilePicturePath = :profilePicturePath,"
                        + "lastUpdate = :lastUpdate"
                        + " WHERE employeeId = :employeeId";
            } else {
                hql = "UPDATE Employee SET "
                        + "fname = :fname,"
                        + "lname = :lname,"
                        + "nic = :nic,"
                        + "dob = :dob,"
                        + "gender = :gender,"
                        + "email = :email,"
                        + "address = :address,"
                        + "cntOne = :cntOne,"
                        + "cntTwo = :cntTwo,"
                        + "lastUpdate = :lastUpdate"
                        + " WHERE employeeId = :employeeId";
            }

            Query query = session.createQuery(hql);
            query.setParameter("employeeId", employee.getEmployeeId());
            query.setParameter("fname", employee.getFname());
            query.setParameter("lname", employee.getLname());
            query.setParameter("nic", employee.getNic());
            query.setParameter("dob", employee.getDob());
            query.setParameter("gender", employee.getGender());
            query.setParameter("email", employee.getEmail());
            query.setParameter("address", employee.getAddress());
            query.setParameter("cntOne", employee.getCntOne());
            query.setParameter("cntTwo", employee.getCntTwo());
            if (employee.getProfilePicturePath() != null) {
                query.setParameter("profilePicturePath", employee.getProfilePicturePath());
            }
            query.setParameter("lastUpdate", employee.getLastUpdate());
            query.executeUpdate();
            session.flush();
            tr.commit();
             System.out.println("Session : " + session.hashCode());
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        } finally {

        }
    }

    public void save(Employee employee) {
        Transaction tr = null;
        Session session = ConnectionBuilder.hibSession();
        try {
            tr = session.beginTransaction();
            session.save(employee);
            tr.commit();
            session.flush();
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        } finally {

        }
    }

    public long GetRowCount() {
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(Employee.class);
        criteria.setProjection(org.hibernate.criterion.Projections.rowCount());
        criteria.setCacheable(false);
        long rowCount = (long) criteria.uniqueResult();

        return rowCount;

    }

    public List<Employee> getEmployeeList() {
        List<Employee> employeeList = null;
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(Employee.class);
        employeeList = criteria.list();

        return employeeList;
    }

    public void updateStatus(boolean status, Employee emp) {
        Transaction tr = null;
        Session session = ConnectionBuilder.hibSession();
        try {
            tr = session.beginTransaction();
            String hql = "UPDATE Employee SET status = :status WHERE employeeId = :employeeId";
            Query query = session.createQuery(hql);
            query.setParameter("status", status);
            query.setParameter("employeeId", emp.getEmployeeId());
            query.executeUpdate();
            session.flush();
            session.refresh(emp);
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        } finally {

        }
    }

    public Employee searchById(String id) {
        Employee employee = null;
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(Employee.class);
        criteria.add(Restrictions.eq("id", id));
        employee = (Employee) criteria.uniqueResult();

        return employee;
    }

    public Employee searchById(String id, Session session) {
        Employee employee = null;
        Criteria criteria = session.createCriteria(Employee.class);
        criteria.add(Restrictions.eq("id", id));
        criteria.setCacheable(false);
        employee = (Employee) criteria.uniqueResult();
        return employee;
    }

    public String loadEmployeeList() {
        List<Employee> employeeList = new EmployeeDAO().getEmployeeList();
        String html = "<option value=\"\" selected disabled>Select Employee</option>";
        for (Employee employee : employeeList) {
            if (new SystemUserDAO().checkAvailabilityEmplyeeHasUserAccount(employee)) {
                html += "<option value=\"" + employee.getEmployeeId() + "\">" + employee.getEmployeeId() + " : " + employee.getFname() + " " + employee.getLname() + "</option>";
            }
        }
        return html;
    }
}
