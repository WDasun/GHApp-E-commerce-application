/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class CreateLog {

    private String logType;
    private String Message;
    private String userId;
    private String filePath;
    private String className;

    public CreateLog(String logType, String Message, String userId, String className) {
        this.logType = logType;
        this.Message = Message;
        this.userId = userId;
        this.className = className;
    }

    public void customerActivity() {
        String folderPath = "D:\\AppLogs\\Application\\CustomerActivty\\";
        String fileName = "[" + userId + "]" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".txt";
        filePath = folderPath + fileName;
        saveLog();
    }

    public void employeeActivey() {
        String folderPath = "D:\\AppLogs\\Application\\EmployeeActivity\\";
        String fileName = "[" + userId + "]" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".txt";
        filePath = folderPath + fileName;
//        filePath = "D:\\AppLogs\\Application\\Test.txt";
        saveLog();
    }

    public void customerError() {
        String folderPath = "D:\\AppLogs\\Application\\Errors\\CustomerError\\";
        String fileName = "[" + userId + "]" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".txt";
        filePath = folderPath + fileName;
        saveLog();
    }

    public void employeeError() {
        String folderPath = "D:\\AppLogs\\Application\\Errors\\EmployeeError\\";
        String fileName = "[" + userId + "]" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".txt";
        filePath = folderPath + fileName;
        saveLog();
    }
    
    public void systemLog() {
        String folderPath = "D:\\AppLogs\\Application\\SystemLog\\";
        String fileName = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".txt";
        filePath = folderPath + fileName;
        saveLog();
    }

    private void saveLog() {
        FileHandler fh = null;
        try {
            Logger logger = Logger.getLogger(className);

            fh = new FileHandler(filePath, true);

            fh.setFormatter(new DefaultLogFormatter(userId));
            logger.addHandler(fh);

            switch (logType) {
                case "warning":
                    logger.warning(Message);
                    break;
                case "info":
                    logger.info(Message);
                    break;
                case "severe":
                    logger.severe(Message);
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fh != null) {
                try {
                    fh.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
