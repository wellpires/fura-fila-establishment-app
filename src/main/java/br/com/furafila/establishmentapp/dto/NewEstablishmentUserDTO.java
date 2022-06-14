package br.com.furafila.establishmentapp.dto;

import javax.validation.GroupSequence;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.com.furafila.establishmentapp.util.Messages;
import br.com.furafila.establishmentapp.validation.order.FirstOrderValidation;
import br.com.furafila.establishmentapp.validation.order.SecondOrderValidation;

@GroupSequence({ NewEstablishmentUserDTO.class, FirstOrderValidation.class, SecondOrderValidation.class })
public class NewEstablishmentUserDTO {

	@NotNull(message = Messages.ESTABLISHMENT_ID_IS_REQUIRED, groups = FirstOrderValidation.class)
	@Min(value = 1, message = Messages.ESTABLISHMENT_ID_NOT_VALID, groups = SecondOrderValidation.class)
	private Long establishmentId;

	@NotNull(message = Messages.LOGIN_ID_IS_REQUIRED, groups = FirstOrderValidation.class)
	@Min(value = 1, message = Messages.LOGIN_ID_NOT_VALID, groups = SecondOrderValidation.class)
	private Long loginId;

	public Long getEstablishmentId() {
		return establishmentId;
	}

	public void setEstablishmentId(Long establishmentId) {
		this.establishmentId = establishmentId;
	}

	public Long getLoginId() {
		return loginId;
	}

	public void setLoginId(Long loginId) {
		this.loginId = loginId;
	}

}
