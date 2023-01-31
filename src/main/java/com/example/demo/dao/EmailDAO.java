package com.example.demo.dao;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.EmailDto;
import com.example.demo.models.EmailModel;
import com.example.demo.services.EmailService;

@Component
public class EmailDAO {
	
	@Autowired
	EmailService emailService;
	
	public void sendingEmail(EmailDto emailDto){
		EmailModel emailModel = new EmailModel();
		BeanUtils.copyProperties(emailDto, emailModel);
		emailService.sendEmail(emailModel);
	}

}
