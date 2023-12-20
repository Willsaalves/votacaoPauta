package com.votacaoPauta.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.votacaoPauta.domain.associate.Associate;
import com.votacaoPauta.domain.schedule.Schedule;
import com.votacaoPauta.domain.vote.Vote;
import com.votacaoPauta.repositories.VoteRepository;

@Service
public class VoteService {

	@Autowired
	VoteRepository voteRepository;
	
	 public List<Vote> listVote() {
	        return voteRepository.findAll();
	    }

	    public Optional<Vote> findVoteById(Long id) {
	        return voteRepository.findById(id);
	    }

	    public Vote saveVote(Schedule schedule , Vote vote) {
	        return voteRepository.save(vote);
	    }

	    public void deleteVote(Long id) {
	    	voteRepository.deleteById(id);
	    }
	    
		public boolean existsByAssociateAndSchedule(Associate associate, Schedule schedule){
			return voteRepository.existsByAssociateAndSchedule(associate, schedule);
		}

		public long countByScheduleAndVoto(Schedule schedule, boolean vote) {
			return voteRepository.countByScheduleAndVoto(schedule, vote);
		}

		 public boolean hasVotedInSchedule(Associate associate, Schedule schedule) {
		        // Verificar se o associado já votou nesta Schedule
		        List<Vote> votes = voteRepository.findByAssociateAndSchedule(associate, schedule);
		        return !votes.isEmpty();
		    }
	
}
