package br.com.furafila.establishmentapp.builder;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import br.com.furafila.establishmentapp.model.Establishment;

public class EstablishmentDummyBuilder {

	private long itemsAmount;

	public EstablishmentDummyBuilder itemsAmount(long itemsAmount) {
		this.itemsAmount = itemsAmount;
		return this;
	}

	public List<Establishment> buildList() {

		List<Establishment> establishments = new ArrayList<>();

		for (int i = 0; i < itemsAmount; i++) {

			Establishment establishment = new Establishment();
			establishment.setCnpj(RandomStringUtils.randomNumeric(15));
			establishment.setCorporateName(RandomStringUtils.randomAlphabetic(40));
			establishment.setEmail(RandomStringUtils.randomAlphabetic(20));
			establishment.setId(RandomUtils.nextLong());
			establishment.setStateRegistration(RandomStringUtils.randomAlphabetic(10));
			establishment.setStatus(RandomUtils.nextBoolean());
			establishment.setImageId(RandomUtils.nextLong());

			establishments.add(establishment);
		}

		return establishments;
	}

}
