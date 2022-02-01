package br.com.furafila.establishmentapp.controller.resource;

import org.springframework.http.ResponseEntity;

import br.com.furafila.establishmentapp.request.NewEstablishmentRequest;

public interface EstablishmentResource {

	ResponseEntity<Void> create(NewEstablishmentRequest newEstablishmentRequest);

}
