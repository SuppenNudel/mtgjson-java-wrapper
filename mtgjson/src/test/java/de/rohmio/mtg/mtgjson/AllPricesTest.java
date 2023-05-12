package de.rohmio.mtg.mtgjson;

public class AllPricesTest {

	/*
	@Test
	public void readPrices() throws IOException {
		File file = new File("src/test/resources/AllPrices.json");
		MtgJsonReader.loadPrices(file, priceData -> {
			Map<String, PriceProvider> paper = priceData.getPaper();
			if(paper == null) {
				return;
			}
			PriceProvider priceProvider = paper.get("cardmarket");
			if(priceProvider == null) {
				return;
			}
			Prices retail = priceProvider.getRetail();
			if(retail == null) {
				return;
			}
			Map<String, Double> normal = retail.getNormal();
			if(normal == null) {
				return;
			}
			System.out.println("----------");
			System.out.println(priceData.getId());
			System.out.println("-----");
			normal.forEach((t, u) -> System.out.println(t+" "+u));
			System.out.println();
		});
	}
	*/

}
