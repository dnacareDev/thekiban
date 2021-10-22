package com.thekiban.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.thekiban.Service.LoginService;

@Controller
public class LoginController
{
	@Autowired
	private LoginService service;
	
	@GetMapping("/login")
	public ModelAndView login(ModelAndView mv)
	{
		mv.setViewName("login/login");
		
		return mv;
	}
}