package org.demo.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Address 
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String line1;
    
    private String line2;

    @OneToOne(mappedBy="address")
    @JsonIgnore
    private Customer customer;
    
    protected Address() {}

    public Address(String line1, String line2) {
        this.line1 = line1;
        this.line2 = line2;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
    public String toString() {
        return String.format(
                "Address[id=%d, line1='%s', line2='%s']",
                id, line1, line2);
    }
}
