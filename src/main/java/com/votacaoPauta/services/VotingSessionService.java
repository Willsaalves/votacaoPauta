package com.votacaoPauta.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.votacaoPauta.domain.schedule.Schedule;
import com.votacaoPauta.domain.vote.Vote;
import com.votacaoPauta.domain.votingSession.VotingSession;
import com.votacaoPauta.repositories.VoteRepository;
import com.votacaoPauta.repositories.VotingSessionRepository;

@Service
public class VotingSessionService {

    private static final String RESULTADO_APROVADO = "Resultado: Aprovado";
    private static final String RESULTADO_REJEITADO = "Resultado: Rejeitado";
    private static final String RESULTADO_EMPATE = "Resultado: Empate";

    private final VotingSessionRepository votingSessionRepository;
    private final VoteRepository voteRepository;

    @Autowired
    public VotingSessionService(VotingSessionRepository votingSessionRepository, VoteRepository voteRepository) {
        this.votingSessionRepository = votingSessionRepository;
        this.voteRepository = voteRepository;
    }

    public Optional<VotingSession> findVotingSessionById(Long id) {
        return votingSessionRepository.findById(id);
    }

    public Optional<VotingSession> findBySchedule(Schedule schedule) {
        return votingSessionRepository.findBySchedule(schedule);
    }

    public VotingSession saveVotingSession(VotingSession votingSession) {
        return votingSessionRepository.save(votingSession);
    }

    public boolean isVotingSessionOpen(VotingSession votingSession) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime closingTime = votingSession.getDataAbertura().plusMinutes(votingSession.getDuracaoEmMinutos());

        return closingTime.isAfter(now);
    }

    public Object contabilizarVotos(VotingSession votingSession) {
        List<Vote> votes = voteRepository.findByVotingSession(votingSession);

        long votosSim = votes.stream().filter(vote -> "SIM".equals(vote.getVoto())).count();
        long votosNao = votes.stream().filter(vote -> "NAO".equals(vote.getVoto())).count();

        if (votosSim > votosNao) {
            return RESULTADO_APROVADO;
        } else if (votosNao > votosSim) {
            return RESULTADO_REJEITADO;
        } else {
            return RESULTADO_EMPATE;
        }
    }
}
