package br.com.furafila.establishmentapp.service.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import br.com.furafila.establishmentapp.dto.StockIdDTO;
import br.com.furafila.establishmentapp.response.StockIdResponse;
import br.com.furafila.establishmentapp.util.ReplaceCamelCase;

@ExtendWith(SpringExtension.class)
@DisplayNameGeneration(ReplaceCamelCase.class)
@TestPropertySource("classpath:application-dev.properties")
public class StockApiServiceImplTest {

	@InjectMocks
	private StockApiServiceImpl stockApiService;

	@Mock
	private RestTemplate client;

	@Value("${furafila.api.stock.find-stock-by-establishment-id}")
	private String findStockByEstablishmentIdUrl;

	@BeforeEach
	public void setup() {
		this.stockApiService = new StockApiServiceImpl(findStockByEstablishmentIdUrl, client);
	}

	@Test
	public void shouldFindStockId() {

		Long stockId = 3122l;

		StockIdResponse stockIdResponse = new StockIdResponse();
		StockIdDTO stockIdDTO = new StockIdDTO();
		stockIdDTO.setId(stockId);
		stockIdResponse.setStockIdDTO(stockIdDTO);
		ResponseEntity<Object> responseEntity = ResponseEntity.ok(stockIdResponse);
		when(client.getForEntity(anyString(), any())).thenReturn(responseEntity);

		StockIdDTO stockIdDTOFound = stockApiService.findStockId(123l);
		
		assertNotNull(stockIdDTOFound);
		assertThat(stockIdDTOFound.getId(), equalTo(stockId));
		

	}

}
