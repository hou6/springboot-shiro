package com.houliu.mapper;

import org.apache.ibatis.annotations.Param;

import com.houliu.domain.User;

public interface UserMapper {
	public User findByName(@Param("name") String name);
	
	public User findById(@Param("id") Integer id);
}
