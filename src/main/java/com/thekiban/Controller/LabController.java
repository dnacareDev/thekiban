package com.thekiban.Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

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

import com.thekiban.Entity.AnalysisFile;
import com.thekiban.Entity.ChromosomeViewer;
import com.thekiban.Entity.MABCDesign;
import com.thekiban.Entity.MABCSample;
import com.thekiban.Entity.MarkerInformation;
import com.thekiban.Entity.QTLInformation;
import com.thekiban.Entity.QTL_trait;
import com.thekiban.Entity.Uploads;
import com.thekiban.Entity.User;
import com.thekiban.RModule.RunEtcR;
import com.thekiban.RModule.RunGetLength;
import com.thekiban.Service.LabService;

import DigitalTools.RunMarkerDB;
import DigitalTools.RunQtlDB;

@Controller
public class LabController
{
	@Autowired
	private LabService service;
	
	@Autowired
	private FileController fileController;
	
	@RequestMapping("/mabc")
	public ModelAndView getDataManage(ModelAndView mv, Authentication auth)
	{
		User user = (User)auth.getPrincipal();
		
		int analysis_type = 0;
		
		AnalysisFile analysis = service.SelectAnalysisFile(user.getUser_id(), analysis_type);
		
		if(analysis != null)
		{
			analysis.setAnalysis_file("/common/r/result/" + analysis.getAnalysis_file());
		}
		
		mv.addObject("analysis", analysis);
		
		mv.setViewName("lab/mabc");

		return mv;
	}
	
	@ResponseBody
	@RequestMapping("/insertMarker")
	public String InsertMarker(Authentication auth, @RequestParam("file") MultipartFile file) throws IOException
	{
		User user = (User)auth.getPrincipal();
		
		Date date = new Date();
		String date_name = (1900 + date.getYear()) + "" + (date.getMonth() + 1) + "" + date.getDate() + "" + date.getHours() + "" + date.getMinutes() + "" + date.getSeconds();
		
		String[] file_extension = file.getOriginalFilename().split("\\.");
		String file_name = date_name + "." + file_extension[1];
		
		String path = "/data/apache-tomcat-9.0.8/webapps/ROOT/common/r/result/" + date_name;
		
		File filePath = new File(path);
		
		if (!filePath.exists())
			filePath.mkdirs();
		
       	Path fileLocation = Paths.get(path).toAbsolutePath().normalize();
       	Path targetLocation = fileLocation.resolve(file_name);
		
       	Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			
       	AnalysisFile analysis = new AnalysisFile();
       	analysis.setUser_id(user.getUser_id());
       	analysis.setAnalysis_type(0);
       	analysis.setAnalysis_file(date_name + "/lengh.len");
       	analysis.setAnalysis_origin_file(file.getOriginalFilename());
       	
       	int insert = service.InsertAnalysisFile(analysis);
       	
		RunGetLength rungetlength = new RunGetLength();
		
		rungetlength.MakeRunGetLength(date_name, file_name);
		
		String result = "/common/r/result/" + date_name + "/lengh.len";
		
       	return result;
	}
	
	// analysis tool 페이지
	@RequestMapping("/matrix")
	public ModelAndView Analysis(ModelAndView mv, Authentication auth)
	{
		User user = (User)auth.getPrincipal();
		
		int analysis_type = 1;
		
		AnalysisFile analysis = service.SelectAnalysisFile(user.getUser_id(), analysis_type);
		
		if(analysis != null)
		{
			String[] extension = analysis.getAnalysis_file().split("_");
			
			mv.addObject("analysis", analysis);
			mv.addObject("path", extension[0]);
		}
		
		mv.setViewName("lab/matrix");
		
		return mv;
	}
	
