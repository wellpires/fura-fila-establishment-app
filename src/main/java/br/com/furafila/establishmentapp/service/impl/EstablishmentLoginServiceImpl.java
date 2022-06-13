package br.com.furafila.establishmentapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.furafila.establishmentapp.model.Establishment;
import br.com.furafila.establishmentapp.model.EstablishmentLogin;
import br.com.furafila.establishmentapp.repository.EstablishmentLoginRepository;
import br.com.furafila.establishmentapp.service.EstablishmentLoginService;

@Service
public class EstablishmentLoginServiceImpl implements EstablishmentLoginService {

	@Autowired
	private EstablishmentLoginRepository establishmentLoginRepository;

	@Override
	public void relateLogin(Long establishmentId, Long idLogin) {

		EstablishmentLogin establishmentLogin = new EstablishmentLogin();
		establishmentLogin.setIdLogin(idLogin);
		
		Establishment establishment = new Establishment();
		establishment.setId(establishmentId);
		establishmentLogin.setEstablishment(establishment);

		establishmentLoginRepository.save(establishmentLogin);
	}

}
