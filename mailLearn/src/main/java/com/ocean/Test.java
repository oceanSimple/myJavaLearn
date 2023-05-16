package com.ocean;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

public class Test {
    public static void main(String[] args) {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost("smtp.qq.com");  //设置发送方的邮箱格式
        javaMailSender.setProtocol("smtp");     //设置协议
        javaMailSender.setUsername("1252074183@qq.com");    //发送方账号
        javaMailSender.setPassword("vennihzjcgaxjbji");     //发送方授权码
        javaMailSender.setPort(587);                        //端口号
        javaMailSender.setDefaultEncoding("UTF-8");         //编码

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("20216928@stu.neu.edu.cn");           //收件方邮箱
        message.setFrom("1252074183@qq.com");               //必须与发送发账号相同！！！
        message.setSubject("测试！");                        //标题
        message.setText("hello mail!!!");                   //正文内容

        Properties properties = new Properties();           //配置
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.timeout", "25000");

        javaMailSender.setJavaMailProperties(properties);
        javaMailSender.send(message);
    }
}
