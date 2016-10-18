package org.demo.jpa.rest;

import java.util.List;

import javax.inject.Inject;

import org.demo.jpa.domain.Address;
import org.demo.jpa.repository.AddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AddressResource 
{

	private final Logger		log	= LoggerFactory
			.getLogger(AddressResource.class);

	@Inject
	private AddressRepository	addressRepository;
	
	/**
	 * POST /address -> Create a new address.
	 */
	@RequestMapping(value = "/address", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void create(@RequestBody Address address)
	{
		log.debug("REST request to save Address : {}", address);
		addressRepository.save(address);
	}
	
	/**
	 * GET /addresses -> get all the addresses.
	 */
	@RequestMapping(value = "/addresses", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Address> getAll()
	{
		log.debug("REST request to get all Addresses");
		return addressRepository.findAll();
	}
	
}
