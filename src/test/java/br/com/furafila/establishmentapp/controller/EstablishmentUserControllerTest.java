package br.com.furafila.establishmentapp.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
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
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.furafila.establishmentapp.dto.EstablishmentUserDTO;
import br.com.furafila.establishmentapp.exception.EstablishmentLoginNotFoundException;
import br.com.furafila.establishmentapp.request.NewEstablishmentUserRequest;
import br.com.furafila.establishmentapp.response.EstablishmentUserResponse;
import br.com.furafila.establishmentapp.service.EstablishmentLoginService;
import br.com.furafila.establishmentapp.util.ReplaceCamelCase;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EstablishmentUserController.class)
@DisplayNameGeneration(ReplaceCamelCase.class)
class EstablishmentUserControllerTest {

	private static final String ESTABLISHMENT_PATH = "/establishments";
	private static final String ADD_ESTABLISHMENT_USER_PATH = ESTABLISHMENT_PATH.concat("/users");
	private static final String ESTABLISHMENT_USER_PATH = ESTABLISHMENT_PATH.concat("/users");
	private static final String DELETE_ESTABLISHMENT_USER_PATH = ESTABLISHMENT_PATH.concat("/users/{loginId}");

	@MockBean
	private EstablishmentLoginService establishmentLoginService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	private NewEstablishmentUserRequest newEstablishmentUserRequest;

	@BeforeEach
	public void setup() throws StreamReadException, DatabindException, IOException {
		this.newEstablishmentUserRequest = mapper.readValue(
				Paths.get("src", "test", "resources", "NewEstablishmentUserRequest.json").toFile(),
				NewEstablishmentUserRequest.class);
	}

	@Test
	void shouldAddEstablishmentUser() throws JsonProcessingException, Exception {

		mockMvc.perform(post(ADD_ESTABLISHMENT_USER_PATH).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(newEstablishmentUserRequest))).andExpect(status().isNoContent())
				.andDo(print());

	}

	@Test
	void shouldNotAddEstablishmentUserBecauseEstablishmentUserIsNull() throws JsonProcessingException, Exception {

		newEstablishmentUserRequest.setNewEstablishmentUserDTO(null);

		mockMvc.perform(post(ADD_ESTABLISHMENT_USER_PATH).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(newEstablishmentUserRequest))).andExpect(status().isBadRequest())
				.andDo(print());

	}

	@Test
	void shouldNotAddEstablishmentUserBecauseEstablishmentIdIsNull() throws JsonProcessingException, Exception {

		newEstablishmentUserRequest.getNewEstablishmentUserDTO().setEstablishmentId(null);

		mockMvc.perform(post(ADD_ESTABLISHMENT_USER_PATH).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(newEstablishmentUserRequest))).andExpect(status().isBadRequest())
				.andDo(print());

	}

	@Test
	void shouldNotAddEstablishmentUserBecauseEstablishmentIdIsNotValid() throws JsonProcessingException, Exception {

		newEstablishmentUserRequest.getNewEstablishmentUserDTO().setEstablishmentId(0l);

		mockMvc.perform(post(ADD_ESTABLISHMENT_USER_PATH).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(newEstablishmentUserRequest))).andExpect(status().isBadRequest())
				.andDo(print());

	}

	@Test
	void shouldNotAddEstablishmentUserBecauseLoginIdIsNotNull() throws JsonProcessingException, Exception {

		newEstablishmentUserRequest.getNewEstablishmentUserDTO().setLoginId(null);

		mockMvc.perform(post(ADD_ESTABLISHMENT_USER_PATH).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(newEstablishmentUserRequest))).andExpect(status().isBadRequest())
				.andDo(print());

	}

	@Test
	void shouldNotAddEstablishmentUserBecauseLoginIdIsNotValid() throws JsonProcessingException, Exception {

		newEstablishmentUserRequest.getNewEstablishmentUserDTO().setLoginId(0l);

		mockMvc.perform(post(ADD_ESTABLISHMENT_USER_PATH).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(newEstablishmentUserRequest))).andExpect(status().isBadRequest())
				.andDo(print());

	}

	@Test
	void shouldListEstablishmentUsers() throws JsonProcessingException, Exception {

		EstablishmentUserDTO establishmentUserDTO1 = new EstablishmentUserDTO();
		establishmentUserDTO1.setUserId(12l);
		establishmentUserDTO1.setUsername("username 1");

		EstablishmentUserDTO establishmentUserDTO2 = new EstablishmentUserDTO();
		establishmentUserDTO2.setUserId(24l);
		establishmentUserDTO2.setUsername("username 2");

		EstablishmentUserDTO establishmentUserDTO3 = new EstablishmentUserDTO();
		establishmentUserDTO3.setUserId(48l);
		establishmentUserDTO3.setUsername("username 3");

		List<EstablishmentUserDTO> establishments = Arrays.asList(establishmentUserDTO1, establishmentUserDTO2,
				establishmentUserDTO3);
		when(this.establishmentLoginService.listEstablishmentUsers(anyLong(), anyLong())).thenReturn(establishments);

		String path = UriComponentsBuilder.fromPath(ESTABLISHMENT_USER_PATH).queryParam("establishmentId", 12)
				.queryParam("loginId", 5).build().toUriString();

		MvcResult result = mockMvc.perform(get(path).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andDo(print()).andReturn();

		EstablishmentUserResponse establishmentUserResponse = mapper
				.readValue(result.getResponse().getContentAsString(), EstablishmentUserResponse.class);

		assertThat(establishmentUserResponse.getEstablishmentUserDTO(), hasSize(3));

	}

	@Test
	public void shouldDeleteEstablishmentUser() throws Exception {

		HashMap<String, Object> param = new HashMap<>();
		param.put("loginId", 123);
		String path = UriComponentsBuilder.fromPath(DELETE_ESTABLISHMENT_USER_PATH).buildAndExpand(param).toUriString();

		mockMvc.perform(delete(path)).andExpect(status().isNoContent()).andDo(print());

		verify(establishmentLoginService, times(1)).deleteEstablishmentUser(anyLong());

	}

	@Test
	public void shouldNotDeleteEstablishmentUserBecauseNotFound() throws Exception {

		doThrow(new EstablishmentLoginNotFoundException()).when(establishmentLoginService)
				.deleteEstablishmentUser(anyLong());

		HashMap<String, Object> param = new HashMap<>();
		param.put("loginId", 123);
		String path = UriComponentsBuilder.fromPath(DELETE_ESTABLISHMENT_USER_PATH).buildAndExpand(param).toUriString();

		mockMvc.perform(delete(path)).andExpect(status().isNotFound()).andDo(print());

	}

}
