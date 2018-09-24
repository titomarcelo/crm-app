package com.bangalo.sale.domain;

public enum Card {

	TABLE("Comanda mesa"), CARD("Cartela individual");

	private String description;

	private Card(final String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public String getValue() {
		return name();
	}

}
