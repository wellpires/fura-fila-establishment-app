package br.com.furafila.establishmentapp.service;

import br.com.furafila.establishmentapp.dto.EstablishmentInitialInfoDTO;
import br.com.furafila.establishmentapp.dto.NewEstablishmentDTO;

public interface EstablishmentService {

	void createEstablishment(NewEstablishmentDTO newEstablishmentDTO);

	EstablishmentInitialInfoDTO findInitialInfo(Long loginId);

}