	// analysis tool 분석 실행
	@RequestMapping("/insertMatrix")
	public ModelAndView InsertMatrix(ModelAndView mv, Authentication auth, @RequestParam("file") MultipartFile file) throws IOException
	{
		User user = (User)auth.getPrincipal();
		
		Date date = new Date();
		
        String date_name = (1900 + date.getYear()) + "" + (date.getMonth() + 1) + "" + date.getDate() + "" + date.getHours() + "" + date.getMinutes() + "" + date.getSeconds();
        String origin_name = file.getOriginalFilename();
        String file_name = date_name + "_" + origin_name;
        
        String path = "/data/apache-tomcat-9.0.8/webapps/ROOT/common/r/result/" + date_name;
        
        File filePath = new File(path);
        
        if (!filePath.exists())
        	filePath.mkdirs();
        
       	Path fileLocation = Paths.get(path).toAbsolutePath().normalize();
       	Path targetLocation = fileLocation.resolve(file_name);
		
       	Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
       	
       	AnalysisFile analysis = new AnalysisFile();
       	analysis.setUser_id(user.getUser_id());
       	analysis.setAnalysis_type(1);
       	analysis.setAnalysis_file(file_name);
       	analysis.setAnalysis_origin_file(origin_name);
       	
       	int result = service.InsertAnalysisFile(analysis);
       	
       	RunEtcR runetcr = new RunEtcR();
       	runetcr.MakeRunEtcR(date_name, file_name);
		
		mv.setViewName("redirect:/matrix");
		
		return mv;
	}

	
	//-----------------------------------marker_information.html 기능---------------------------------------------------//
	// 2022-05-09 | marker_information.html 출력
	@RequestMapping("/marker_information")
	  public ModelAndView MarkerInformation(ModelAndView mv) {
	    mv.setViewName("lab/marker_information");

	    return mv;
	  }
	
	// 2022-05-09 | marker_information 테이블 불러오기. grid출력
	@ResponseBody
	@RequestMapping("search_marker_information")
	public Map<String, Object> SearchMarkerInfomation() {

		Map<String, Object> result = new LinkedHashMap<String, Object>();

		List<MarkerInformation> marker_information = service.SearchMarkerInformation();
		List<Uploads> search_uploads = service.SearchUploads();
		
	    result.put("marker_information", marker_information);
	    result.put("search_uploads",search_uploads);
		
	    return result;
	}

	
	// 2022-05-18 | 다른방향. form action submit 형식으로 작성
		@ResponseBody
		@RequestMapping("insertMarkerFile") 
		public ModelAndView InsertMarkerFile(ModelAndView mv, @ModelAttribute MarkerInformation marker_information, 
				   										   	@RequestParam("file") MultipartFile file) throws IOException {
															// 아직 html쪽에 file 변수 없음
			
			// 현재 날짜 구하기 및 포맷,문자열화
			LocalDateTime now = LocalDateTime.now();
			String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
			
			// html에서 제출된 name들이 정상출력됐는지 여부
			System.out.println(marker_information);
			
			String[] extension = file.getOriginalFilename().split("\\.");

		    String file_name = fileController.ChangeFileName(extension[1]);
		    String origin_file_name = file.getOriginalFilename();

//		    String path = "src/main/webapp/upload";
		    String path = "/data/apache-tomcat-9.0.8/webapps/ROOT/upload/";

		    File filePath = new File(path);

		    if (!filePath.exists())
		      filePath.mkdirs();
		    
		    Path fileLocation = Paths.get(path).toAbsolutePath().normalize();
		    Path targetLocation = fileLocation.resolve(file_name);
		    
		    // 서버에 첨부파일 업로드
		    Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
		    // DB에 파일명 및 파일경로 지정
		    int insert_file = service.InsertMarkerInformation(marker_information);
		    
		    Uploads upload = new Uploads();
		    upload.setUploads_file(file_name);
		    upload.setUploads_origin_file(origin_file_name);
		    upload.setMarker_num(marker_information.getMarker_num());
		    
		    //System.out.println();
		    System.out.println("marker_num" + marker_information.getMarker_num());
		    
		    int insert_upload = service.InsertMarkerUpload(upload);
		    
		  //파일 병합
		    //RunMarkerDB runMarkerDB = new RunMarkerDB();
			//runMarkerDB.MakeRunMarkerDB("1111");
			StringTokenizer st = null;
			String str1 = null;
			String str2 = null;
			String str3 = null;
			
			ChromosomeViewer chromosomeViewer = null;
			
			//csv 읽기
			File csvFile = new File("C:\\read_test.csv");
			if(csvFile.exists()) { 
				BufferedReader inFile = new BufferedReader(new FileReader(csvFile)); 
				inFile.readLine();	// 한줄을 미리 읽어서 버린다
				String sLine = null; 
				while( (sLine = inFile.readLine()) != null ) {
					System.out.println("sLine"); //읽어들인 문자열을 출력 합니다. } }
					st = new StringTokenizer(sLine, ",");
					str1 = st.nextToken();
					str2 = st.nextToken();
					str3 = st.nextToken();
					System.out.print(str1+ " ");
					System.out.print(str2+ " ");
					System.out.println(str3);
					
					chromosomeViewer = new ChromosomeViewer();
					
					chromosomeViewer.setStr1(str1);
					chromosomeViewer.setStr2(str2);
					chromosomeViewer.setStr3(str3);
					chromosomeViewer.setSel(0);
					chromosomeViewer.setJobid("1111");
					
					System.out.println(chromosomeViewer);
					
					int result = service.InsertChromosomeViewer(chromosomeViewer);
				}
			}
		    
		    
		    mv.setViewName("redirect:/marker_information");

		    return mv;
		}
		
