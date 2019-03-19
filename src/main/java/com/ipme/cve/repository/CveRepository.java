package com.ipme.cve.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ipme.cve.model.Cve;

public interface CveRepository extends JpaRepository<Cve, Integer>{
	
	@Query("select c from Cve c where c.title = :title")
	Cve findByTitle(@Param("title")String title);
}
