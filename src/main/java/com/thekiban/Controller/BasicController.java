package com.thekiban.Controller;

import com.thekiban.Entity.Basic;
import com.thekiban.Entity.Detail;
import com.thekiban.Service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BasicController
{
	@Autowired
	private BasicService service;
	
	// 원종 관리 페이지
	@RequestMapping("basic")
	public ModelAndView BasicList(ModelAndView mv)
	{
		List<Detail> detail = service.SelectDetail();
		
		mv.addObject("detail", detail);
		
		mv.setViewName("genome/basic");
		
		return mv;
	}
	
	// 원종 등록
	@RequestMapping("insertBasic")
	public ModelAndView InsertBasic(ModelAndView mv, @ModelAttribute Basic basic)
	{
		System.out.println(basic);
		
		mv.setViewName("redirect:/basic");
		
		return mv;
	}
}