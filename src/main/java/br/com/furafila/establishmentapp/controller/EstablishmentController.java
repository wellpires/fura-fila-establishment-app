package br.com.furafila.establishmentapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.furafila.establishmentapp.controller.resource.EstablishmentResource;
import br.com.furafila.establishmentapp.dto.EstablishmentInfoDTO;
import br.com.furafila.establishmentapp.dto.EstablishmentInitialInfoDTO;
import br.com.furafila.establishmentapp.request.EditEstablishmentRequest;
import br.com.furafila.establishmentapp.request.NewEstablishmentRequest;
import br.com.furafila.establishmentapp.response.EstablishmentInfoResponse;
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
		this.establishmentService.create(newEstablishmentRequest.getNewEstablishmentDTO());
		return ResponseEntity.noContent().build();
	}

	@Override
	@GetMapping(path = "/login/{loginId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EstablishmentInitialInfoResponse> findInitialInfo(@PathVariable("loginId") Long loginId) {

		EstablishmentInitialInfoDTO establishmentInitialInfoDTO = this.establishmentService.findInitialInfo(loginId);

		return ResponseEntity.ok(new EstablishmentInitialInfoResponse(establishmentInitialInfoDTO));
	}

	@Override
	@GetMapping(path = "/{establishmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EstablishmentInfoResponse> findEstablishment(
			@PathVariable("establishmentId") Long establishmentId) {

		EstablishmentInfoDTO establishmentDTO = this.establishmentService.findEstablishment(establishmentId);

		return ResponseEntity.ok(new EstablishmentInfoResponse(establishmentDTO));
	}

	// TODO CHANGE TO PATCH WHEN FRONT END IS NO LONGER JSF
	@Override
	@PutMapping(path = "/{establishmentId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> edit(@RequestBody @Valid EditEstablishmentRequest editEstablishmentRequest,
			@PathVariable("establishmentId") Long establishmentId) {

		this.establishmentService.edit(editEstablishmentRequest.getEditEstablishmentDTO(), establishmentId);

		return ResponseEntity.noContent().build();
	}

}