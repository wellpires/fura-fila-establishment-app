package br.com.furafila.establishmentapp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.furafila.establishmentapp.builder.EstablishmentBuilder;
import br.com.furafila.establishmentapp.builder.EstablishmentInfoDTOBuilder;
import br.com.furafila.establishmentapp.builder.EstablishmentInitialInfoDTOBuilder;
import br.com.furafila.establishmentapp.dto.EditEstablishmentDTO;
import br.com.furafila.establishmentapp.dto.EstablishmentDTO;
import br.com.furafila.establishmentapp.dto.EstablishmentInfoDTO;
import br.com.furafila.establishmentapp.dto.EstablishmentInitialInfoDTO;
import br.com.furafila.establishmentapp.dto.EstablishmentStatusDTO;
import br.com.furafila.establishmentapp.dto.NewEstablishmentDTO;
import br.com.furafila.establishmentapp.dto.StockIdDTO;
import br.com.furafila.establishmentapp.dto.WelcomeEmailDTO;
import br.com.furafila.establishmentapp.exception.EstablishmentBasicInfoNotFoundException;
import br.com.furafila.establishmentapp.exception.EstablishmentInfoNotFoundException;
import br.com.furafila.establishmentapp.function.Establishment2EstablishmentDTOFunction;
import br.com.furafila.establishmentapp.model.Establishment;
import br.com.furafila.establishmentapp.repository.EstablishmentRepository;
import br.com.furafila.establishmentapp.service.EmailQueueService;
import br.com.furafila.establishmentapp.service.EstablishmentLoginService;
import br.com.furafila.establishmentapp.service.EstablishmentService;
import br.com.furafila.establishmentapp.service.StockApiService;

@Service
public class EstablishmentServiceImpl implements EstablishmentService {

	@Autowired
	private EstablishmentRepository establishmentRepository;

	@Autowired
	private EstablishmentLoginService establishmentLoginService;

	@Autowired
	private StockApiService stockService;

	@Autowired
	private EmailQueueService emailQueueService;

	@Override
	public void create(NewEstablishmentDTO newEstablishmentDTO) {

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

		StockIdDTO stockIdDTO = stockService.findStockId(establishment.getId());

		return new EstablishmentInitialInfoDTOBuilder().id(establishment.getId())
				.corporateName(establishment.getCorporateName()).status(establishment.getStatus())
				.imageId(establishment.getImageId()).stockId(stockIdDTO.getId()).build();
	}

	@Override
	public EstablishmentInfoDTO findEstablishment(Long establishmentId) {

		Establishment establishment = this.establishmentRepository.findById(establishmentId)
				.orElseThrow(EstablishmentInfoNotFoundException::new);

		return new EstablishmentInfoDTOBuilder().id(establishment.getId())
				.corporateName(establishment.getCorporateName()).email(establishment.getEmail())
				.cnpj(establishment.getCnpj()).stateRegistration(establishment.getStateRegistration())
				.imageId(establishment.getImageId()).status(establishment.getStatus()).build();
	}

	@Override
	public void edit(EditEstablishmentDTO editEstablishmentDTO, Long establishmentId) {

		Establishment establishment = this.establishmentRepository.findById(establishmentId)
				.orElseThrow(EstablishmentInfoNotFoundException::new);

		establishment.setCorporateName(editEstablishmentDTO.getCorporateName());
		establishment.setEmail(editEstablishmentDTO.getEmail());

		this.establishmentRepository.save(establishment);

	}

	@Override
	public List<EstablishmentDTO> listEstablishments() {
		return establishmentRepository.findAll().stream().map(new Establishment2EstablishmentDTOFunction())
				.collect(Collectors.toList());
	}

	@Override
	public void editStatus(EstablishmentStatusDTO establishmentStatusDTO, Long establishmentId) {

		Establishment establishment = establishmentRepository.findById(establishmentId)
				.orElseThrow(EstablishmentInfoNotFoundException::new);

		establishment.setStatus(establishmentStatusDTO.getStatus());

		if (establishment.getStatus()) {
			WelcomeEmailDTO welcomeEmailDTO = new WelcomeEmailDTO();
			welcomeEmailDTO.setEmail(establishment.getEmail());
			welcomeEmailDTO.setCorporateName(establishment.getCorporateName());
			emailQueueService.sendWelcomeEmail(welcomeEmailDTO);
		}

		establishmentRepository.save(establishment);

	}

}
