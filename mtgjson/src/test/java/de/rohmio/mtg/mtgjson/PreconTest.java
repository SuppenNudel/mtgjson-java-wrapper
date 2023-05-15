package de.rohmio.mtg.mtgjson;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import de.rohmio.mtg.mtgjson.model.Deck;

public class PreconTest {

//	private static final File WORKSPACE = new File(System.getenv("HOMEPATH"), "Documents/mtg-tools");
//	private static final File ALL_DECK_FILES = new File(WORKSPACE, "AllDeckFiles.zip");
	private static final File ALL_DECK_FILES = new File("src/test/resources/AllDeckFiles.zip");
	private static final String ALL_DECK_FILES_URL = "https://mtgjson.com/api/v5/" + ALL_DECK_FILES.getName();

	@Test
	public void getAllPrecons() throws MalformedURLException, IOException {
		List<Deck> loadZipAllDecks = loadZipAllDecks();
		loadZipAllDecks.stream().filter(d -> d.getType().contains("Challenger Deck")).sorted(new Comparator<Deck>() {
			@Override
			public int compare(Deck o1, Deck o2) {
				return o1.getReleaseDate().compareTo(o2.getReleaseDate());
			}
		}).forEach(System.out::println);
	}

	public List<Deck> loadZipAllDecks() throws MalformedURLException, IOException {
		downloadAllDecks(false);
		List<Deck> allDecks = new ArrayList<>();
		try (ZipFile zipFile = new ZipFile(ALL_DECK_FILES)) {
			Enumeration<? extends ZipEntry> entries = zipFile.entries();
			while (entries.hasMoreElements()) {
				ZipEntry entry = entries.nextElement();
				Deck deck = MtgJsonReader.loadDeck(zipFile.getInputStream(entry));
				deck.setFileName(entry.getName());
				allDecks.add(deck);
			}
		}
		return allDecks;
	}

	public void downloadAllDecks(boolean force) throws MalformedURLException, IOException {
		if (force || !ALL_DECK_FILES.exists()) {
			// download file
			FileUtils.copyURLToFile(new URL(ALL_DECK_FILES_URL), ALL_DECK_FILES);
			System.out.println("Finished downloading " + ALL_DECK_FILES.getName());
		}
	}

}
