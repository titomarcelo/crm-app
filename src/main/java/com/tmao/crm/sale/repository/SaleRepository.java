
package com.tmao.crm.sale.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tmao.crm.customer.domain.Customer;
import com.tmao.crm.sale.domain.Sale;

@Repository
public interface SaleRepository extends MongoRepository<Sale, String> {

    List<Sale> findByCustomer(Customer customer);

}
