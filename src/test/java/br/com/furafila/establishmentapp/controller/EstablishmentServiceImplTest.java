package br.com.furafila.establishmentapp.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.furafila.establishmentapp.dto.NewEstablishmentDTO;
import br.com.furafila.establishmentapp.model.Establishment;
import br.com.furafila.establishmentapp.repository.EstablishmentRepository;
import br.com.furafila.establishmentapp.service.EstablishmentService;
import br.com.furafila.establishmentapp.util.ReplaceCamelCase;

@ExtendWith(SpringExtension.class)
@DisplayNameGeneration(ReplaceCamelCase.class)
public class EstablishmentServiceImplTest {

	@InjectMocks
	private EstablishmentService establishmentService = new EstablishmentServiceImpl();

	@Mock
	private EstablishmentRepository establishmentRepository;

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
		Long establishmentId = establishmentService.createEstablishment(newEstablishmentDTO);

		ArgumentCaptor<Establishment> captor = ArgumentCaptor.forClass(Establishment.class);

		verify(establishmentRepository).save(captor.capture());

		Establishment establishment = captor.getValue();
		assertThat(newEstablishmentDTO.getCnpj(), equalTo(establishment.getCnpj()));
		assertThat(newEstablishmentDTO.getCorporateName(), equalTo(establishment.getCorporateName()));
		assertThat(newEstablishmentDTO.getEmail(), equalTo(establishment.getEmail()));
		assertThat(newEstablishmentDTO.getStateRegistration(), equalTo(establishment.getStateRegistration()));
		assertThat(establishmentIdSaved, equalTo(establishmentId));

	}

}
