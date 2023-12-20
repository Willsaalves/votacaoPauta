package com.votacaoPauta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.votacaoPauta.domain.associate.Associate;
import com.votacaoPauta.services.AssociateService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/associados")
@Validated
public class AssociateController {

	@Autowired
	private AssociateService associateService;

	@PostMapping("/criarAssociado")
	public ResponseEntity<?> criarAssociado(@Valid @RequestBody Associate associate) {
		try {
			
			String name = associate.getNome();

			if (name != null && name.trim().isEmpty()) {
				return ResponseEntity.badRequest().body("O nome não pode estar em branco.");
			}

			Associate createdAssociate = associateService.saveAssociate(associate);

			// Verifica se a criação foi bem-sucedida
			if (createdAssociate != null) {
				return new ResponseEntity<>(createdAssociate, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
