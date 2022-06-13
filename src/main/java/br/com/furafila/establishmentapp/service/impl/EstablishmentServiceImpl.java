package br.com.furafila.establishmentapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.furafila.establishmentapp.builder.EstablishmentBuilder;
import br.com.furafila.establishmentapp.builder.EstablishmentInitialInfoDTOBuilder;
import br.com.furafila.establishmentapp.dto.EstablishmentInitialInfoDTO;
import br.com.furafila.establishmentapp.dto.NewEstablishmentDTO;
import br.com.furafila.establishmentapp.exception.EstablishmentBasicInfoNotFoundException;
import br.com.furafila.establishmentapp.model.Establishment;
import br.com.furafila.establishmentapp.repository.EstablishmentRepository;
import br.com.furafila.establishmentapp.service.EstablishmentLoginService;
import br.com.furafila.establishmentapp.service.EstablishmentService;

@Service
public class EstablishmentServiceImpl implements EstablishmentService {

	@Autowired
	private EstablishmentRepository establishmentRepository;

	@Autowired
	private EstablishmentLoginService establishmentLoginService;

	@Override
	public void createEstablishment(NewEstablishmentDTO newEstablishmentDTO) {

		Establishment establishment = new EstablishmentBuilder().corporateName(newEstablishmentDTO.getCorporateName())
				.email(newEstablishmentDTO.getEmail()).cnpj(newEstablishmentDTO.getCnpj())
				.stateRegistration(newEstablishmentDTO.getStateRegistration()).idImage(newEstablishmentDTO.getIdImage())
				.build();

		Long establishmentId = establishmentRepository.save(establishment).getId();

		establishmentLoginService.relateLogin(establishmentId, newEstablishmentDTO.getIdLogin());

	}

	@Override
	public EstablishmentInitialInfoDTO findInitialInfo(Long loginId) {

		Establishment establishment = establishmentRepository.findInitialInfo(loginId)
				.orElseThrow(EstablishmentBasicInfoNotFoundException::new);

		return new EstablishmentInitialInfoDTOBuilder().id(establishment.getId())
				.corporateName(establishment.getCorporateName()).status(establishment.getStatus())
				.imageId(establishment.getImageId()).build();
	}

}
