package com.houliu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.houliu.domain.User;
import com.houliu.mapper.UserMapper;
import com.houliu.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public User findByName(String name) {
		return userMapper.findByName(name);
	}

}
