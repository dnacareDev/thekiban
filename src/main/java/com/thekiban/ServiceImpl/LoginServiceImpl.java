package com.thekiban.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.thekiban.Entity.User;
import com.thekiban.Mapper.LoginMapper;
import com.thekiban.Service.LoginService;

@Service
public class LoginServiceImpl implements LoginService
{
	@Autowired
	private LoginMapper mapper;
	
	// 로그인
	@Override
	public User loadUserByUsername(String user_username) throws UsernameNotFoundException
	{
		User user = mapper.LoginUserInfo(user_username);

    return user;
	}
}