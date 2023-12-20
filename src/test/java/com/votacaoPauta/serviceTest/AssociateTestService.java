package com.votacaoPauta.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.votacaoPauta.domain.associate.Associate;
import com.votacaoPauta.repositories.AssociateRepository;
import com.votacaoPauta.services.AssociateService;

@SpringBootTest
public class AssociateTestService {

	@Mock
    private AssociateRepository associateRepository;

    @InjectMocks
    private AssociateService associateService;
	
	 @Test
	    public void testFindAssociateById() {
	        // Mock dependencies
	        AssociateRepository associateRepository = mock(AssociateRepository.class);

	        // Create AssociateService with mocked dependencies
	        AssociateService associateService = new AssociateService(associateRepository);

	        // Create a sample Associate
	        Associate associate = new Associate();
	        associate.setId(1L);

	        // Mock behavior of repository
	        when(associateRepository.findById(1L)).thenReturn(Optional.of(associate));

	        // Test the findAssociateById method
	        Optional<Associate> result = associateService.findAssociateById(1L);

	        // Assert the result
	        assertTrue(result.isPresent());
	        assertEquals(1L, result.get().getId());
	    }

	    @Test
	    public void testCreateAssociate() {
	        // Mock dependencies
	        AssociateRepository associateRepository = mock(AssociateRepository.class);

	        // Create AssociateService with mocked dependencies
	        AssociateService associateService = new AssociateService(associateRepository);

	        // Create a sample Associate
	        Associate associate = new Associate();
	        associate.setId(1L);
	        associate.setNome("John Doe");


	        // Mock behavior of repository
	        when(associateRepository.save(any(Associate.class))).thenReturn(associate);

	        // Test the createAssociate method
	        Associate result = associateService.saveAssociate(associate);

	        // Assert the result
	        assertNotNull(result);
	        assertEquals(1L, result.getId());
	        assertEquals("John Doe", result.getNome());
	
	    }
	    
	    @Test
	    void testFindAssociateByIdNotFound() {
	        // Simula o repositório retornando um Optional vazio quando findById é chamado
	        when(associateRepository.findById(1L)).thenReturn(Optional.empty());

	        // Tenta encontrar um associado com ID inexistente
	        Optional<Associate> result = associateService.findAssociateById(1L);

	        // Verifica se o resultado é vazio
	        assertFalse(result.isPresent());
	    }
}
