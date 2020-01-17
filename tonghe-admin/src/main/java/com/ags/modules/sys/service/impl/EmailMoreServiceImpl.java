package com.ags.modules.sys.service.impl;

import com.ags.modules.sys.service.EmailMoreService;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Map;
import java.util.Properties;

//TODO 邮箱

@Service("emailMoreServiceImpl")
public class EmailMoreServiceImpl implements EmailMoreService {

    @Autowired
    private  JavaMailSenderImpl javaMailSender;

    private Logger logger = LoggerFactory.getLogger(getClass());

    private int emailId = 0;

    private List<Map<String, Object>> listEmail;

    @PostConstruct
    private void setListEmail() {
//        this.listEmail = listEmail;
    }

    private synchronized void setEmailId() {
        // 设置下次可发送邮箱
        boolean isMax = emailId == (listEmail.size()-1);
        emailId = isMax ? 0 : emailId+1;
    }

    private synchronized Map<String, Object> getEmail() {
        // 获取当前可发送邮箱
        Map<String, Object> email = listEmail.get(emailId);
        setEmailId();
        return email;
    }

//    private synchronized JavaMailSenderImpl getJavaMailSender() {
//        // 获取邮箱发送实例
//        Map<String, Object> email = getEmail();
//        String host = email.get("host_addr").toString();
//        String username = email.get("email").toString();
//        String password = email.get("pwd").toString();
//        String sslPort = email.get("ssl_port").toString();
//        JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
//        javaMailSenderImpl.setHost(host);
//        javaMailSenderImpl.setUsername(username);
//        javaMailSenderImpl.setPassword(password);
//        javaMailSenderImpl.setDefaultEncoding("UTF-8");
//        Properties properties = new Properties();
//        properties.setProperty("mail.smtp.auth", "true");
//        properties.setProperty("mail.smtp.socketFactory.port", sslPort);
//        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        javaMailSenderImpl.setJavaMailProperties(properties);
//        return javaMailSenderImpl;
//    }

    @Override
    public boolean sendSimpleMail(String to, String subject, String content) {
        logger.info("简单邮件开始发送");
        try {
//            JavaMailSenderImpl javaMailSender = getJavaMailSender();
            String username = javaMailSender.getUsername();
            logger.info("当前发送邮箱: " + username);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(username);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(content);
            javaMailSender.send(message);
            logger.info("简单邮件发送成功");
            return true;
        } catch (Exception e) {
            logger.error("简单邮件发送异常", e);
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean sendHtmlMail(String to, String subject, String content) {
        logger.info("HTML邮件开始发送");
        try {
//            JavaMailSenderImpl javaMailSender = getJavaMailSender();
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            String username = javaMailSender.getUsername();
            logger.info("当前发送邮箱: " + username);
            messageHelper.setFrom(username);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(content, true);
            javaMailSender.send(message);
            logger.info("HTML邮件发送成功");
            return true;
        } catch (Exception e) {
            logger.error("HTML邮件发送异常", e);
            e.printStackTrace();
        }
        return false;
    }

}
