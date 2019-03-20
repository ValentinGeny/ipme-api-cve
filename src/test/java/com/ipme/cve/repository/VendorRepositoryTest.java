package com.ipme.cve.repository;


import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ipme.cve.model.Vendor;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VendorRepositoryTest {
	
	@Autowired
	private VendorRepository vendorRepository;
	
	@Before
	public void beforeTest() {
		Vendor vendor = new Vendor();
		vendor.setLabel("microsoft");
		vendorRepository.save(vendor);
	}
	
	public void afterTest() {
		Vendor vendor = vendorRepository.findByLabel("microsoft");
		vendorRepository.delete(vendor);
	}
	
	@Test
	public void findByTitle() {
		Vendor vendor = new Vendor();
		String label = "microsoft";
		System.out.println("Test avant label");
		vendor = vendorRepository.findByLabel(label);
		assertNotNull(vendor);
		System.out.println("Test après label");
	}
}
