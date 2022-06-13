package br.com.furafila.establishmentapp.controller.resource;

import org.springframework.http.ResponseEntity;

import br.com.furafila.establishmentapp.request.NewEstablishmentRequest;
import br.com.furafila.establishmentapp.response.EstablishmentInitialInfoResponse;

public interface EstablishmentResource {

	ResponseEntity<Void> create(NewEstablishmentRequest newEstablishmentRequest);

	ResponseEntity<EstablishmentInitialInfoResponse> findInitialInfo(Long loginId);
	
}
