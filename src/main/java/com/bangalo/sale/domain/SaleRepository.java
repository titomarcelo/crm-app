
package com.bangalo.sale.domain;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bangalo.customer.domain.Customer;

@Repository
public interface SaleRepository extends MongoRepository<Sale, String> {

    List<Sale> findByCustomer(Customer customer);

}
