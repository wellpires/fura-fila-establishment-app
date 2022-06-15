package br.com.furafila.establishmentapp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.furafila.establishmentapp.dto.EstablishmentUserDTO;
import br.com.furafila.establishmentapp.dto.NewEstablishmentUserDTO;
import br.com.furafila.establishmentapp.exception.EstablishmentLoginNotFoundException;
import br.com.furafila.establishmentapp.function.EstablishmentLogin2EstablishmentUserDTOFunction;
import br.com.furafila.establishmentapp.model.Establishment;
import br.com.furafila.establishmentapp.model.EstablishmentLogin;
import br.com.furafila.establishmentapp.model.Login;
import br.com.furafila.establishmentapp.repository.EstablishmentLoginRepository;
import br.com.furafila.establishmentapp.service.EstablishmentLoginService;

@Service
public class EstablishmentLoginServiceImpl implements EstablishmentLoginService {

	@Autowired
	private EstablishmentLoginRepository establishmentLoginRepository;

	@Override
	public void relateLogin(Long establishmentId, Long idLogin) {

		Login login = new Login();
		login.setId(idLogin);

		EstablishmentLogin establishmentLogin = new EstablishmentLogin();
		establishmentLogin.setLogin(login);

		Establishment establishment = new Establishment();
		establishment.setId(establishmentId);
		establishmentLogin.setEstablishment(establishment);

		establishmentLoginRepository.save(establishmentLogin);
	}

	@Override
	public void relateLogin(NewEstablishmentUserDTO establishmentUserDTO) {
		this.relateLogin(establishmentUserDTO.getEstablishmentId(), establishmentUserDTO.getLoginId());
	}

	@Override
	public List<EstablishmentUserDTO> listEstablishmentUsers(Long establishmentId, Long loginId) {

		List<EstablishmentLogin> listEstablishmentUsers = this.establishmentLoginRepository
				.listEstablishmentUsers(establishmentId, loginId);

		return listEstablishmentUsers.stream().map(new EstablishmentLogin2EstablishmentUserDTOFunction())
				.collect(Collectors.toList());
	}

	@Override
	public void deleteEstablishmentUser(Long loginId) {

		EstablishmentLogin establishmentLogin = this.establishmentLoginRepository.findByLoginId(loginId)
				.orElseThrow(EstablishmentLoginNotFoundException::new);

		this.establishmentLoginRepository.deleteById(establishmentLogin.getId());
	}

}
