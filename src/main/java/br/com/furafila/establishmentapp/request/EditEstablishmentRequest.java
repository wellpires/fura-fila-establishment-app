package br.com.furafila.establishmentapp.request;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.establishmentapp.dto.EditEstablishmentDTO;
import br.com.furafila.establishmentapp.util.Messages;

public class EditEstablishmentRequest {

	@JsonProperty("establishment")
	@Valid
	@NotNull(message = Messages.ESTABLISHMENT_INFO_IS_REQUIRED)
	private EditEstablishmentDTO editEstablishmentDTO;

	public EditEstablishmentDTO getEditEstablishmentDTO() {
		return editEstablishmentDTO;
	}

	public void setEditEstablishmentDTO(EditEstablishmentDTO editEstablishmentDTO) {
		this.editEstablishmentDTO = editEstablishmentDTO;
	}

}