		// QTL 맵 분석
		@ResponseBody
		@RequestMapping("MarkerMap") 
		public String MarkerMap(@RequestParam("total_file_name[]") String[] total_file_name) throws IOException {		
															
			
			// 현재 날짜 구하기 및 포맷,문자열화
			LocalDateTime now = LocalDateTime.now();
			String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

			String path = null;
			String newPath = null;
			
			
			for(int i=0 ; i<total_file_name.length ; i++) {
				System.out.println(total_file_name[i]);
				
				
				path = "/data/apache-tomcat-9.0.8/webapps/ROOT/upload/" + total_file_name[i];
				newPath = "/data/apache-tomcat-9.0.8/webapps/ROOT/common/r/markerDB/" + formatedNow + "/";
				
				File file = new File(path);
				File newFile = new File(newPath + file.getName());
				
				if (!newFile.exists())
					newFile.mkdirs();
				
				try 
		        {
		        Files.copy(file.toPath(), newFile.toPath(),StandardCopyOption.REPLACE_EXISTING);  
		          // Static Methods To Copy Copy source path to destination path
		        } catch(Exception e) {
		             System.out.println(e);  // printing in case of error.
		        }
				// 여기까지 체크한 엑셀파일들을 서버에 업로드
				
//				System.out.println("aaa");
				 //파일 병합
			    RunMarkerDB runMarkerDB = new RunMarkerDB();			// 받은 java파일로 한거니까 marker로 수정해야함
//			    System.out.println("bbb");
				runMarkerDB.MakeRunMarkerDB(formatedNow);
				StringTokenizer st = null;
				String str1 = null;
				String str2 = null;
				String str3 = null;
				
//				System.out.println("ccc");
				
				ChromosomeViewer chromosomeViewer = null;
				
				
				System.out.println("check path : " + newPath + formatedNow + ".csv");
				//csv 읽기
				File csvFile = new File(newPath + formatedNow + ".csv");
				if(csvFile.exists()) { 
					BufferedReader inFile = new BufferedReader(new FileReader(csvFile)); 
					inFile.readLine();	// 한줄을 미리 읽어서 버린다
					String sLine = null; 
					while( (sLine = inFile.readLine()) != null ) {
//						System.out.println("sLine"); 					//읽어들인 문자열을 출력 합니다. 
						st = new StringTokenizer(sLine, ",");
						str1 = st.nextToken().replaceAll("\"", "");
						str2 = st.nextToken().replaceAll("\"", "");
						str3 = st.nextToken().replaceAll("\"", "");
						System.out.print(str1+ " ");
						System.out.print(str2+ " ");
						System.out.println(str3);
						
						chromosomeViewer = new ChromosomeViewer();
						
						chromosomeViewer.setStr1(str1);
						chromosomeViewer.setStr2(str2);
						chromosomeViewer.setStr3(str3);
						chromosomeViewer.setSel(0);
						chromosomeViewer.setJobid(formatedNow);
						
//						System.out.println(chromosomeViewer);
						
						int result = service.InsertChromosomeViewer(chromosomeViewer);
					}
				}
				
				
			}
				

			
		
			return formatedNow;

		}
		
		
	
	// 2022-05-11 | marker_id or marker_name cell 클릭시 marker_id를 기준으로 DB검색 
	@ResponseBody
	@RequestMapping("search_marker_by_id")
	public Map<String, Object> SearchMarkerById(@RequestParam("marker_id") String marker_id) {
	    Map<String, Object> result = new LinkedHashMap<String, Object>();

	    List<MarkerInformation> marker_information = service.SearchMarkerById(marker_id);

	    result.put("marker_information", marker_information);

	    return result;
	}
	
	
	// 2022-05-12 | 체크된 행의 maker_id를 기준으로 데이터 삭제
	@ResponseBody
	@RequestMapping("delete_marker_information")
	public int DeleteMarkerInformation(@RequestParam("total_marker_num[]") int[] total_marker_num) {
		// System.out.println(); 
		
		service.DeleteMarkerInformation(total_marker_num);
		
		return 1;
	}
	//-----------------------------------------------------------------------------------------------------------------//
	
	
	//---------------------------------------qtl_information.html 기능--------------------------------------------------//
	// 2022-05-11 | qtl_information.html 출력
	@RequestMapping("/qtl_information")
	public ModelAndView QTLInformation(ModelAndView mv) {
	  mv.setViewName("lab/qtl_information");

	  return mv;
	}
	
