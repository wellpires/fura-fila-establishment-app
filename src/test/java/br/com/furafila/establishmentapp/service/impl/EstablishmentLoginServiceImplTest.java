package br.com.furafila.establishmentapp.service.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.furafila.establishmentapp.dto.EstablishmentUserDTO;
import br.com.furafila.establishmentapp.dto.NewEstablishmentUserDTO;
import br.com.furafila.establishmentapp.exception.EstablishmentLoginNotFoundException;
import br.com.furafila.establishmentapp.model.EstablishmentLogin;
import br.com.furafila.establishmentapp.model.Login;
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

	@Test
	public void shouldRelateLoginWithObject() {

		long establishmentId = 123l;
		long loginId = 321l;
		NewEstablishmentUserDTO establishmentUserDTO = new NewEstablishmentUserDTO();
		establishmentUserDTO.setEstablishmentId(establishmentId);
		establishmentUserDTO.setLoginId(loginId);
		establishmentLoginService.relateLogin(establishmentUserDTO);

		verify(establishmentLoginRepository, times(1)).save(any(EstablishmentLogin.class));

	}

	@Test
	public void shouldListEstablishmentUsers() {

		Login login1 = new Login();
		login1.setId(12l);
		login1.setUsername("username 1");

		EstablishmentLogin establishmentLogin1 = new EstablishmentLogin();
		establishmentLogin1.setLogin(login1);

		Login login2 = new Login();
		login2.setId(24l);
		login2.setUsername("username 2");

		EstablishmentLogin establishmentLogin2 = new EstablishmentLogin();
		establishmentLogin2.setLogin(login2);

		Login login3 = new Login();
		login3.setId(36l);
		login3.setUsername("username 3");

		EstablishmentLogin establishmentLogin3 = new EstablishmentLogin();
		establishmentLogin3.setLogin(login3);

		List<EstablishmentLogin> establishmentLogins = Arrays.asList(establishmentLogin1, establishmentLogin2,
				establishmentLogin3);
		when(this.establishmentLoginRepository.listEstablishmentUsers(anyLong(), anyLong()))
				.thenReturn(establishmentLogins);

		List<EstablishmentUserDTO> establishmentUsers = establishmentLoginService.listEstablishmentUsers(10l, 9l);

		assertThat(establishmentUsers, hasSize(3));

	}

	@Test
	public void shouldDeleteEstablishmentUser() {

		EstablishmentLogin establishmentLogin = new EstablishmentLogin();
		establishmentLogin.setId(12l);

		when(this.establishmentLoginRepository.findByLoginId(anyLong()))
				.thenReturn(Optional.ofNullable(establishmentLogin));

		establishmentLoginService.deleteEstablishmentUser(123l);

		verify(establishmentLoginRepository, times(1)).deleteById(anyLong());

	}

	@Test
	public void shouldNotDeleteEstablishmentUserBecauseEstablishmentUserNotFound() {

		EstablishmentLogin establishmentLogin = new EstablishmentLogin();
		establishmentLogin.setId(12l);

		when(this.establishmentLoginRepository.findByLoginId(anyLong()))
				.thenThrow(EstablishmentLoginNotFoundException.class);

		assertThrows(EstablishmentLoginNotFoundException.class, () -> {
			establishmentLoginService.deleteEstablishmentUser(123l);
		});

		verify(establishmentLoginRepository, never()).deleteById(anyLong());

	}

}
