//package com.entry_exit_system.mail;
//import java.util.Properties;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
//public class mailService {
//
//    public void sendMailTo(String email){
//        Properties popProps = new Properties();
//        popProps.put("mail.pop3.host", "outlook.office365.com");
//        popProps.put("mail.pop3.port", "995");
//        popProps.put("mail.pop3.tls.enable", "true");
//
//        // Sender's credentials for POP3
//        final String popUsername = "projectmanagerdbms@gmail.com";
//        final String popPassword = "smxolamkteprguov";
//
//        // SMTP server properties
//        Properties smtpProps = new Properties();
//        smtpProps.put("mail.smtp.auth", "true");
//        smtpProps.put("mail.smtp.starttls.enable", "true");
//        smtpProps.put("mail.smtp.ssl.protocols", "TLSv1.2");
//        smtpProps.put("mail.smtp.host", "smtp.gmail.com");
//        smtpProps.put("mail.smtp.port", "587");
////
//        smtpProps.put("mail.smtp.ssl.trust", "*");
////
//
//        // Create session with authentication
//        Session session = Session.getInstance(smtpProps, new javax.mail.Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(popUsername, popPassword);
//            }
//        });
//
//        try {
//            // Create message
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress("no-reply@google.com"));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
//            message.setSubject("Severe Case of Indiscipline");
//            message.setText("There is a ban placed on your entry/exit into the campus permanently. Any measures to avoid this ban will lead to your rustication.\n You are requested to submit an apolog");
//
//            // Send message
//            Transport.send(message);
//
//            System.out.println("Email sent succesfully");
//
//        } catch (MessagingException e) {
//            System.out.println( "Error sending email: " + e.getMessage());
//        }
//    }
////    public static void main(String[] args) {
////
////    }
//}
