package de.rohmio.mtg.mtgjson.sqlite;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

import java.sql.Connection;
import java.sql.DriverManager;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.sqlite.JDBC;

import de.rohmio.mtg.mtgjson.model.CardSet;

public class SQLiteJDBC {

	public static void connect() {
		String url = JDBC.PREFIX+"src/test/resources/AllPrintings.sqlite";
		System.out.println("Connecting to "+url);
		
		try (Connection conn = DriverManager.getConnection(url)) {
			System.out.println("Opened database successfully");
			DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
			CardSet anyCard = create.selectFrom("cards").fetchAnyInto(CardSet.class);
			System.out.println(anyCard.getName());
			
			CardSet cardById = create
					.selectFrom(table("cards"))
					.where(field("uuid").eq("d26fdb0c-80d4-5ea2-af6e-adbd290fc163"))
					.fetchOneInto(CardSet.class);
			System.out.println(cardById.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
