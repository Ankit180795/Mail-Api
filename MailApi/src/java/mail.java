/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.mail.PasswordAuthentication;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.Session;

/**
 *
 * @author Administrator
 */
public class mail extends HttpServlet {

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out= response.getWriter();
        String to = request.getParameter("to");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");
        
          
            Properties pro = new Properties();
            pro.put("mail.smtp.auth","true");
            pro.put("mail.smtp.starttls.enable","true");
            pro.put("mail.smtp.host","smtp.gmail.com");
            pro.put("mail.smtp.port","587");
            
            Session s = Session.getDefaultInstance(pro, new javax.mail.Authenticator(){
                     @Override
                     protected PasswordAuthentication getPasswordAuthentication(){
                         return new PasswordAuthentication("abc@gmail.com","9xxxxxx9");
                     }
                 });
            
            s.setDebug(true);
                
        try {
            Message m = new MimeMessage(s);
            m.setFrom(new InternetAddress("luvkush1823@gmail.com"));
                 m.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
                 m.setSubject(subject);
                 m.setText(message);
                 Transport.send(m);
                 out.println("Mail Sent Successful!");
        } catch (MessagingException ex) {
            Logger.getLogger(mail.class.getName()).log(Level.SEVERE, null, ex);
        }
                 
    }

   
}
