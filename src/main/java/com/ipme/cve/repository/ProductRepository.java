package com.ipme.cve.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ipme.cve.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
