package com.ipme.cve.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Vendor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true)
	private String label;
	
	@OneToMany(mappedBy="vendor", fetch=FetchType.LAZY)
	private Set<Product> products;
	
	@OneToMany(mappedBy="vendor", fetch=FetchType.LAZY)
	private Set<SubVendor> subVendors;
	
	public Vendor() {
		
	}

	public Vendor(String label) {
		this.label = label;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Set<SubVendor> getSubVendors() {
		return subVendors;
	}

	public void setSubVendors(Set<SubVendor> subVendors) {
		this.subVendors = subVendors;
	}
	
	
	
}
