package org.demo.jpa.rest;

import java.util.List;

import javax.inject.Inject;

import org.demo.jpa.domain.Order;
import org.demo.jpa.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OrderResource 
{
	private final Logger		log	= LoggerFactory
			.getLogger(OrderResource.class);

	@Inject
	private OrderRepository	orderRepository;

	/**
	 * POST /order -> Create a new order.
	 */
	@RequestMapping(value = "/order", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void create(@RequestBody Order order)
	{
		log.debug("REST request to save Order : {}", order);
		orderRepository.save(order);
	}
	
	/**
	 * GET /orders -> get all the orders.
	 */
	@RequestMapping(value = "/orders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Order> getAll()
	{
		log.debug("REST request to get all Orders");
		return orderRepository.findAll();
	}

}
