package com.thekiban.Controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LabController
{
	@RequestMapping("/mabc")
	public ModelAndView getDataManage(ModelAndView mv)
	{
		mv.setViewName("lab/mabc");

		return mv;
	}
	
	@RequestMapping("/matrix")
	public ModelAndView Analysis(ModelAndView mv)
	{
		mv.setViewName("lab/matrix");
		
		return mv;
	}
	
	@RequestMapping("/insertMatrix")
	public ModelAndView InsertMatrix(ModelAndView mv, @RequestParam("file") MultipartFile file)
	{
		System.out.println(file);
		System.out.println(file.getContentType());
		System.out.println(file.getOriginalFilename());
		
		Date date = new Date();
		System.out.println(date);
		System.out.println(date.getYear());
		System.out.println(date.getMonth());
		System.out.println(date.getDate());
		System.out.println(date.getDay());
		System.out.println(date.getHours());
		System.out.println(date.getMinutes());
		System.out.println(date.getSeconds());
		System.out.println(date.getTime());
		
		String file_name = (1900 + date.getYear()) + "" + (date.getMonth() + 1) + "" + date.getDate() + "" + date.getHours() + "" + date.getMinutes() + "" + date.getSeconds();
		System.out.println(file_name);
		
		mv.setViewName("lab/matrix");
		
		return mv;
	}
}