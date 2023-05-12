package de.rohmio.mtg.mtgjson;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import de.rohmio.mtg.mtgjson.model.CardAtomic;
import de.rohmio.mtg.mtgjson.model.Data;
import de.rohmio.mtg.mtgjson.model.Deck;

public class MtgJsonReader {

	public static Deck loadDeck(String fileContent) throws IOException {
		Gson gson = new Gson();
		Data<Deck> fromJson = gson.fromJson(fileContent, new TypeToken<Data<Deck>>() {}.getType());
		return fromJson.getData();
	}
	
	public static List<Deck> loadDecks(File file) throws IOException {
		String readFileToString = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
		Gson gson = new Gson();
		List<Deck> fromJson = gson.fromJson(readFileToString, new TypeToken<List<Deck>>() {}.getType());
		return fromJson;
	}
	
	public static Deck loadDeck(InputStream ins) throws IOException {
		Gson gson = new Gson();
		try(InputStreamReader reader = new InputStreamReader(ins)) {
			Data<Deck> fromJson = gson.fromJson(reader, new TypeToken<Data<Deck>>() {}.getType());
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
			Data<Map<String, List<CardAtomic>>> fromJson = gson.fromJson(readFileToString, new TypeToken<Data<Map<String, List<CardAtomic>>>>() {}.getType());
			return fromJson.getData();
	}

	/*
	public static void loadPrices(File file, Consumer<PriceData> callback) throws IOException {
		Gson gson = new Gson();
		FileInputStream fin = new FileInputStream(file);
		JsonReader reader = new JsonReader(new InputStreamReader(fin));
		try (reader) {
			reader.beginObject();
			while(reader.hasNext()) {
				String nextName = reader.nextName();
				if("data".equals(nextName)) {
					reader.beginObject();
					while(reader.hasNext()) {
						String key = reader.nextName();
						PriceData prices = gson.fromJson(reader, PriceData.class);
						prices.setId(key);
						callback.accept(prices);
					}
					reader.endObject();
				} else {
					reader.skipValue();
				}
			}
			reader.endObject();
		}
	}
	*/

}
