package br.com.furafila.establishmentapp.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;

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
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.furafila.establishmentapp.exception.EstablishmentBasicInfoNotFoundException;
import br.com.furafila.establishmentapp.request.NewEstablishmentRequest;
import br.com.furafila.establishmentapp.service.EstablishmentService;
import br.com.furafila.establishmentapp.util.ReplaceCamelCase;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EstablishmentController.class)
@DisplayNameGeneration(ReplaceCamelCase.class)
class EstablishmentControllerTest {

	private static final String ESTABLISHMENT_PATH = "/establishments";
	private static final String FIND_INITIAL_INFO = ESTABLISHMENT_PATH + "/{loginId}";

	@MockBean
	private EstablishmentService establishmentService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	private NewEstablishmentRequest newEstablishmentRequest;

	@BeforeEach
	public void setup() throws StreamReadException, IOException {

		newEstablishmentRequest = mapper.readValue(
				Paths.get("src", "test", "resources", "NewEstablishmentRequest.json").toFile(),
				NewEstablishmentRequest.class);
	}

	@Test
	void shouldSaveEstablishmentWithSuccess() throws Exception {

		mockMvc.perform(post(ESTABLISHMENT_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(newEstablishmentRequest))).andExpect(status().isNoContent())
				.andReturn();

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

	@Test
	void shouldNotSaveEstablishmentBecauseStateRegistrationIsNotValid() throws Exception {

		newEstablishmentRequest.getNewEstablishmentDTO().setStateRegistration("123684681681681616168138516816818");

		mockMvc.perform(post(ESTABLISHMENT_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(newEstablishmentRequest))).andExpect(status().isBadRequest());

		verify(establishmentService, never()).createEstablishment(any());

	}

	@Test
	void shouldNotSaveEstablishmentBecauseIdLoginIsRequired() throws Exception {

		newEstablishmentRequest.getNewEstablishmentDTO().setIdLogin(null);

		mockMvc.perform(post(ESTABLISHMENT_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(newEstablishmentRequest))).andExpect(status().isBadRequest());

		verify(establishmentService, never()).createEstablishment(any());

	}

	@Test
	void shouldNotSaveEstablishmentBecauseIdLoginIsNotValid() throws Exception {

		newEstablishmentRequest.getNewEstablishmentDTO().setIdLogin(0l);

		mockMvc.perform(post(ESTABLISHMENT_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(newEstablishmentRequest))).andExpect(status().isBadRequest());

		verify(establishmentService, never()).createEstablishment(any());

	}

	@Test
	void shouldNotSaveEstablishmentBecauseIdImageIsRequired() throws Exception {

		newEstablishmentRequest.getNewEstablishmentDTO().setIdImage(null);

		mockMvc.perform(post(ESTABLISHMENT_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(newEstablishmentRequest))).andExpect(status().isBadRequest());

		verify(establishmentService, never()).createEstablishment(any());

	}

	@Test
	void shouldNotSaveEstablishmentBecauseIdImageIsNotValid() throws Exception {

		newEstablishmentRequest.getNewEstablishmentDTO().setIdImage(0l);

		mockMvc.perform(post(ESTABLISHMENT_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(newEstablishmentRequest))).andExpect(status().isBadRequest());

		verify(establishmentService, never()).createEstablishment(any());

	}

	@Test
	void shouldFindEstablishmentInfoWithSuccess() throws JsonProcessingException, Exception {

		HashMap<String, Object> param = new HashMap<>();
		param.put("loginId", 12);

		String path = UriComponentsBuilder.fromPath(FIND_INITIAL_INFO).buildAndExpand(param).toUriString();

		mockMvc.perform(get(path).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(newEstablishmentRequest))).andExpect(status().isOk());
	}

	@Test
	void shouldNotFindEstablishmentInfoBecauseWasNotFound() throws JsonProcessingException, Exception {

		HashMap<String, Object> param = new HashMap<>();
		param.put("loginId", 12);

		String path = UriComponentsBuilder.fromPath(FIND_INITIAL_INFO).buildAndExpand(param).toUriString();

		when(establishmentService.findInitialInfo(anyLong())).thenThrow(new EstablishmentBasicInfoNotFoundException());

		mockMvc.perform(get(path).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(newEstablishmentRequest))).andExpect(status().isNotFound());
	}

}
