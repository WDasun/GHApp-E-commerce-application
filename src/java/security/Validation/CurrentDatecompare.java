/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security.Validation;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class CurrentDatecompare {

    public boolean beforCurrentDate(int year, int month, int day) {
        boolean status = false;

        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        Date vDate = c.getTime();
        Date currentDate = new Date();

        status = vDate.before(currentDate);

        return status;
    }

    public boolean afterCurrentDate(int year, int month, int day) {
        boolean status = false;

        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        Date vDate = c.getTime();
        Date currentDate = new Date();

        status = vDate.after(currentDate);

        return status;
    }

}
