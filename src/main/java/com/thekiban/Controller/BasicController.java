package com.thekiban.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.thekiban.Entity.*;
import com.thekiban.Service.BreedService;
import com.thekiban.Service.DataListService;
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
	private BreedService breedService;

	@Autowired
	private DataListService d_service;

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
		
		List<Basic> basic = service.SearchBasic(basic_name);						// 원종 검색
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
	public int InsertBasic(@RequestParam("data") String data)
	{
		JSONArray arr = new JSONArray(data);

		List<Standard> standards = new ArrayList<Standard>();

		JSONObject item = arr.getJSONObject(0);

		String basic_name = item.getString("standard");

		List<Detail> detail = service.SearchBasicDetail(basic_name);

		Basic basic = new Basic();
		basic.setBasic_name(basic_name);

		int basic_result = service.InsertBasic(basic);
		int insert_standard = service.InsertStandard(basic.getBasic_id(), basic_name, detail);

		for (int i = 0; i < arr.length(); i++) {
			JSONObject item_list = arr.getJSONObject(i);

			Standard standard = new Standard();

			standard.setBasic_id(basic.getBasic_id());
			standard.setDetail_id(item_list.getInt("detail_id"));
			standard.setStandard(item_list.getString("standard"));

			standards.add(standard);
		}

		service.UpdateAllBasic(standards);

		return 1;
	}

	// 재고 등록
	@ResponseBody
	@RequestMapping("insertBasicRemain")
	public BasicRemain InsertBasicRemain(@ModelAttribute BasicRemain basicRemain, @RequestParam("input_data") String input_data) {
		JSONArray arr = new JSONArray(input_data);

		JSONObject obj = arr.getJSONObject(0);

		String value = (String) obj.get("basic_name");

		basicRemain.setBasic_name(value);

		service.InsertRemain(basicRemain);

		return basicRemain;
	}

	// 원종 수정
	@ResponseBody
	@RequestMapping("updateBasic")
	public int UpdateBasic(@RequestParam("data") String data)
	{
		int result = 0;

		JSONArray arr = new JSONArray(data);

		for (int i = 0; i < arr.length(); i++) {
			JSONObject item = arr.getJSONObject(i);
			int basic_id = item.getInt("basic_id");
			int detail_id = item.getInt("detail_id");
			String standard = item.getString("standard");

			result = service.UpdateBasic(basic_id, detail_id, standard);
		}

		return result;
	}

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
	public int UpdateBasicRemain(@RequestParam("data") String data)
	{
		int result = 0;

		JSONArray arr = new JSONArray(data);

		for (int i = 0; i < arr.length(); i++) {
			JSONObject item = arr.getJSONObject(i);
			int basic_remain_id = item.getInt("basic_remain_id");
			String basic_remain_name = item.getString("basic_remain_name");
			String basic_remain_value = item.getString("basic_remain_value");

			result = service.UpdateBasicRemain(basic_remain_id, basic_remain_name, basic_remain_value);
		}

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
	@ResponseBody
	@RequestMapping("updateInsertBasicRemain")
	public int UpdateInsertBasicRemain(@ModelAttribute BasicRemain basicRemain, @RequestParam("data") String data) {
		JSONArray arr = new JSONArray(data);

		JSONObject obj = arr.getJSONObject(0);

		if(!obj.isNull("basic_name")) {
			basicRemain.setBasic_name((String) obj.get("basic_name"));
		} else {
			basicRemain.setBasic_name("");
		}

		if(!obj.isNull("basic_remain_num")) {
			basicRemain.setBasic_remain_num((String) obj.get("basic_remain_num"));
		} else {
			basicRemain.setBasic_remain_num("");
		}

		if(!obj.isNull("basic_remain_amount")) {
			String basic_remain_amount = (String) obj.get("basic_remain_amount");
			basic_remain_amount = basic_remain_amount.trim();
			basicRemain.setBasic_remain_amount(Integer.parseInt(basic_remain_amount));
		} else {
			basicRemain.setBasic_remain_amount(0);
		}

		if(!obj.isNull("basic_remain_in")) {
			String basic_remain_in = (String) obj.get("basic_remain_in");
			basic_remain_in = basic_remain_in.trim();
			basicRemain.setBasic_remain_in(Integer.parseInt(basic_remain_in));
		} else {
			basicRemain.setBasic_remain_in(0);
		}

		if(!obj.isNull("basic_remain_out")) {
			String basic_remain_out = (String) obj.get("basic_remain_out");
			basic_remain_out = basic_remain_out.trim();
			basicRemain.setBasic_remain_out(Integer.parseInt(basic_remain_out));
		} else {
			basicRemain.setBasic_remain_out(0);
		}

		if(!obj.isNull("basic_remain_re")) {
			String basic_remain_re = (String) obj.get("basic_remain_re");
			basic_remain_re = basic_remain_re.trim();
			basicRemain.setBasic_remain_re(Integer.parseInt(basic_remain_re));
		} else {
			basicRemain.setBasic_remain_re(0);
		}

		if(!obj.isNull("basic_remain_person")) {
			basicRemain.setBasic_remain_person((String) obj.get("basic_remain_person"));
		} else {
			basicRemain.setBasic_remain_person("");
		}

		if(!obj.isNull("basic_remain_date")) {
			String date = (String) obj.get("income_date");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.KOREA);
			LocalDate ldate = LocalDate.parse(date, formatter);

			basicRemain.setBasic_remain_date(ldate);
		} else {
			basicRemain.setBasic_remain_date(null);
		}

		int result = service.UpdateInsertBasicRemain(basicRemain);

		return result;
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
		
		int delete = service.DeleteDisplay(user.getUser_id(), basic_name);
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

//		String path = "src/main/webapp/upload";
		String path = "/data/apache-tomcat-9.0.8/webapps/ROOT/upload/";
		
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
//			String delete_path = "upload/" + basic_file.getUploads_file();
			String delete_path = "/data/apache-tomcat-9.0.8/webapps/ROOT/upload/" + basic_file.getUploads_file();
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

	@ResponseBody
	@RequestMapping("excelBasic")
	public int excelUpload(ModelAndView mv, @RequestParam("excel_list") String excel_list) {

		JSONArray arr = new JSONArray(excel_list);

		List<Standard> standards = new ArrayList<Standard>();

		for(int i = 0; i < arr.length(); i++)
		{
			JSONArray item = arr.getJSONArray(i);

			String basic_name = (String)item.get(0);

			Basic basic = new Basic();
			basic.setBasic_name(basic_name);

			int basic_result = service.InsertBasic(basic);

			List<Detail> detail = service.SelectDetailExcel(basic_name);

			for(int j = 0; j < detail.size(); j++)
			{
				Standard standard = new Standard();
				standard.setBasic_id(basic.getBasic_id());
				standard.setDetail_id(detail.get(j).getDetail_id());
				if(j < item.length()) {
					standard.setStandard((String) item.get(j));
				} else {
					standard.setStandard(null);
				}

				standards.add(standard);
			}
		}

		service.InsertBasicExcel(standards);

		return 1;
	}

	@ResponseBody
	@RequestMapping("excelRemain")
	public int remainExcelUpload(ModelAndView mv, @ModelAttribute BasicRemain basicRemain, @RequestParam("excel_list") String excel_list) {
		JSONArray arr = new JSONArray(excel_list);

		for (int i = arr.length() - 1; i > -1; i--) {

			JSONObject obj = arr.getJSONObject(i);

			Set<String> key = obj.keySet();

			for (String k : key) {

				if (k.equals("종자번호 (ID)")) {
					basicRemain.setBasic_remain_num(obj.getString(k));
				} else if (k.equals("종자 보유량")) {
					basicRemain.setBasic_remain_amount(Integer.parseInt(obj.getString(k)));
				} else if (k.equals("입고량")) {
					basicRemain.setBasic_remain_in(Integer.parseInt(obj.getString(k)));
				} else if (k.equals("출고량")) {
					basicRemain.setBasic_remain_out(Integer.parseInt(obj.getString(k)));
				} else if (k.equals("재고량")) {
					basicRemain.setBasic_remain_re(Integer.parseInt(obj.getString(k)));
				} else if (k.equals("담당자")) {
					basicRemain.setBasic_remain_person(obj.getString(k));
				} else if (k.equals("일자")) {
					String date = obj.getString(k);
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.KOREA);
					LocalDate ldate1 = LocalDate.parse(date, formatter);

					basicRemain.setBasic_remain_date(ldate1);
				} else if (k.equals("작물")) {
					basicRemain.setBasic_name(obj.getString(k));
				}
			}

			service.InsertRemainExcel(basicRemain);
		}

		return 1;
	}

	@ResponseBody
	@RequestMapping("insertBasicDataList")
	public DataList InsertDataList(@ModelAttribute DataList dataList, @RequestParam("listData") String listData) {
		JSONArray arr = new JSONArray(listData);

		JSONObject obj = arr.getJSONObject(0);

		List<Basic> basic = service.SearchBasicExcel(obj.getString("basic_name"));

		for (int i = 0; i < basic.size(); i++) {
			if(Objects.equals(basic.get(i).getCreate_date().split(" ")[0], obj.getString("datalist_date"))) {
				dataList.setDatalist_type(obj.getString("datalist_type"));
				dataList.setDatalist_date(obj.getString("datalist_date"));
				dataList.setTarget_id(basic.get(i).getBasic_id());
			} else {
				continue;
			}

			d_service.InsertDataList(dataList);
		}

		return dataList;
	}

	@ResponseBody
	@RequestMapping("insertBRemainDataList")
	public DataList InsertBRemainDataList(@ModelAttribute DataList dataList, @RequestParam("listData") String listData) {
		JSONArray arr = new JSONArray(listData);

		JSONObject obj = arr.getJSONObject(0);

		List<BasicRemain> basicRemains = service.SearchBasicRemainExcel(obj.getString("basic_name"));

		for (int i = 0; i < basicRemains.size(); i++) {
			if(Objects.equals(basicRemains.get(i).getCreate_date().split(" ")[0], obj.getString("datalist_date"))) {
				dataList.setDatalist_type(obj.getString("datalist_type"));
				dataList.setDatalist_date(obj.getString("datalist_date"));
				dataList.setTarget_id(basicRemains.get(i).getBasic_remain_id());
			} else {
				continue;
			}

			d_service.InsertDataList(dataList);
		}

		return dataList;
	}

	@ResponseBody
	@RequestMapping("selectBasicDateGroup")
	public Map<String, Object> SelectDateGroup(@RequestParam("datalist_type") String datalist_type) {
		Map<String, Object> result = new LinkedHashMap<String, Object>();

		List<Map<String, String>> dataGroup = d_service.SelectDateGroup(datalist_type);

		result.put("dataGroup", dataGroup);

		return result;
	}

	@ResponseBody
	@RequestMapping("selectBasicRemainDateGroup")
	public Map<String, Object> SelectRemainDateGroup(@RequestParam("datalist_type") String datalist_type) {
		Map<String, Object> result = new LinkedHashMap<String, Object>();

		List<Map<String, String>> dataGroup = d_service.SelectDateGroup(datalist_type);

		result.put("dataGroup", dataGroup);

		return result;
	}

	@ResponseBody
	@RequestMapping("searchBasicExcel")
	public Map<String, Object> SearchBasicExcel(@RequestParam("basic_name") String basic_name) {
		Map<String, Object> result = new LinkedHashMap<String, Object>();

		List<Basic> basic = service.SearchBasicExcel(basic_name);

		result.put("basic", basic);

		return result;
	}

	// 품종 검색
	@ResponseBody
	@RequestMapping("searchBreedListBasic")
	public Map<String, Object> SearchBreed(Authentication auth, @RequestParam("breed_name") String breed_name)
	{
		Map<String, Object> result = new LinkedHashMap<String, Object>();

		User user = (User)auth.getPrincipal();

		int count = breedService.SelectBreedCount(breed_name);

		List<Breed> breed = breedService.SearchBreedList(breed_name);								// 품종 검색
		List<Detail> detail = breedService.SearchBreedDetail(breed_name);									// 품종 작물별 컬럼 조회
		List<Display> display = breedService.SelectDisplay(user.getUser_id(), breed_name);					// 사용자별 품종 표시항목 조회

		List<Standard> standard = new ArrayList<Standard>();

		if(!detail.isEmpty())
		{
			for(int i = 0; i < breed.size(); i++)
			{
				standard = breedService.SearchBreedStandard(detail, user.getUser_id(), breed.get(i).getBreed_id());

				breed.get(i).setBreed_standard(standard);
			}
		}

		result.put("breed", breed);
		result.put("detail", detail);
		result.put("display", display);

		return result;
	}

	@ResponseBody
	@RequestMapping("searchTargetBasic")
	public Map<String, Object> SearchTarget(Authentication auth, @RequestParam("datalist_date") String datalist_date, @RequestParam("basic_name") String basic_name) {
		Map<String, Object> result = new LinkedHashMap<String, Object>();

		User user = (User) auth.getPrincipal();

		List<Integer> basic_id = d_service.SelectTarget(datalist_date, "basic");
		List<Detail> detail = service.SearchBasicDetail(basic_name);

		Map<Integer, Object> Basic = new LinkedHashMap<Integer, Object>();

		for(int i = 0; i < basic_id.size(); i++) {
			Basic.put(i, service.SelectBasicExcel(basic_id.get(i)));
		}

		result.put("basic", Basic);
		result.put("detail", detail);

		return result;
	}

	@ResponseBody
	@RequestMapping("searchTargetBRemain")
	public Map<String, Object> SearchTarget(@RequestParam("datalist_date") String datalist_date) {
		Map<String, Object> result = new LinkedHashMap<String, Object>();

		List<Integer> basic_remain_id = d_service.SelectTarget(datalist_date, "basic_remain");

		Map<Integer, Object> BasicRemain = new LinkedHashMap<Integer, Object>();

		for(int i = 0; i < basic_remain_id.size(); i++) {
			BasicRemain.put(i, service.SelectBRemainExcel(basic_remain_id.get(i)));
		}

		result.put("basic_remain", BasicRemain);

		return result;
	}
}