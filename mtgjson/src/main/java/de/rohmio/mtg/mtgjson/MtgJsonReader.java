package de.rohmio.mtg.mtgjson;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import de.rohmio.mtg.mtgjson.model.CardAtomic;
import de.rohmio.mtg.mtgjson.model.Data;
import de.rohmio.mtg.mtgjson.model.Deck;
import de.rohmio.mtg.mtgjson.model.MtgJsonFile;
import de.rohmio.mtg.mtgjson.model.MtgJsonFileMeta;
import de.rohmio.mtg.mtgjson.model.enums.card.Availability;
import de.rohmio.mtg.mtgjson.model.price.PriceData;
import de.rohmio.mtg.mtgjson.model.price.PriceProvider;

public class MtgJsonReader {

	public static Deck loadDeck(String fileContent) throws IOException {
		Gson gson = new Gson();
		Data<Deck> fromJson = gson.fromJson(fileContent, new TypeToken<Data<Deck>>() {
		}.getType());
		return fromJson.getData();
	}

	public static List<Deck> loadDecks(File file) throws IOException {
		String readFileToString = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
		Gson gson = new Gson();
		List<Deck> fromJson = gson.fromJson(readFileToString, new TypeToken<List<Deck>>() {
		}.getType());
		return fromJson;
	}

	public static Deck loadDeck(InputStream ins) throws IOException {
		Gson gson = new Gson();
		try (InputStreamReader reader = new InputStreamReader(ins)) {
			Data<Deck> fromJson = gson.fromJson(reader, new TypeToken<Data<Deck>>() {
			}.getType());
			return fromJson.getData();
		}
	}

	public static Deck loadDeck(File file) throws IOException {
		String readFileToString = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
		return loadDeck(readFileToString);
	}

	public static Map<String, List<CardAtomic>> loadCards(File file) throws IOException {
		Gson gson = new Gson();
		String readFileToString = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
		Data<Map<String, List<CardAtomic>>> fromJson = gson.fromJson(readFileToString,
				new TypeToken<Data<Map<String, List<CardAtomic>>>>() {
				}.getType());
		return fromJson.getData();
	}

	private static Gson gson = new Gson();

	public static MtgJsonFile<String, Map<Availability, PriceData>> loadPrices(File file) throws IOException {
		MtgJsonFile<String, Map<Availability, PriceData>> priceDatas = new MtgJsonFile<>();
		try (JsonReader reader = new JsonReader(new InputStreamReader(new FileInputStream(file)))) {
//			read(reader, priceDatas, null);
			reader.beginObject();
			while (reader.hasNext()) {
				String dataType = reader.nextName();
				if ("meta".equals(dataType)) {
					MtgJsonFileMeta meta = gson.fromJson(reader, MtgJsonFileMeta.class);
					priceDatas.setMeta(meta);
				} else if ("data".equals(dataType)) {
					reader.beginObject();
					while (reader.hasNext()) {
						String cardUuid = reader.nextName();
						reader.beginObject(); // whole card opened
						while(reader.hasNext()) {
							String availabilityString = reader.nextName();
							Availability availability = Availability.valueOf(availabilityString);
							if (availability == Availability.paper) {
								reader.beginObject();
								while (reader.hasNext()) {
									String priceProvider = reader.nextName();
									if ("cardmarket".equals(priceProvider)) {
										PriceProvider cardmarket = gson.fromJson(reader, PriceProvider.class);
										HashMap<Availability, PriceData> availabilities = new HashMap<>();
										PriceData priceData = new PriceData();
										priceData.setCardmarket(cardmarket);
										availabilities.put(availability, priceData);
										priceDatas.getData().put(cardUuid, availabilities);
									} else {
										reader.skipValue();
									}
								}
								reader.endObject();
							} else {
								reader.skipValue();
							}
						}
						reader.endObject();
					}
					reader.endObject();
				} else {
					throw new RuntimeException("This data does not exist here");
				}
			}

		}
		return priceDatas;
	}

	@SuppressWarnings({ "unchecked", "unlikely-arg-type" })
	private static String read(JsonReader reader, Object obj, String key) throws IOException {
		while (reader.hasNext()) {
			JsonToken token = reader.peek();

			switch (token) {
			case BEGIN_OBJECT:
				if(key == null) {
					reader.beginObject();
				} else {
					switch (key) {
					case "cardkingdom":
					case "cardsphere":
					case "tcgplayer":
					case "mtgo":
						reader.skipValue();
						break;
					case "meta":
						MtgJsonFileMeta meta = gson.fromJson(reader, MtgJsonFileMeta.class);
						((MtgJsonFile<String, Map<Availability, PriceData>>) obj).setMeta(meta);
						break;
					case "data":
					case "paper":
						reader.beginObject();
						while (reader.hasNext()) {
							if("data".equals(key)) {
								read(reader, ((MtgJsonFile<String, Map<Availability, PriceData>>) obj).getData(), key);
							} else if("paper".equals(key)) {
								read(reader, ((MtgJsonFile<String, Map<Availability, PriceData>>) obj).getData(), key);
							}
						}
						break;	
					default: // when it is a card uuid
						read(reader, ((Map<Availability, PriceData>) obj).get(key), key);
						break;
					}
				}
				break;
			case END_OBJECT:
				reader.endObject();
				break;
			case BEGIN_ARRAY:
				reader.beginArray();
				break;
			case END_ARRAY:
				reader.endArray();
				break;
			case NAME:
				key = reader.nextName();
				break;
			case STRING:
//                if (obj instanceof Data) {
//                    Data data = (Data) obj;
//                    if ("name".equals(key))
//                        data.setName(reader.nextString());
//                    else if("role".equals(key))
//                        data.setRole(reader.nextString());
//                }
//                if (obj instanceof Address) {
//                    Address address = (Address) obj;
//                    if ("city".equals(key))
//                        address.setCity(reader.nextString());
//                }
				System.out.println("SRING");
				break;
			case NULL:
				reader.nextNull();
				break;
			case END_DOCUMENT:
				break;
			default:
				break;

			}
		}
		return key;
	}

}
