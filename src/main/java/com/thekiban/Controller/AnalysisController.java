package com.thekiban.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.thekiban.Entity.Basic;
import com.thekiban.Entity.Breed;
import com.thekiban.Entity.Detail;
import com.thekiban.RModule.RunCorrlation;
import com.thekiban.Service.AnalysisService;

@Controller
public class AnalysisController
{
	@Autowired
	private AnalysisService service;
	
	// 통합 분석 페이지
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
		
		mv.addObject("total_id", total_id);
		mv.addObject("breed", breed);
		mv.addObject("basic", basic);
		
		mv.setViewName("analysis/analysis");
		
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("selectTrait")
	public List<Detail> SelectTrait(@RequestParam("deatil_name") String deatil_name)
	{
		List<Detail> result = service.SelectTrait(deatil_name);
		
		return result;
	}
	
	@RequestMapping("Correlation")
	public ModelAndView Correlation(ModelAndView mv, @RequestParam("file") MultipartFile file)
	{
		System.out.println(file);
		System.out.println(file.getOriginalFilename());
		Date date = new Date();
		
        String date_name = (1900 + date.getYear()) + "" + (date.getMonth() + 1) + "" + date.getDate() + "" + date.getHours() + "" + date.getMinutes() + "" + date.getSeconds();
        String origin_name = file.getOriginalFilename();
        String file_name = date_name + "_" + origin_name;
        
        String path = "/data/apache-tomcat-9.0.8/webapps/ROOT/kiban/resultfiles/r_plot/corrplot/" + date_name;
        
        File filePath = new File(path);
        
        if (!filePath.exists())
        	filePath.mkdirs();
        
       	Path fileLocation = Paths.get(path).toAbsolutePath().normalize();
       	Path targetLocation = fileLocation.resolve(file_name);
       	
       	try
       	{
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
		}
       	catch (IOException e)
       	{
			System.out.println(e.getMessage());
			
			e.printStackTrace();
		}
        
//       	RunCorrlation runcorrlation = new RunCorrlation();
//       	runcorrlation.MakeCorrplot("c(2,4,8)", path, date_name);

        
        mv.setViewName("redirect:/analysis");
        
        return mv;
	}
}