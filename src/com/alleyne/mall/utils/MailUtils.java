package com.alleyne.mall.utils;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

public class MailUtils {
    public static void sendMail(String email, String emailMsg) throws MessagingException, AddressException {

        // 1.创建一个程序与邮件服务器会话对象 Session
        Properties props = new Properties();
        //设置发送的协议
        props.setProperty("mail.transport.protocol", "SMTP");
        //设置发送邮件的服务器
        props.setProperty("mail.host", "smtp.163.com");
        props.setProperty("mail.smtp.auth", "true");// 指定验证为true

        // 创建验证器
        Authenticator auth = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("ggstudy220@163.com", "wy123456");
            }
        };

        Session session = (Session) Session.getInstance(props, auth);
        // 2.创建一个Message，它相当于是邮件内容
        Message message = new MimeMessage(session);
        //设置发送者
        message.setFrom(new InternetAddress("ggstudy220@163.com"));
        //设置发送方式与接收者
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
        //设置邮件主题
        message.setSubject("用户激活");
        //设置邮件内容
        String url="http://localhost:8080/MallDemo/UserServlet?method=active&code="+emailMsg;
        String content="<h1>来自好好学习的激活邮件!激活请点击以下链接!</h1><h3><a href='"+url+"'>"+url+"</a></h3>";
        message.setContent(content, "text/html;charset=utf-8");

        // 3.创建 Transport用于将邮件发送
        Transport.send(message);
    }

    public static void main(String[] args) throws AddressException, MessagingException {
        MailUtils.sendMail("2209947775@qq.com", "1234567890");
        System.out.println("OK");

    }
}
