package com.votacaoPauta.domain.vote;

import com.votacaoPauta.domain.associate.Associate;
import com.votacaoPauta.domain.schedule.Schedule;
import com.votacaoPauta.domain.votingSession.VotingSession;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vote")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Vote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "associate_id")
	private Associate associate;

	@ManyToOne
	@JoinColumn(name = "schedule_id")
	private Schedule schedule;

	@ManyToOne
	private VotingSession votingSession;

	private boolean voto; // true para Sim, false para Não

	public void setVoto(String voto) {
		// Converte a string para boolean
		this.voto = "Sim".equalsIgnoreCase(voto);
	}

	public String getVoto() {
		// Converte o boolean para string
		return voto ? "Sim" : "Não";
	}
}
