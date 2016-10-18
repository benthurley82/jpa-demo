package org.demo.jpa.rest;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.inject.Inject;

import org.demo.jpa.domain.Customer;
import org.demo.jpa.domain.Order;
import org.demo.jpa.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CustomerResource {

	private final Logger		log	= LoggerFactory
											.getLogger(CustomerResource.class);

	@Inject
	private CustomerRepository	customerRepository;

	/**
	 * POST /customer -> Create a new customer.
	 */
	@RequestMapping(value = "/customer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void create(@RequestBody Customer customer)
	{
		log.debug("REST request to save Customer : {}", customer);
		customerRepository.save(customer);
	}

	/**
	 * GET /customers -> get all the customers.
	 */
	@RequestMapping(value = "/customers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Customer> getAll()
	{
		log.debug("REST request to get all Customers");
		List<Customer> custs = customerRepository.findAll();
		for(Customer customer : custs)
		{
			for(Order order: customer.getOrders())
			{
				log.debug("Order: " + order.getId() + " - " 
						 + order.getDescription()); 
			}
		}
		return custs;
	}

	/**
	 * GET /customer/:id -> get the "id" customer.
	 */
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> get(@PathVariable Long id)
	{
		log.debug("REST request to get Customer : {}", id);
		return Optional.ofNullable(customerRepository.findOne(id))
				.map(customer -> new ResponseEntity<>(customer, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * GET /customers -> get customers by last name.
	 */
	@RequestMapping(value = "/customers/name/{lastName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Customer> getByName(@PathVariable String lastName)
	{
		log.debug("REST request to get Customers by last name");
		return customerRepository.findByLastName(lastName);
	}
	
	/**
	 * GET /customer/:id/orders -> get the "id" customer orders.
	 */
	@RequestMapping(value = "/customer/{id}/orders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<Order>> getCustomerOrders(@PathVariable Long id)
	{
		log.debug("REST request to get Customer orders : {}", id);
		return Optional.ofNullable(customerRepository.findOne(id))
				.map(customer -> new ResponseEntity<>(customer.getOrders(), HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	/**
	 * DELETE /customer/:id -> delete the "id" customer.
	 */
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable Long id)
	{
		log.debug("REST request to delete Customer : {}", id);
		customerRepository.delete(id);
	}
}
