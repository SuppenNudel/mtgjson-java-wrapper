package de.rohmio.mtg.mtgjson.model.price;

public class PriceData {

	private PriceProvider cardkingdom;
	private PriceProvider cardmarket;
	private PriceProvider cardsphere;
	private PriceProvider tcgplayer;
	
	public PriceProvider getCardmarket() {
		return cardmarket;
	}

	public void setCardmarket(PriceProvider cardmarket) {
		this.cardmarket = cardmarket;
	}

}
