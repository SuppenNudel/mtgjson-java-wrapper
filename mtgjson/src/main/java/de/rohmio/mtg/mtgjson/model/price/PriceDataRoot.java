package de.rohmio.mtg.mtgjson.model.price;

import java.util.Map;

public abstract class PriceDataRoot implements Map<String, PriceData> {

	@Override
	public String toString() {
		return size()+" elements";
	}

}