	// 2022-05-11 | qtl_information 테이블 불러오기. qtl grid 출력
	@ResponseBody
	@RequestMapping("search_qtl_information")
	public Map<String, Object> SearchQTLInfomation() {
	    Map<String, Object> result = new LinkedHashMap<String, Object>();
	    
	    List<QTLInformation> qtl_information = service.SearchQTLInformation();
	    List<Uploads> search_uploads = service.SearchUploads();

	    result.put("qtl_information", qtl_information);
	    result.put("search_uploads",search_uploads);

	    System.out.println("qtl_information" + qtl_information);
	    
	    return result;
	}

	
	// 2022-05-18 | 다른방향. form action submit 형식으로 작성
	@ResponseBody
	@RequestMapping("insertQTLFile") 
	public ModelAndView InsertQTLFile(ModelAndView mv, @ModelAttribute QTLInformation qtl_information, 
			   										   @RequestParam("file") MultipartFile file) throws IOException {		
														// 아직 html쪽에 file 변수 없음
		
		
		// 현재 날짜 구하기 및 포맷,문자열화
		LocalDateTime now = LocalDateTime.now();
		String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

		
		// html에서 제출된 name들이 정상출력됐는지 여부
		System.out.println(qtl_information);
		
		String[] extension = file.getOriginalFilename().split("\\.");

	    String file_name = fileController.ChangeFileName(extension[1]);
	    String origin_file_name = file.getOriginalFilename();

//	    String path = "src/main/webapp/upload";
	    
	    // 기존 업로드 폴더에 업로드날짜를 이름으로하는 새로운 폴더(경로)를 추가
	    //String path = "/data/apache-tomcat-9.0.8/webapps/ROOT/common/r/qtlDB/" + formatedNow;
	    String path = "/data/apache-tomcat-9.0.8/webapps/ROOT/upload/";

	    File filePath = new File(path);

	    if (!filePath.exists())
	      filePath.mkdirs();
	    
	    Path fileLocation = Paths.get(path).toAbsolutePath().normalize();
	    Path targetLocation = fileLocation.resolve(file_name);
	    
	    // 서버에 첨부파일 업로드
	    Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
	    // DB에 파일명 및 파일경로 지정
	    int insert_file = service.InsertQTLInformation(qtl_information);
	    
	    Uploads upload = new Uploads();
	    upload.setUploads_file(file_name);
	    upload.setUploads_origin_file(origin_file_name);
	    upload.setQtl_num(qtl_information.getQtl_num());
	    
	    //System.out.println();
	    System.out.println("qtl_num" + qtl_information.getQtl_num());
	    
	    int insert_upload = service.InsertQTLUpload(upload);
	    
	    //파일 병합
	    //RunQtlDB runQtlDB = new RunQtlDB();
		//runQtlDB.MakeRunQtlDB("1111");
		StringTokenizer st = null;
		String str1 = null;
		String str2 = null;
		String str3 = null;
		
		ChromosomeViewer chromosomeViewer = null;
		
		//csv 읽기
		File csvFile = new File("C:\\read_test.csv");
		if(csvFile.exists()) { 
			BufferedReader inFile = new BufferedReader(new FileReader(csvFile)); 
			inFile.readLine();	// 한줄을 미리 읽어서 버린다
			String sLine = null; 
			while( (sLine = inFile.readLine()) != null ) {
				System.out.println("sLine"); //읽어들인 문자열을 출력 합니다. } }
				st = new StringTokenizer(sLine, ",");
				str1 = st.nextToken();
				str2 = st.nextToken();
				str3 = st.nextToken();
				System.out.print(str1+ " ");
				System.out.print(str2+ " ");
				System.out.println(str3);
				
				chromosomeViewer = new ChromosomeViewer();
				
				chromosomeViewer.setStr1(str1);
				chromosomeViewer.setStr2(str2);
				chromosomeViewer.setStr3(str3);
				chromosomeViewer.setSel(0);
				chromosomeViewer.setJobid("1111");
				
				System.out.println(chromosomeViewer);
				
				int result = service.InsertChromosomeViewer(chromosomeViewer);
			}
		}
	    
	    mv.setViewName("redirect:/qtl_information");

	    return mv;
	}
	
