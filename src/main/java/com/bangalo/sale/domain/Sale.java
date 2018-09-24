package com.bangalo.sale.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bangalo.customer.domain.Customer;
import com.bangalo.product.domain.Product;

@Document(collection = "sale")
public class Sale {

	@Id
	private String id;

	@DBRef
	private Customer customer;

	@DBRef
	private List<Product> products = new ArrayList<>();

	private Card card;
	private LocalDate date;
	private BigDecimal amount;

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(final Card card) {
		this.card = card;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(final LocalDate date) {
		this.date = date;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(final Customer customer) {
		this.customer = customer;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(final List<Product> products) {
		this.products = products;
	}

	public void addProduct(final Product product) {
		this.products.add(product);
	}

	public void removeProduct(final Product product) {
		this.products.remove(product);
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(final BigDecimal amount) {
		this.amount = amount;
	}

}
