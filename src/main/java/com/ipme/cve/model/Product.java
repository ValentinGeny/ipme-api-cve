package com.ipme.cve.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Product {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;
		
		@Column(unique = true)
		private String label;
		
		@OneToMany(mappedBy="product", fetch=FetchType.EAGER)
		private List<ProductCve> productCves;
		
		@OneToMany(mappedBy="product", fetch=FetchType.EAGER)
		private Set<SubProduct> subProducts;
		
		@ManyToOne
		private Vendor vendor;
		
		
		public Product() {
			
		}

		public Product(String label, Vendor vendor) {
			this.label = label;
			this.vendor = vendor;
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

		public Vendor getVendor() {
			return vendor;
		}

		public void setVendor(Vendor vendor) {
			this.vendor = vendor;
		}


		public Set<SubProduct> getSubProducts() {
			return subProducts;
		}

		public void setSubProducts(Set<SubProduct> subProducts) {
			this.subProducts = subProducts;
		}

		public List<ProductCve> getProductCves() {
			return productCves;
		}

		public void setProductCves(List<ProductCve> productCves) {
			this.productCves = productCves;
		}
		
		
		
		
		
		
}
