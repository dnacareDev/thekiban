package com.thekiban.Service;

import com.thekiban.Entity.User;

public interface LoginService
{
	User loadUserByUsername(String user_username);
}