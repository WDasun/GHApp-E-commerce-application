/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 *
 * @author ASUS
 */
public class DefaultLogFormatter extends Formatter{

String employeeId;

    public DefaultLogFormatter(String employeeId) {
        this.employeeId = employeeId;
    }
     
    @Override
    public String format(LogRecord record) {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return String.format("[%s] - %s\n - %s\n - %s\n - %s%n\n",
                sdf.format(new Date(record.getMillis())), // Timestamp
                record.getLevel().getLocalizedName(), // Log Level
                "Employee : " + employeeId,
                record.getLoggerName(), // Logger Name
                formatMessage(record) // Log Message
                );
    }
    
}
