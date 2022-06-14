package br.com.furafila.establishmentapp.service;

import br.com.furafila.establishmentapp.dto.EditEstablishmentDTO;
import br.com.furafila.establishmentapp.dto.EstablishmentInfoDTO;
import br.com.furafila.establishmentapp.dto.EstablishmentInitialInfoDTO;
import br.com.furafila.establishmentapp.dto.NewEstablishmentDTO;

public interface EstablishmentService {

	void create(NewEstablishmentDTO newEstablishmentDTO);

	EstablishmentInitialInfoDTO findInitialInfo(Long loginId);

	EstablishmentInfoDTO findEstablishment(Long establishmentId);

	void edit(EditEstablishmentDTO editEstablishmentDTO, Long establishmentId);

}
