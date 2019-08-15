
package com.tmao.crm.address.domain;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends MongoRepository<Address, String> {

    Optional<Address> findByZipcode(String zipcode);

}
