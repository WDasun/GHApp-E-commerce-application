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
public class SendInvoiceToEmail {

    Session newSession = null;
    MimeMessage mimeMessage = null;

    String filePath;
    String emailReceipient;
    String customerName;

    public SendInvoiceToEmail(String filePath, String emailReceipient, String customerName) {
        this.filePath = filePath;
        this.emailReceipient = emailReceipient;
        this.customerName = customerName;
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

        String emailSubject = "Your Order Receipt from Glimmer Heaven";
        String emailBody = "<h1>Dear, "+customerName+"</h1>"
                + "<p>Thank you for your recent purchase from Glimmer Heaven.<br>"
                + "Attached to this email, you will find the receipt for your order.</p>"
                + "<p>We appreciate your business and hope you are satisfied with your purchase.<br>"
                + " If you have any questions or need further assistance, feel free to contact us at customer Support</p>";
        mimeMessage = new MimeMessage(newSession);

        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailReceipient));

        mimeMessage.setSubject(emailSubject);

        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(emailBody, "text/html");

        // Create body part for attachment
        MimeBodyPart attachmentPart = new MimeBodyPart();
        DataSource source = new FileDataSource(filePath);
        attachmentPart.setDataHandler(new DataHandler(source));
        attachmentPart.setFileName(new File(filePath).getName());

        MimeMultipart multiPart = new MimeMultipart();
        multiPart.addBodyPart(bodyPart);
        multiPart.addBodyPart(attachmentPart);

        mimeMessage.setContent(multiPart);
        return mimeMessage;
    }

    private void sendEmail() throws NoSuchProviderException, MessagingException {
        String fromUser = "example@email.w";
        String fromUserPassword = "ghfghfgh";
        String emailHost = "smtp.gmail.com";
        Transport transport = newSession.getTransport("smtp");
        transport.connect(emailHost, fromUser, fromUserPassword);
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();
        System.out.println("Email send successfully !");
    }
}
