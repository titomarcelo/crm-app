package com.bangalo.product.domain;

public enum Category {

    NON_ALCOHOLIC_DRINK("Bebida não alcoólica"),

    ALCOHOLIC_DRINK("Bebida alcoólica"),

    SNACK("Petisco"),

    DISH("Prato"),

    DESSERT("Sobremesa");

    private String description;

    private Category(final String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getValue() {
        return name();
    }

}
