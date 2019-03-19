package com.ipme.cve.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ipme.cve.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	@Query("select p from Product p where p.label = :label")
	Product findByLabel(@Param("label")String label);
}
