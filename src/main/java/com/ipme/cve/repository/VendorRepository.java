package com.ipme.cve.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ipme.cve.model.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Integer> {

}
