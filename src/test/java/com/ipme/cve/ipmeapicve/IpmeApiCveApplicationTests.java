package com.ipme.cve.ipmeapicve;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ipme.cve.model.Cve;
import com.ipme.cve.repository.CveRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IpmeApiCveApplicationTests {

	@Autowired
	private CveRepository cveRepository;
	
	@Test
	public void contextLoads() {
		Cve cveTest = new Cve();
		cveTest = findByTitle("CVE-2008-2252");
		System.out.println(cveTest.getTitle());
	}
	
	@Test
	public Cve findByTitle(String title) {
		return cveRepository.findByTitle(title);
	}

}
