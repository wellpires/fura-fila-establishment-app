package br.com.furafila.establishmentapp.exception;

public class EstablishmentBasicInfoNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 6870880908327785914L;

	public EstablishmentBasicInfoNotFoundException() {
		super("Establishment Basic Info not found!");
	}

}
