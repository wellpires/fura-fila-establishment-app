package br.com.furafila.establishmentapp.builder;

import br.com.furafila.establishmentapp.model.Establishment;

public class EstablishmentBuilder {

	private String corporateName;
	private String email;
	private String cnpj;
	private String stateRegistration;
	private Long idImage;

	public EstablishmentBuilder corporateName(String corporateName) {
		this.corporateName = corporateName;
		return this;
	}

	public EstablishmentBuilder email(String email) {
		this.email = email;
		return this;
	}

	public EstablishmentBuilder cnpj(String cnpj) {
		this.cnpj = cnpj;
		return this;
	}

	public EstablishmentBuilder stateRegistration(String stateRegistration) {
		this.stateRegistration = stateRegistration;
		return this;
	}

	public EstablishmentBuilder idImage(Long idImage) {
		this.idImage = idImage;
		return this;
	}

	public Establishment build() {
		Establishment establishment = new Establishment();
		establishment.setCorporateName(corporateName);
		establishment.setEmail(email);
		establishment.setCnpj(cnpj);
		establishment.setStateRegistration(stateRegistration);
		establishment.setImageId(idImage);
		return establishment;
	}

}
