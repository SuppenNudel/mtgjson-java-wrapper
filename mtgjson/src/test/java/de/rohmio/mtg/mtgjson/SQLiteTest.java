package de.rohmio.mtg.mtgjson;

import org.junit.jupiter.api.Test;

import de.rohmio.mtg.mtgjson.sqlite.SQLiteJDBC;

public class SQLiteTest {
	
	@Test
	public void test() {
		SQLiteJDBC.connect();
	}

}
