package br.com.furafila.establishmentapp.request;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.establishmentapp.dto.NewEstablishmentDTO;
import br.com.furafila.establishmentapp.util.Messages;

public class NewEstablishmentRequest {

	@JsonProperty("establishment")
	@NotNull(message = Messages.ESTABLISHMENT_INFO_IS_REQUIRED)
	@Valid
	private NewEstablishmentDTO newEstablishmentDTO;

	public NewEstablishmentRequest() {
	}

	public NewEstablishmentRequest(NewEstablishmentDTO newEstablishmentDTO) {
		this.newEstablishmentDTO = newEstablishmentDTO;
	}

	public NewEstablishmentDTO getNewEstablishmentDTO() {
		return newEstablishmentDTO;
	}

	public void setNewEstablishmentDTO(NewEstablishmentDTO newEstablishmentDTO) {
		this.newEstablishmentDTO = newEstablishmentDTO;
	}

}
