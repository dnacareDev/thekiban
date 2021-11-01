package com.thekiban.Controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.thekiban.Entity.Sample;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.thekiban.Entity.Basic;
import com.thekiban.Entity.Detail;
import com.thekiban.Entity.Standard;
import com.thekiban.Service.BasicService;

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
	
	// 원종 검색
	@ResponseBody
	@RequestMapping("searchBasic")
	public Map<String, Object> SearchBasic(@RequestParam("basic_name") String basic_name, @RequestParam("page_num") int page_num)
	{
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		
		int count = service.SelectBasicCount(basic_name);
		
		int limit = 10;
		int offset = (page_num - 1) * limit;
		int end_page = (count + limit - 1) / limit;
		
		List<Basic> basic = service.SearchBasic(basic_name, offset, limit);
		
		result.put("basic", basic);
		result.put("page_num", page_num);
		result.put("end_page", end_page);
		result.put("offset", offset);

		return result;
	}
	
	// 원종 등록
	@RequestMapping("insertBasic")
	public ModelAndView InsertBasic(ModelAndView mv, @ModelAttribute Basic basic, @RequestParam("detail_list") String detail_list)
	{
		int insert_basic = service.InsertBasic(basic);
		
		JSONArray arr = new JSONArray(detail_list);
		
		List<Standard> standard = new ArrayList<Standard>();
		
   		for(int i = 0; i < arr.length(); i++)
   		{
   			Standard item = new Standard();
   			
   			JSONObject obj = arr.getJSONObject(i);
   			
   			String detail_id = (String)obj.get("key");
   			String value = (String)obj.get("value");

   			if(value != "")
   			{
   				item.setBasic_id(basic.getBasic_id());
   				item.setDetail_id(Integer.parseInt(detail_id));
					item.setStandard((String)obj.get("value"));
   				
   				standard.add(item);
   			}
   		}
   		
   		if(insert_basic != 0)
   		{
   			int insert_standard = service.InsertStandard(standard);
   		}
		
		mv.setViewName("redirect:/basic");
		
		return mv;
	}

	// 선택삭제
	@ResponseBody
	@RequestMapping("deleteBasic")
	public int DeleteBasic(@RequestParam("basic_id[]") int[] basic_id)
	{
		service.DeleteBasic(basic_id);

		return 1;
	}
	
	// 원종 수정
	@RequestMapping("updateBasic")
	public ModelAndView UpdateBasic(ModelAndView mv, @ModelAttribute Basic basic, @RequestParam("update_list") String update_list)  {
		JSONArray arr = new JSONArray(update_list);

		JSONObject obj = arr.getJSONObject(0);

		String value = (String)obj.get("value");

		basic.setBasic_id(Integer.parseInt(value));

		service.UpdateBasic(basic);

		mv.setViewName("redirect:/basic");

		return mv;
	}
}