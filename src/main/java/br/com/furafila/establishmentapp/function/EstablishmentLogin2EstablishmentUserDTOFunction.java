package br.com.furafila.establishmentapp.function;

import java.util.function.Function;

import br.com.furafila.establishmentapp.builder.EstablishmentUserDTOBuilder;
import br.com.furafila.establishmentapp.dto.EstablishmentUserDTO;
import br.com.furafila.establishmentapp.model.EstablishmentLogin;

public class EstablishmentLogin2EstablishmentUserDTOFunction
		implements Function<EstablishmentLogin, EstablishmentUserDTO> {

	@Override
	public EstablishmentUserDTO apply(EstablishmentLogin establishmentLogin) {
		return new EstablishmentUserDTOBuilder().userId(establishmentLogin.getLogin().getId())
				.username(establishmentLogin.getLogin().getUsername()).build();
	}

}
