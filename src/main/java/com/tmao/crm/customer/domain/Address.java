package com.tmao.crm.customer.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "address")
public class Address {

    @Id
    private String id;

    private String city;
    private String state;
    private String street;
    private String number;
    private String country;
    private String zipcode;
    private String additional;
    private String neighborhood;

    // _TITO - Usar ou nao?

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(final String zipcode) {
        this.zipcode = zipcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(final String street) {
        this.street = street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(final String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(final String state) {
        this.state = state;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(final String number) {
        this.number = number;
    }

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(final String additional) {
        this.additional = additional;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

}
