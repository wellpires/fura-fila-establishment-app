package br.com.furafila.establishmentapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.furafila.establishmentapp.controller.resource.EstablishmentUserResource;
import br.com.furafila.establishmentapp.dto.EstablishmentUserDTO;
import br.com.furafila.establishmentapp.request.NewEstablishmentUserRequest;
import br.com.furafila.establishmentapp.response.EstablishmentUserResponse;
import br.com.furafila.establishmentapp.service.EstablishmentLoginService;

@RestController
@RequestMapping("establishments/users")
public final class EstablishmentUserController implements EstablishmentUserResource {

	@Autowired
	private EstablishmentLoginService establishmentLoginService;

	@Override
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> addEstablishmentUser(
			@RequestBody @Valid NewEstablishmentUserRequest newEstablishmentUserRequest) {

		this.establishmentLoginService.relateLogin(newEstablishmentUserRequest.getNewEstablishmentUserDTO());

		return ResponseEntity.noContent().build();
	}

	@Override
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EstablishmentUserResponse> listEstablishmentUsers(
			@RequestParam("establishmentId") Long establishmentId, @RequestParam("loginId") Long loginId) {

		List<EstablishmentUserDTO> establishmentUserDTO = this.establishmentLoginService
				.listEstablishmentUsers(establishmentId, loginId);

		return ResponseEntity.ok(new EstablishmentUserResponse(establishmentUserDTO));
	}

	@Override
	@DeleteMapping(path = "/{loginId}")
	public ResponseEntity<Void> deleteEstablishmentUser(@PathVariable("loginId") Long loginId) {
		this.establishmentLoginService.deleteEstablishmentUser(loginId);
		return ResponseEntity.noContent().build();
	}

}