	// 2022-05-12 | qtl_id or qtl_name cell 클릭시 qtl_num를 기준으로 DB검색 
	@ResponseBody
	@RequestMapping("search_qtl_by_id")
	public Map<String, Object> SearchQTLById(@RequestParam("qtl_id") String qtl_id) {
	    Map<String, Object> result = new LinkedHashMap<String, Object>();

	    List<QTLInformation> qtl_information = service.SearchQTLById(qtl_id);

	    result.put("qtl_information", qtl_information);

	    return result;
	}
	
	
	
	// 2022-05-12 | 체크된 행의 qtl_num을 기준으로 데이터 삭제
	@ResponseBody
	@RequestMapping("delete_qtl_information")
	public int DeleteQTLInformation(@RequestParam("total_qtl_num[]") int[] total_qtl_num) {
		
		service.DeleteQTLInformation(total_qtl_num);
		
		return 1;
	}
	
	
	// QTL 맵 분석
	@ResponseBody
	@RequestMapping("QTLMap") 
	public String QTLMap(@RequestParam("total_file_name[]") String[] total_file_name) throws IOException {		
														
		
		// 현재 날짜 구하기 및 포맷,문자열화
		LocalDateTime now = LocalDateTime.now();
		String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

		String path = null;
		String newPath = null;
		
		
		for(int i=0 ; i<total_file_name.length ; i++) {
			System.out.println(total_file_name[i]);
			
			
			path = "/data/apache-tomcat-9.0.8/webapps/ROOT/upload/" + total_file_name[i];
			newPath = "/data/apache-tomcat-9.0.8/webapps/ROOT/common/r/qtlDB/" + formatedNow + "/";
			
			File file = new File(path);
			File newFile = new File(newPath + file.getName());
			
			if (!newFile.exists())
				newFile.mkdirs();
			
			try 
	        {
	        Files.copy(file.toPath(), newFile.toPath(),StandardCopyOption.REPLACE_EXISTING);  
	          // Static Methods To Copy Copy source path to destination path
	        } catch(Exception e) {
	             System.out.println(e);  // printing in case of error.
	        }
			// 여기까지 체크한 엑셀파일들을 서버에 업로드
			
//			System.out.println("aaa");
			 //파일 병합
		    RunQtlDB runQtlDB = new RunQtlDB();
//		    System.out.println("bbb");
			runQtlDB.MakeRunQtlDB(formatedNow);
			StringTokenizer st = null;
			String str1 = null;
			String str2 = null;
			String str3 = null;
			
//			System.out.println("ccc");
			
			ChromosomeViewer chromosomeViewer = null;
			
			
			System.out.println("check path : " + newPath + formatedNow + ".csv");
			//csv 읽기
			File csvFile = new File(newPath + formatedNow + ".csv");
			if(csvFile.exists()) { 
				BufferedReader inFile = new BufferedReader(new FileReader(csvFile)); 
				inFile.readLine();	// 한줄을 미리 읽어서 버린다
				String sLine = null; 
				while( (sLine = inFile.readLine()) != null ) {
					System.out.println("sLine"); //읽어들인 문자열을 출력 합니다. } }
					st = new StringTokenizer(sLine, ",");
					str1 = st.nextToken().replaceAll("\"", "");
					str2 = st.nextToken().replaceAll("\"", "");
					str3 = st.nextToken().replaceAll("\"", "");
					System.out.print(str1+ " ");
					System.out.print(str2+ " ");
					System.out.println(str3);
					
					chromosomeViewer = new ChromosomeViewer();
					
					chromosomeViewer.setStr1(str1);
					chromosomeViewer.setStr2(str2);
					chromosomeViewer.setStr3(str3);
					chromosomeViewer.setSel(0);
					chromosomeViewer.setJobid(formatedNow);
					
					System.out.println(chromosomeViewer);
					
					int result = service.InsertChromosomeViewer(chromosomeViewer);
				}
			}
			
			
		}
			

		
	
		return formatedNow;

	}


	
	
	//-----------------------------------------------------------------------------------------------------------------//
	
	//---------------------------------------mabc_analysis.html 기능----------------------------------------------------//
	// 2022-05-12 | mabc_information.html 웹페이지 
	@RequestMapping("/genotype_data")
	public ModelAndView MABCAnalysis(ModelAndView mv) {
	  mv.setViewName("lab/genotype_data");

	  return mv;
	}
	
