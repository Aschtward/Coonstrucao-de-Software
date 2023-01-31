package com.example.demo.models;

import java.util.Date;
import java.util.UUID;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class ConfirmationTolkenModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="token_id")
    private long tokenid;

    @Column(name="confirmation_token")
    private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @OneToOne(targetEntity = Cliente.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "cliente_id")
    private Cliente user;
    
    public ConfirmationTolkenModel() {
 
	}

    public ConfirmationTolkenModel(Cliente user) {
    	this.user = user;
        createdDate =  new Date();
        confirmationToken = UUID.randomUUID().toString();
	}

	public long getTokenid() {
		return tokenid;
	}

	public void setTokenid(long tokenid) {
		this.tokenid = tokenid;
	}

	public String getConfirmationToken() {
		return confirmationToken;
	}

	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Cliente getUser() {
		return user;
	}

	public void setUser(Cliente user) {
		this.user = user;
	}
    
    
}
