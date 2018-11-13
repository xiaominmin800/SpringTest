package service;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * created by chenminqing
 */
public class mails {
    public static void main(String [] args) {

        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost("smtp.mxhichina.com");
        sender.setUsername("minqing.chen@fulintechfin.com");
        sender.setPassword("!QAZxsw2");
        sender.setProtocol("smtp");
        sender.setDefaultEncoding(Charset.defaultCharset().name());

        Properties propertiesMap = new Properties();
        propertiesMap.setProperty("mail.smtp.auth", "true");
        propertiesMap.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        propertiesMap.setProperty("mail.smtp.socketFactory.fallback", "false");
        propertiesMap.setProperty("mail.smtp.socketFactory.port", "465");

        sender.setJavaMailProperties(propertiesMap);


        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom("minqing.chen@fulintechfin.com");
            helper.setTo("minqing.chen@fulintechfin.com");
            helper.setSubject("2B");
            helper.setText("2B", true);
            sender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
