package com.alleyne.mall.utils;

import javax.mail.Session;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;

public class MailUtils {
    public static void sendMail(String email, String emailMsg){

        // 1.创建一个程序与邮件服务器会话对象 Session
        Properties props = new Properties();
        //设置发送的协议
        props.setProperty("mail.transport.protocol", "SMTP");
        //设置发送邮件的服务器
        props.setProperty("mail.host", "smtp.126.com");
        props.setProperty("mail.smtp.auth", "true");// 指定验证为true

        // 创建验证器
        Authenticator auth = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("admin@store.com", "admin".toCharArray());
            }
        };

        Session session = (Session) Session.getInstance(props, auth);
    }
}
