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
public class MobileNumberValidation {
     public boolean patternMatch(String number) {
        boolean status = false;

        String regPattern = "[0-9]{10}";

        status = Pattern.compile(regPattern)
                .matcher(number)
                .matches();

        return status;
    }
}
