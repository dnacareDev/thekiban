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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.thekiban.Entity.Breed;
import com.thekiban.Entity.BreedFile;
import com.thekiban.Entity.Detail;
import com.thekiban.Entity.Display;
import com.thekiban.Entity.Standard;
import com.thekiban.Entity.Uploads;
import com.thekiban.Entity.User;
import com.thekiban.Service.BreedService;

@Controller
public class BreedController
{
	@Autowired
	private BreedService service;
	
	@Autowired
	private FileController fileController;
  
	// 품종 관리 페이지
	@RequestMapping("breed")
	public ModelAndView BreedList(ModelAndView mv)
	{
		mv.setViewName("genome/breed");
    
		return mv;
	}
  
	// 품종 검색
	@ResponseBody
	@RequestMapping("searchBreed")
	public Map<String, Object> SearchBreed(Authentication auth, @RequestParam("breed_name") String breed_name, @RequestParam("page_num") int page_num, @RequestParam("limit") int limit)
	{
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		
		User user = (User)auth.getPrincipal();
		
		int count = service.SelectBreedCount(breed_name);

		int offset = (page_num - 1) * limit;
		int end_page = (count + limit - 1) / limit;
    
		List<Breed> breed = service.SearchBreed(breed_name, offset, limit);								// 품종 검색
		List<Detail> detail = service.SearchBreedDetail(breed_name);									// 품종 작물별 컬럼 조회
		List<Display> display = service.SelectDisplay(user.getUser_id(), breed_name);					// 사용자별 품종 표시항목 조회
		
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
	
	// 표시항목 조회
	@ResponseBody
	@RequestMapping("selectBreedStandard")
	public List<Standard> SelectBreedStandard(@RequestParam("breed_id") int breed_id)
	{
		List<Standard> result = service.SelectBreedStandard(breed_id);
		
		return result;
	}

	// 선택삭제
	@ResponseBody
	@RequestMapping("deleteBreed")
	public int DeleteBreed(@RequestParam("breed_id[]") int[] breed_id)
	{
		List<Uploads> uploads = service.SelectUploads(breed_id);
		
		for(int i = 0; i < uploads.size(); i++)
		{
			String delete_path = "upload/" + uploads.get(i).getUploads_file();
			File origin_file = new File(delete_path);
			
			origin_file.delete();
		}
		
		int[] delete_breed = service.DeleteBreed(breed_id);
		int[] delete_standard = service.DeleteStandard(breed_id);
		int delete_file = service.DeleteFile(breed_id);
		
		if(!uploads.isEmpty())
		{
			int delete_uploads = service.DeleteUploads(uploads);
		}

		return 1;
	}
	
	// 표시항목 설정
	@RequestMapping("insertBreedDisplay")
	public ModelAndView InsertBreedDisplay(ModelAndView mv, Authentication auth, @RequestParam("breed_name") String breed_name, @RequestParam(required = false, value = "detail_id") int[] detail_id)
	{
		User user = (User)auth.getPrincipal();
		
		int delete = service.DeleteDisplay(user.getUser_id());
		int insert = service.InsertDisplay(user.getUser_id(), breed_name, detail_id);
		
		mv.setViewName("redirect:/breed");
		
		return mv;
	}
	
	// 첨부 파일 조회
	@ResponseBody
	@RequestMapping("selectBreedFile")
	public Map<String, Object> SelectBreedFile(@RequestParam("breed_id") int breed_id)
	{
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		
		Breed breed = service.SelectBreedDetail(breed_id);
		List<BreedFile> breed_file = service.SelectBreedFile(breed_id);
		
		result.put("breed", breed);
		result.put("breed_file", breed_file);
		
		return result;
	}
	
	// 첨부파일 등록
	@RequestMapping("insertBreedFile")
	public ModelAndView InsertBreedFile(ModelAndView mv, @ModelAttribute BreedFile breed_file, @RequestParam("file") MultipartFile file) throws IOException
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
       	
       	int insert_file = service.InsertBreedFile(breed_file);
       	
		Uploads upload = new Uploads();
		upload.setUploads_file(file_name);
		upload.setUploads_origin_file(origin_file_name);
		upload.setBreed_file_id(breed_file.getBreed_file_id());
		
		int insert_upload = service.InsertBreedUpload(upload);
		
		mv.setViewName("redirect:/breed");
		
		return mv;
	}
	
	// 첨부파일 수정
	@RequestMapping("updateBreedFile")
	public ModelAndView UpdateBreedFile(ModelAndView mv, @ModelAttribute BreedFile breed_file, @RequestParam("file") MultipartFile file) throws IOException
	{
		if(file.isEmpty())
		{
			int update_file = service.UpdateBreedFile(breed_file);
		}
		else
		{
			String delete_path = "upload/" + breed_file.getUploads_file();
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
				upload.setBreed_file_id(breed_file.getBreed_file_id());
				
				int update_upload = service.UpdateBreedUpload(upload);
			}
		}
		
		mv.setViewName("redirect:/breed");
		
		return mv;
	}
}