package com.ipme.cve.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ipme.cve.model.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Integer> {
	
	@Query("select v from Vendor v where v.label = :label")
	Vendor findByLabel(@Param("label")String label);
}
