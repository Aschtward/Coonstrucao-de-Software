package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.adapter.ClientAdapter;
import com.example.demo.dto.EmailDto;
import com.example.demo.enums.RoleName;
import com.example.demo.models.ClienteModels;
import com.example.demo.models.ConfirmationTolkenModel;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.ConfirmationTolkenRepository;

@Component
public class ClientDAO{
	
	@Autowired
    private ConfirmationTolkenRepository tolkenRepository;
	@Autowired
	ClientRepository clientRepo;
	@Autowired
	ClientAdapter clientAdapter;
	@Autowired
	EmailDAO emailDao;
	@Autowired
	RolesDAO roleDao;
	
	public ClienteModels adicionarCliente( String name, String email, String password) {	
		BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
		ClienteModels cliente = new ClienteModels(email, enconder.encode(password),
				name, false, roleDao.buscarRoles(RoleName.ROLE_CLIENTE));
		ConfirmationTolkenModel confirmationTolken = new ConfirmationTolkenModel(cliente);
		clientRepo.save(cliente);
		tolkenRepository.save(confirmationTolken);
		EmailDto emailConfirmation = new EmailDto("Apply & Growth", "applyandgrowth@gmail.com",
				cliente.getEmail(), "Confirmação de email",
				"Confirme o email no link: localhost:8080/confirmAccount?token=" + confirmationTolken.getConfirmationToken());
		emailDao.sendingEmail(emailConfirmation);
		return cliente;
	}
	
	public void confirmarEmail(String confirmationToken) {
		ConfirmationTolkenModel token = tolkenRepository.findByConfirmationToken(confirmationToken);
		if(token != null) {
			ClienteModels cliente = new ClienteModels();
			clientAdapter.geraCliente(clientRepo.findByEmail(token.getUser().getEmail()), cliente);
			cliente.setIsConfirmed(true);
			clientRepo.save(cliente);
		}
	}


}
