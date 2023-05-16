package br.com.furafila.establishmentapp.request;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.establishmentapp.dto.EstablishmentStatusDTO;
import br.com.furafila.establishmentapp.util.Messages;

public class EstablishmentStatusRequest {

	@JsonProperty("establishment")
	@Valid
	@NotNull(message = Messages.EDIT_STATUS_ESTABLISHMENT_INFO_IS_NULL)
	private EstablishmentStatusDTO establishmentStatusDTO;

	public EstablishmentStatusDTO getEstablishmentStatusDTO() {
		return establishmentStatusDTO;
	}

	public void setEstablishmentStatusDTO(EstablishmentStatusDTO establishmentStatusDTO) {
		this.establishmentStatusDTO = establishmentStatusDTO;
	}

}
