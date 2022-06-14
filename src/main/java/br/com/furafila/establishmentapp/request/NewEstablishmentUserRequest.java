package br.com.furafila.establishmentapp.request;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.establishmentapp.dto.NewEstablishmentUserDTO;
import br.com.furafila.establishmentapp.util.Messages;

public class NewEstablishmentUserRequest {

	@JsonProperty("establishmentUser")
	@Valid
	@NotNull(message = Messages.ESTABLISHMENT_USER_IS_REQUIRED)
	private NewEstablishmentUserDTO newEstablishmentUserDTO;

	public NewEstablishmentUserDTO getNewEstablishmentUserDTO() {
		return newEstablishmentUserDTO;
	}

	public void setNewEstablishmentUserDTO(NewEstablishmentUserDTO establishmentUserDTO) {
		this.newEstablishmentUserDTO = establishmentUserDTO;
	}

}
