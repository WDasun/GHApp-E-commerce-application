/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.util.Random;

/**
 *
 * @author ASUS
 */
public class CreateRecoveryCode {
        public String create(int length) {
        Random random = new Random();

        String password = "";

        for (int i = 0; i < length; i++) {

            int numberChanse = random.nextInt(2);
            if (numberChanse == 1) {
                int randomNumber = random.nextInt(10);
                password += randomNumber;
            }
            if (numberChanse == 0) {
                int num = 65 + random.nextInt(26);
                char c = (char) num;
                password += c;
            }

        }
        return password;
    }
}
