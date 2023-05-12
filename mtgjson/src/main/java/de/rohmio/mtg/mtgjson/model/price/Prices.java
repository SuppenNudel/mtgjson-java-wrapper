package de.rohmio.mtg.mtgjson.model.price;

import java.util.Map;

public class Prices {

	// date, price
	private Map<String, Double> foil;
	private Map<String, Double> normal;

	public Map<String, Double> getFoil() {
		return foil;
	}
	public Map<String, Double> getNormal() {
		return normal;
	}

}
