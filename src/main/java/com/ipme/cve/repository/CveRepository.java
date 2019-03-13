package com.ipme.cve.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ipme.cve.model.Cve;

public interface CveRepository extends JpaRepository<Cve, Integer>{

}
