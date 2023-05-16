package br.com.furafila.establishmentapp.controller;

import static br.com.furafila.establishmentapp.matchers.ZeroValue.zeroValue;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.furafila.establishmentapp.builder.EstablishmentDTODummyBuilder;
import br.com.furafila.establishmentapp.dto.EstablishmentDTO;
import br.com.furafila.establishmentapp.dto.EstablishmentInfoDTO;
import br.com.furafila.establishmentapp.dto.EstablishmentStatusDTO;
import br.com.furafila.establishmentapp.exception.EstablishmentBasicInfoNotFoundException;
import br.com.furafila.establishmentapp.exception.EstablishmentInfoNotFoundException;
import br.com.furafila.establishmentapp.request.EditEstablishmentRequest;
import br.com.furafila.establishmentapp.request.EstablishmentStatusRequest;
import br.com.furafila.establishmentapp.request.NewEstablishmentRequest;
import br.com.furafila.establishmentapp.response.EstablishmentInfoResponse;
import br.com.furafila.establishmentapp.response.EstablishmentsResponse;
import br.com.furafila.establishmentapp.service.EstablishmentService;
import br.com.furafila.establishmentapp.util.ReplaceCamelCase;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EstablishmentController.class)
@DisplayNameGeneration(ReplaceCamelCase.class)
class EstablishmentControllerTest {

	private static final String ESTABLISHMENT_PATH = "/establishments";
	private static final String FIND_INITIAL_INFO = ESTABLISHMENT_PATH + "/login/{loginId}";
	private static final String FIND_ESTABLISHMENT = ESTABLISHMENT_PATH + "/{establishmentId}";
	private static final String EDIT_ESTABLISHMENT = ESTABLISHMENT_PATH + "/{establishmentId}";
	private static final String LIST_ESTABLISHMENTS = ESTABLISHMENT_PATH;
	private static final String CHANGE_ESTABLISHMENT_STATUS = ESTABLISHMENT_PATH + "/{establishmentId}/status";

	@MockBean
	private EstablishmentService establishmentService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	private NewEstablishmentRequest newEstablishmentRequest;
	private EditEstablishmentRequest editEstablishmentRequest;
	private EstablishmentStatusRequest establishmentStatusRequest;

	@BeforeEach
	public void setup() throws StreamReadException, IOException {

		newEstablishmentRequest = mapper.readValue(
				Paths.get("src", "test", "resources", "NewEstablishmentRequest.json").toFile(),
				NewEstablishmentRequest.class);

		editEstablishmentRequest = mapper.readValue(
				Paths.get("src", "test", "resources", "EditEstablishmentRequest.json").toFile(),
				EditEstablishmentRequest.class);

		establishmentStatusRequest = mapper.readValue(
				Paths.get("src", "test", "resources", "EstablishmentStatusRequest.json").toFile(),
				EstablishmentStatusRequest.class);
	}

	@Test
	void shouldSaveEstablishmentWithSuccess() throws Exception {

		mockMvc.perform(post(ESTABLISHMENT_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(newEstablishmentRequest))).andExpect(status().isNoContent())
				.andReturn();

		verify(establishmentService, times(1)).create(any());

	}

	@Test
	void shouldNotSaveEstablishmentBecauseEstablishmentInfoIsRequired() throws Exception {

		newEstablishmentRequest.setNewEstablishmentDTO(null);

		mockMvc.perform(post(ESTABLISHMENT_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(newEstablishmentRequest))).andExpect(status().isBadRequest());

		verify(establishmentService, never()).create(any());

	}

	@Test
	void shouldNotSaveEstablishmentBecauseCorporateNameIsRequired() throws Exception {

		newEstablishmentRequest.getNewEstablishmentDTO().setCorporateName(null);

		mockMvc.perform(post(ESTABLISHMENT_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(newEstablishmentRequest))).andExpect(status().isBadRequest());

		verify(establishmentService, never()).create(any());

	}

	@Test
	void shouldNotSaveEstablishmentBecauseCorporateNameIsNotValid() throws Exception {

		newEstablishmentRequest.getNewEstablishmentDTO().setCorporateName("asde");

		mockMvc.perform(post(ESTABLISHMENT_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(newEstablishmentRequest))).andExpect(status().isBadRequest());

		verify(establishmentService, never()).create(any());

	}

	@Test
	void shouldNotSaveEstablishmentBecauseEmailIsRequired() throws Exception {

		newEstablishmentRequest.getNewEstablishmentDTO().setEmail(null);

		mockMvc.perform(post(ESTABLISHMENT_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(newEstablishmentRequest))).andExpect(status().isBadRequest());

		verify(establishmentService, never()).create(any());

	}

