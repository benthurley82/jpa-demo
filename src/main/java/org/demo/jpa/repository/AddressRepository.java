package org.demo.jpa.repository;

import org.demo.jpa.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> 
{

}
