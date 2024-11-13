package com.poll.service.impl;

import com.poll.pojo.User;
import com.poll.service.EmailService;
import com.poll.utils.RandomUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final StringRedisTemplate redisTemplate;

    public EmailServiceImpl(JavaMailSender javaMailSender, StringRedisTemplate redisTemplate) {
        this.javaMailSender = javaMailSender;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void send(User user) {
        String verificationCode = RandomUtils.generateVerificationCode();

        redisTemplate.opsForValue().set(user.getEmail(), verificationCode, 10, TimeUnit.MINUTES);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("Online Polling System <20223802051@m.scnu.edu.cn>");
        message.setTo(user.getEmail());
        message.setSubject("Your Verification Code");
        message.setText("Dear User,\n\n" +
                "Thank you for using our services! To verify your email address, please enter the verification code below within the next 10 minutes:\n\n" +
                "Verification Code: " + verificationCode + "\n\n" +
                "Please note that this verification code is valid only for this session and must be used within 10 minutes. If you do not complete the verification within 10 minutes or if this was not a request from you, please ignore this email and request a new code.\n\n" +
                "If you have any questions or need assistance, please reply to this email.\n\n" +
                "Best regards,\n" +
                "Online Polling System");

        javaMailSender.send(message);
    }
}