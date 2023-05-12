package de.rohmio.mtg.mtgjson.model;

public class Data<T> {

	private Meta meta;
	private T data;

	@Override
	public String toString() {
		return getClass()+"@"+meta.getDate()+" "+data;
	}

	public Meta getMeta() {
		return meta;
	}
	public T getData() {
		return data;
	}

}
