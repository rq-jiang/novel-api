package com.example.demo.service;


import com.example.demo.entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class SendEmailService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromName;

    /**
     * 发送简单邮件
     * @param toMail
     * @param subject
     * @param content
     */
    public Result sendSimpleMail(String toMail, String subject, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromName);
        simpleMailMessage.setTo(toMail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        try {
            javaMailSender.send(simpleMailMessage);
            logger.info("发送给:{}简单邮件已经发送。 subject:{}", toMail, subject);
            return new Result(200,"验证码已发送至您的邮箱，请注意查收！","");
        } catch (Exception e) {
            logger.info("发送给:{}send mail error subject:{}", toMail, subject);
            e.printStackTrace();
            return new Result(400,"验证码发送失败，请检查邮箱地址","");
        }
    }


    /**
     * 发送HTML格式的邮件
     * @param toMail
     * @param subject
     * @param content
     */
    public void sendHtmlMail(String toMail,String subject,String content) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message,true);
            mimeMessageHelper.setTo(toMail);
            mimeMessageHelper.setFrom(fromName);
            mimeMessageHelper.setText(content,true);
            mimeMessageHelper.setSubject(subject);

            javaMailSender.send(message);
            logger.info("发送给：{}html邮件已经发送。subject:{}",toMail,subject);
        }catch (Exception e){
            logger.info("发送给：{}html send mail error subject:{}",toMail,subject);
            e.printStackTrace();
        }
    }

}

