package br.com.furafila.establishmentapp.controller.resource;

import org.springframework.http.ResponseEntity;

import br.com.furafila.establishmentapp.request.NewEstablishmentUserRequest;
import br.com.furafila.establishmentapp.response.EstablishmentUserResponse;

public interface EstablishmentUserResource {

	ResponseEntity<Void> addEstablishmentUser(NewEstablishmentUserRequest establishmentUserRequest);

	ResponseEntity<EstablishmentUserResponse> listEstablishmentUsers(Long establishmentId, Long loginId);

	ResponseEntity<Void> deleteEstablishmentUser(Long loginId);

}
