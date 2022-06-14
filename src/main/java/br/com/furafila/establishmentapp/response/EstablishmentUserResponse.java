package br.com.furafila.establishmentapp.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.establishmentapp.dto.EstablishmentUserDTO;

public class EstablishmentUserResponse {

	@JsonProperty("establishmentUsers")
	private List<EstablishmentUserDTO> establishmentUserDTO;

	public EstablishmentUserResponse() {
	}

	public EstablishmentUserResponse(List<EstablishmentUserDTO> establishmentUserDTO) {
		this.establishmentUserDTO = establishmentUserDTO;
	}

	public List<EstablishmentUserDTO> getEstablishmentUserDTO() {
		return establishmentUserDTO;
	}

	public void setEstablishmentUserDTO(List<EstablishmentUserDTO> establishmentUserDTO) {
		this.establishmentUserDTO = establishmentUserDTO;
	}

}
