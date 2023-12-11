package com.votacaoPauta.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.votacaoPauta.domain.associate.Associate;

public interface AssociateRepository extends JpaRepository<Associate, Long> {
	
	Optional<Associate> findAssociateById(Long id);
	
}
