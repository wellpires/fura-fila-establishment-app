package br.com.furafila.establishmentapp.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.establishmentapp.dto.EstablishmentInfoDTO;

public class EstablishmentInfoResponse {

	@JsonProperty("establishment")
	private EstablishmentInfoDTO establishmentInfoDTO;

	public EstablishmentInfoResponse() {
	}

	public EstablishmentInfoResponse(EstablishmentInfoDTO establishmentDTO) {
		establishmentInfoDTO = establishmentDTO;
	}

	public EstablishmentInfoDTO getEstablishmentInfoDTO() {
		return establishmentInfoDTO;
	}

	public void setEstablishmentInfoDTO(EstablishmentInfoDTO establishmentInfoDTO) {
		this.establishmentInfoDTO = establishmentInfoDTO;
	}

}
