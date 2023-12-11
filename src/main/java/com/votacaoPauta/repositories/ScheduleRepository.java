package com.votacaoPauta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.votacaoPauta.domain.associate.Associate;

public interface ScheduleRepository extends JpaRepository<Associate, Long> {

}
