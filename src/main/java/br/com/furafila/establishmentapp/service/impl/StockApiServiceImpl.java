package br.com.furafila.establishmentapp.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.furafila.establishmentapp.dto.StockIdDTO;
import br.com.furafila.establishmentapp.response.StockIdResponse;
import br.com.furafila.establishmentapp.service.StockApiService;

@Service
public class StockApiServiceImpl implements StockApiService {

	@Autowired
	private RestTemplate client;

	@Value("${furafila.api.stock.find-stock-by-establishment-id}")
	private String findStockByEstablishmentIdUrl;

	public StockApiServiceImpl() {
	}

	public StockApiServiceImpl(String findStockByEstablishmentIdUrl, RestTemplate client) {
		this.findStockByEstablishmentIdUrl = findStockByEstablishmentIdUrl;
		this.client = client;
	}

	@Override
	public StockIdDTO findStockId(Long establishmentId) {

		HashMap<String, Object> param = new HashMap<>();
		param.put("establishmentId", establishmentId);
		String path = UriComponentsBuilder.fromHttpUrl(findStockByEstablishmentIdUrl).buildAndExpand(param)
				.toUriString();

		StockIdResponse stockIdResponse = client.getForEntity(path, StockIdResponse.class).getBody();

		return stockIdResponse.getStockIdDTO();
	}

}
