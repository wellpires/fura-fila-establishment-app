package br.com.furafila.establishmentapp.builder;

import br.com.furafila.establishmentapp.dto.EstablishmentUserDTO;

public class EstablishmentUserDTOBuilder {

	private Long userId;
	private String username;

	public EstablishmentUserDTOBuilder userId(Long userId) {
		this.userId = userId;
		return this;
	}

	public EstablishmentUserDTOBuilder username(String username) {
		this.username = username;
		return this;
	}

	public EstablishmentUserDTO build() {
		EstablishmentUserDTO establishmentUserDTO = new EstablishmentUserDTO();
		establishmentUserDTO.setUserId(userId);
		establishmentUserDTO.setUsername(username);
		return establishmentUserDTO;
	}

}