	@Test
	void shouldNotSaveEstablishmentBecauseEmailIsNotValid() throws Exception {

		newEstablishmentRequest.getNewEstablishmentDTO().setEmail("asdasdas");

		mockMvc.perform(post(ESTABLISHMENT_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(newEstablishmentRequest))).andExpect(status().isBadRequest());

		verify(establishmentService, never()).create(any());

	}

	@Test
	void shouldNotSaveEstablishmentBecauseCNPJIsRequired() throws Exception {

		newEstablishmentRequest.getNewEstablishmentDTO().setCnpj(null);

		mockMvc.perform(post(ESTABLISHMENT_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(newEstablishmentRequest))).andExpect(status().isBadRequest());

		verify(establishmentService, never()).create(any());

	}

	@Test
	void shouldNotSaveEstablishmentBecauseCNPJIsNotValid() throws Exception {

		newEstablishmentRequest.getNewEstablishmentDTO().setCnpj("123456987");

		mockMvc.perform(post(ESTABLISHMENT_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(newEstablishmentRequest))).andExpect(status().isBadRequest());

		verify(establishmentService, never()).create(any());

	}

	@Test
	void shouldNotSaveEstablishmentBecauseStateRegistrationIsRequired() throws Exception {

		newEstablishmentRequest.getNewEstablishmentDTO().setStateRegistration(null);

		mockMvc.perform(post(ESTABLISHMENT_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(newEstablishmentRequest))).andExpect(status().isBadRequest());

		verify(establishmentService, never()).create(any());

	}

	@Test
	void shouldNotSaveEstablishmentBecauseStateRegistrationIsNotValid() throws Exception {

		newEstablishmentRequest.getNewEstablishmentDTO().setStateRegistration("123684681681681616168138516816818");

		mockMvc.perform(post(ESTABLISHMENT_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(newEstablishmentRequest))).andExpect(status().isBadRequest());

		verify(establishmentService, never()).create(any());

	}

	@Test
	void shouldNotSaveEstablishmentBecauseIdLoginIsRequired() throws Exception {

		newEstablishmentRequest.getNewEstablishmentDTO().setIdLogin(null);

		mockMvc.perform(post(ESTABLISHMENT_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(newEstablishmentRequest))).andExpect(status().isBadRequest());

		verify(establishmentService, never()).create(any());

	}

	@Test
	void shouldNotSaveEstablishmentBecauseIdLoginIsNotValid() throws Exception {

		newEstablishmentRequest.getNewEstablishmentDTO().setIdLogin(0l);

		mockMvc.perform(post(ESTABLISHMENT_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(newEstablishmentRequest))).andExpect(status().isBadRequest());

		verify(establishmentService, never()).create(any());

	}

	@Test
	void shouldNotSaveEstablishmentBecauseIdImageIsRequired() throws Exception {

		newEstablishmentRequest.getNewEstablishmentDTO().setIdImage(null);

		mockMvc.perform(post(ESTABLISHMENT_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(newEstablishmentRequest))).andExpect(status().isBadRequest());

		verify(establishmentService, never()).create(any());

	}

	@Test
	void shouldNotSaveEstablishmentBecauseIdImageIsNotValid() throws Exception {

		newEstablishmentRequest.getNewEstablishmentDTO().setIdImage(0l);

		mockMvc.perform(post(ESTABLISHMENT_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(newEstablishmentRequest))).andExpect(status().isBadRequest());

		verify(establishmentService, never()).create(any());

	}

