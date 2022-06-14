package br.com.furafila.establishmentapp.service;

import java.util.List;

import br.com.furafila.establishmentapp.dto.EstablishmentUserDTO;
import br.com.furafila.establishmentapp.dto.NewEstablishmentUserDTO;

public interface EstablishmentLoginService {

	void relateLogin(Long establishmentId, Long idLogin);

	void relateLogin(NewEstablishmentUserDTO establishmentUserDTO);

	List<EstablishmentUserDTO> listEstablishmentUsers(Long establishmentId, Long loginId);

}