	// 2022-05-13 | mabc_analysis 테이블 불러오기, grid 출력
	@ResponseBody
	@RequestMapping("search_mabc_sample")
	public Map<String, Object> SearchMABCSample() {
	    Map<String, Object> result = new LinkedHashMap<String, Object>();

	    List<MABCSample> search_mabc_sample = service.SearchMABCSample();
	    List<Uploads> search_uploads = service.SearchUploads();
	    
	    result.put("search_mabc_sample", search_mabc_sample);
	    result.put("search_uploads",search_uploads);

	    return result;
	}
	
	
	// mabc_sample 등록
	// 
		@ResponseBody
		@RequestMapping("insert_mabc_sample")
		public int InsertQTLInformation( @ModelAttribute MABCSample mabc_sample, 
		  								 @RequestParam("input_mabc_data") String input_mabc_data) {
			
			JSONArray arr = new JSONArray(input_mabc_data);
			
		    JSONObject obj = arr.getJSONObject(0);
		    
		    
	    	mabc_sample.setCrop((String) obj.get("crop"));

		    int result = service.InsertMABCSample(mabc_sample);
		    
		    return result;
		}
	
	// 2022-05-13 | 체크된 행의 sys_id를 기준으로 데이터 삭제
		// search 수정중에 에러나서 지웠으니까 insert할때 다시 수정해야 함
	@ResponseBody
	@RequestMapping("delete_mabc_sample")
	public int DeleteMABCSample(@RequestParam("total_mabc_id[]") String[] total_mabc_id) {
		
		service.DeleteMABCSample(total_mabc_id);
		
		return 1;
	}
	
	// 2022-05-17 | 첨부파일(excel) 등록
	@RequestMapping("insertMABCFile")
	public ModelAndView InsertMABCFile(ModelAndView mv, @ModelAttribute MABCSample mabc_sample, 
									   @RequestParam("file") MultipartFile file) throws IOException {
		
		// mabc_sample에서 변수 받은것 확인 
		// System.out.println(mabc_sample);
		
		String[] extension = file.getOriginalFilename().split("\\.");

	    String file_name = fileController.ChangeFileName(extension[1]);
	    String origin_file_name = file.getOriginalFilename();

//	    String path = "src/main/webapp/upload";
	    String path = "/data/apache-tomcat-9.0.8/webapps/ROOT/upload/";

	    
	    File filePath = new File(path);

	    if (!filePath.exists())
	      filePath.mkdirs();
	    
	    Path fileLocation = Paths.get(path).toAbsolutePath().normalize();
	    Path targetLocation = fileLocation.resolve(file_name);
	    

	    Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
	    
	    // DB에 파일명 및 파일경로 지정
	    int insert_file = service.InsertMABCSample(mabc_sample);

	    Uploads upload = new Uploads();
	    upload.setUploads_file(file_name);
	    upload.setUploads_origin_file(origin_file_name);
	    upload.setMabc_id(mabc_sample.getMabc_id());

	    int insert_upload = service.InsertMABCUpload(upload);

	    mv.setViewName("redirect:/genotype_data");

	    return mv;
	  }
	
	//-------------------------------------------------------
	// runQtl 테스트
//	@RequestMapping("readcsv")
//	public void readCSV() {
//		
//		CSVReader csvReader = new CSVReader();
//        System.out.println(csvReader.readCSV());
//		
//        //return csvReader.readCSV();
//        
//	}
	
	@ResponseBody
	@RequestMapping("MABC_DB") 
	public String MABC_DB(@RequestParam("total_file_name[]") String[] total_file_name,
						@RequestParam("total_file_origin_name[]") String[] total_file_origin_name) {		
														
		String str = "";
		
		System.out.println(total_file_name);
		System.out.println(total_file_origin_name);
		
		for(int i=0 ; i<total_file_name.length ; i++) {
			if(i != total_file_name.length -1 ) {
				str += total_file_name[i] + ","+ total_file_origin_name[i] + ",";
			}
			else {
				str += total_file_name[i] + ","+ total_file_origin_name[i];
				
			}
		}
		
		System.out.println(str);
		
		
		return str;
	}
	
	// -------------------------------------------------------
	
	@RequestMapping("/specificmaker")
	  public ModelAndView SpecificMaker(ModelAndView mv) {
	    mv.setViewName("lab/specificmaker");

	    return mv;
	  }
	
	@RequestMapping("MABCDesign")
	  public ModelAndView MABCDesign(ModelAndView mv) {
	    mv.setViewName("lab/MABCDesign");

	    return mv;
	  }
	
