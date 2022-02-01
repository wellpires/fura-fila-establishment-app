package br.com.furafila.establishmentapp.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.furafila.establishmentapp.request.NewEstablishmentRequest;
import br.com.furafila.establishmentapp.service.EstablishmentService;
import br.com.furafila.establishmentapp.util.ReplaceCamelCase;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EstablishmentController.class)
@DisplayNameGeneration(ReplaceCamelCase.class)
class EstablishmentControllerTest {

	private static final String ESTABLISHMENT_PATH = "/establishments";

	@MockBean
	private EstablishmentService establishmentService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	private NewEstablishmentRequest newEstablishmentRequest;

	@BeforeEach
	public void setup() throws StreamReadException, DatabindException, IOException {

		newEstablishmentRequest = mapper.readValue(
				Paths.get("src", "test", "resources", "NewEstablishmentRequest.json").toFile(),
				NewEstablishmentRequest.class);

	}

	@Test
	void shouldSaveEstablishment() throws Exception {

		mockMvc.perform(post(ESTABLISHMENT_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(newEstablishmentRequest))).andExpect(status().isNoContent());

		verify(establishmentService, times(1)).createEstablishment(any());

	}

	@Test
	void shouldNotSaveEstablishmentBecauseEstablishmentInfoIsRequired() throws Exception {

		newEstablishmentRequest.setNewEstablishmentDTO(null);

		mockMvc.perform(post(ESTABLISHMENT_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(newEstablishmentRequest))).andExpect(status().isBadRequest());

		verify(establishmentService, never()).createEstablishment(any());

	}
	
	@Test
	void shouldNotSaveEstablishmentBecauseCorporateNameIsRequired() throws Exception {

		newEstablishmentRequest.getNewEstablishmentDTO().setCorporateName(null);

		mockMvc.perform(post(ESTABLISHMENT_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(newEstablishmentRequest))).andExpect(status().isBadRequest());

		verify(establishmentService, never()).createEstablishment(any());

	}
	
	@Test
	void shouldNotSaveEstablishmentBecauseCorporateNameIsNotValid() throws Exception {

		newEstablishmentRequest.getNewEstablishmentDTO().setCorporateName("asde");

		mockMvc.perform(post(ESTABLISHMENT_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(newEstablishmentRequest))).andExpect(status().isBadRequest());

		verify(establishmentService, never()).createEstablishment(any());

	}
	
	@Test
	void shouldNotSaveEstablishmentBecauseEmailIsRequired() throws Exception {

		newEstablishmentRequest.getNewEstablishmentDTO().setEmail(null);

		mockMvc.perform(post(ESTABLISHMENT_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(newEstablishmentRequest))).andExpect(status().isBadRequest());

		verify(establishmentService, never()).createEstablishment(any());

	}
	
	@Test
	void shouldNotSaveEstablishmentBecauseEmailIsNotValid() throws Exception {

		newEstablishmentRequest.getNewEstablishmentDTO().setEmail("asdasdas");

		mockMvc.perform(post(ESTABLISHMENT_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(newEstablishmentRequest))).andExpect(status().isBadRequest());

		verify(establishmentService, never()).createEstablishment(any());

	}
	
	@Test
	void shouldNotSaveEstablishmentBecauseCNPJIsRequired() throws Exception {

		newEstablishmentRequest.getNewEstablishmentDTO().setCnpj(null);

		mockMvc.perform(post(ESTABLISHMENT_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(newEstablishmentRequest))).andExpect(status().isBadRequest());

		verify(establishmentService, never()).createEstablishment(any());

	}
	
	@Test
	void shouldNotSaveEstablishmentBecauseCNPJIsNotValid() throws Exception {

		newEstablishmentRequest.getNewEstablishmentDTO().setCnpj("123456987");

		mockMvc.perform(post(ESTABLISHMENT_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(newEstablishmentRequest))).andExpect(status().isBadRequest());

		verify(establishmentService, never()).createEstablishment(any());

	}
	
	@Test
	void shouldNotSaveEstablishmentBecauseStateRegistrationIsRequired() throws Exception {

		newEstablishmentRequest.getNewEstablishmentDTO().setStateRegistration(null);

		mockMvc.perform(post(ESTABLISHMENT_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(newEstablishmentRequest))).andExpect(status().isBadRequest());

		verify(establishmentService, never()).createEstablishment(any());

	}

}
