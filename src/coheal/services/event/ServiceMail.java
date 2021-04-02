/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.services.event;

import coheal.iservices.event.IMailSender;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Admin
 */
public class ServiceMail implements IMailSender{

    @Override
    public void sendmailfunc(String emailto, String message) {
    
         //Get properties object    
          Properties props = new Properties();    
          props.put("mail.smtp.host", "smtp.gmail.com");    
          props.put("mail.smtp.socketFactory.port", "465");    
          props.put("mail.smtp.socketFactory.class",    
                    "javax.net.ssl.SSLSocketFactory");    
          props.put("mail.smtp.auth", "true");    
          props.put("mail.smtp.port", "465");    
          //get Session   
          Session session = Session.getDefaultInstance(props,    
           new javax.mail.Authenticator() {    
           protected PasswordAuthentication getPasswordAuthentication() {    
           return new PasswordAuthentication("mohamed.belaid.1@esprit.tn","181JMT2960");  
           }    
          });    
          
           //compose message    
          try {    
           MimeMessage msg = new MimeMessage(session);    
           msg.addRecipient(Message.RecipientType.TO,new InternetAddress(emailto));    
           msg.setSubject("nouvel evenement");    
           msg.setText(message);    
           //send message  
           Transport.send(msg);    
           System.out.println("message sent successfully");    
          } catch (MessagingException e) {throw new RuntimeException(e);}    
} 
    }
    

