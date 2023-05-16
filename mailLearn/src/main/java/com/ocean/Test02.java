package com.ocean;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.text.MessageFormat;
import java.util.Properties;

public class Test02 {
    public static void main(String[] args) throws MessagingException {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost("smtp.qq.com");  //设置发送方的邮箱格式
        javaMailSender.setProtocol("smtp");     //设置协议
        javaMailSender.setUsername("1252074183@qq.com");    //发送方账号
        javaMailSender.setPassword("vennihzjcgaxjbji");     //发送方授权码
        javaMailSender.setPort(587);                        //端口号
        javaMailSender.setDefaultEncoding("UTF-8");         //编码

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);//通过helper配置

        helper.setTo("20216928@stu.neu.edu.cn");           //收件方邮箱
        helper.setFrom("1252074183@qq.com");               //必须与发送发账号相同！！！
        helper.setSubject("测试！");                        //标题
        helper.setText(getHtml(), true);//正文内容

        Properties properties = new Properties();           //配置
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.timeout", "25000");
        javaMailSender.setJavaMailProperties(properties);

        javaMailSender.send(message);
    }

    public static String getHtml() {
        String context = """
                        <!DOCTYPE html>
                        <html lang="en">
                        <head>
                            <meta charset="UTF-8">
                            <title>测试系统</title>
                        </head>
                        <body>
                          <h1>{0}</h1>
                          <h1>{1}</h1>
                          <h1>hello mail</h1><br>
                          账号：<input type="text"><br>
                          密码：<input type="password">
                        </body>
                        </html>
                """;
        context = MessageFormat.format(context,"ocean", "sea");
        System.out.println(context);
        return context;
    }
}
