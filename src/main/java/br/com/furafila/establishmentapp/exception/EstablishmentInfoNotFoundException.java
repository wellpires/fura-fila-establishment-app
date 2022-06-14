package br.com.furafila.establishmentapp.exception;

public class EstablishmentInfoNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8836529198330867672L;

	public EstablishmentInfoNotFoundException() {
		super("Establishment not found!");
	}
}
