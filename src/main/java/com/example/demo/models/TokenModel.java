package com.example.demo.models;

import java.time.LocalDate;
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
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "tb_passwordtoken")
public class TokenModel {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="token_id")
    private long tokenid;

    @Column(name="recovery_token")
    private String recoveryToken;

    @Column
    private LocalDate endDate;

    @OneToOne(targetEntity = ClienteModels.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "cliente_id")
    private ClienteModels user;

	public TokenModel(ClienteModels user) {
		super();
		this.recoveryToken = UUID.randomUUID().toString();
		this.endDate = LocalDate.now().plusDays(1);
		this.user = user;
	}
	
	public TokenModel() {
		
	}

	public String getRecoveryToken() {
		return recoveryToken;
	}

	public void setRecoveryToken(String recoveryToken) {
		this.recoveryToken = recoveryToken;
	}

	public LocalDate getCreatedDate() {
		return endDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.endDate = createdDate;
	}

	public ClienteModels getUser() {
		return user;
	}

	public void setUser(ClienteModels user) {
		this.user = user;
	}
	
}
