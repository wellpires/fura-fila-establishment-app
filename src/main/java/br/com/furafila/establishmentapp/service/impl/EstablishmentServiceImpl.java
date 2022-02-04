package br.com.furafila.establishmentapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.furafila.establishmentapp.builder.EstablishmentBuilder;
import br.com.furafila.establishmentapp.dto.NewEstablishmentDTO;
import br.com.furafila.establishmentapp.model.Establishment;
import br.com.furafila.establishmentapp.repository.EstablishmentRepository;
import br.com.furafila.establishmentapp.service.EstablishmentService;

@Service
public class EstablishmentServiceImpl implements EstablishmentService {

	@Autowired
	private EstablishmentRepository establishmentRepository;

	@Override
	public Long createEstablishment(NewEstablishmentDTO newEstablishmentDTO) {

		Establishment establishment = new EstablishmentBuilder().corporateName(newEstablishmentDTO.getCorporateName())
				.email(newEstablishmentDTO.getEmail()).cnpj(newEstablishmentDTO.getCnpj())
				.stateRegistration(newEstablishmentDTO.getStateRegistration()).build();
		return establishmentRepository.save(establishment).getId();
	}

}
