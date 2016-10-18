package org.demo.jpa.repository;

import java.util.List;

import org.demo.jpa.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long>
{
	List<Customer> findByLastName(String lastName);
}
