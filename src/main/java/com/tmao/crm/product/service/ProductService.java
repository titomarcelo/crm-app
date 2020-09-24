package com.tmao.crm.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.tmao.crm.commons.exception.DuplicateException;
import com.tmao.crm.commons.exception.NotFoundException;
import com.tmao.crm.product.domain.Product;
import com.tmao.crm.product.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product create(final Product product) throws DuplicateException {
        try {
            return repository.insert(product);

        } catch (final DuplicateKeyException duplicateKeyException) {
            throw new DuplicateException(String.format("Duplicated product. Description %s.", product.getDescription()));
        }
    }

    public Product update(final Product product) {
        return repository.save(product);
    }

    public void delete(final String productId) throws NotFoundException {
        repository.delete(findById(productId));
    }

    public Product findById(final String productId) throws NotFoundException {
        return repository.findById(productId).map(p -> p).orElseThrow(() -> new NotFoundException("Product not found."));
    }

    public List<Product> findAllActive() {
        return repository.findByActive(true);
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

}
