package com.thekiban.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LabController
{
	@RequestMapping(value = "mabc")
	public ModelAndView getDataManage(ModelAndView mv)
	{
		mv.setViewName("lab/mabc");

		return mv;
	}
}