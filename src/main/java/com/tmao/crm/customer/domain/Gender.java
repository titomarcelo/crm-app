package com.tmao.crm.customer.domain;

public enum Gender {

	NO_MATTER("No Matter"), FEMALE("Female"), MALE("Male");

	private String description;

	private Gender(final String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public String getValue() {
		return name();
	}

}
