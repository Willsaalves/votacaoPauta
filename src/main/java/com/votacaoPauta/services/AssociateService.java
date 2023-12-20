package com.votacaoPauta.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.votacaoPauta.domain.associate.Associate;
import com.votacaoPauta.repositories.AssociateRepository;

@Service
public class AssociateService {

	@Autowired
	private AssociateRepository repository;
	
	@Autowired
    public AssociateService(AssociateRepository associateRepository) {
        this.repository = associateRepository;
    }
	
	 public List<Associate> listAssociate() {
	        return repository.findAll();
	    }

	    public Optional<Associate> findAssociateById(Long id) {
	        return repository.findById(id);
	    }

	    public Associate saveAssociate(Associate associate) {
	        return repository.save(associate);
	    }

	    public void deleteAssociate(Long id) {
	    	repository.deleteById(id);
	    }
	
}
