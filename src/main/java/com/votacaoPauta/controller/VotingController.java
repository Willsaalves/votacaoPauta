package com.votacaoPauta.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.votacaoPauta.DTO.SessionRequestDTO;
import com.votacaoPauta.DTO.VoteDTO;
import com.votacaoPauta.domain.associate.Associate;
import com.votacaoPauta.domain.schedule.Schedule;
import com.votacaoPauta.domain.vote.Vote;
import com.votacaoPauta.domain.votingSession.VotingSession;
import com.votacaoPauta.services.AssociateService;
import com.votacaoPauta.services.ScheduleService;
import com.votacaoPauta.services.VoteService;
import com.votacaoPauta.services.VotingSessionService;

@RestController
@RequestMapping("/votacao")
public class VotingController {

	@Autowired
	ScheduleService scheduleService;

	@Autowired
	VotingSessionService votingSessionService;

	@Autowired
	AssociateService associateService;

	
	@Autowired
	VoteService voteService;

	@PostMapping("/cadastrarSchedule")
	public ResponseEntity<Schedule> cadastrarSchedule(@RequestBody Schedule Schedule) {
		try {
			Schedule newSchedule = scheduleService.saveAgenda(Schedule);
			return new ResponseEntity<>(newSchedule, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/abrirSessao/{scheduleId}")
	public ResponseEntity<?> abrirSessao(@PathVariable Long scheduleId,
	                                      @RequestBody SessionRequestDTO sessaoRequestDTO) {

	    try {
	        LocalDateTime dataAbertura = LocalDateTime.now();

	        Schedule schedule = scheduleService.findAgendaById(scheduleId)
	                .orElseThrow(() -> new RuntimeException("Schedule não encontrada"));

	        // Verificar se há uma sessão aberta para esta Schedule
	        Optional<VotingSession> sessaoExistente = votingSessionService.findBySchedule(schedule);

	        if (sessaoExistente.isPresent()) {
	            VotingSession sessaoAtual = sessaoExistente.get();
	            LocalDateTime dataFechamento = sessaoAtual.getDataAbertura()
	                    .plusMinutes(sessaoAtual.getDuracaoEmMinutos());

	            if (LocalDateTime.now().isBefore(sessaoAtual.getDataAbertura()) || LocalDateTime.now().isAfter(dataFechamento)) {
	                return new ResponseEntity<>("A sessão de votação para esta Schedule está fechada",
	                        HttpStatus.BAD_REQUEST);
	            } else {
	                return new ResponseEntity<>("Já existe uma sessão de votação aberta para esta Schedule",
	                        HttpStatus.BAD_REQUEST);
	            }
	        }

	        // Criar uma nova sessão
	        VotingSession novaSessao = new VotingSession();
	        novaSessao.setSchedule(schedule);
	        novaSessao.setDataAbertura(dataAbertura);

	        Integer duracaoEmMinutos = (sessaoRequestDTO.getDuracaoEmMinutos() != null)
	                ? sessaoRequestDTO.getDuracaoEmMinutos()
	                : 1;
	        novaSessao.setDuracaoEmMinutos(duracaoEmMinutos);

	        votingSessionService.saveVotingSession(novaSessao);

	        return new ResponseEntity<>(novaSessao, HttpStatus.CREATED);

	    } catch (Exception e) {
	        return new ResponseEntity<>("Erro ao abrir sessão de votação: " + e.getMessage(),
	                HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	@PostMapping("/receberVoto/{scheduleId}")
	public ResponseEntity<String> receberVoto(@RequestBody VoteDTO voteDTO, @PathVariable Long scheduleId) {

		Long associateId = voteDTO.getAssociateId();
		String voto = voteDTO.getVoto();

		try {
			// Verificar se o associado existe
			Associate associate = associateService.findAssociateById(associateId)
					.orElseThrow(() -> new RuntimeException("Associado não encontrado"));

			// Verificar se a Schedule existe
			Schedule schedule = scheduleService.findAgendaById(scheduleId)
					.orElseThrow(() -> new RuntimeException("Schedule não encontrada"));
			
		    // Verificar se a sessão está aberta
	        VotingSession votingSession = votingSessionService.findBySchedule(schedule)
	                .orElseThrow(() -> new RuntimeException("Sessão não encontrada para a Schedule"));

	        LocalDateTime dataAtual = LocalDateTime.now();
	        LocalDateTime dataFechamento = votingSession.getDataAbertura().plusMinutes(votingSession.getDuracaoEmMinutos());

	        if (dataAtual.isBefore(votingSession.getDataAbertura()) || dataAtual.isAfter(dataFechamento)) {
	            return new ResponseEntity<>("A sessão de votação para esta Schedule está fechada", HttpStatus.BAD_REQUEST);
	        }

			// Verificar se o associado já votou nesta Schedule
			if (voteService.hasVotedInSchedule(associate, schedule)) {
				return new ResponseEntity<>("Associado já votou nesta Schedule", HttpStatus.BAD_REQUEST);
			}

			// Registrar o voto
			Vote novoVoto = new Vote();
			novoVoto.setAssociate(associate);
			novoVoto.setSchedule(schedule);
			novoVoto.setVoto(voto);

			voteService.saveVote(schedule, novoVoto);

			return new ResponseEntity<>("Voto registrado com sucesso", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>("Erro ao registrar voto: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/resultadoVotacao/{scheduleId}")
	public ResponseEntity<String> resultadoVotacao(@PathVariable Long scheduleId) {
		try {
			// Verificar se a Schedule existe
			Schedule schedule = scheduleService.findAgendaById(scheduleId)
					.orElseThrow(() -> new RuntimeException("Schedule não encontrada"));

			// Contabilizar os votos
			long votosSim = voteService.countByScheduleAndVoto(schedule, true);
			long votosNao = voteService.countByScheduleAndVoto(schedule, false);

			// Dar o resultado da votação
			String resultado = "Resultado da votação para a Schedule '" + schedule.getDescricao() + "': "
					+ "Votos Sim: " + votosSim + ", Votos Não: " + votosNao;

			return new ResponseEntity<>(resultado, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>("Erro ao obter resultado da votação: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
