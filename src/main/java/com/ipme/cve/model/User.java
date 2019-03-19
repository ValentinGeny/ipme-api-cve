package com.ipme.cve.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	@Column(unique = true)
	private String email;
	
	@NotNull
	private String password;
	
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	private List<SubProduct> subProducts;
	
	@OneToMany
	private List<SubVendor> subVendors;
	
	public User() {
		
	}
	
	public User(Integer id, String email, String password) {
		this(email, password);
		this.id = id;
	}

	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<SubProduct> getSubProducts() {
		return subProducts;
	}

	public void setSubProducts(List<SubProduct> subProducts) {
		this.subProducts = subProducts;
	}

	public List<SubVendor> getSubVendors() {
		return subVendors;
	}

	public void setSubVendors(List<SubVendor> subVendors) {
		this.subVendors = subVendors;
	}	
	
	

}
