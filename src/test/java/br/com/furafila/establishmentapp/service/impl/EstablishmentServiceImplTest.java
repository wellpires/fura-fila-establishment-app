package br.com.furafila.establishmentapp.service.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.furafila.establishmentapp.dto.EditEstablishmentDTO;
import br.com.furafila.establishmentapp.dto.EstablishmentInfoDTO;
import br.com.furafila.establishmentapp.dto.EstablishmentInitialInfoDTO;
import br.com.furafila.establishmentapp.dto.NewEstablishmentDTO;
import br.com.furafila.establishmentapp.exception.EstablishmentBasicInfoNotFoundException;
import br.com.furafila.establishmentapp.exception.EstablishmentInfoNotFoundException;
import br.com.furafila.establishmentapp.model.Establishment;
import br.com.furafila.establishmentapp.repository.EstablishmentRepository;
import br.com.furafila.establishmentapp.service.EstablishmentLoginService;
import br.com.furafila.establishmentapp.service.EstablishmentService;
import br.com.furafila.establishmentapp.util.ReplaceCamelCase;

@ExtendWith(SpringExtension.class)
@DisplayNameGeneration(ReplaceCamelCase.class)
public class EstablishmentServiceImplTest {

	@InjectMocks
	private EstablishmentService establishmentService = new EstablishmentServiceImpl();

	@Mock
	private EstablishmentRepository establishmentRepository;

	@Mock
	private EstablishmentLoginService establishmentLoginService;

	@Test
	public void shouldSaveEstablishment() {

		Long establishmentIdSaved = 123l;
		Establishment establishmentSaved = new Establishment();
		establishmentSaved.setId(establishmentIdSaved);
		when(establishmentRepository.save(any())).thenReturn(establishmentSaved);

		NewEstablishmentDTO newEstablishmentDTO = new NewEstablishmentDTO();
		newEstablishmentDTO.setCorporateName("corporate test");
		newEstablishmentDTO.setCnpj("123654789984");
		newEstablishmentDTO.setEmail("teste@email.com");
		newEstablishmentDTO.setStateRegistration("6549874");
		newEstablishmentDTO.setIdLogin(321l);
		establishmentService.create(newEstablishmentDTO);

		ArgumentCaptor<Establishment> captor = ArgumentCaptor.forClass(Establishment.class);
		verify(establishmentRepository).save(captor.capture());

		Establishment establishment = captor.getValue();
		assertThat(newEstablishmentDTO.getCnpj(), equalTo(establishment.getCnpj()));
		assertThat(newEstablishmentDTO.getCorporateName(), equalTo(establishment.getCorporateName()));
		assertThat(newEstablishmentDTO.getEmail(), equalTo(establishment.getEmail()));
		assertThat(newEstablishmentDTO.getStateRegistration(), equalTo(establishment.getStateRegistration()));

		ArgumentCaptor<Long> establishmentIdCaptor = ArgumentCaptor.forClass(Long.class);
		ArgumentCaptor<Long> loginIdCaptor = ArgumentCaptor.forClass(Long.class);
		verify(establishmentLoginService, times(1)).relateLogin(establishmentIdCaptor.capture(),
				loginIdCaptor.capture());

		assertThat(establishmentIdCaptor.getValue(), equalTo(establishmentIdSaved));
		assertThat(loginIdCaptor.getValue(), equalTo(newEstablishmentDTO.getIdLogin()));

	}

