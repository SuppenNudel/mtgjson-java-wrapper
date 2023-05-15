package de.rohmio.mtg.mtgjson;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import org.junit.jupiter.api.Test;

import de.rohmio.mtg.mtgjson.model.MtgJsonFile;
import de.rohmio.mtg.mtgjson.model.enums.card.Availability;
import de.rohmio.mtg.mtgjson.model.price.PriceData;

public class AllPricesTest {

	@Test
	public void readPrices() throws IOException {
		File file = new File("src/test/resources/AllPrices.json");
		LocalDateTime start = LocalDateTime.now();
		MtgJsonFile<String, Map<Availability, PriceData>> prices = MtgJsonReader.loadPrices(file);
		System.out.println(ChronoUnit.MILLIS.between(start, LocalDateTime.now()));
		Map<Availability, PriceData> bonecrusherGiantPrices = prices.getData().get("d26fdb0c-80d4-5ea2-af6e-adbd290fc163");
		System.out.println();
	}
	
}
