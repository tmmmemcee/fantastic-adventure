/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.fantasyfootball;


import java.io.IOException;
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
 * @author tmmmemcee
 */


public class main {

    public static void main(String args[]) throws IOException  {
        final String username = "kaycieDaletherapy@gmail.com";
        final String password = "chic0d0gdal3";

        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("tmmmemcee@gmail.com" )
            );
            message.setSubject("New Contact");
            message.setText("Dear Mail Crawler,"
                    + "\n\n Please do not spam my email!");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }



//        Document doc = Jsoup.connect("https://www.pro-football-reference.com/years/2019/fantasy.htm").get();
//        String title = doc.title();
//        System.out.println(title);
//        Elements rows = doc.select("#fantasy tbody tr");
//        Element r = rows.first().child(1);
//        rows.removeIf((n -> n.child(2).hasClass("sort_default_asc")));
////        System.out.println(r.text());
////        System.out.println(rows.first());
//        for (Element row : rows) { 
//            String name = row.child(1).text().replaceAll("[^a-zA-Z]+", "");
//            System.out.println(name);
//            
//        }
    }
//        File input = new File("/tmp/input.html");
//        try {
//            if (input.createNewFile() ){
//                System.out.println("Created");
//            }
//            Document doc = Jsoup.parse(input, "UTF-8", "https://www.pro-football-reference.com/years/2019/passing.htm");
//            Elements content = doc.getElementsByTag("tr");
//            for (Element qb: content) {
//                System.out.println(qb.html());
//            }
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }
    
    
}
