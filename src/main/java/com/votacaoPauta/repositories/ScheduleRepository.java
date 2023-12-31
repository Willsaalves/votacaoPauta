package com.votacaoPauta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.votacaoPauta.domain.schedule.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}
