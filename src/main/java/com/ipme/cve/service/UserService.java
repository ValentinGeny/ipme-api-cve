package com.ipme.cve.service;

import com.ipme.cve.model.User;

public interface UserService {
    void save(User user);

    User findByEmail(String email);
    
    User findByUsername(String username);
}