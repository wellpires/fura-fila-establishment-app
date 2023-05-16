package br.com.furafila.establishmentapp.builder;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import br.com.furafila.establishmentapp.dto.EstablishmentDTO;

public class EstablishmentDTODummyBuilder {

	private int itemsAmount;

	public EstablishmentDTODummyBuilder itemsAmout(int itemsAmount) {
		this.itemsAmount = itemsAmount;
		return this;
	}

	public List<EstablishmentDTO> buildList() {

		List<EstablishmentDTO> establishments = new ArrayList<>();

		for (int i = 0; i < itemsAmount; i++) {

			EstablishmentDTO establishmentDTO = new EstablishmentDTO();
			establishmentDTO.setCnpj(RandomStringUtils.randomNumeric(15));
			establishmentDTO.setCorporateName(RandomStringUtils.randomAlphabetic(40));
			establishmentDTO.setEmail(RandomStringUtils.randomAlphabetic(20));
			establishmentDTO.setId(RandomUtils.nextLong());
			establishmentDTO.setStateRegistration(RandomStringUtils.randomAlphabetic(10));
			establishmentDTO.setStatus(RandomUtils.nextBoolean());

			establishments.add(establishmentDTO);

		}

		return establishments;
	}

}
