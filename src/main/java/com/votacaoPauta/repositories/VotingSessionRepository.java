package com.votacaoPauta.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.votacaoPauta.domain.schedule.Schedule;
import com.votacaoPauta.domain.votingSession.VotingSession;

@Repository
public interface VotingSessionRepository extends JpaRepository<VotingSession, Long> {

	Optional<VotingSession> findBySchedule(Schedule schedule);

}
