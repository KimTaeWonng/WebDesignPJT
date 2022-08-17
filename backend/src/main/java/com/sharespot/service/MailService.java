package com.sharespot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.sharespot.entity.Mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void mailSend(Mail mail) {
		
		System.out.println("이메일 전송 완료");
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(mail.getAddress());
		message.setFrom("apxjvm@gmail.com");
		message.setSubject(mail.getTitle());
        message.setText(mail.getContent());
        
        mailSender.send(message);
	}

}
