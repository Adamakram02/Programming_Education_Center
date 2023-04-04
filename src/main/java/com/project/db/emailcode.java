package com.project.db;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.* ;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class emailcode{
    public static void sendMailTo (String mail,String  id ,String name,String passs,String s) throws MessagingException {

        String from = "codeacademi2022@gmail.com" ;   // sender email
        String pass = "vujaboiowvctespe" ; // password
      //  String to="admakrm3@gmail.com";
        Properties properties =new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.host", "smtp.gmail.com");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.debug", "true");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "false");
        properties.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("codeacademi2022@gmail.com" ,"vujaboiowvctespe") ;
            }
        });
        /*Message message = prpMessage(session,myAcc,mail,id,name,passs) ;
        Transport.send(message);*/
        session.setDebug(true);
        try {
            MimeMessage message=new MimeMessage(session);
          //  System.out.println("hala");
            System.out.println("hala1");
            message.setFrom(new InternetAddress(from));
            System.out.println("hala2");
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(mail));
            System.out.println("hala3");
            message.setSubject("Registration completed"); // subject of mail
            System.out.println("hala4");
           // message.setText( "Hello "+name+"\n Your registration in our courses has been completed.\n In order to follow your progress and know the details, log in to our CodeAcademe program\n your id ="+id+"\n and password ="+passs+"\nGood luck") ; // text message
            message.setText(s) ; // text message
            System.out.println("hala5");
           // return message ;
            Transport.send(message);
            System.out.println("Done");

        }
        catch (Exception e){
            System.out.println("Mor");
             Logger.getLogger(AddStudentController.class.getName()).log(Level.SEVERE , null, e) ;
        }
    }

    private static Message prpMessage(Session session ,String acc ,String recipient , String id,String name,String pass){

        return null ;
    }
}