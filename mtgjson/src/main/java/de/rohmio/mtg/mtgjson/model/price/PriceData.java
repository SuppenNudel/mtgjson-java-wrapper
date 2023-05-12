package de.rohmio.mtg.mtgjson.model.price;

import java.util.Map;

public class PriceData {

	private String id;
	private Map<String, PriceProvider> mtgo;
	private Map<String, PriceProvider> paper;

	public Map<String, PriceProvider> getMtgo() {
		return mtgo;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public Map<String, PriceProvider> getPaper() {
		return paper;
	}

}
