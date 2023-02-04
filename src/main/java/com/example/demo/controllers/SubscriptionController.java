package com.example.demo.controllers;

import com.example.demo.dao.AssinaturaDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SubscriptionController {
    @Autowired
    AssinaturaDAO aDao;

    @GetMapping("/assinar")
    public String subscribe() {
        return "promo";
    }

    @PostMapping("/subscription")
    private String validarAssinatura() {
        aDao.getCliente();
        return "/index";
    }
}
