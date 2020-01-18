package com.houliu.service;

import com.houliu.domain.User;

public interface UserService {
	
	public User findByName(String name);
	
	public User findById(Integer id);

}