	@Test
	public void shouldFindInitialInfo() {

		Establishment establishment = new Establishment();
		establishment.setId(12l);
		establishment.setCorporateName("corporate name");
		establishment.setStatus(Boolean.FALSE);
		establishment.setImageId(30l);
		when(establishmentRepository.findInitialInfo(anyLong())).thenReturn(Optional.ofNullable(establishment));

		EstablishmentInitialInfoDTO establishmentInitialInfoDTO = establishmentService.findInitialInfo(12l);

		assertNotNull(establishmentInitialInfoDTO);
		assertThat(establishmentInitialInfoDTO.getIdEstablishment(), equalTo(establishment.getId()));
		assertThat(establishmentInitialInfoDTO.getCorporateName(), equalTo(establishment.getCorporateName()));
		assertThat(establishmentInitialInfoDTO.getStatus(), equalTo(establishment.getStatus()));
		assertThat(establishmentInitialInfoDTO.getIdImage(), equalTo(establishment.getImageId()));

	}

	@Test
	public void shouldNotFindInitialInfoBecauseWasNotFound() {

		when(establishmentRepository.findInitialInfo(anyLong()))
				.thenThrow(new EstablishmentBasicInfoNotFoundException());

		assertThrows(EstablishmentBasicInfoNotFoundException.class, () -> {
			establishmentService.findInitialInfo(12l);
		});

	}

	@Test
	public void shouldFindEstablishment() {

		Establishment establishment = new Establishment();
		establishment.setId(12l);
		establishment.setCorporateName("Coporate Name Teste");
		establishment.setEmail("corporate_name@localhost.com");
		establishment.setCnpj("123123123123");
		establishment.setStateRegistration("123124141413");
		establishment.setImageId(454l);
		establishment.setStatus(Boolean.TRUE);
		when(establishmentRepository.findById(anyLong())).thenReturn(Optional.ofNullable(establishment));

		EstablishmentInfoDTO establishmentInfoDTO = establishmentService.findEstablishment(12l);

		assertNotNull(establishmentInfoDTO);
		assertThat(establishmentInfoDTO.getId(), equalTo(establishment.getId()));
		assertThat(establishmentInfoDTO.getCorporateName(), equalTo(establishment.getCorporateName()));
		assertThat(establishmentInfoDTO.getEmail(), equalTo(establishment.getEmail()));
		assertThat(establishmentInfoDTO.getCnpj(), equalTo(establishment.getCnpj()));
		assertThat(establishmentInfoDTO.getStateRegistration(), equalTo(establishment.getStateRegistration()));
		assertThat(establishmentInfoDTO.getImageId(), equalTo(establishment.getImageId()));
		assertThat(establishmentInfoDTO.getStatus(), equalTo(establishment.getStatus()));

	}

	@Test
	public void shouldNotFindEstablishmentBecauseNotFound() {

		Establishment establishment = null;
		when(establishmentRepository.findById(anyLong())).thenReturn(Optional.ofNullable(establishment));

		assertThrows(EstablishmentInfoNotFoundException.class, () -> {
			establishmentService.findEstablishment(12l);
		});

	}

	@Test
	public void shouldEditEstablishment() {

		Establishment establishment = new Establishment();
		when(establishmentRepository.findById(anyLong())).thenReturn(Optional.ofNullable(establishment));

		EditEstablishmentDTO editEstablishmentDTO = new EditEstablishmentDTO();
		editEstablishmentDTO.setEmail("corporate_name@localhost.com");
		editEstablishmentDTO.setCorporateName("Corporate Name");
		establishmentService.edit(editEstablishmentDTO, 12l);

		verify(this.establishmentRepository, times(1)).save(any());
	}

	@Test
	public void shouldNotEditEstablishmentBecauseNotFound() {

		Establishment establishment = null;
		when(establishmentRepository.findById(anyLong())).thenReturn(Optional.ofNullable(establishment));

		EditEstablishmentDTO editEstablishmentDTO = new EditEstablishmentDTO();
		editEstablishmentDTO.setEmail("corporate_name@localhost.com");
		editEstablishmentDTO.setCorporateName("Corporate Name");

		assertThrows(EstablishmentInfoNotFoundException.class, () -> {
			establishmentService.edit(editEstablishmentDTO, 12l);
		});

		verify(this.establishmentRepository, never()).save(any());
	}

}
