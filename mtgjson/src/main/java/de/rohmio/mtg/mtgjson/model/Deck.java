package de.rohmio.mtg.mtgjson.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Deck {

	/**
	 * The set code for the deck.
	 */
	@SerializedName(value = "code", alternate={"set_code"})
	private String code;
	/**
	 * The card that is the Commander in this deck. See the Card (Deck) Data Model.
	 * optional
	 */
	private List<DeckCard> commander;
	/**
	 * The cards in the main-board. See the Card (Deck) Data Model.
	 */
	@SerializedName(value = "mainBoard", alternate={"cards"})
	private List<DeckCard> mainBoard;
	/**
	 * The name of the deck.
	 */
	private String name;
	/**
	 * The cards in the side-board. See the Card (Deck) Data Model.
	 */
	private List<DeckCard> sideBoard;
	/**
	 * The release date in ISO 8601 (opens new window) format for the set. Returns null if the set was not formally released as a product.
	 */
	private String releaseDate;
	/**
	 * The type of deck.
	 */
	private String type;

	private String fileName;

	@Override
	public String toString() {
		return String.format("%s(%s) - %s", type, code, name);
	}

	public String getCode() {
		return code;
	}
	public List<DeckCard> getCommander() {
		return commander;
	}
	public List<DeckCard> getMainBoard() {
		return mainBoard;
	}
	public String getName() {
		return name;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public List<DeckCard> getSideBoard() {
		return sideBoard;
	}
	public String getType() {
		return type;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

}
