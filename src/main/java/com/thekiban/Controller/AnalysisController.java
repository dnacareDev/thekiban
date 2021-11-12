package com.thekiban.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.thekiban.Entity.Basic;
import com.thekiban.Entity.Breed;
import com.thekiban.Service.AnalysisService;

@Controller
public class AnalysisController
{
	@Autowired
	private AnalysisService service;
	
	@RequestMapping("/analysis")
	public ModelAndView Analysis(ModelAndView mv, @RequestParam(required = false, value = "total_id") int[] total_id, @RequestParam(defaultValue = "0", value = "type") int type)
	{
		List<Breed> breed = new ArrayList<Breed>();
		List<Basic> basic = new ArrayList<Basic>();
		
		if(type != 2)
		{
			breed = service.SelectBreed(total_id, type);
		}
		
		if(type != 1)
		{
			basic = service.SelectBasic(total_id, type);
		}
		
		mv.addObject("breed", breed);
		mv.addObject("basic", basic);
		
		mv.setViewName("analysis/analysis");
		
		return mv;
	}
}