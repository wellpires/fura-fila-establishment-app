package br.com.furafila.establishmentapp.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.establishmentapp.dto.StockIdDTO;

public class StockIdResponse {

	@JsonProperty("stock")
	private StockIdDTO stockIdDTO;

	public StockIdDTO getStockIdDTO() {
		return stockIdDTO;
	}

	public void setStockIdDTO(StockIdDTO stockIdDTO) {
		this.stockIdDTO = stockIdDTO;
	}

}
