
package com.tmao.crm.customer.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tmao.crm.customer.domain.Address;

@Repository
public interface AddressRepository extends MongoRepository<Address, String> {

    Optional<Address> findByZipcode(String zipcode);

}
