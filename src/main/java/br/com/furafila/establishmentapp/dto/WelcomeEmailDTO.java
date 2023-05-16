package br.com.furafila.establishmentapp.dto;

import java.io.Serializable;

public class WelcomeEmailDTO implements Serializable {

	private static final long serialVersionUID = -1889031742692523303L;

	private String email;
	private String corporateName;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCorporateName() {
		return corporateName;
	}

	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
	}

}
