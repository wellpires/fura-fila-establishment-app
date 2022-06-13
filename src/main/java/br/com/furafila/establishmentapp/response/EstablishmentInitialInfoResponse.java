package br.com.furafila.establishmentapp.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.establishmentapp.dto.EstablishmentInitialInfoDTO;

public class EstablishmentInitialInfoResponse {

	@JsonProperty("establishmentInitialInfo")
	private EstablishmentInitialInfoDTO establishmentInitialInfoDTO;

	public EstablishmentInitialInfoResponse() {
	}

	public EstablishmentInitialInfoResponse(EstablishmentInitialInfoDTO establishmentInitialInfoDTO) {
		this.establishmentInitialInfoDTO = establishmentInitialInfoDTO;
	}

	public EstablishmentInitialInfoDTO getEstablishmentInitialInfoDTO() {
		return establishmentInitialInfoDTO;
	}

	public void setEstablishmentInitialInfoDTO(EstablishmentInitialInfoDTO establishmentInitialInfoDTO) {
		this.establishmentInitialInfoDTO = establishmentInitialInfoDTO;
	}

}
