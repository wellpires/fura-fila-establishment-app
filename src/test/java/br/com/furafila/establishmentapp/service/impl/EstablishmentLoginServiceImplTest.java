package br.com.furafila.establishmentapp.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.furafila.establishmentapp.model.EstablishmentLogin;
import br.com.furafila.establishmentapp.repository.EstablishmentLoginRepository;
import br.com.furafila.establishmentapp.service.EstablishmentLoginService;
import br.com.furafila.establishmentapp.util.ReplaceCamelCase;

@ExtendWith(SpringExtension.class)
@DisplayNameGeneration(ReplaceCamelCase.class)
public class EstablishmentLoginServiceImplTest {

	@InjectMocks
	private EstablishmentLoginService establishmentLoginService = new EstablishmentLoginServiceImpl();

	@Mock
	private EstablishmentLoginRepository establishmentLoginRepository;

	@Test
	public void shouldRelateLogin() {

		long establishmentId = 123l;
		long idLogin = 321l;
		establishmentLoginService.relateLogin(establishmentId, idLogin);

		verify(establishmentLoginRepository, times(1)).save(any(EstablishmentLogin.class));

	}

}
