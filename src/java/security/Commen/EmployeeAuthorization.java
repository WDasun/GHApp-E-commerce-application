/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security.Commen;

import dao.FirstLevelAccessDAO;
import dao.SystemUserDAO;
import model.Employee;
import model.FirstLevelAccess;
import model.SystemUser;
import org.hibernate.Session;

/**
 *
 * @author ASUS
 */
public class EmployeeAuthorization {

    public boolean CheckFirstLevelAuthorization(String SystemUserName, String level) {
        boolean authoraization = false;
        try {
            SystemUser su = new SystemUserDAO().searchById(SystemUserName);
            FirstLevelAccess fla = new FirstLevelAccessDAO().searchByRoleAndLevel(su.getRole(), level);
            if (fla != null) {
                authoraization = true;
            } else {
                authoraization = false;
            }
        } catch (Exception e) {
            authoraization = false;
        }
        return authoraization;
    }
}
