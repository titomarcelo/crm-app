package com.tmao.crm.customer.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.tmao.crm.commons.domain.DomainException;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public Customer create(final Customer customer) throws DomainException {
        try {
            return repository.insert(customer);

        } catch (DuplicateKeyException duplicateKeyException) {
            throw new DomainException(String.format("Duplicated customer. Cpf %s.", customer.getCpf()));
        }
    }

    public Customer update(final Customer customer) {
        return repository.save(customer);
    }

    public void delete(final String customerId) throws DomainException {
        repository.delete(findById(customerId));
    }

    public Customer findById(final String customerId) throws DomainException {
        return repository.findById(customerId).map(c -> c)
                .orElseThrow(() -> new DomainException("Customer not found."));
    }

    public Customer findByCpf(final String cpf) throws DomainException {
        return repository.findByCpf(cpf).map(c -> c).orElseThrow(() -> new DomainException("Customer not found."));
    }

    public List<Customer> findAll() {
        return repository.findAll();
    }

}
