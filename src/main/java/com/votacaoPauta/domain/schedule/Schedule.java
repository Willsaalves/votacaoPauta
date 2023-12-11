package com.votacaoPauta.domain.schedule;

import com.votacaoPauta.domain.associate.Associate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "schedule")
@Table(name = "schedule")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Schedule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;

	@ManyToMany(mappedBy = "schedule")
	private Associate associate;
}
