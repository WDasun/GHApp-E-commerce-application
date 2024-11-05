/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security.Validation;

import java.util.regex.Pattern;

/**
 *
 * @author ASUS
 */
public class EmailValidation {

    public boolean patternMatch(String email) {
        boolean status = false;

        String regPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*" 
        + "@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        status = Pattern.compile(regPattern)
                .matcher(email)
                .matches();

        return status;
    }
}
