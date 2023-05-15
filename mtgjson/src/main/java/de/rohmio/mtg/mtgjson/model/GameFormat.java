package de.rohmio.mtg.mtgjson.model;

import java.util.Map;

import de.rohmio.mtg.mtgjson.model.price.PriceData;

public class GameFormat {

	private Map<String, PriceData> priceProviders;

	public Map<String, PriceData> getPriceProviders() {
		return priceProviders;
	}

}
