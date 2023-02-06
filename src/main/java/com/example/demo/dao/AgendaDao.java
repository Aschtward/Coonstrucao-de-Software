package com.example.demo.dao;

import com.example.demo.models.Agenda;
import com.example.demo.models.Atividades;
import com.example.demo.repository.AgendaRepository;
import com.example.demo.repository.AtividadesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AgendaDao {

    final AgendaRepository agendaRepository;
    final AtividadesRepository atividadesRepository;

    @Autowired
    public AgendaDao(AgendaRepository agendaRepository, AtividadesRepository atividadesRepository) {
        this.agendaRepository = agendaRepository;
        this.atividadesRepository = atividadesRepository;
    }

    public void addAgenda(Agenda agenda) {
        agendaRepository.save(agenda);
    }

    public Agenda getAgenda(Integer id) {
        return agendaRepository.findById(id.longValue()).get();
    }

    public List<Atividades> getAtividades(Integer id) {
        Optional<Agenda> agenda = agendaRepository.findById(id.longValue());
        return agenda.map(Agenda::getAtividades).orElse(null);
    }

    public void addAtividade(Atividades atividades, Integer agendaId) {
        Agenda agenda = getAgenda(agendaId);
        List<Atividades> atividadesList = getAtividades(agendaId);
        atividadesList.add(atividadesRepository.save(atividades));
        agenda.setAtividades(atividadesList);
        addAgenda(agenda);
    }

    public void deleteAtividade(Integer atividadeId, Integer agendaId) {
        Agenda agenda = getAgenda(agendaId);
        List<Atividades> atividadesList = getAtividades(agendaId);
        atividadesList.remove(atividadesRepository.findById())
        atividadesRepository.deleteById(atividadeId.longValue());
    }
}
