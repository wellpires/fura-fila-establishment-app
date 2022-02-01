package br.com.furafila.establishmentapp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.furafila.establishmentapp.model.converter.Bit2BooleanConverter;

@Table(name = "estabelecimento")
@Entity
public class Establishment implements Serializable {

	private static final long serialVersionUID = -2246942265361827662L;

	@Id
	@Column(name = "id_estabelecimento", columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long establishmentId;

	@Column(name = "razao_social")
	private String corporateName;

	@Column(name = "cnpj")
	private String cnpj;

	@Column(name = "inscricao_estadual")
	private String stateRegistration;

	@Column(name = "status")
	@Convert(converter = Bit2BooleanConverter.class)
	private Boolean status;

	@Column(name = "email")
	private String email;

	@Column(name = "id_imagem_FK", columnDefinition = "int4")
	private Long imageId;

	public Long getEstablishmentId() {
		return establishmentId;
	}

	public void setEstablishmentId(Long establishmentId) {
		this.establishmentId = establishmentId;
	}

	public String getCorporateName() {
		return corporateName;
	}

	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getStateRegistration() {
		return stateRegistration;
	}

	public void setStateRegistration(String stateRegistration) {
		this.stateRegistration = stateRegistration;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

}
