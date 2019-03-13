package com.ipme.cve.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ipme.cve.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
