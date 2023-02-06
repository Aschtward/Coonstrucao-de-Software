package com.example.demo.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "tb_agenda")
public class Agenda {

    @Id
    @GeneratedValue(strategy  = GenerationType.AUTO)
    private Integer id;

    private LocalDate data;

    @OneToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Atividades> atividades;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
//    private ClienteModels cliente;
}