	// ---------------------------------------------------------------
	
	
	@ResponseBody
	@RequestMapping("search_MABC_design")
	public Map<String, Object> Search_MABCDesign() {

		Map<String, Object> result = new LinkedHashMap<String, Object>();

		
		List<MABCDesign> MABCDesign = service.Search_MABCDesign();
		//List<Uploads> search_uploads = service.SearchUploads();

	    result.put("MABCDesign", MABCDesign);
	    //result.put("search_uploads",search_uploads);
		
//		System.out.println("result" + result);
	    
	    return result;
	} 
	
	
	
	
	
	
	@ResponseBody
	@RequestMapping("insertMABCDesign") 
	public String insertMABCDesign( @ModelAttribute MABCDesign mabc_design,
//														@RequestParam("htwo") String htwo,
//														@RequestParam("nbchromosome") String nbchromosome,
//														@RequestParam("numberofrepeats") String numberofrepeats,
//														@RequestParam("gtrainingpop") String gtrainingpop,
//														@RequestParam("geneticlengthf") String geneticlengthf,
//														@RequestParam("geneticlengthm") String geneticlengthm,
//														@RequestParam("nbqtls") String nbqtls,
//														@RequestParam("nbsnps") String nbsnps,
//														@RequestParam("nbkeepqtl") String nbkeepqtl,
//														@RequestParam("nugammaf") String nugammaf,
//														@RequestParam("pf") String pf,
//														@RequestParam("nugammam") String nugammam,
//														@RequestParam("pm") String pm,
														@RequestParam("crop") String crop,
														@RequestParam("memo") String memo,
														
														@RequestParam("file1") MultipartFile file1,
														@RequestParam("file2") MultipartFile file2,
														@RequestParam("file3") MultipartFile file3,
														@RequestParam("file4") MultipartFile file4,
														@RequestParam("file5") MultipartFile file5,
														@RequestParam("file6") MultipartFile file6 ) throws IOException {  
																		
														// 아직 html쪽에 file 변수 없음
		
		 
		// 현재 날짜 구하기 및 포맷,문자열화
		LocalDateTime now = LocalDateTime.now();
		String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

		// html에서 제출된 name들이 정상출력됐는지 여부
		
//		String[] extension = file1.getOriginalFilename().split("\\.");
//
//	    String file_name = fileController.ChangeFileName(extension[1]);
//	    String origin_file_name = file1.getOriginalFilename();

//	    String path = "src/main/webapp/upload";
	    
	    // 기존 업로드 폴더에 업로드날짜를 이름으로하는 새로운 폴더(경로)를 추가
	    String path = "/data/apache-tomcat-9.0.8/webapps/ROOT/common/r/MABC/" + formatedNow + "/";
	    //String path = "/data/apache-tomcat-9.0.8/webapps/ROOT/upload/";

	    Path target1 = Paths.get("/data/apache-tomcat-9.0.8/webapps/ROOT/common/r/MABC/" + formatedNow + "/positionQTLsF.csv");
	    Path target2 = Paths.get("/data/apache-tomcat-9.0.8/webapps/ROOT/common/r/MABC/" + formatedNow + "/positionQTLsM.csv");
	    Path target3 = Paths.get("/data/apache-tomcat-9.0.8/webapps/ROOT/common/r/MABC/" + formatedNow + "/positionSNPsF.csv");
	    Path target4 = Paths.get("/data/apache-tomcat-9.0.8/webapps/ROOT/common/r/MABC/" + formatedNow + "/positionSNPsM.csv");
	    Path target5 = Paths.get("/data/apache-tomcat-9.0.8/webapps/ROOT/common/r/MABC/" + formatedNow + "/weightMrkrs_Cold.csv");
	    Path target6 = Paths.get("/data/apache-tomcat-9.0.8/webapps/ROOT/common/r/MABC/" + formatedNow + "/GenerationParameters.csv");
	    
	    File filePath = new File(path);

	    if (!filePath.exists())
	      filePath.mkdirs();
	    
	    String time = formatedNow;
	    
//	    Path fileLocation = Paths.get(path).toAbsolutePath().normalize();
//	    Path targetLocation = fileLocation.resolve(file_name);
	    
	    // 서버에 첨부파일 업로드
	    
	    Files.copy(file1.getInputStream(), target1, StandardCopyOption.REPLACE_EXISTING);
	    Files.copy(file2.getInputStream(), target2, StandardCopyOption.REPLACE_EXISTING);
	    Files.copy(file3.getInputStream(), target3, StandardCopyOption.REPLACE_EXISTING);
	    Files.copy(file4.getInputStream(), target4, StandardCopyOption.REPLACE_EXISTING);
	    Files.copy(file5.getInputStream(), target5, StandardCopyOption.REPLACE_EXISTING);
	    Files.copy(file6.getInputStream(), target6, StandardCopyOption.REPLACE_EXISTING);
	    // DB에 파일명 및 파일경로 지정
	    
	    String jobid = formatedNow;
	    mabc_design.setCrop(crop);
	    mabc_design.setJobid(jobid);
	    service.InsertMABCDesign(mabc_design);
	    
//	    mv.setViewName("lab/MABC_parameter");
//	    mv.addObject("NbChromosome", NbChromosome);
//	    mv.addObject("NumberOfRepeats", NumberOfRepeats);
//	    mv.addObject("gTrainingPop", gTrainingPop);
//	    mv.addObject("geneticLengthF", geneticLengthF);
//	    mv.addObject("geneticLengthM", geneticLengthM);
//	    mv.addObject("NbQTLs", NbQTLs);
//	    mv.addObject("NbSNPs", NbSNPs);
//	    mv.addObject("NbKeepQTL", NbKeepQTL);
//	    mv.addObject("nuGammaF", nuGammaF);
//	    mv.addObject("nuGammaM", nuGammaM);
//	    mv.addObject("pM", pM);
//	    mv.addObject("jobid", formatedNow);
	    
//	    mv.setViewName("lab/MABCDesign");

	    System.out.println(formatedNow);
	    
	    return formatedNow;
	    
	}
	
