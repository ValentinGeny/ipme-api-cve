package com.ipme.cve.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class SubProduct {

	@OneToMany(mappedBy="sub_product", fetch=FetchType.EAGER)
	private List<Product> products;
}