	@Test
	void shouldFindEstablishmentInfoWithSuccess() throws JsonProcessingException, Exception {

		HashMap<String, Object> param = new HashMap<>();
		param.put("loginId", 12);

		String path = UriComponentsBuilder.fromPath(FIND_INITIAL_INFO).buildAndExpand(param).toUriString();

		mockMvc.perform(get(path).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void shouldNotFindEstablishmentInfoBecauseWasNotFound() throws JsonProcessingException, Exception {

		HashMap<String, Object> param = new HashMap<>();
		param.put("loginId", 12);

		String path = UriComponentsBuilder.fromPath(FIND_INITIAL_INFO).buildAndExpand(param).toUriString();

		when(establishmentService.findInitialInfo(anyLong())).thenThrow(new EstablishmentBasicInfoNotFoundException());

		mockMvc.perform(get(path).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}

	@Test
	void shouldFindEstablishment() throws JsonProcessingException, Exception {

		EstablishmentInfoDTO establishmentInfoDTO = new EstablishmentInfoDTO();
		establishmentInfoDTO.setId(123l);
		establishmentInfoDTO.setCnpj("123123123123");
		establishmentInfoDTO.setCorporateName("Corporate Name Teste");
		establishmentInfoDTO.setEmail("corporate_name@localhost.com");
		establishmentInfoDTO.setImageId(54l);
		establishmentInfoDTO.setStateRegistration("12312312312");
		establishmentInfoDTO.setStatus(Boolean.TRUE);

		when(this.establishmentService.findEstablishment(anyLong())).thenReturn(establishmentInfoDTO);

		HashMap<String, Object> param = new HashMap<>();
		param.put("establishmentId", 15);

		String path = UriComponentsBuilder.fromPath(FIND_ESTABLISHMENT).buildAndExpand(param).toUriString();
		MvcResult result = mockMvc.perform(get(path).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andReturn();

		EstablishmentInfoResponse establishmentInfoResponse = mapper
				.readValue(result.getResponse().getContentAsString(), EstablishmentInfoResponse.class);

		EstablishmentInfoDTO establishmentInfoDTOReturned = establishmentInfoResponse.getEstablishmentInfoDTO();
		assertNotNull(establishmentInfoDTOReturned);
		assertThat(establishmentInfoDTOReturned.getId(), equalTo(establishmentInfoDTO.getId()));
		assertThat(establishmentInfoDTOReturned.getCnpj(), equalTo(establishmentInfoDTO.getCnpj()));
		assertThat(establishmentInfoDTOReturned.getCorporateName(), equalTo(establishmentInfoDTO.getCorporateName()));
		assertThat(establishmentInfoDTOReturned.getEmail(), equalTo(establishmentInfoDTO.getEmail()));
		assertThat(establishmentInfoDTOReturned.getImageId(), equalTo(establishmentInfoDTO.getImageId()));
		assertThat(establishmentInfoDTOReturned.getStateRegistration(),
				equalTo(establishmentInfoDTO.getStateRegistration()));
		assertThat(establishmentInfoDTOReturned.getStatus(), equalTo(establishmentInfoDTO.getStatus()));

	}

	@Test
	void shouldNotFindEstablishmentBecauseNotFound() throws JsonProcessingException, Exception {

		when(this.establishmentService.findEstablishment(anyLong()))
				.thenThrow(new EstablishmentInfoNotFoundException());

		HashMap<String, Object> param = new HashMap<>();
		param.put("establishmentId", 15);

		String path = UriComponentsBuilder.fromPath(FIND_ESTABLISHMENT).buildAndExpand(param).toUriString();
		mockMvc.perform(get(path).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound()).andReturn();

	}

	@Test
	void shouldEditEstablishmentWithSuccess() throws JsonProcessingException, Exception {

		HashMap<String, Object> param = new HashMap<>();
		param.put("establishmentId", 123l);
		String path = UriComponentsBuilder.fromPath(EDIT_ESTABLISHMENT).buildAndExpand(param).toUriString();

		mockMvc.perform(put(path).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(editEstablishmentRequest))).andExpect(status().isNoContent())
				.andReturn();

		verify(establishmentService, times(1)).edit(any(), anyLong());

	}

	@Test
	void shouldNotEditEstablishmentBecauseEstablishmentInfoIsRequired() throws Exception {

		editEstablishmentRequest.setEditEstablishmentDTO(null);

		HashMap<String, Object> param = new HashMap<>();
		param.put("establishmentId", 123l);
		String path = UriComponentsBuilder.fromPath(EDIT_ESTABLISHMENT).buildAndExpand(param).toUriString();

		mockMvc.perform(put(path).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(editEstablishmentRequest))).andExpect(status().isBadRequest());

		verify(establishmentService, never()).edit(any(), anyLong());

	}

	@Test
	void shouldNotEditEstablishmentBecauseCorporateNameIsRequired() throws Exception {

		editEstablishmentRequest.getEditEstablishmentDTO().setCorporateName(null);

		HashMap<String, Object> param = new HashMap<>();
		param.put("establishmentId", 123l);
		String path = UriComponentsBuilder.fromPath(EDIT_ESTABLISHMENT).buildAndExpand(param).toUriString();

		mockMvc.perform(put(path).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(editEstablishmentRequest))).andExpect(status().isBadRequest());

		verify(establishmentService, never()).edit(any(), anyLong());

	}

	@Test
	void shouldNotEditEstablishmentBecauseCorporateNameIsNotValid() throws Exception {

		editEstablishmentRequest.getEditEstablishmentDTO().setCorporateName("iasd");

		HashMap<String, Object> param = new HashMap<>();
		param.put("establishmentId", 123l);
		String path = UriComponentsBuilder.fromPath(EDIT_ESTABLISHMENT).buildAndExpand(param).toUriString();

		mockMvc.perform(put(path).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(editEstablishmentRequest))).andExpect(status().isBadRequest());

		verify(establishmentService, never()).edit(any(), anyLong());

	}

