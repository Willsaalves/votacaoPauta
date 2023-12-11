package com.votacaoPauta.domain.associate;

import java.util.Set;

import com.votacaoPauta.domain.schedule.Schedule;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "associate")
@Table(name = "associate")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class Associate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	
	@ManyToMany
	@JoinTable(
			name = "associate_schedule",
            joinColumns = @JoinColumn(name = "associate_id"),
            inverseJoinColumns = @JoinColumn(name = "schedule_id"))
	private Set<Schedule>schedule;

}
