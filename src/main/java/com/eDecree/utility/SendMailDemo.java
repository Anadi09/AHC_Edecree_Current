package com.eDecree.utility;




import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendMailDemo {
	public static void main(String[] args) 
    {
        /***** CHANGE THESE FOUR VARIABLE VALUES TO REFLECT YOUR ENVIRONMENT ******/
        String user = "bilalkhan0408@gmail.com";   // Newly created user on JAMES Server
        String password = "Aliza@0511"; // user password

        String fromAddress = "bilalkhan0408@gmail.com"; // newlycreateduser@localhost
        String toAddress = "bilalkhan0408@gmail.com";


        // Create a mail session
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.host", "127.0.0.1");
        properties.put("mail.smtp.port", "25");
        properties.put("mail.smtp.username", user);
        properties.put("mail.smtp.password", password);
        Session session = Session.getDefaultInstance(properties, null);

        try 
        {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromAddress));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));

            message.setSubject("Email From my Own Server");
            message.setText("Test Mail sent from My Apache James Server!!");
            Transport.send(message);

            System.out.println("Email sent successfully");
        }
        catch (MessagingException e) 
        {
            e.printStackTrace();
        }
    }
	

}
