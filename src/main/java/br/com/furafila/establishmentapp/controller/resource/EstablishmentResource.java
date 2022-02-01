package br.com.furafila.establishmentapp.controller.resource;

import org.springframework.http.ResponseEntity;

import br.com.furafila.establishmentapp.request.NewEstablishmentRequest;
import br.com.furafila.establishmentapp.response.NewEstablishmentResponse;

public interface EstablishmentResource {

	ResponseEntity<NewEstablishmentResponse> create(NewEstablishmentRequest newEstablishmentRequest);

}
