package de.rohmio.mtg.mtgjson.model;

import java.util.Map;

import de.rohmio.mtg.mtgjson.model.price.PriceProvider;

public class GameFormat {

	private Map<String, PriceProvider> priceProviders;

	public Map<String, PriceProvider> getPriceProviders() {
		return priceProviders;
	}

}
