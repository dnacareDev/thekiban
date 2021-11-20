package com.thekiban.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONString;
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
import com.thekiban.RModule.RunTrait;
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
		mv.addObject("total_id", total_id);
		mv.addObject("type", type);
		
		mv.setViewName("analysis/analysis");
		
		return mv;
	}
	
	// 작목 조회
	@ResponseBody
	@RequestMapping("/selectTarget")
	public Map<String, Object> SelectTarget(@RequestParam("name") String name, @RequestParam(required = false, value = "total_id") int[] total_id, @RequestParam("type") int type)
	{
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		
		List<Breed> breed = new ArrayList<Breed>();
		List<Basic> basic = new ArrayList<Basic>();
		
		if(type == 1)
		{
			breed = service.SelectBreed(name, total_id, type);
			
			result.put("breed", breed);
		}
		else if(type == 2)
		{
			basic = service.SelectBasic(name, total_id, type);
			
			result.put("basic", basic);
		}
		
		return result;
	}
	
	// 분석형질 조회(품종, 원종)
	@ResponseBody
	@RequestMapping("selectTrait")
	public List<Detail> SelectTrait(@RequestParam("deatil_name") String deatil_name, @RequestParam("detail_type") int detail_type)
	{
		List<Detail> result = service.SelectTrait(deatil_name, detail_type);
		
		return result;
	}
	
	// 형질 분석
	@RequestMapping("runAnalysis")
	public ModelAndView RunAnalysis(ModelAndView mv, @RequestParam("method") String method, @RequestParam("file") MultipartFile file)
	{
		Date date = new Date();
		
        String date_name = (1900 + date.getYear()) + "" + (date.getMonth() + 1) + "" + date.getDate() + "" + date.getHours() + "" + date.getMinutes() + "" + date.getSeconds();
        String origin_name = file.getOriginalFilename();
        String file_name = date_name + "_" + origin_name;
        
        /*
        File testPath = new File("C:\\Users\\User\\pref\\project\\DNA\\kiban_github\\upload\\" + date_name);
        File testFile = new File("C:\\Users\\User\\pref\\project\\DNA\\kiban_github\\upload\\" + date_name + "\\" + file_name);
        
        try
        {
        	if (!testPath.exists())
        	{
        		testPath.mkdirs();
        	}
        	
        	if (!testFile.exists())
        	{
        		testFile.createNewFile();
        	}
        	
        	BufferedWriter writer = new BufferedWriter(new FileWriter(testFile, true));
        	
			writer.write("test1");
			writer.write("\t");
			writer.write("test2");
			
			writer.flush();
			writer.close();
			
        	Path fileLocation = Paths.get("C:\\Users\\User\\pref\\project\\DNA\\kiban_github\\upload\\" + date_name + ".txt").toAbsolutePath().normalize();
        	Path targetLocation = fileLocation.resolve(file_name);
        	
        	Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
		}
        catch
        (IOException e)
        {
        	System.out.println(e.getMessage());
			e.printStackTrace();
		}
		*/
        
        if(method.equals("Correlation"))
        {
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
        	
        	RunCorrlation runcorrlation = new RunCorrlation();
        	runcorrlation.MakeCorrplot("c\\(2,4,8\\)", "/data/apache-tomcat-9.0.8/webapps/ROOT/kiban/resultfiles/", date_name);
        }
        else if(method.equals("Trait View"))
        {
        	String path = "/data/apache-tomcat-9.0.8/webapps/ROOT/kiban/resultfiles/r_plot/trait/" + date_name;
        	
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
        	
        	RunTrait runtrait = new RunTrait();
        	runtrait.MakeTraitplot("c\\(4\\)", "/data/apache-tomcat-9.0.8/webapps/ROOT/kiban/resultfiles/", date_name);
        }
        
        mv.setViewName("redirect:/analysis");
        
        return mv;
	}
}