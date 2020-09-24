package com.tmao.crm.sale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmao.crm.commons.exception.NotFoundException;
import com.tmao.crm.sale.domain.Sale;
import com.tmao.crm.sale.repository.SaleRepository;

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

    public void delete(final String saleId) throws NotFoundException {
        repository.delete(findById(saleId));
    }

    public Sale findById(final String saleId) throws NotFoundException {
        return repository.findById(saleId).map(s -> s).orElseThrow(() -> new NotFoundException("Sale not found."));
    }

    public List<Sale> findAll() {
        return repository.findAll();
    }

}
