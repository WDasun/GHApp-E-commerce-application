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
public class PasswordValidation {

    public boolean patternMatch(String password) {
        boolean status = false;

        String regPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%?&]{6,}$";

        status = Pattern.compile(regPattern)
                .matcher(password)
                .matches();

        return status;
    }
}
