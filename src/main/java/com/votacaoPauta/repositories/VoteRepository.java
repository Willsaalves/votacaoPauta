package com.votacaoPauta.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.votacaoPauta.domain.associate.Associate;
import com.votacaoPauta.domain.schedule.Schedule;
import com.votacaoPauta.domain.vote.Vote;
import com.votacaoPauta.domain.votingSession.VotingSession;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long>{

	boolean existsByAssociateAndSchedule(Associate associate, Schedule schedule);

	long countByScheduleAndVoto(Schedule schedule, boolean vote);
	
	List<Vote> findByAssociateAndSchedule(Associate associate, Schedule schedule);
	
	List<Vote> findByVotingSession(VotingSession votingSession);


}
