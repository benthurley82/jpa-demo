package org.demo.jpa.repository;

import org.demo.jpa.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> 
{
	
}
