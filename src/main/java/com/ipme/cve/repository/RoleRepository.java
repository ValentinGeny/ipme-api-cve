package com.ipme.cve.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ipme.cve.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
		Role findByName(String name);
}
