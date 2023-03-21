package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.dao.AssinaturaDAO;
import com.example.demo.dao.ClientDAO;
import com.example.demo.dao.ProdutoCompradoDAO;
import com.example.demo.dao.RolesDAO;
import com.example.demo.enums.RoleName;
import com.example.demo.models.ClienteModels;
import com.example.demo.models.ProdutoCompradoModel;
import com.example.demo.models.RoleModel;

@Controller
public class SubscriptionController {
    @Autowired
    AssinaturaDAO aDao;
    @Autowired
    ClientDAO clienteDao;
    @Autowired
    RolesDAO roleDao;
	@Autowired
	ProdutoCompradoDAO compraDAO;
	
    @GetMapping("/anunciante")
    public ModelAndView subscribe() {
    	List<RoleModel> roles = roleDao.buscarRoles(RoleName.ROLE_ANUNCIANTE);
    	ClienteModels cliente  = clienteDao.buscarSessaoCliente();
    	List<RoleModel> clientRoles = cliente.getRoles();
        if(cliente != null && clientRoles.size() > 1) {
        	ModelAndView anuncianteMenu = new ModelAndView("menu_anunciante");
        	List<ProdutoCompradoModel> vendas = compraDAO.buscarPorVendedor(cliente);
        	anuncianteMenu.addObject("anuncios", cliente.getAnuncio());
        	anuncianteMenu.addObject("vendas",vendas);
        	anuncianteMenu.addObject("cliente",clienteDao.buscarSessaoCliente());
        	return anuncianteMenu;
        }
        ModelAndView novoAnunciante = new ModelAndView("novo_anunciante");
        novoAnunciante.addObject("cliente",clienteDao.buscarSessaoCliente());
        return novoAnunciante;
    }

    @PostMapping("/subscription")
    private RedirectView validarAssinatura() {
        aDao.getCliente();
        return new RedirectView("/");
    }
}
