package de.rohmio.mtg.mtgjson.model.price;

public class PriceProvider {

	private String currency;
	private Prices buylist;
	private Prices retail;

	public String getCurrency() {
		return currency;
	}
	public Prices getBuylist() {
		return buylist;
	}
	public Prices getRetail() {
		return retail;
	}

}
