package com.thekiban.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class HomeController
{
	@RequestMapping("/home")
	public ModelAndView getHome(ModelAndView mv)
	{
		mv.setViewName("home/kiban_home");

		return mv;
	}
	
	@RequestMapping("/test1")
	public ModelAndView test1(ModelAndView mv)
	{
		mv.setViewName("template/example47");
		
		return mv;
	}
	@RequestMapping("/test2")
	public ModelAndView test2(ModelAndView mv)
	{
		mv.setViewName("template/example52");
		
		return mv;
	}
}