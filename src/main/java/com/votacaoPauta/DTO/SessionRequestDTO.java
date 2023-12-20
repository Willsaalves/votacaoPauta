package com.votacaoPauta.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionRequestDTO {
	
	@JsonProperty("duracaoEmMinutos")
	private Integer duracaoEmMinutos;

}
