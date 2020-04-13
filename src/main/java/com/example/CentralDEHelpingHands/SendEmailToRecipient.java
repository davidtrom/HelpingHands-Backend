package com.example.CentralDEHelpingHands;

import java.time.LocalDate;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmailToRecipient {

    public static Boolean sendMessageToRecipient (String toVolEmail, String recipientName, String volFirstName, String volLastName, String email, String phoneNumber, LocalDate dateOfBirth, String reasonForContact, String prefferedApptTime, String messsage){

        String emailPassword = System.getenv("EMAIL_PASSWORD");

        // Recipient's email ID needs to be mentioned.
        String to = toVolEmail;
        //String to = "davidtrom@hotmail.com";


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
            message.setSubject("Someone Wants to Help!");

            // Now set the actual message
            message.setText("Dear " + recipientName + "," + "\n" +
                    "The following potential Volunteer has agreed to help you with the following Help Request: \n" + "\n" +
                    "" +
                    "Name: " + volFirstName +" " + volLastName + "\n" + "Email: " + email + "\n" + "Phone Number: " + phoneNumber + "\n" +
                    "Date of Birth: " + dateOfBirth + "\n" + "Reason for Contact: " +reasonForContact + "\n" +
                    "Preferred Appointment Time: " + prefferedApptTime + "\n" + "Message: " + messsage + "\n" + "\n" +
                    "Sincerely yours," + "\n" + "\n" + "Website Contact "  );

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
