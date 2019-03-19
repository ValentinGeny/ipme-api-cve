package com.ipme.cve.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="product_cves")
public class ProductCve {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private Product product;
	
	@ManyToOne
	private Cve cve;
	
	public ProductCve() {
		
	}
	
	public ProductCve(Product product, Cve cve) {
		this.product = product;
		this.cve = cve;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Cve getCve() {
		return cve;
	}

	public void setCve(Cve cve) {
		this.cve = cve;
	}
	
	
}
