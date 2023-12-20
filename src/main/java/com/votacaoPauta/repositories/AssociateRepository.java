package com.votacaoPauta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.votacaoPauta.domain.associate.Associate;

@Repository
public interface AssociateRepository extends JpaRepository<Associate, Long> {

}
