package br.com.furafila.establishmentapp.service;

import java.util.List;

import br.com.furafila.establishmentapp.dto.EditEstablishmentDTO;
import br.com.furafila.establishmentapp.dto.EstablishmentDTO;
import br.com.furafila.establishmentapp.dto.EstablishmentInfoDTO;
import br.com.furafila.establishmentapp.dto.EstablishmentInitialInfoDTO;
import br.com.furafila.establishmentapp.dto.EstablishmentStatusDTO;
import br.com.furafila.establishmentapp.dto.NewEstablishmentDTO;

public interface EstablishmentService {

	void create(NewEstablishmentDTO newEstablishmentDTO);

	EstablishmentInitialInfoDTO findInitialInfo(Long loginId);

	EstablishmentInfoDTO findEstablishment(Long establishmentId);

	void edit(EditEstablishmentDTO editEstablishmentDTO, Long establishmentId);

	List<EstablishmentDTO> listEstablishments();

	void editStatus(EstablishmentStatusDTO establishmentStatusDTO, Long establishmentId);

}
