package de.rohmio.mtg.mtgjson.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class DeckCard {

	/**
	 * The ASCII (opens new window) (Basic/128) code formatted card name with no special unicode characters.
	 */
	private String asciiName;
	/**
	 * The count of how many of this card exists in a relevant deck.
	 */
	private int count;
	/**
	 * The indicator for which duel deck the card is in.
	 */
	private String duelDeck;
	/**
	 * The name on the face of the card.
	 */
	private String faceName;
	/**
	 * The name of the card. Cards with multiple faces, like "Split" and "Meld" cards are given a delimiter.
	 */
	private String name;
	/**
	 * The number of the card. Can be prefixed or suffixed with a * or other characters for promotional sets.
	 */
	private String number;
	/**
	 * The set printing code that the card is from.
	 */
	@SerializedName(value = "setCode", alternate={"set_code"})
	private String setCode;

	private List<String> colorIdentity;

	private List<String> supertypes;

	@Override
	public String toString() {
		return String.format("%sx %s", count, name);
	}

	public List<String> getSupertypes() {
		return supertypes;
	}

	public String getAsciiName() {
		return asciiName;
	}
	public int getCount() {
		return count;
	}
	public String getDuelDeck() {
		return duelDeck;
	}
	public String getFaceName() {
		return faceName;
	}
	public String getName() {
		return name;
	}
	public String getNumber() {
		return number;
	}
	public String getSetCode() {
		return setCode;
	}
	public List<String> getColorIdentity() {
		return colorIdentity;
	}

}
