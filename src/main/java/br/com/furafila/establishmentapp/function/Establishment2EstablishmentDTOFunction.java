package br.com.furafila.establishmentapp.function;

import java.util.function.Function;

import br.com.furafila.establishmentapp.builder.EstablishmentDTOBuilder;
import br.com.furafila.establishmentapp.dto.EstablishmentDTO;
import br.com.furafila.establishmentapp.model.Establishment;

public class Establishment2EstablishmentDTOFunction implements Function<Establishment, EstablishmentDTO> {

	@Override
	public EstablishmentDTO apply(Establishment establishment) {
		return new EstablishmentDTOBuilder().id(establishment.getId())
				.corporateName(establishment.getCorporateName()).cnpj(establishment.getCnpj())
				.stateRegistration(establishment.getStateRegistration()).status(establishment.getStatus())
				.email(establishment.getEmail()).build();
	}

}
