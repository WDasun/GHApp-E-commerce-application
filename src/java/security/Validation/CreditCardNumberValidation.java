/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security.Validation;

/**
 *
 * @author ASUS
 */
public class CreditCardNumberValidation {

    public boolean isValidCreditCardCheckout(String value) {
        // accept only digits, dashes or spaces
        if (!value.matches("[0-9\\-\\s]+")) {
            return false;
        }

        // The Luhn Algorithm
        int nCheck = 0, nDigit = 0;
        boolean bEven = false;
        value = value.replaceAll("\\D", ""); // Remove non-digit characters

        for (int n = value.length() - 1; n >= 0; n--) {
            char cDigit = value.charAt(n);
            nDigit = Character.getNumericValue(cDigit);

            if (bEven) {
                nDigit *= 2;
                if (nDigit > 9) {
                    nDigit -= 9;
                }
            }

            nCheck += nDigit;
            bEven = !bEven;
        }

        return (nCheck % 10) == 0;
    }
}
