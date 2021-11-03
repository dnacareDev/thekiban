package com.thekiban.Controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.thekiban.Entity.Breed;
import com.thekiban.Entity.Detail;
import com.thekiban.Entity.Display;
import com.thekiban.Entity.Standard;
import com.thekiban.Entity.User;
import com.thekiban.Service.BreedService;

@Controller
public class BreedController
{
	@Autowired
	private BreedService service;
  
	// 품종 관리 페이지
	@RequestMapping("breed")
	public ModelAndView BreedList(ModelAndView mv)
	{
		mv.setViewName("genome/breed");
    
		return mv;
	}
  
	// 원종 검색
	@ResponseBody
	@RequestMapping("searchBreed")
	public Map<String, Object> SearchBreed(Authentication auth, @RequestParam("breed_name") String breed_name, @RequestParam("page_num") int page_num)
	{
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		
		User user = (User)auth.getPrincipal();
		
		int count = service.SelectBreedCount(breed_name);

		int limit = 10;
		int offset = (page_num - 1) * limit;
		int end_page = (count + limit - 1) / limit;
    
		List<Breed> breed = service.SearchBreed(breed_name, offset, limit);
		List<Detail> detail = service.SearchBreedDetail(breed_name);
		List<Display> display = service.SelectDisplay(user.getUser_id(), breed_name);
		
		List<Standard> standard = new ArrayList<Standard>();
		
		if(!detail.isEmpty())
		{
			for(int i = 0; i < breed.size(); i++)
			{
				standard = service.SearchBreedStandard(detail, user.getUser_id(), breed.get(i).getBreed_id());
				
				breed.get(i).setBreed_standard(standard);
			}
		}
		
		result.put("breed", breed);
		result.put("detail", detail);
		result.put("display", display);
		result.put("page_num", page_num);
		result.put("end_page", end_page);
		result.put("offset", offset);

		return result;
	}

	// 품종 등록
	@ResponseBody
	@RequestMapping("insertBreed")
	public Map<String, Object> InsertBreed(ModelAndView mv, @RequestParam("breed_name") String breed_name, @RequestParam("offset") int offset)
	{
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		
		Breed breed = new Breed();
		breed.setBreed_name(breed_name);
		
		List<Detail> detail = service.SearchBreedDetail(breed_name);
		
		int insert_breed = service.InsertBreed(breed);
		int insert_standard = service.InsertStandard(breed.getBreed_id(), breed_name, detail);
		
		List<Breed> breed_list = service.SelectBreedAll(breed_name, offset);
		
		List<Standard> standard = new ArrayList<Standard>();
		
		if(!breed_list.isEmpty())
		{
			for(int i = 0; i < breed_list.size(); i++)
			{
				standard = service.SelectBreedStandard(breed_list.get(i).getBreed_id());
				
				breed_list.get(i).setBreed_standard(standard);
			}
		}
		
		result.put("breed", breed_list);
		result.put("new_breed", breed);
		result.put("detail", detail);
		
		return result;
	}
	
	// 품종 수정
	@ResponseBody
	@RequestMapping("updateBreed")
	public int UpdateBreed(@RequestParam("breed_id") int breed_id, @RequestParam("detail_id") int detail_id, @RequestParam("standard") String standard)
	{
		int result = service.UpdateBreed(breed_id, detail_id, standard);
		
		return result;
	}
	
	// 품종 수정
	@RequestMapping("updateAllBreed")
	public ModelAndView UpdateAllBreed(ModelAndView mv, @RequestParam("breed_id") int breed_id, @RequestParam("detail_id") int[] detail_id, @RequestParam("standard") String[] standard)
	{
		int result = 0;
		
		List<Standard> list = new ArrayList<Standard>();
		
		Standard item = new Standard();
		
		for(int i = 0; i < detail_id.length; i++)
		{
			item = new Standard();
			
			if(standard[i].equals(""))
			{
				item.setBreed_id(breed_id);
				item.setDetail_id(detail_id[i]);
				item.setStandard(null);
				
				list.add(item);
			}
			else
			{
				item.setBreed_id(breed_id);
				item.setDetail_id(detail_id[i]);
				item.setStandard(standard[i]);
				
				list.add(item);
			}
		}
		
		result = service.UpdateAllBreed(list);
		
		mv.setViewName("redirect:/breed");
		
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("selectStandard")
	public List<Standard> SelectStandard(@RequestParam("breed_id") int breed_id)
	{
		List<Standard> result = service.SelectBreedStandard(breed_id);
		
		return result;
	}

	// 선택삭제
	@ResponseBody
	@RequestMapping("deleteBreed")
	public int DeleteBreed(@RequestParam("breed_id[]") int[] breed_id)
	{
		int[] delete_breed = service.DeleteBreed(breed_id);
		int[] delete_standard = service.DeleteStandard(breed_id); 

		return 1;
	}
	
	// 표시항목 설정
	@RequestMapping("insertDisplay")
	public ModelAndView InsertDisplay(ModelAndView mv, Authentication auth, @RequestParam("breed_name") String breed_name, @RequestParam(required = false, value = "detail_id") int[] detail_id)
	{
		User user = (User)auth.getPrincipal();
		
		int delete = service.DeleteDisplay(user.getUser_id());
		int insert = service.InsertDisplay(user.getUser_id(), breed_name, detail_id);
		
		mv.setViewName("redirect:/breed");
		
		return mv;
	}
}