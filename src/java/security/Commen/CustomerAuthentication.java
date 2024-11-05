/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security.Commen;

import dao.CustomerDAO;
import model.Customer;

/**
 *
 * @author ASUS
 */
public class CustomerAuthentication {

    private String email;
    private String password;
    private Customer customer;

    public CustomerAuthentication(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public boolean check() {
        customer = new CustomerDAO().getCustomerIfAvailable(email, password);
        if(customer != null){
            return true;
        } else{
            return false;
        }
    }

    public Customer getCustomer() {
        return customer;
    }

}
