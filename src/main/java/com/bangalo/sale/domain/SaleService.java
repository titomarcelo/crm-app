package com.bangalo.sale.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bangalo.commons.domain.DomainException;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	public Sale create(final Sale sale) {
		return repository.insert(sale);
	}

	public Sale update(final Sale sale) {
		return repository.save(sale);
	}

	public void delete(final String saleId) throws DomainException {
		repository.delete(findById(saleId));
	}

	public Sale findById(final String saleId) throws DomainException {
		return repository.findById(saleId).map(s -> s).orElseThrow(() -> new DomainException("Venda não encontrada."));
	}

	public List<Sale> findAll() {
		return repository.findAll();
	}

}
