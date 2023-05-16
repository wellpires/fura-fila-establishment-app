package br.com.furafila.establishmentapp.builder;

import br.com.furafila.establishmentapp.dto.EstablishmentDTO;

public class EstablishmentDTOBuilder {

	private Long id;
	private String corporateName;
	private String cnpj;
	private String stateRegistration;
	private Boolean status;
	private String email;

	public EstablishmentDTOBuilder id(Long id) {
		this.id = id;
		return this;
	}

	public EstablishmentDTOBuilder corporateName(String corporateName) {
		this.corporateName = corporateName;
		return this;
	}

	public EstablishmentDTOBuilder cnpj(String cnpj) {
		this.cnpj = cnpj;
		return this;
	}

	public EstablishmentDTOBuilder stateRegistration(String stateRegistration) {
		this.stateRegistration = stateRegistration;
		return this;
	}

	public EstablishmentDTOBuilder status(Boolean status) {
		this.status = status;
		return this;
	}

	public EstablishmentDTOBuilder email(String email) {
		this.email = email;
		return this;
	}

	public EstablishmentDTO build() {
		EstablishmentDTO establishmentDTO = new EstablishmentDTO();
		establishmentDTO.setId(id);
		establishmentDTO.setCorporateName(corporateName);
		establishmentDTO.setCnpj(cnpj);
		establishmentDTO.setStateRegistration(stateRegistration);
		establishmentDTO.setStatus(status);
		establishmentDTO.setEmail(email);
		return establishmentDTO;
	}

}
