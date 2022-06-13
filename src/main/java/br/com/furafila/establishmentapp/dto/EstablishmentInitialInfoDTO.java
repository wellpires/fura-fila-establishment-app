package br.com.furafila.establishmentapp.dto;

public class EstablishmentInitialInfoDTO {

	private Long idEstablishment;
	private String corporateName;
	private Boolean status;
	private Long idImage;

	public Long getIdEstablishment() {
		return idEstablishment;
	}

	public void setIdEstablishment(Long idEstablishment) {
		this.idEstablishment = idEstablishment;
	}

	public String getCorporateName() {
		return corporateName;
	}

	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Long getIdImage() {
		return idImage;
	}

	public void setIdImage(Long idImage) {
		this.idImage = idImage;
	}

}
