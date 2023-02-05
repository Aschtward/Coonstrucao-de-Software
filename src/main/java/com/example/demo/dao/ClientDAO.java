package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.adapter.ClientAdapter;
import com.example.demo.dto.EmailDto;
import com.example.demo.models.Cliente;
import com.example.demo.models.ConfirmationTolkenModel;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.ConfirmationTolkenRepository;
import com.example.demo.security.ClienteDetailsServiceImpl;

@Component
public class ClientDAO {

	@Autowired
	private ConfirmationTolkenRepository tolkenRepository;
	@Autowired
	ClientRepository clientRepo;
	@Autowired
	ClientAdapter clientAdapter;
	@Autowired
	EmailDAO emailDao;

	public Cliente adicionarCliente(String name, String email, String password) {
		BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
		Cliente cliente = clientAdapter.geraCliente(name, email, enconder.encode(password));
		cliente.setIsConfirmed(false);
		ConfirmationTolkenModel confirmationTolken = new ConfirmationTolkenModel(cliente);
		clientRepo.save(cliente);
		tolkenRepository.save(confirmationTolken);
		EmailDto emailConfirmation = new EmailDto("Apply & Growth", "applyandgrowth@gmail.com", cliente.getEmail(),
				"Confirmação de email", "Confirme o email no link: localhost:8080/confirmAccount?token="
						+ confirmationTolken.getConfirmationToken());
		emailDao.sendingEmail(emailConfirmation);
		return null;
	}

	public void confirmarEmail(String confirmationToken) {
		ConfirmationTolkenModel token = tolkenRepository.findByConfirmationToken(confirmationToken);
		if (token != null) {
			Cliente cliente = clientRepo.findByEmail(token.getUser().getEmail()).get();
			cliente.setIsConfirmed(true);
			clientRepo.save(cliente);
		}
	}

	public Cliente buscaCliente(String email) {
		Cliente cliente = clientRepo.findByEmail(email).get();
		return cliente;
	}

	public void salvarCliente(Cliente c) {
		clientRepo.save(c);
	}
}
