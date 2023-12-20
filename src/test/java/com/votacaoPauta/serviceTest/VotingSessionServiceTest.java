package com.votacaoPauta.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.votacaoPauta.domain.vote.Vote;
import com.votacaoPauta.domain.votingSession.VotingSession;
import com.votacaoPauta.repositories.VoteRepository;
import com.votacaoPauta.repositories.VotingSessionRepository;
import com.votacaoPauta.services.VotingSessionService;

@SpringBootTest
public class VotingSessionServiceTest {

    @Test
    public void testContabilizarVotos() {
        // Mock dependencies
        VotingSessionRepository votingSessionRepository = mock(VotingSessionRepository.class);
        VoteRepository voteRepository = mock(VoteRepository.class);

        // Create VotingSessionService with mocked dependencies
        VotingSessionService votingSessionService = new VotingSessionService(votingSessionRepository, voteRepository);

        // Create a sample VotingSession
        VotingSession votingSession = new VotingSession();
        votingSession.setId(1L);

        // Create sample votes
        Vote vote1 = new Vote();
        vote1.setVoto("SIM");

        Vote vote2 = new Vote();
        vote2.setVoto("NAO");

        List<Vote> votes = new ArrayList<>();
        votes.add(vote1);
        votes.add(vote2);

        // Mock behavior of repositories
        when(voteRepository.findByVotingSession(any(VotingSession.class))).thenReturn(votes);

        // Test the contabilizarVotos method
        Object resultado = votingSessionService.contabilizarVotos(votingSession);

        // Assert the result
        assertEquals("Resultado: Empate", resultado);
    }
}
