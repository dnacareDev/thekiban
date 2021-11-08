package com.thekiban.Mapper;

import org.apache.ibatis.annotations.Mapper;

import com.thekiban.Entity.User;

@Mapper
public interface LoginMapper
{
	User LoginUserInfo(String user_username);

	// 비밀번호 변경
	int UpdatePassword(User user);
}