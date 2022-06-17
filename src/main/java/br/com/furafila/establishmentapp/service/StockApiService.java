package br.com.furafila.establishmentapp.service;

import br.com.furafila.establishmentapp.dto.StockIdDTO;

public interface StockApiService {

	StockIdDTO findStockId(Long establishmentId);

}
