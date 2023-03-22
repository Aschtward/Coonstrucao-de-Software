package com.example.demo.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.models.AnuncioModel;
import com.example.demo.models.AvaliacaoModel;
import com.example.demo.models.ClienteModels;
import com.example.demo.models.ProdutoCompradoModel;
import com.example.demo.repository.AnuncioRepository;
import com.example.demo.repository.ProdutoRepository;

@Component
public class AnuncioDAO {

	@Autowired
	private AnuncioRepository anuncioRepo;
	@Autowired
	ClientDAO cDao;
	@Autowired
	private ProdutoRepository compraRepository;

	public List<AnuncioModel> exibirTodos() {
		return anuncioRepo.findAll();
	}

	public List<AnuncioModel> maisvendido() {
		List<AnuncioModel> anuncios = anuncioRepo.findAll();
		if (anuncios.size() >= 10) {
			List<AnuncioModel> anuncioMaisVendido = new ArrayList<AnuncioModel>();
			anuncios.sort(new Comparator<AnuncioModel>() {
				public int compare(AnuncioModel a1, AnuncioModel a2) {
					if (a1.getVendas() > a2.getVendas()) {
						return -1;
					} else {
						return +1;
					}
				}
			});
			for (int i = 0; i < 10; i++) {
				anuncioMaisVendido.add(anuncios.get(i));
			}
			return anuncioMaisVendido;
		}
		return anuncios;
	}

	public Optional<AnuncioModel> buscarAnuncio(String id) {
		return anuncioRepo.findById(Long.valueOf(id));
	}

	public boolean inserirAnuncio(String nome, BigDecimal price, MultipartFile image, String descricao) {
		try {
			AnuncioModel anuncio = new AnuncioModel();
			anuncio.setName(nome);
			anuncio.setPreco(price);
			anuncio.setDescricao(descricao);
			anuncio.setVendas(0);
			anuncio.setNotaTotal(0);
			String imageId = UUID.randomUUID().toString();
			anuncio.setImage(imageId);
			byte[] bytesDaImagem = image.getBytes();
			File arquivo = new File("src/main/resources/static/img/prodImages/" + imageId + ".jpg");
			try (FileOutputStream fos = new FileOutputStream(arquivo)) {
				fos.write(bytesDaImagem);
				fos.close();
				anuncioRepo.save(anuncio);
			}
			Object cliente = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (cliente instanceof UserDetails) {
				String email = ((UserDetails) cliente).getUsername();
				ClienteModels anunciante = cDao.buscaCliente(email);
				List<AnuncioModel> anuncios = anunciante.getAnuncio();
				anuncios.add(anuncio);
				anunciante.setAnuncio(anuncios);
				cDao.salvarCliente(anunciante);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;

	}

	public List<AnuncioModel> buscarAnuncioLike(String nome) {
		return anuncioRepo.findByNameContaining(nome);
	}

	public boolean avaliarProduto(String id, String nota, String avaliacao, String idCompra) {
		Optional<AnuncioModel> anuncio = anuncioRepo.findById(Long.parseLong(id));
		if (anuncio.isPresent()) {
			ClienteModels cliente = cDao.buscarSessaoCliente();
			AvaliacaoModel novaAvaliacao = new AvaliacaoModel();
			novaAvaliacao.setCliente(cliente);
			novaAvaliacao.setNota(Integer.parseInt(nota));
			novaAvaliacao.setAvaliacao(avaliacao);
			anuncio.get().getAvaliacao().add(novaAvaliacao);
			System.out.println("oxi");
			int notaTotal = 0;
			for (AvaliacaoModel ava : anuncio.get().getAvaliacao()) {
				notaTotal += ava.getNota();
			}
			anuncio.get().setNotaTotal((int) (notaTotal / anuncio.get().getAvaliacao().size()));
			anuncioRepo.save(anuncio.get());
			Optional<ProdutoCompradoModel> compra = compraRepository.findById(Long.parseLong(idCompra));
			if (compra.isPresent()) {
				compra.get().setFoiAvaliado(true);
				compraRepository.save(compra.get());
			}
			return true;
		}
		return true;

	}

	public boolean alterarAnuncio(String nome, BigDecimal price, MultipartFile image, String descricao, String id) {
		Optional<AnuncioModel> anuncio = anuncioRepo.findById(Long.parseLong(id));
		if (anuncio.isPresent()) {
				anuncio.get().setName(nome);
				anuncio.get().setPreco(price);
				anuncio.get().setDescricao(descricao);
				if (image != null) {
					File arquivo = new File(
							"src/main/resources/static/img/prodImages/" + anuncio.get().getImage() + ".jpg");
					try (FileOutputStream fos = new FileOutputStream(arquivo)) {
						byte[] bytesDaImagem = image.getBytes();
						fos.write(bytesDaImagem);
						fos.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				anuncioRepo.save(anuncio.get());
				return true;
		}
		return false;

	}
}
