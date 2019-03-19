package com.ipme.cve.model;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Cve {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String severity;
	
	@Column(unique = true)
	private String title;
	
	@Column
	private String version;
	
	@Lob
	private String description;
	
	@Column
	private String published;
	
	@Column
	private String modified;
	
	@OneToMany(mappedBy="cve", fetch=FetchType.EAGER)
	private List<ProductCve> productCves;

	
	public Cve() {
		
	}
	
	public Cve(String severity, String title, String version, String published, String modified) {
		this.severity = severity;
		this.title = title;
		this.version = version;
		this.published = published;
		this.modified = modified;
	}
	
	public Cve(String severity, String title, String version, String description, String published, String modified) {
		this.severity = severity;
		this.title = title;
		this.version = version;
		this.description = description;
		this.published = published;
		this.modified = modified;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPublished() {
		return published;
	}

	public void setPublished(String published) {
		this.published = published;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

	public List<ProductCve> getProductCves() {
		return productCves;
	}

	public void setProductCves(List<ProductCve> productCves) {
		this.productCves = productCves;
	}
	
	
	
	
}
