package com.ipme.cve.repository;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ipme.cve.model.Cve;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CveRepositoryTest {
	
	@Autowired
	private CveRepository cveRepository;
	
	@Before
	public void beforeTest() {
		System.out.println("Avant test");
	}
	
	@After
	public void afterTest() {
		System.out.println("Après test");
	}
	
	@Test
	public void findByTitle() {
		Cve cveFind = new Cve();
		String title = "CVE-2008-3473";
		System.out.println("Test avant findByTitle ---------");
		cveFind = cveRepository.findByTitle(title);
		assertNotNull(cveFind);
		System.out.println(cveFind.getTitle());
		System.out.println("Test après findByTitle ---------");
	}

}
