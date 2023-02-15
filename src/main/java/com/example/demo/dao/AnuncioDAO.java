package com.example.demo.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.models.AnuncioModel;
import com.example.demo.models.ClienteModels;
import com.example.demo.repository.AnuncioRepository;

@Component
public class AnuncioDAO {

	@Autowired
	private AnuncioRepository anuncioRepo;
    @Autowired
    ClientDAO cDao;

	public void inserirAnuncio(String nome, BigDecimal price, MultipartFile image) {
		try {
			AnuncioModel anuncio = new AnuncioModel();
			anuncio.setName(nome);
			anuncio.setPreco(price);
			String imageId = UUID.randomUUID().toString();
			anuncio.setImage(imageId);
			byte[] bytesDaImagem = image.getBytes();
			File arquivo = new File("src/main/resources/static/prodImages/" + imageId + ".jpg");
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
	        }else {
	        	System.out.println("ERROR");
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
