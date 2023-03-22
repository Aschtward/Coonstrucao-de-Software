package com.example.demo.dao;

import com.example.demo.dto.EmailDto;
import com.example.demo.models.EmailModel;
import com.example.demo.services.EmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailDAO {

	@Autowired
	EmailService emailService;

	public void sendConfirmationEmail(String email, String token) {
		EmailModel emailModel = new EmailModel();
		EmailDto emailDto = new EmailDto(
				"Apply & Growth", "applyandgrowth@gmail.com", email,
				"Porfavor confirme o suu email",
				"Para confirmar o seu email use o link: localhost:8080/confirmAccount?token=" +
						token);
		BeanUtils.copyProperties(emailDto, emailModel);
		emailService.sendEmail(emailModel);
	}

	public void sendRecoveryEmail(String email, String token) {
		EmailModel emailModel = new EmailModel();
		EmailDto emailDto = new EmailDto(
				"Apply & Growth", "applyandgrowth@gmail.com", email,
				"Aqui está o link para trocar de senha, caso não o tenha solicitado desconsidere essa mensagem, o link é valido por um dia. ",
				"localhost:8080/changePassword?token=" + token);
		BeanUtils.copyProperties(emailDto, emailModel);
		emailService.sendEmail(emailModel);
	}

	public void sendSupportEmail(String ownerRef, String emailSender,
			String emailReciever, String subject,
			String content) {
		EmailModel emailModel = new EmailModel();
		EmailDto emailDto = new EmailDto(ownerRef, emailSender, emailReciever, subject, content);
		BeanUtils.copyProperties(emailDto, emailModel);
		emailService.sendEmail(emailModel);
	}
}
