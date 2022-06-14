package br.com.furafila.establishmentapp.dto;

import javax.validation.GroupSequence;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.furafila.establishmentapp.util.Messages;
import br.com.furafila.establishmentapp.validation.order.FirstOrderValidation;
import br.com.furafila.establishmentapp.validation.order.SecondOrderValidation;

@GroupSequence({ EditEstablishmentDTO.class, FirstOrderValidation.class, SecondOrderValidation.class })
public class EditEstablishmentDTO {

	@NotBlank(message = Messages.CORPORATE_NAME_IS_REQUIRED, groups = FirstOrderValidation.class)
	@Size(min = 10, max = 70, message = Messages.CORPORATE_NAME_IS_NOT_VALID, groups = SecondOrderValidation.class)
	private String corporateName;

	@NotBlank(message = Messages.EMAIL_IS_REQUIRED, groups = FirstOrderValidation.class)
	@Email(message = Messages.EMAIL_IS_NOT_VALID, groups = SecondOrderValidation.class)
	private String email;

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

}
