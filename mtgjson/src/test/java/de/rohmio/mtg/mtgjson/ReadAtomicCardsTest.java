package de.rohmio.mtg.mtgjson;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import de.rohmio.mtg.mtgjson.model.CardAtomic;

public class ReadAtomicCardsTest {

	@Test
	public void read() throws IOException {
		File file = new File("src/test/resources/AtomicCards.json");
		Map<String, List<CardAtomic>> loadCards = MtgJsonReader.loadCards(file);
		for(String cardName : loadCards.keySet()) {
			List<CardAtomic> cards = loadCards.get(cardName);
			CardAtomic card = cards.get(0);
			String v4Id = card.getIdentifiers().getScryfallOracleId();
			if(v4Id != null) {
				System.out.println(cardName);
			}
		}
	}

}
