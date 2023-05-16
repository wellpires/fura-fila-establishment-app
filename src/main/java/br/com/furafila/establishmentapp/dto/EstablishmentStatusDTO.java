package br.com.furafila.establishmentapp.dto;

import javax.validation.constraints.NotNull;

import br.com.furafila.establishmentapp.util.Messages;

public class EstablishmentStatusDTO {

	@NotNull(message = Messages.EDIT_STATUS_ESTABLISHMENT_STATUS_IS_NULL)
	private Boolean status;

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
