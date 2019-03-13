package com.ipme.cve.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Product {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Integer id;
		
		@Column
		private String label;
		
		@ManyToOne
		private Cve cve;
		
		@ManyToOne
		private Vendor vendor;
		
		@ManyToOne
		private SubProduct subProduct;
		
		public Product() {
			
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

		public Cve getCve() {
			return cve;
		}

		public void setCve(Cve cve) {
			this.cve = cve;
		}

		public SubProduct getSubProduct() {
			return subProduct;
		}

		public void setSubProduct(SubProduct subProduct) {
			this.subProduct = subProduct;
		}
		
		
		
		
}
