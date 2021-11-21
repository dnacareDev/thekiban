package com.thekiban.Controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.thekiban.Entity.Basic;
import com.thekiban.Entity.Breed;
import com.thekiban.Entity.Detail;
import com.thekiban.Entity.Standard;
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
	@ResponseBody
	@RequestMapping("runAnalysis")
	public int RunAnalysis(@RequestParam("detail_name") String detail_name, @RequestParam("detail_type") int detail_type, @RequestParam("target_id[]") int[] target_id, @RequestParam("method") int method, @RequestParam("trait_id") String trait_id)
	{
		List<Detail> detail = service.SelectDetail(detail_name, detail_type);
		List<Standard> standard = service.SelectStandard(target_id, detail_type);
		
		Date date = new Date();
		
        String date_name = (1900 + date.getYear()) + "" + (date.getMonth() + 1) + "" + date.getDate() + "" + date.getHours() + "" + date.getMinutes() + "" + date.getSeconds();
//        String origin_name = file.getOriginalFilename();
//        String file_name = date_name + "_" + origin_name;
        String file_name = date_name + "_phenotype_list.txt";
        
        String root = null;
        File path = null;
        File file = null;
        
        if(method == 0)
        {
        	root = "/data/apache-tomcat-9.0.8/webapps/ROOT/kiban/resultfiles/r_plot/corrplot/" + date_name;
        	
        	path = new File(root);
        	file = new File(root + "/" + file_name);
        }
        else
        {
        	root = "/data/apache-tomcat-9.0.8/webapps/ROOT/kiban/resultfiles/r_plot/trait/" + date_name;
        	
        	path = new File(root);
        	file = new File(root + "/" + file_name);
        }
        
        try
        {
        	if (!path.exists())
        	{
        		path.mkdirs();
        	}
        	
        	if (!file.exists())
        	{
        		file.createNewFile();
        	}
        	
        	BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        	
        	for(int i = 0; i < detail.size(); i++)
    		{
        		if(!(detail.get(i).getDetail_title().equals("작물")))
        		{
        			if(!detail.get(i).getDetail_title().equals("시교명 (ID)"))
        			{
        				writer.write("ch" + (i - 1));
        				writer.write("\t");
        			}
        			else
        			{
        				writer.write("\t");
        			}
        		}
    		}
        	for(int i = 0; i < standard.size(); i++)
        	{
        		if(i == 0)
        		{
        			
        		}
        		if(i == 1)
        		{
        			if(!standard.get(i).getStandard().isEmpty())
        			{
        				writer.write(standard.get(i).getStandard());
        			}
    				writer.write("\t");
        		}
        		else if(i % detail.size() == 0)
        		{
        			writer.newLine();
        		}
        		else
        		{
        			if(standard.get(i).getStandard() != (null))
        			{
        				if(!standard.get(i).getStandard().equals(""))
        				{
        					writer.write(standard.get(i).getStandard());
        				}
        				else
        				{
        					writer.write("NaN");
        				}
        			}
        			else
        			{
        				writer.write("NaN");
        			}
    				writer.write("\t");
        		}
        	}
			
			writer.flush();
			writer.close();
			
        	Path fileLocation = Paths.get(root + "/" + file_name).toAbsolutePath().normalize();
        	Path targetLocation = fileLocation.resolve(file_name);
        	
        	String trait = trait_id.replace("[", "");
        	trait = trait.replace("]", "");
        	trait = trait.replace("\"", "");
        	
            if(method == 0)
            {
            	RunCorrlation runcorrlation = new RunCorrlation();
            	runcorrlation.MakeCorrplot("c\\("+ trait + "\\)", "/data/apache-tomcat-9.0.8/webapps/ROOT/kiban/resultfiles/", date_name);
            }
            else
            {
            	RunTrait runtrait = new RunTrait();
            	runtrait.MakeTraitplot("c\\("+ trait + "\\)", "/data/apache-tomcat-9.0.8/webapps/ROOT/kiban/resultfiles/", date_name);
            }
		}
        catch
        (IOException e)
        {
        	System.out.println(e.getMessage());
        	
			e.printStackTrace();
		}
        
        /*
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
        */
        
        return 0;
	}
}