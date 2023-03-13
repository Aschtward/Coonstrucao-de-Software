package com.example.demo.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ErrorControler implements ErrorController {
	
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
    	return "error";
    }
}
