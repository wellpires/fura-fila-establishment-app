package br.com.furafila.establishmentapp.controller.resource;

import org.springframework.http.ResponseEntity;

import br.com.furafila.establishmentapp.request.EditEstablishmentRequest;
import br.com.furafila.establishmentapp.request.EstablishmentStatusRequest;
import br.com.furafila.establishmentapp.request.NewEstablishmentRequest;
import br.com.furafila.establishmentapp.response.EstablishmentInfoResponse;
import br.com.furafila.establishmentapp.response.EstablishmentInitialInfoResponse;
import br.com.furafila.establishmentapp.response.EstablishmentsResponse;

public interface EstablishmentResource {

	ResponseEntity<Void> create(NewEstablishmentRequest newEstablishmentRequest);

	ResponseEntity<EstablishmentInitialInfoResponse> findInitialInfo(Long loginId);

	ResponseEntity<EstablishmentInfoResponse> findEstablishment(Long establishmentId);

	ResponseEntity<Void> edit(EditEstablishmentRequest editEstablishmentRequest, Long establishmentId);

	ResponseEntity<EstablishmentsResponse> listEstablishments();
	
	ResponseEntity<Void> changeStatus(EstablishmentStatusRequest establishmentStatusRequest, Long establishmentId);

}
