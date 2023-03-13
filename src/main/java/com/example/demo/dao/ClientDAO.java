package com.example.demo.dao;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.adapter.ClientAdapter;
import com.example.demo.enums.RoleName;
import com.example.demo.models.ClienteModels;
import com.example.demo.models.ProdutoCompradoModel;
import com.example.demo.models.TokenModel;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.TokenRepository;

@Component
public class ClientDAO {

	@Autowired
	ClientRepository clientRepo;
	@Autowired
	ClientAdapter clientAdapter;
	@Autowired
	EmailDAO emailDao;
	@Autowired
	RolesDAO roleDao;
	@Autowired
	TokenRepository tokenRepo;
	@Autowired
	TokenDAO tokenDao;
	

	public ClienteModels adicionarCliente(String name, String email, String password) {
		BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
		
		if(roleDao.buscarAllRoles().isEmpty()) {
			roleDao.inserirRole(RoleName.ROLE_CLIENTE);
			roleDao.inserirRole(RoleName.ROLE_ANUNCIANTE);
		}

		ClienteModels cliente = new ClienteModels(email, enconder.encode(password), name, false,
				roleDao.buscarRoles(RoleName.ROLE_CLIENTE));

		TokenModel confirmationTolken = new TokenModel(cliente);
		clientRepo.save(cliente);
		emailDao.sendConfirmationEmail(email, confirmationTolken.getRecoveryToken());
		tokenRepo.save(confirmationTolken);
		return cliente;
	}

	public void confirmarEmail(String confirmationToken) {
		TokenModel token = tokenRepo.findByRecoveryToken(confirmationToken);
		if (token != null) {
			ClienteModels cliente = new ClienteModels();
			clientAdapter.geraCliente(clientRepo.findByEmail(token.getUser().getEmail()), cliente);
			cliente.setIsConfirmed(true);
			clientRepo.save(cliente);
		}
	}

	public boolean changePassword(String newPassword, String token) {
		TokenModel tokenModel = tokenDao.buscarToken(token);
		if (tokenModel != null && tokenModel.getCreatedDate().isAfter(LocalDate.now())) {
			Optional<ClienteModels> cliente = clientRepo.findByEmail(tokenModel.getUser().getEmail());
			if (cliente.isPresent()) {
				BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
				cliente.get().setPassword(enconder.encode(newPassword));
				tokenDao.removerToken(token);
				clientRepo.save(cliente.get());
				return true;
			}
		}
		return false;
	}

	public void sendRecoveryToken(String email) {
		ClienteModels cliente = new ClienteModels();
		clientAdapter.geraCliente(clientRepo.findByEmail(email), cliente);
		String token = tokenDao.gerarToken(cliente);
		emailDao.sendRecoveryEmail(email, token);
	}

	public void salvarCliente(ClienteModels aux) {
		clientRepo.save(aux);

	}
	
	public ClienteModels buscarSessaoCliente() {
		Object cliente = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (cliente instanceof UserDetails) {
        	String email = ((UserDetails) cliente).getUsername();
        	ClienteModels clienteAtual = buscaCliente(email);
        	return clienteAtual;
        }
        return null;
	}

	public ClienteModels buscaCliente(String email) {
		return clientRepo.findByEmail(email).get();
	}

}
