package br.com.furafila.establishmentapp.builder;

import br.com.furafila.establishmentapp.dto.EstablishmentInitialInfoDTO;

public class EstablishmentInitialInfoDTOBuilder {

	private Long id;
	private String corporateName;
	private Boolean status;
	private Long imageId;

	public EstablishmentInitialInfoDTOBuilder id(Long id) {
		this.id = id;
		return this;
	}

	public EstablishmentInitialInfoDTOBuilder corporateName(String corporateName) {
		this.corporateName = corporateName;
		return this;
	}

	public EstablishmentInitialInfoDTOBuilder status(Boolean status) {
		this.status = status;
		return this;
	}

	public EstablishmentInitialInfoDTOBuilder imageId(Long imageId) {
		this.imageId = imageId;
		return this;
	}

	public EstablishmentInitialInfoDTO build() {
		EstablishmentInitialInfoDTO establishmentInitialInfoDTO = new EstablishmentInitialInfoDTO();
		establishmentInitialInfoDTO.setIdEstablishment(id);
		establishmentInitialInfoDTO.setCorporateName(corporateName);
		establishmentInitialInfoDTO.setStatus(status);
		establishmentInitialInfoDTO.setIdImage(imageId);
		return establishmentInitialInfoDTO;
	}

}
