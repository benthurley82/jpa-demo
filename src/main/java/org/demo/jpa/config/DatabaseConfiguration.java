package org.demo.jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

@Configuration
public class DatabaseConfiguration 
{
	
	/**
	 * Register a jackson module for hibernate 5. This makes the 
	 * JSON serializer aware of the structure of hibernate proxies 
	 * so we don't serialize the hidden proxy attributes.
	 * @return
	 */
	@Bean
	public Hibernate5Module hibernate4Module()
	{
		return new Hibernate5Module();
	}

}
