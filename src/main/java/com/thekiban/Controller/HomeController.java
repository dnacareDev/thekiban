package com.thekiban.Controller;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.thekiban.Entity.SampleOutcome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.thekiban.Entity.Breed;
import com.thekiban.Entity.ChartCount;
import com.thekiban.Service.HomeService;

@Controller
@RequestMapping(value = "/")
public class HomeController
{
	@Autowired
	private HomeService service;

	@RequestMapping("/")
	public ModelAndView Index(ModelAndView mv)
	{
		mv.setViewName("home/kiban_home");
		
		return mv;
	}

	@RequestMapping("/home")
	public ModelAndView Home(ModelAndView mv)
	{
		mv.setViewName("home/kiban_home");

		return mv;
	}
	
	@ResponseBody
	@RequestMapping("selectChart")
	public Map<String, Object> SelectChart()
	{
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		
		ChartCount breed = service.SelectChartBreed();
		ChartCount sales = service.SelectChartSales();
		ChartCount apply = service.SelectChartApply();
		ChartCount protect = service.SelectChartProtect();

		result.put("breed", breed);
		result.put("sales", sales);
		result.put("apply", apply);
		result.put("protect", protect);
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("selectChartBar")
	public Map<Integer, Object> SelectChartBar()
	{
		Map<Integer, Object> result = new LinkedHashMap<Integer, Object>();
		
		Date date = new Date();
		
		for(int i = 1; i > -4; i--)
		{
			String year = (date.getYear() + 1900 + i) + "-01-01";
			
			List<Breed> breed = service.SelectChartBar(year);
			
			result.put((date.getYear() + 1899 + i), breed);
		}
		
		return result;
	}

	@ResponseBody
	@RequestMapping("selectOutcomeList")
	public Map<String, Object> SelectOutcome() {
		Map<String, Object> result = new LinkedHashMap<String, Object>();

		List<SampleOutcome> sampleOutcomes = service.SelectOutcomeList();

		result.put("sampleOutcome", sampleOutcomes);

		return result;
	}
}