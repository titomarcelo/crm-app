
package com.tmao.crm.customer.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tmao.crm.customer.domain.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

    Optional<Customer> findByCpf(String cpf);

}
