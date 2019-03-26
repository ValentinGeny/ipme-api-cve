package com.ipme.cve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ipme.cve.model.Product;
import com.ipme.cve.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> findAll(){
		return productRepository.findAll(); 
	}
	
	public Page<Product> findAll(int page, int pageSize){
		return productRepository.findAll(PageRequest.of(page, pageSize));
	}

}
