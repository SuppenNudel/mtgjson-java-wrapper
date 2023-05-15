package de.rohmio.mtg.mtgjson.model;

import java.util.HashMap;
import java.util.Map;

public class MtgJsonFile<K, V> {
	
	private MtgJsonFileMeta meta;
	private Map<K, V> data = new HashMap<>();
	
	public Map<K, V> getData() {
		return data;
	}
	
	public MtgJsonFileMeta getMeta() {
		return meta;
	}
	
	public void setMeta(MtgJsonFileMeta meta) {
		this.meta = meta;
	}

}
