package br.com.furafila.establishmentapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.furafila.establishmentapp.controller.resource.EstablishmentResource;
import br.com.furafila.establishmentapp.dto.EstablishmentInitialInfoDTO;
import br.com.furafila.establishmentapp.request.NewEstablishmentRequest;
import br.com.furafila.establishmentapp.response.EstablishmentInitialInfoResponse;
import br.com.furafila.establishmentapp.service.EstablishmentService;

@RestController
@RequestMapping("establishments")
public class EstablishmentController implements EstablishmentResource {

	@Autowired
	private EstablishmentService establishmentService;

	@Override
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> create(@RequestBody @Valid NewEstablishmentRequest newEstablishmentRequest) {
		establishmentService.createEstablishment(newEstablishmentRequest.getNewEstablishmentDTO());
		return ResponseEntity.noContent().build();
	}

	@Override
	@GetMapping(path = "/{loginId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EstablishmentInitialInfoResponse> findInitialInfo(@PathVariable("loginId") Long loginId) {

		EstablishmentInitialInfoDTO establishmentInitialInfoDTO = establishmentService.findInitialInfo(loginId);

		return ResponseEntity.ok(new EstablishmentInitialInfoResponse(establishmentInitialInfoDTO));
	}

}