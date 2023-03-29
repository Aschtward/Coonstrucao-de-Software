package com.example.demo.dao;

import com.example.demo.adapter.ClientAdapter;
import com.example.demo.enums.RoleName;
import com.example.demo.models.ClienteModels;
import com.example.demo.models.EnderecoModel;
import com.example.demo.models.TokenModel;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.TokenRepository;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

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
	@Autowired
	EnderecoDAO enderecoDao;

	public ClienteModels adicionarCliente(String name, String email,
			String password) {
		BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();

		if (roleDao.buscarAllRoles().isEmpty()) {
			roleDao.inserirRole(RoleName.ROLE_CLIENTE);
			roleDao.inserirRole(RoleName.ROLE_ANUNCIANTE);
		}

		ClienteModels cliente = new ClienteModels(email, enconder.encode(password), name, false,
				roleDao.buscarRoles(RoleName.ROLE_CLIENTE));

		TokenModel confirmationTolken = new TokenModel(cliente);
		clientRepo.save(cliente);
		emailDao.sendConfirmationEmail(email,
				confirmationTolken.getRecoveryToken());
		tokenRepo.save(confirmationTolken);
		return cliente;
	}

	public void confirmarEmail(String confirmationToken) {
		TokenModel token = tokenRepo.findByRecoveryToken(confirmationToken);
		if (token != null) {
			ClienteModels cliente = new ClienteModels();
			clientAdapter.geraCliente(
					clientRepo.findByEmail(token.getUser().getEmail()), cliente);
			cliente.setIsConfirmed(true);
			clientRepo.save(cliente);
		}
	}

	public boolean changePassword(String newPassword, String token) {
		TokenModel tokenModel = tokenDao.buscarToken(token);
		if (tokenModel != null &&
				tokenModel.getCreatedDate().isAfter(LocalDate.now())) {
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

	public boolean sendRecoveryToken(String email) {
		Optional<ClienteModels> cliente = clientRepo.findByEmail(email);
		if (cliente.isPresent()) {
			String token = tokenDao.gerarToken(cliente.get());
			emailDao.sendRecoveryEmail(email, token);
			return true;
		}
		return false;
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

	public void cadastrarEnderecoCliente(String nome, String cidade, String rua,
			String bairro, String numero) {
		EnderecoModel endereco = enderecoDao.mudarEndereco(bairro, rua, cidade, numero, nome);
		System.out.println(endereco.getNome());
		ClienteModels cliente = buscarSessaoCliente();
		cliente.getEndereco().add(endereco);
		clientRepo.save(cliente);
	}

	public boolean alterarCliente(String nome, String senhaAntiga,
			String senhaNova, String email) {
		ClienteModels cliente = this.buscarSessaoCliente();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if (encoder.matches(senhaAntiga, cliente.getPassword())) {
			if (!senhaNova.equals("")) {
				cliente.setPassword(encoder.encode(senhaNova));
			}
			cliente.setEmail(email);
			cliente.setName(nome);
			clientRepo.save(cliente);
			return true;
		}
		return false;
	}

<<<<<<< HEAD

=======
	public void excluirConta() {
		clientRepo.delete(buscarSessaoCliente());
	}
>>>>>>> bc7665c5c17266c16d477949d5c9e1de6fb077e6
}
