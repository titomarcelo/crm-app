package com.bangalo.product.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.bangalo.commons.domain.DomainException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product create(final Product product) throws DomainException {
        try {
            return repository.insert(product);

        } catch (DuplicateKeyException duplicateKeyException) {
            throw new DomainException(String.format("Duplicate product. Description %s.", product.getDescription()));
        }
    }

    public Product update(final Product product) {
        return repository.save(product);
    }

    public void delete(final String productId) throws DomainException {
        repository.delete(findById(productId));
    }

    public Product findById(final String productId) throws DomainException {
        return repository.findById(productId).map(p -> p).orElseThrow(() -> new DomainException("Product not found."));
    }

    public List<Product> findAllActive() {
        return repository.findByActive(true);
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

}
