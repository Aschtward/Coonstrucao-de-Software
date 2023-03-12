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
import com.example.demo.models.ClienteModels;
import com.example.demo.repository.AnuncioRepository;

@Component
public class AnuncioDAO {

	@Autowired
	private AnuncioRepository anuncioRepo;
    @Autowired
    ClientDAO cDao;
    
    public List<AnuncioModel> exibirTodos(){
    	return anuncioRepo.findAll();
    }
    
    public List<AnuncioModel> maisvendido(){
    	List<AnuncioModel> anuncios = anuncioRepo.findAll();
    	if(anuncios.size() >= 5) {
        	List<AnuncioModel> anuncioMaisVendido = new ArrayList<AnuncioModel>();
        	anuncios.sort(new Comparator<AnuncioModel>() {
        		public int compare(AnuncioModel a1, AnuncioModel a2) {
        			if(a1.getVendas() > a2.getVendas()) {
        				return +1;
        			}else {
        				return -1;
        			}
        		}
        	});
        	for(int i = 0; i < 10; i++) {
        		anuncioMaisVendido.add(anuncios.get(i));
        	}
        	return anuncioMaisVendido;
    	}
    	return anuncios;
    }
    
    public Optional<AnuncioModel> buscarAnuncio(String id) {
    	return anuncioRepo.findById(Long.valueOf(id));
    }

	public void inserirAnuncio(String nome, BigDecimal price, MultipartFile image, String descricao) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
