/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.io.File;
import java.security.NoSuchProviderException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author ASUS
 */
public class SendRecoveryCodeToEmail {
    
    Session newSession = null;
    MimeMessage mimeMessage = null;

    String emailReceipient;
    String recoveryCode;
    String pwRestLink;

    public SendRecoveryCodeToEmail(String emailReceipient, String recoveryCode, String pwRestLink) {
        this.emailReceipient = emailReceipient;
        this.recoveryCode = recoveryCode;
        this.pwRestLink = pwRestLink;
    }


    public void Post() {
        try {
            setupServerProperties();
            draftEmail();
            sendEmail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupServerProperties() {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        newSession = Session.getDefaultInstance(properties, null);
    }

    private MimeMessage draftEmail() throws AddressException, MessagingException {

        String emailSubject = "Account password recovery";
        String emailBody = "<h1>Account Recovery</h1>"
                + "<p>Use given link to reset account password.<br> Recovery Code : "+recoveryCode+"<br> Password reset link : "+pwRestLink+"</p>";
        mimeMessage = new MimeMessage(newSession);

        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailReceipient));

        mimeMessage.setSubject(emailSubject);

        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(emailBody, "text/html");
        
        MimeMultipart multiPart = new MimeMultipart();
        multiPart.addBodyPart(bodyPart);

        mimeMessage.setContent(multiPart);
        return mimeMessage;
    }

    private void sendEmail() throws NoSuchProviderException, MessagingException {
        String fromUser = "skyblue.wv@gmail.com";
        String fromUserPassword = "hworvpkfpoohgfml";
        String emailHost = "smtp.gmail.com";
        Transport transport = newSession.getTransport("smtp");
        transport.connect(emailHost, fromUser, fromUserPassword);
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();
        System.out.println("Email send successfully !");
    }
}
