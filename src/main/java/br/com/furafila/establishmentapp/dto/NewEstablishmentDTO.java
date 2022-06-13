package br.com.furafila.establishmentapp.dto;

import javax.validation.GroupSequence;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;

import br.com.furafila.establishmentapp.util.Messages;
import br.com.furafila.establishmentapp.validation.order.FirstOrderValidation;
import br.com.furafila.establishmentapp.validation.order.SecondOrderValidation;

@GroupSequence({ NewEstablishmentDTO.class, FirstOrderValidation.class, SecondOrderValidation.class })
public class NewEstablishmentDTO {

	@NotBlank(message = Messages.CORPORATE_NAME_IS_REQUIRED, groups = FirstOrderValidation.class)
	@Size(min = 10, max = 70, message = Messages.CORPORATE_NAME_IS_NOT_VALID, groups = SecondOrderValidation.class)
	private String corporateName;

	@NotBlank(message = Messages.EMAIL_IS_REQUIRED, groups = FirstOrderValidation.class)
	@Email(message = Messages.EMAIL_IS_NOT_VALID, groups = SecondOrderValidation.class)
	private String email;

	@NotBlank(message = Messages.CNPJ_IS_REQUIRED, groups = FirstOrderValidation.class)
	@CNPJ(message = Messages.CNPJ_IS_NOT_VALID, groups = SecondOrderValidation.class)
	private String cnpj;

	@NotBlank(message = Messages.STATE_REGISTRATION_IS_REQUIRED, groups = FirstOrderValidation.class)
	@Size(min = 8, max = 14, message = Messages.STATE_REGISTRATION_IS_NOT_VALID, groups = SecondOrderValidation.class)
	private String stateRegistration;

	@NotNull(message = Messages.LOGIN_IDENTIFICATION_IS_REQUIRED, groups = FirstOrderValidation.class)
	@Min(value = 1, message = Messages.LOGIN_IDENTIFICATION_IS_NOT_VALID, groups = SecondOrderValidation.class)
	private Long idLogin;

	@NotNull(message = Messages.IMAGE_IDENTIFICATION_IS_REQUIRED, groups = FirstOrderValidation.class)
	@Min(value = 1, message = Messages.IMAGE_IDENTIFICATION_IS_NOT_VALID, groups = SecondOrderValidation.class)
	private Long idImage;

	public String getCorporateName() {
		return corporateName;
	}

	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Long getIdLogin() {
		return idLogin;
	}

	public void setIdLogin(Long idLogin) {
		this.idLogin = idLogin;
	}

	public Long getIdImage() {
		return idImage;
	}

	public void setIdImage(Long idImage) {
		this.idImage = idImage;
	}

}
