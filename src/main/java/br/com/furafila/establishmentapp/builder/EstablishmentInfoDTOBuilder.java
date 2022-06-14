package br.com.furafila.establishmentapp.builder;

import br.com.furafila.establishmentapp.dto.EstablishmentInfoDTO;

public class EstablishmentInfoDTOBuilder {

	private Long id;
	private String corporateName;
	private String email;
	private String cnpj;
	private String stateRegistration;
	private Long imageId;
	private Boolean status;

	public EstablishmentInfoDTOBuilder id(Long id) {
		this.id = id;
		return this;
	}

	public EstablishmentInfoDTOBuilder corporateName(String corporateName) {
		this.corporateName = corporateName;
		return this;
	}

	public EstablishmentInfoDTOBuilder email(String email) {
		this.email = email;
		return this;
	}

	public EstablishmentInfoDTOBuilder cnpj(String cnpj) {
		this.cnpj = cnpj;
		return this;
	}

	public EstablishmentInfoDTOBuilder stateRegistration(String stateRegistration) {
		this.stateRegistration = stateRegistration;
		return this;
	}

	public EstablishmentInfoDTOBuilder imageId(Long imageId) {
		this.imageId = imageId;
		return this;
	}

	public EstablishmentInfoDTOBuilder status(Boolean status) {
		this.status = status;
		return this;
	}

	public EstablishmentInfoDTO build() {
		EstablishmentInfoDTO establishmentInfoDTO = new EstablishmentInfoDTO();
		establishmentInfoDTO.setId(id);
		establishmentInfoDTO.setCorporateName(corporateName);
		establishmentInfoDTO.setEmail(email);
		establishmentInfoDTO.setCnpj(cnpj);
		establishmentInfoDTO.setStateRegistration(stateRegistration);
		establishmentInfoDTO.setImageId(imageId);
		establishmentInfoDTO.setStatus(status);
		return establishmentInfoDTO;
	}

}
