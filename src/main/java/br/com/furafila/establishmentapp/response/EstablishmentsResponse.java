package br.com.furafila.establishmentapp.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.establishmentapp.dto.EstablishmentDTO;

public class EstablishmentsResponse {

	@JsonProperty("establishments")
	private List<EstablishmentDTO> establishmentsDTO;

	public EstablishmentsResponse() {
	}

	public EstablishmentsResponse(List<EstablishmentDTO> establishmentsDTO) {
		this.establishmentsDTO = establishmentsDTO;
	}

	public List<EstablishmentDTO> getEstablishmentsDTO() {
		return establishmentsDTO;
	}

	public void setEstablishmentsDTO(List<EstablishmentDTO> establishmentsDTO) {
		this.establishmentsDTO = establishmentsDTO;
	}

}
