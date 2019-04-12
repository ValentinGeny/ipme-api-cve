package com.ipme.cve.service;

public interface SecurityService {
	
	 String findLoggedInUsername();
	 void autoLogin(String username, String password);
}
