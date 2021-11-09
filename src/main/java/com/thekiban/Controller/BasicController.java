package com.thekiban.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.thekiban.Entity.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.thekiban.Service.BasicService;

@Controller
public class BasicController
{
	@Autowired
	private BasicService service;
	
	@Autowired
	private FileController fileController;
	
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
	public Map<String, Object> SearchBasic(Authentication auth, @RequestParam("basic_name") String basic_name, @RequestParam("page_num") int page_num, @RequestParam("limit") int limit)
	{
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		
		User user = (User)auth.getPrincipal();
		
		int count = service.SelectBasicCount(basic_name);
		
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

	// 재고 검색
	@ResponseBody
	@RequestMapping("searchBasicRemain")
	public Map<String, Object> SearchBasicRemain(@RequestParam("basic_name") String basic_name, @RequestParam("page_num") int page_num, @RequestParam("limit") int limit) {
		Map<String, Object> result = new LinkedHashMap<String, Object>();

		int count = service.SelectRemainCount(basic_name);

		int offset = (page_num - 1) * limit;
		int end_page = (count + limit - 1) / limit;

		List<BasicRemain> basicRemains = service.SearchBasicRemain(basic_name, offset, limit);

		result.put("basicRemain", basicRemains);
		result.put("page_num", page_num);
		result.put("end_page", end_page);
		result.put("offset", offset);

		return result;
	}

	// 원종 등록
	@ResponseBody
	@RequestMapping("insertBasic")
	public Map<String, Object> InsertBasic(ModelAndView mv, @RequestParam("basic_name") String basic_name, @RequestParam("offset") int offset)
	{
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		
		Basic basic = new Basic();
		basic.setBasic_name(basic_name);
		
		List<Detail> detail = service.SearchBasicDetail(basic_name);
		
		int insert_basic = service.InsertBasic(basic);
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

	// 재고 등록
	@ResponseBody
	@RequestMapping("insertBasicRemain")
	public BasicRemain InsertBasicRemain(@ModelAttribute BasicRemain basicRemain, @RequestParam("input_data") String input_data) {
		JSONArray arr = new JSONArray(input_data);

		JSONObject obj = arr.getJSONObject(0);

		String value = (String) obj.get("value");

		basicRemain.setBasic_name(value);

		service.InsertRemain(basicRemain);

		return basicRemain;
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

	// 재고 수정
	@ResponseBody
	@RequestMapping("updateBasicRemain")
	public int UpdateBasicRemain(@RequestParam("basic_remain_id") int basic_remain_id, @RequestParam("basic_remain_name") String basic_remain_name, @RequestParam("basic_remain_value") String basic_remain_value)
	{
		int result = service.UpdateBasicRemain(basic_remain_id, basic_remain_name, basic_remain_value);

		return result;
	}

	// 표시항목 조회
	@ResponseBody
	@RequestMapping("selectBasicStandard")
	public List<Standard> SelectBasicStandard(@RequestParam("basic_id") int basic_id)
	{
		List<Standard> result = service.SelectBasicStandard(basic_id);
		
		return result;
	}

	// 재고 입력
	@RequestMapping("updateInsertBasicRemain")
	public ModelAndView UpdateInsertBasicRemain(ModelAndView mv, @ModelAttribute BasicRemain basicRemain, @RequestParam("data") String data) {
		JSONArray arr = new JSONArray(data);

		JSONObject input_id = arr.getJSONObject(0);

		int value_id = (Integer) input_id.get("value");

		basicRemain.setBasic_remain_id(value_id);

		for (int i = 1; i < arr.length(); i++) {
			JSONObject obj = arr.getJSONObject(i);

			String key_id = (String) obj.get("key");
			String value = (String) obj.get("value");

			if (!value.equals("")) {
				if (key_id.equals("basic_remain_num")) {
					basicRemain.setBasic_remain_num(value);
				} else if (key_id.equals("basic_remain_amount")) {
					basicRemain.setBasic_remain_amount(Integer.parseInt(value));
				} else if (key_id.equals("basic_remain_in")) {
					basicRemain.setBasic_remain_in(Integer.parseInt(value));
				} else if (key_id.equals("basic_remain_out")) {
					basicRemain.setBasic_remain_out(Integer.parseInt(value));
				} else if (key_id.equals("basic_remain_re")) {
					basicRemain.setBasic_remain_re(Integer.parseInt(value));
				} else if (key_id.equals("basic_remain_person")) {
					basicRemain.setBasic_remain_person(value);
				} else if (key_id.equals("basic_remain_date")) {
					basicRemain.setBasic_remain_date(value);
				}
			} else {
				if (key_id.equals("basic_remain_num")) {
					basicRemain.setBasic_remain_num("");
				} else if (key_id.equals("basic_remain_amount")) {
					basicRemain.setBasic_remain_amount(0);
				} else if (key_id.equals("basic_remain_in")) {
					basicRemain.setBasic_remain_in(0);
				} else if (key_id.equals("basic_remain_out")) {
					basicRemain.setBasic_remain_out(0);
				} else if (key_id.equals("basic_remain_re")) {
					basicRemain.setBasic_remain_re(0);
				} else if (key_id.equals("basic_remain_person")) {
					basicRemain.setBasic_remain_person("");
				} else if (key_id.equals("basic_remain_date")) {
					basicRemain.setBasic_remain_date(null);
				}
			}
		}
		service.UpdateInsertBasicRemain(basicRemain);

		mv.setViewName("redirect:/basic");

		return mv;
	}

	// 선택삭제
	@ResponseBody
	@RequestMapping("deleteBasic")
	public int DeleteBasic(@RequestParam("basic_id[]") int[] basic_id)
	{
		List<Uploads> uploads = service.SelectUploads(basic_id);
		
		for(int i = 0; i < uploads.size(); i++)
		{
			String delete_path = "upload/" + uploads.get(i).getUploads_file();
			File origin_file = new File(delete_path);
			
			origin_file.delete();
		}
		
		int[] delete_basic = service.DeleteBasic(basic_id);
		int[] delete_standard = service.DeleteStandard(basic_id);
		int delete_file = service.DeleteFile(basic_id);

		if(!uploads.isEmpty())
		{
			int delete_uploads = service.DeleteUploads(uploads);
		}
		
		return 1;
	}

	// 재고 삭제
	@ResponseBody
	@RequestMapping("deleteBasicRemain")
	public int DeleteBasicRemain(@RequestParam("basic_remain_id[]") int[] basic_remain_id)
	{
		service.DeleteBasicRemain(basic_remain_id);

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
	
	// 첨부 파일 조회
	@ResponseBody
	@RequestMapping("selectBasicFile")
	public Map<String, Object> SelectBasicFile(@RequestParam("basic_id") int basic_id)
	{
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		
		Basic basic = service.SelectBasicDetail(basic_id);
		List<BasicFile> basic_file = service.SelectBasicFile(basic_id);
		
		result.put("basic", basic);
		result.put("basic_file", basic_file);
		
		return result;
	}
	
	// 첨부파일 등록
	@RequestMapping("insertBasicFile")
	public ModelAndView InsertBasicFile(ModelAndView mv, @ModelAttribute BasicFile basic_file, @RequestParam("file") MultipartFile file) throws IOException
	{
		String[] extension = file.getOriginalFilename().split("\\.");
		
		String file_name = fileController.ChangeFileName(extension[1]);
		String origin_file_name = file.getOriginalFilename();
		
		String path = "upload";
		
		File filePath = new File(path);
		
        if (!filePath.exists())
            filePath.mkdirs();
        
       	Path fileLocation = Paths.get(path).toAbsolutePath().normalize();
       	Path targetLocation = fileLocation.resolve(file_name);
		
       	Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
       	
       	int insert_file = service.InsertBasicFile(basic_file);
       	
		Uploads upload = new Uploads();
		upload.setUploads_file(file_name);
		upload.setUploads_origin_file(origin_file_name);
		upload.setBasic_file_id(basic_file.getBasic_file_id());
		
		int insert_upload = service.InsertBasicUpload(upload);
		
		mv.setViewName("redirect:/basic");
		
		return mv;
	}
	
	// 첨부파일 수정
	@RequestMapping("updateBasicFile")
	public ModelAndView UpdateBasicFile(ModelAndView mv, @ModelAttribute BasicFile basic_file, @RequestParam("file") MultipartFile file) throws IOException
	{
		if(file.isEmpty())
		{
			int update_file = service.UpdateBasicFile(basic_file);
		}
		else
		{
			String delete_path = "upload/" + basic_file.getUploads_file();
			File origin_file = new File(delete_path);
			
			if(origin_file.delete())
			{
				String[] extension = file.getOriginalFilename().split("\\.");
				
				String file_name = fileController.ChangeFileName(extension[1]);
				String origin_file_name = file.getOriginalFilename();
				
				String path = "upload";
				
				File filePath = new File(path);
				
		        if (!filePath.exists())
		            filePath.mkdirs();
		        
		       	Path fileLocation = Paths.get(path).toAbsolutePath().normalize();
		       	Path targetLocation = fileLocation.resolve(file_name);
				
		       	Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
		       	
		       	Uploads upload = new Uploads();
		       	upload.setUploads_file(file_name);
				upload.setUploads_origin_file(origin_file_name);
				upload.setBasic_file_id(basic_file.getBasic_file_id());
				
				int update_upload = service.UpdateBasicUpload(upload);
			}
		}
		
		mv.setViewName("redirect:/basic");
		
		return mv;
	}
}