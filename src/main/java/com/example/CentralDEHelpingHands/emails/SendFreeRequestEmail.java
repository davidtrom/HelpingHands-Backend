package com.example.CentralDEHelpingHands.emails;

import com.example.CentralDEHelpingHands.entities.Request;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendFreeRequestEmail {

    public static Boolean sendMessageToRecipient (String toRecipientEmail, String recipientName, Request helpRequest){

        String emailPassword = System.getenv("EMAIL_PASSWORD");

        // Recipient's email ID needs to be mentioned.
        //String to = toRecipientEmail;
        String to = "davidtrom@hotmail.com";


        // Sender's email ID needs to be mentioned
        //String from = "ddsrwebsite2@gmail.com";
        String from = "delawarehelpinghands@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("delawarehelpinghands@gmail.com", emailPassword);

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Unable to Help");

            // Now set the actual message
            message.setText("Dear " + recipientName + "," + "\n" +
                    "This email is to inform you that your Volunteer has decided that they are unable to help you with the following Help Request at this time: \n" +
                    helpRequest.toString() + "\n" +
                    "We are sorry for this inconvenience to you, but during such uncertain times, things like this happen.  Your request will be once again " +
                    "be re-opened for another potential Volunteer to help.  Please stay safe!" + "\n" +
                    "Sincerely yours," + "\n" + "\n" + "Delaware Helping Hands"  );

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
            return true;
        } catch (MessagingException mex) {
            mex.printStackTrace();
            return false;
        }
    }
}
