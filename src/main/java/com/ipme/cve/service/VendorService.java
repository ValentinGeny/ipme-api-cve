package com.ipme.cve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipme.cve.model.Vendor;
import com.ipme.cve.repository.VendorRepository;

@Service
public class VendorService {
	
	@Autowired
	private VendorRepository vendorRepository;
	
	public List<Vendor> findAll(){
		return vendorRepository.findAll();
	}

}
