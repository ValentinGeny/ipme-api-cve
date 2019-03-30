package com.ipme.cve;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IpmeApiCveApplication {

	public static void main(String[] args) {
		SpringApplication.run(IpmeApiCveApplication.class, args);
	}

}
