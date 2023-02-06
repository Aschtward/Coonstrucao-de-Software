package com.example.demo.dao;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.demo.models.Assinatura;
import com.example.demo.models.ClienteModels;
import com.example.demo.repository.AssinaturaRepository;

@Component
public class AssinaturaDAO {
    @Autowired
    ClientDAO cDao;

    @Autowired
    AssinaturaRepository aRepo;

    @Autowired
    RolesDAO rDao;

    public void getCliente() {
        Object cliente = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (cliente instanceof UserDetails) {
            Assinatura ass = new Assinatura(geraData());
            String email = ((UserDetails) cliente).getUsername();
            ClienteModels aux = cDao.buscaCliente(email);
            aux.setRoles(rDao.buscarAllRoles());
            aux.setAssinatura(ass);
            aRepo.save(ass);
            cDao.salvarCliente(aux);
        }else {
        	System.out.println("ERROR");
        }
    }

    public LocalDate geraData() {
        return LocalDate.now().plusMonths(1);
    }
}
