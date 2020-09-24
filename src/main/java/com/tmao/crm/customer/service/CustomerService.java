package com.tmao.crm.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.tmao.crm.commons.exception.DuplicateException;
import com.tmao.crm.commons.exception.NotFoundException;
import com.tmao.crm.customer.domain.Customer;
import com.tmao.crm.customer.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public Customer create(final Customer customer) throws DuplicateException {
        try {
            return repository.insert(customer);

        } catch (final DuplicateKeyException duplicateKeyException) {
            throw new DuplicateException(String.format("Duplicated customer. Cpf %s.", customer.getCpf()));
        }
    }

    public Customer update(final Customer customer) {
        return repository.save(customer);
    }

    public void delete(final String customerId) throws NotFoundException {
        repository.delete(findById(customerId));
    }

    public Customer findById(final String customerId) throws NotFoundException {
        return repository.findById(customerId).map(c -> c)
                .orElseThrow(() -> new NotFoundException("Customer not found."));
    }

    public Customer findByCpf(final String cpf) throws NotFoundException {
        return repository.findByCpf(cpf).map(c -> c).orElseThrow(() -> new NotFoundException("Customer not found."));
    }

    public List<Customer> findAll() {
        return repository.findAll();
    }

}
