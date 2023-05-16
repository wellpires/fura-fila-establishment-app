package br.com.furafila.establishmentapp.message;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.establishmentapp.dto.WelcomeEmailDTO;

public class WelcomeEmailMessage implements Serializable {

	private static final long serialVersionUID = -5013615775561194173L;

	@JsonProperty("welcomeEmail")
	private WelcomeEmailDTO welcomeEmailDTO;

	public WelcomeEmailMessage() {
	}

	public WelcomeEmailMessage(WelcomeEmailDTO welcomeEmailDTO) {
		this.welcomeEmailDTO = welcomeEmailDTO;
	}

	public WelcomeEmailDTO getWelcomeEmailDTO() {
		return welcomeEmailDTO;
	}

	public void setWelcomeEmailDTO(WelcomeEmailDTO welcomeEmailDTO) {
		this.welcomeEmailDTO = welcomeEmailDTO;
	}

}
