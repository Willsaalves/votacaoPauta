package com.votacaoPauta.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.votacaoPauta.domain.schedule.Schedule;
import com.votacaoPauta.repositories.ScheduleRepository;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository repository;

    public List<Schedule> listAgendas() {
        return repository.findAll();
    }

    public Optional<Schedule> findAgendaById(Long id) {
        return repository.findById(id);
    }

    public Schedule saveAgenda(Schedule schedule) {
        return repository.save(schedule);
    }

    public void deleteAgenda(Long id) {
    	repository.deleteById(id);
    }
}
