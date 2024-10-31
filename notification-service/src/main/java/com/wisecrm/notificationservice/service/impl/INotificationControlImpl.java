package com.wisecrm.notificationservice.service.impl;
import com.wisecrm.notificationservice.dto.*;
import com.wisecrm.notificationservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service
public class INotificationControlImpl implements INotificationControl {

    @Autowired
    private JavaMailSender mailSender;
    @Override
    public void send(NotificationDto notificationDto) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(notificationDto.getMailFrom());
        message.setTo(notificationDto.getMailTo());
        message.setText(notificationDto.getBody());
        message.setSubject(notificationDto.getSubject());
        mailSender.send(message);

    }
}
