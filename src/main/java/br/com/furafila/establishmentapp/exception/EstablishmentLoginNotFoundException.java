package br.com.furafila.establishmentapp.exception;

public class EstablishmentLoginNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 3991222346075789681L;

	public EstablishmentLoginNotFoundException() {
		super("Establishment User not found!");
	}

}
