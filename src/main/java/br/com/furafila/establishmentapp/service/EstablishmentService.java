package br.com.furafila.establishmentapp.service;

import br.com.furafila.establishmentapp.dto.NewEstablishmentDTO;

public interface EstablishmentService {

	Long createEstablishment(NewEstablishmentDTO newEstablishmentDTO);

}
