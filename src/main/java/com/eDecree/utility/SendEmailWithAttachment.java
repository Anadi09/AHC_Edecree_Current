
package com.eDecree.utility;

import com.sun.mail.smtp.SMTPTransport;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.io.File;
import java.util.Properties;

public class SendEmailWithAttachment {

    // for example, smtp.mailgun.org
    private static final String SMTP_SERVER = "smtp server ";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";

    private static final String EMAIL_FROM = "bilalkhan0408@gmail.com";
    private static final String EMAIL_TO = "bilal.khan@nexsussolutions.com";
    private static final String EMAIL_TO_CC = "";

    private static final String EMAIL_SUBJECT = "Test Send Email via SMTP (ATTACHMENT)";
    private static final String EMAIL_TEXT = "Hello Java Mail \n ABC123";

    public static Boolean sendMail(String sender, String reciever,File file) {

    	  // Recipient's email ID needs to be mentioned.
        String to = "skm311093@gmail.com";

        // Sender's email ID needs to be mentioned
        String from = "bilalkhan0408@gmail.com";

        // Assuming you are sending email from localhost
        String host = "localhost";

        // Get system properties
        Properties properties = System.getProperties();
        properties.setProperty("mail.user", "myuser");
        properties.setProperty("mail.password", "mypwd");

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {
           // Create a default MimeMessage object.
           MimeMessage message = new MimeMessage(session);

           // Set From: header field of the header.
           message.setFrom(new InternetAddress(from));

           // Set To: header field of the header.
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));

           // Set Subject: header field
           message.setSubject("This is the Subject Line!");

           // Create the message part 
           BodyPart messageBodyPart = new MimeBodyPart();

           // Fill the message
           messageBodyPart.setText("This is message body");
           
           // Create a multipar message
           Multipart multipart = new MimeMultipart();

           // Set text message part
           multipart.addBodyPart(messageBodyPart);

           // Part two is attachment
           messageBodyPart = new MimeBodyPart();
           String filename = "D:\\noticeform\\a.pdf";
           DataSource source = new FileDataSource(filename);
           messageBodyPart.setDataHandler(new DataHandler(source));
           messageBodyPart.setFileName(filename);
           multipart.addBodyPart(messageBodyPart);

           // Send the complete message parts
           message.setContent(multipart );

           // Send message
           Transport.send(message);
           System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
           mex.printStackTrace();
        }
        return true;
    }
    
}