	@Test
	void shouldNotEditEstablishmentBecauseEmailIsRequired() throws Exception {

		editEstablishmentRequest.getEditEstablishmentDTO().setEmail(null);

		HashMap<String, Object> param = new HashMap<>();
		param.put("establishmentId", 123l);
		String path = UriComponentsBuilder.fromPath(EDIT_ESTABLISHMENT).buildAndExpand(param).toUriString();

		mockMvc.perform(put(path).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(editEstablishmentRequest))).andExpect(status().isBadRequest());

		verify(establishmentService, never()).edit(any(), anyLong());

	}

	@Test
	void shouldNotEditEstablishmentBecauseEmailIsNotValid() throws Exception {

		editEstablishmentRequest.getEditEstablishmentDTO().setEmail("corporatename");

		HashMap<String, Object> param = new HashMap<>();
		param.put("establishmentId", 123l);
		String path = UriComponentsBuilder.fromPath(EDIT_ESTABLISHMENT).buildAndExpand(param).toUriString();

		mockMvc.perform(put(path).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(editEstablishmentRequest))).andExpect(status().isBadRequest());

		verify(establishmentService, never()).edit(any(), anyLong());

	}

	@Test
	void shouldListEstablishments() throws JsonProcessingException, Exception {

		int itemsAmount = 10;

		List<EstablishmentDTO> establishments = new EstablishmentDTODummyBuilder().itemsAmout(itemsAmount)
				.buildList();

		when(establishmentService.listEstablishments()).thenReturn(establishments);

		MvcResult result = mockMvc.perform(get(LIST_ESTABLISHMENTS).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		EstablishmentsResponse establishmentsResponse = mapper.readValue(result.getResponse().getContentAsString(),
				EstablishmentsResponse.class);

		List<EstablishmentDTO> establishmentsDTO = establishmentsResponse.getEstablishmentsDTO();
		assertThat(establishmentsDTO, hasSize(itemsAmount));

		for (EstablishmentDTO establishmentDTO : establishmentsDTO) {

			assertThat(establishmentDTO.getId(), allOf(not(nullValue()), not(zeroValue())));
			assertThat(establishmentDTO.getCnpj(), not(blankOrNullString()));
			assertThat(establishmentDTO.getCorporateName(), not(blankOrNullString()));
			assertThat(establishmentDTO.getEmail(), not(blankOrNullString()));
			assertThat(establishmentDTO.getStateRegistration(), not(blankOrNullString()));
			assertNotNull(establishmentDTO.getStatus());

		}

	}

	@Test
	void shouldChangeStatus() throws JsonProcessingException, Exception {

		HashMap<String, Object> param = new HashMap<>();
		param.put("establishmentId", 123l);
		String path = UriComponentsBuilder.fromPath(CHANGE_ESTABLISHMENT_STATUS).buildAndExpand(param).toUriString();

		mockMvc.perform(put(path).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(establishmentStatusRequest))).andExpect(status().isNoContent());

		verify(establishmentService, times(1)).editStatus(any(EstablishmentStatusDTO.class), anyLong());

	}

	@Test
	void shouldNotChangeStatusBecauseEstablishmentInfoIsNull() throws JsonProcessingException, Exception {

		establishmentStatusRequest.setEstablishmentStatusDTO(null);

		HashMap<String, Object> param = new HashMap<>();
		param.put("establishmentId", 123l);
		String path = UriComponentsBuilder.fromPath(CHANGE_ESTABLISHMENT_STATUS).buildAndExpand(param).toUriString();

		mockMvc.perform(put(path).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(establishmentStatusRequest))).andExpect(status().isBadRequest());

		verify(establishmentService, never()).editStatus(any(EstablishmentStatusDTO.class), anyLong());

	}

	@Test
	void shouldNotChangeStatusBecauseEstablishmentStatusIsNull() throws JsonProcessingException, Exception {

		establishmentStatusRequest.getEstablishmentStatusDTO().setStatus(null);
		;

		HashMap<String, Object> param = new HashMap<>();
		param.put("establishmentId", 123l);
		String path = UriComponentsBuilder.fromPath(CHANGE_ESTABLISHMENT_STATUS).buildAndExpand(param).toUriString();

		mockMvc.perform(put(path).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(establishmentStatusRequest))).andExpect(status().isBadRequest());

		verify(establishmentService, never()).editStatus(any(EstablishmentStatusDTO.class), anyLong());

	}

}
