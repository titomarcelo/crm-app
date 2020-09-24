
package com.tmao.crm.product.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tmao.crm.product.domain.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findByActive(boolean active);

}
