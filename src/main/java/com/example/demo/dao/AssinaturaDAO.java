package com.example.demo.dao;

import java.time.LocalDate;

import com.example.demo.models.Assinatura;
import com.example.demo.models.Cliente;
import com.example.demo.repository.AssinaturaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

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
        if (cliente instanceof Cliente) {
            Assinatura ass = new Assinatura(geraData());
            String email = ((UserDetails) cliente).getUsername();
            Cliente aux = cDao.buscaCliente(email);
            aux.setRoles(rDao.buscarRoles());
            aux.setAssinatura(ass);
            cDao.salverCliente(aux);
            aRepo.save(ass);
        }
    }

    public LocalDate geraData() {
        return LocalDate.now().plusMonths(1);
    }
}
