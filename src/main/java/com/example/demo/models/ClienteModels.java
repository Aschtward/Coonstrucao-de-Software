package com.example.demo.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "tb_clientes")
public class ClienteModels implements UserDetails {

  private static final long serialVersionUID = 1L;

  @Id @GeneratedValue(strategy = GenerationType.AUTO) protected Long id;

  @NotBlank @Column(unique = true) @Email protected String email;

  @NotBlank @Column protected String password;

  @NotBlank @Column private String name;

  @Column private Boolean isConfirmed;

  @OneToMany(cascade = CascadeType.PERSIST)
  @JoinTable(name = "TB_CLIENTE_ANUNCIO",
             joinColumns = @JoinColumn(name = "user_id"),
             inverseJoinColumns = @JoinColumn(name = "anuncio_id"))
  private List<AnuncioModel> anuncio;

  @OneToMany(cascade = CascadeType.PERSIST)
  @JoinTable(name = "TB_CLIENTE_ENDERECO",
             joinColumns = @JoinColumn(name = "user_id"),
             inverseJoinColumns = @JoinColumn(name = "endereco_id"))
  private List<EnderecoModel> endereco;

  @OneToOne
  @JoinTable(name = "TB_CLIENTE_SUBSCRIPTION",
             joinColumns = @JoinColumn(name = "user_id"),
             inverseJoinColumns = @JoinColumn(name = "subscription_id"))
  private Assinatura assinatura;

  @OneToMany
  @JoinTable(name = "TB_CLIENTE_DIETA",
             joinColumns = @JoinColumn(name = "user_id"),
             inverseJoinColumns = @JoinColumn(name = "dieta_id"))
  private List<DietaModel> dietaModels;

  public List<DietaModel> getDietaModels() { return dietaModels; }

  public void setDietaModels(List<DietaModel> dietaModels) {
    this.dietaModels = dietaModels;
  }

  @OneToMany
  @JoinTable(name = "TB_CLIENTE_TREINO",
             joinColumns = @JoinColumn(name = "user_id"),
             inverseJoinColumns = @JoinColumn(name = "treino_id"))
  private List<TreinoModel> treinoModels;

  public List<TreinoModel> getTreinoModels() { return treinoModels; }

  public void setTreinoModels(List<TreinoModel> treinoModels) {
    this.treinoModels = treinoModels;
  }

  public ClienteModels(@NotBlank String email, @NotBlank String password,
                       @NotBlank String name, Boolean isConfirmed,
                       List<RoleModel> roles) {
    super();
    this.email = email;
    this.password = password;
    this.name = name;
    this.isConfirmed = isConfirmed;
    this.roles = roles;
  }

  public ClienteModels() {
    // TODO Auto-generated constructor stub
  }

  @ManyToMany
  @JoinTable(name = "TB_CLIENTE_ROLES",
             joinColumns = @JoinColumn(name = "user_id"),
             inverseJoinColumns = @JoinColumn(name = "role_id"))
  private List<RoleModel> roles;

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.roles;
  }

  @Override
  public String getUsername() {
    // TODO Auto-generated method stub
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    // TODO Auto-generated method stub
    return this.getIsConfirmed();
  }

  @Override
  public boolean isAccountNonLocked() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isEnabled() {
    // TODO Auto-generated method stub
    return this.isConfirmed;
  }

  public Long getId() { return id; }

  public void setId(Long id) { this.id = id; }

  public String getName() { return name; }

  public void setName(String name) { this.name = name; }

  public String getEmail() { return email; }

  public void setEmail(String email) { this.email = email; }

  public List<RoleModel> getRoles() { return roles; }

  public void setRoles(List<RoleModel> roles) { this.roles = roles; }

  public void setPassword(String password) { this.password = password; }

  public Boolean getIsConfirmed() { return this.isConfirmed; }

  public void setIsConfirmed(Boolean isConfirmed) {
    this.isConfirmed = isConfirmed;
  }

  public Assinatura getAssinatura() { return assinatura; }

  public void setAssinatura(Assinatura assinatura) {
    this.assinatura = assinatura;
  }

  public static long getSerialversionuid() { return serialVersionUID; }

  public List<AnuncioModel> getAnuncio() { return anuncio; }

  public void setAnuncio(List<AnuncioModel> anuncio) { this.anuncio = anuncio; }

  public List<EnderecoModel> getEndereco() { return endereco; }

  public void setEndereco(List<EnderecoModel> endereco) {
    this.endereco = endereco;
  }
}
