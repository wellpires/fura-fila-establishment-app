package br.com.furafila.establishmentapp.service.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.furafila.establishmentapp.dto.WelcomeEmailDTO;
import br.com.furafila.establishmentapp.message.WelcomeEmailMessage;
import br.com.furafila.establishmentapp.service.EmailQueueService;

@Service
public class EmailQueueServiceImpl implements EmailQueueService {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Value("${furafila.queue.welcome-email}")
	private String welcomeEmailQueueName;

	@Value("${furafila.queue.exchange.direct.fura-fila-direct-exchange}")
	private String exchange;

	@Override
	public void sendWelcomeEmail(WelcomeEmailDTO welcomeEmailDTO) {
		rabbitTemplate.convertAndSend(exchange, welcomeEmailQueueName, new WelcomeEmailMessage(welcomeEmailDTO));
	}

}
