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

import com.thekiban.Entity.Basic;
import com.thekiban.Entity.Breed;
import com.thekiban.Entity.Detail;
import com.thekiban.Entity.Display;
import com.thekiban.Entity.Standard;
import com.thekiban.Entity.User;
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
		mv.setViewName("genome/basic");
		
		return mv;
	}
	
	// 원종 검색
	@ResponseBody
	@RequestMapping("searchBasic")
	public Map<String, Object> SearchBasic(Authentication auth, @RequestParam("basic_name") String basic_name, @RequestParam("page_num") int page_num)
	{
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		
		User user = (User)auth.getPrincipal();
		
		int count = service.SelectBasicCount(basic_name);
		
		int limit = 10;
		int offset = (page_num - 1) * limit;
		int end_page = (count + limit - 1) / limit;
		
		List<Basic> basic = service.SearchBasic(basic_name, offset, limit);						// 원종 검색
		List<Detail> detail = service.SearchBasicDetail(basic_name);							// 원종 작물별 컬럼 조회
		List<Display> display = service.SelectDisplay(user.getUser_id(), basic_name);			// 사용자별 원종 표시항목 조회
		
		List<Standard> standard = new ArrayList<Standard>();
		
		if(!detail.isEmpty())
		{
			for(int i = 0; i < basic.size(); i++)
			{
				standard = service.SearchBasicStandard(detail, user.getUser_id(), basic.get(i).getBasic_id());
				basic.get(i).setBasic_standard(standard);
			}
		}
		
		result.put("basic", basic);
		result.put("detail", detail);
		result.put("display", display);
		result.put("page_num", page_num);
		result.put("end_page", end_page);
		result.put("offset", offset);

		return result;
	}
	
	// 원종 등록
	@ResponseBody
	@RequestMapping("insertBasic")
	public Map<String, Object> insertBasic(ModelAndView mv, @RequestParam("basic_name") String basic_name, @RequestParam("offset") int offset)
	{
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		
		Basic basic = new Basic();
		basic.setBasic_name(basic_name);
		
		List<Detail> detail = service.SearchBasicDetail(basic_name);
		
		int insert_breed = service.InsertBasic(basic);
		int insert_standard = service.InsertStandard(basic.getBasic_id(), basic_name, detail);
		
		List<Basic> basic_list = service.SelectBasicAll(basic_name, offset);
		
		List<Standard> standard = new ArrayList<Standard>();
		
		if(!basic_list.isEmpty())
		{
			for(int i = 0; i < basic_list.size(); i++)
			{
				standard = service.SelectBasicStandard(basic_list.get(i).getBasic_id());
				
				basic_list.get(i).setBasic_standard(standard);
			}
		}
		
		result.put("basic", basic_list);
		result.put("new_basic", basic);
		result.put("detail", detail);
		
		return result;
	}
	
	// 원종 수정
	@ResponseBody
	@RequestMapping("updateBasic")
	public int UpdateBasic(@RequestParam("basic_id") int basic_id, @RequestParam("detail_id") int detail_id, @RequestParam("standard") String standard)
	{
		int result = service.UpdateBasic(basic_id, detail_id, standard);
		
		return result;
	}
	
	// 원종 수정
	@RequestMapping("updateAllBasic")
	public ModelAndView UpdateAllBasic(ModelAndView mv, @RequestParam("basic_id") int basic_id, @RequestParam("detail_id") int[] detail_id, @RequestParam("standard") String[] standard)
	{
		int result = 0;
		
		List<Standard> list = new ArrayList<Standard>();
		
		Standard item = new Standard();
		
		for(int i = 0; i < detail_id.length; i++)
		{
			item = new Standard();
			
			if(standard[i].equals(""))
			{
				item.setBasic_id(basic_id);
				item.setDetail_id(detail_id[i]);
				item.setStandard(null);
				
				list.add(item);
			}
			else
			{
				item.setBasic_id(basic_id);
				item.setDetail_id(detail_id[i]);
				item.setStandard(standard[i]);
				
				list.add(item);
			}
		}
		
		result = service.UpdateAllBasic(list);
		
		mv.setViewName("redirect:/basic");
		
		return mv;
	}
	
	// 표시항목 조회
	@ResponseBody
	@RequestMapping("selectBasicStandard")
	public List<Standard> SelectBasicStandard(@RequestParam("basic_id") int basic_id)
	{
		List<Standard> result = service.SelectBasicStandard(basic_id);
		
		return result;
	}

	// 선택삭제
	@ResponseBody
	@RequestMapping("deleteBasic")
	public int DeleteBasic(@RequestParam("basic_id[]") int[] basic_id)
	{
		int[] delete_basic = service.DeleteBasic(basic_id);
		int[] delete_standard = service.DeleteStandard(basic_id);

		return 1;
	}
	
	// 표시항목 설정
	@RequestMapping("insertBasicDisplay")
	public ModelAndView InsertBasicDisplay(ModelAndView mv, Authentication auth, @RequestParam("basic_name") String basic_name, @RequestParam(required = false, value = "detail_id") int[] detail_id)
	{
		User user = (User)auth.getPrincipal();
		
		int delete = service.DeleteDisplay(user.getUser_id());
		int insert = service.InsertDisplay(user.getUser_id(), basic_name, detail_id);
		
		mv.setViewName("redirect:/basic");
		
		return mv;
	}
}