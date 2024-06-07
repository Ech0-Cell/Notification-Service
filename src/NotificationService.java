package com.yourcompany.notificationservice;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.io.InputStream;

public class NotificationService {
    private static Properties loadProperties(String fileName) {
        Properties properties = new Properties();
        try (InputStream input = NotificationService.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                System.out.println("Sorry, unable to find " + fileName);
                return null;
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return properties;
    }

    public static void main(String[] args) {
        Properties emailProps = loadProperties("email.properties");
        MockKafka mockKafka = new MockKafka();
        mockKafka.addMessage(new NotificationRecord("1234567890", 1, 80));
        mockKafka.addMessage(new NotificationRecord("0987654321", 2, 100));
        Map<Integer, String> messageTemplateList = getAllMessages();

        while (true) {
            List<NotificationRecord> notificationList = mockKafka.pollMessages("NotificationTopic");

            for (NotificationRecord notificationRecord : notificationList) {
                String msisdn = notificationRecord.getMsisdn();
                int packageId = notificationRecord.getPackageId();
                int notificationType = notificationRecord.getNotificationType();

                String notificationMessage = messageTemplateList.get(packageId);
                if (notificationMessage != null) {
                    sendEmail(emailProps, msisdn, notificationMessage);
                }
            }

            try {
                Thread.sleep(10000); //10 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static Map<Integer, String> getAllMessages() {
        Map<Integer, String> messages = new HashMap<>();
        messages.put(1, "Dear customer, you have consumed all of your allowance in your university XL package. Please be informed that you will be charged for overusages."); //packageid 1
        messages.put(2, "Dear customer, you have consumed all of your allowance in your university L package. Please be informed that you will be charged for overusages."); //package id 2
        return messages;
    }

    private static void sendEmail(Properties emailProps, String msisdn, String message) {
        String to = msisdn + "@example.com"; // this assumes msisdn can be used to form email address
        String from = emailProps.getProperty("mail.smtp.user");
        String host = emailProps.getProperty("mail.smtp.host");

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", emailProps.getProperty("mail.smtp.port"));
        properties.setProperty("mail.smtp.auth", emailProps.getProperty("mail.smtp.auth"));
        properties.setProperty("mail.smtp.starttls.enable", emailProps.getProperty("mail.smtp.starttls.enable"));

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, emailProps.getProperty("mail.smtp.password"));
            }
        });

        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(from));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setSubject("Notification");
            mimeMessage.setText(message);

            Transport.send(mimeMessage);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