	@RequestMapping("/MABCDesign_result")
	public ModelAndView MABCDesign_result(ModelAndView mv) throws IOException {
	  
		mv.setViewName("lab/MABCDesign_result");

	  return mv;
	}

	@ResponseBody
	@RequestMapping("/MABC_design_result_html_and_pdf")
	public String MABCDesign_result_html_and_pdf(@RequestParam("jobid") String jobid) throws IOException {
		
		
		
		System.out.println("jobid ; "+jobid);
		StringTokenizer st = null;
		String column1;
		String column2;
		String column3;
		String column4;
		String column5;
		String column6;
		String column7;
		String column8;
		String column9;
		String column10;
		String column11;
		
		String qtl_trait_html = null;
		
		
		QTL_trait qtl_trait = null;
		
//		File csvFile_qtl_result = new File("/common/r/MABC/" + jobid + "/result/"+ jobid +".csv");
		File csvFile_qtl_result = new File("/common/r/MABC/20220521213957.csv");
//		File csvFile_qtl_result = new File("/common/r/MABC/20220521213957/results/20220521213957.csv"); 
//		File csvFile_qtl_result = new File("/data/apache-tomcat-9.0.8/webapps/ROOT/common/r/MABC/20220521213957/results/20220521213957.csv"); 
		if(csvFile_qtl_result.exists()) { 
			BufferedReader inFile = new BufferedReader(new FileReader(csvFile_qtl_result)); 
			inFile.readLine();	// 한줄을 미리 읽어서 버린다
			
			qtl_trait_html = "<table><tr><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td><td>8</td><td>9</td><td>10</td><td>11</td></tr>";
			
			String sLine = null; 
			while( (sLine = inFile.readLine()) != null ) {
				
				qtl_trait_html += "<tr>";
				
				st = new StringTokenizer(sLine, ",");
				column1 = st.nextToken();
				qtl_trait_html += "<td>" + column1 + "</td>" ;
				column2 = st.nextToken();
				qtl_trait_html += "<td>" + column2 + "</td>" ;
				column3 = st.nextToken();
				qtl_trait_html += "<td>" + column3 + "</td>" ;
				column4 = st.nextToken();
				qtl_trait_html += "<td>" + column4 + "</td>" ;
				column5 = st.nextToken();
				qtl_trait_html += "<td>" + column5 + "</td>" ;
				column6 = st.nextToken();
				qtl_trait_html += "<td>" + column6 + "</td>" ;
				column7 = st.nextToken();
				qtl_trait_html += "<td>" + column7 + "</td>" ;
				column8 = st.nextToken();
				qtl_trait_html += "<td>" + column8 + "</td>" ;
				column9 = st.nextToken();
				qtl_trait_html += "<td>" + column9 + "</td>" ;
				column10 = st.nextToken();
				qtl_trait_html += "<td>" + column10 + "</td>" ;
				column11 = st.nextToken();
				qtl_trait_html += "<td>" + column11 + "</td>" ;
	
				qtl_trait_html += "</tr>";


				
			}
			
			qtl_trait_html += "</table>";
			

		}
		System.out.println("</table>");
		return qtl_trait_html;
	}
	
}