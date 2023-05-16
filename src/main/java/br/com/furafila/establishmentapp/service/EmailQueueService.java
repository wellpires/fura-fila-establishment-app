package br.com.furafila.establishmentapp.service;

import br.com.furafila.establishmentapp.dto.WelcomeEmailDTO;

public interface EmailQueueService {

	void sendWelcomeEmail(WelcomeEmailDTO welcomeEmailDTO);

}
