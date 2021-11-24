package com.thekiban.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thekiban.Entity.*;
import com.thekiban.Service.DataListService;
import com.thekiban.Service.SampleService;
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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

// 시교자원
@Controller
public class SampleController {

  @Autowired
  private SampleService service;

  @Autowired
  private DataListService d_service;

  @Autowired
  private FileController fileController;

  // 시교자원 관리 페이지
  @RequestMapping("sample")
  public ModelAndView SampleList(ModelAndView mv) {

    mv.setViewName("genome/sample");

    return mv;
  }

  // 시교자원 입력
  @ResponseBody
  @RequestMapping("insertSample")
  public int InsertSample(@ModelAttribute Sample sample, @RequestParam("input_data") String input_data) {
    JSONArray arr = new JSONArray(input_data);

    JSONObject obj = arr.getJSONObject(0);

    if (!obj.isNull("sample_code")) {
      sample.setSample_code((String) obj.get("sample_code"));
    } else {
      sample.setSample_code("");
    }

    if (!obj.isNull("sample_name")) {
      sample.setSample_name((String) obj.get("sample_name"));
    } else {
      sample.setSample_name("");
    }

    if (!obj.isNull("sample_country")) {
      sample.setSample_country((String) obj.get("sample_country"));
    } else {
      sample.setSample_country("");
    }

    if (!obj.isNull("sample_type")) {
      sample.setSample_type((String) obj.get("sample_type"));
    } else {
      sample.setSample_type("");
    }

    if (!obj.isNull("sample_mate")) {
      sample.setSample_mate((String) obj.get("sample_mate"));
    } else {
      sample.setSample_mate("");
    }

    if (!obj.isNull("sample_seed")) {
      sample.setSample_seed((String) obj.get("sample_seed"));
    } else {
      sample.setSample_seed("");
    }

    if (!obj.isNull("sample_amount")) {
      sample.setSample_amount(Double.parseDouble((String) obj.get("sample_amount")));
    } else {
      sample.setSample_amount(0);
    }

    if (!obj.isNull("sample_sprout")) {
      String sample_sprout = (String) obj.get("sample_sprout");
      sample_sprout = sample_sprout.trim();
      sample.setSample_sprout(Integer.parseInt(sample_sprout));
    } else {
      sample.setSample_sprout(0);
    }

    if (!obj.isNull("sample_purity")) {
      String sample_purity = (String) obj.get("sample_sprout");
      sample_purity = sample_purity.trim();
      sample.setSample_purity(Integer.parseInt(sample_purity));
    } else {
      sample.setSample_purity(0);
    }

    if (!obj.isNull("sample_comment")) {
      sample.setSample_comment((String) obj.get("sample_comment"));
    } else {
      sample.setSample_comment("");
    }

    int result = service.InsertSample(sample);

    return result;
  }

  @ResponseBody
  @RequestMapping("insertSampleOutcome")
  public int InsertSampleOutcome(@ModelAttribute SampleOutcome sampleOutcome, @RequestParam("input_data") String input_data) {
    JSONArray arr = new JSONArray(input_data);

    JSONObject obj = arr.getJSONObject(0);

    if (!obj.isNull("sample_outcome_code")) {
      sampleOutcome.setSample_outcome_code((String) obj.get("sample_outcome_code"));
    } else {
      sampleOutcome.setSample_outcome_code("");
    }

    if (!obj.isNull("sample_name")) {
      sampleOutcome.setSample_name((String) obj.get("sample_name"));
    } else {
      sampleOutcome.setSample_name("");
    }

    if (!obj.isNull("sample_outcome_num")) {
      sampleOutcome.setSample_outcome_num((String) obj.get("sample_outcome_num"));
    } else {
      sampleOutcome.setSample_outcome_num("");
    }

    if (!obj.isNull("sample_outcome_amount")) {
      String sample_outcome_amount = (String) obj.get("sample_outcome_amount");
      sample_outcome_amount = sample_outcome_amount.trim();
      sampleOutcome.setSample_outcome_amount(Integer.parseInt(sample_outcome_amount));
    } else {
      sampleOutcome.setSample_outcome_amount(0);
    }

    if (!obj.isNull("sample_outcome_in")) {
      String sample_outcome_in = (String) obj.get("sample_outcome_in");
      sample_outcome_in = sample_outcome_in.trim();
      sampleOutcome.setSample_outcome_in(Integer.parseInt(sample_outcome_in));
    } else {
      sampleOutcome.setSample_outcome_in(0);
    }

    if (!obj.isNull("sample_outcome_out")) {
      String sample_outcome_out = (String) obj.get("sample_outcome_out");
      sample_outcome_out = sample_outcome_out.trim();
      sampleOutcome.setSample_outcome_out(Integer.parseInt(sample_outcome_out));
    } else {
      sampleOutcome.setSample_outcome_out(0);
    }

    if (!obj.isNull("sample_outcome_remain")) {
      String sample_outcome_remain = (String) obj.get("sample_outcome_remain");
      sample_outcome_remain = sample_outcome_remain.trim();
      sampleOutcome.setSample_outcome_remain(Integer.parseInt(sample_outcome_remain));
    } else {
      sampleOutcome.setSample_outcome_remain(0);
    }

    if (!obj.isNull("sample_outcome_person")) {
      sampleOutcome.setSample_outcome_person((String) obj.get("sample_outcome_person"));
    } else {
      sampleOutcome.setSample_outcome_person("");
    }

    if (!obj.isNull("sample_outcome_reciever")) {
      sampleOutcome.setSample_outcome_reciever((String) obj.get("sample_outcome_reciever"));
    } else {
      sampleOutcome.setSample_outcome_reciever("");
    }

    if (!obj.isNull("sample_outcome_date")) {
      String date = (String) obj.get("sample_outcome_date");
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.KOREA);
      LocalDate ldate = LocalDate.parse(date, formatter);

      sampleOutcome.setSample_outcome_date(ldate);
    } else {
      sampleOutcome.setSample_outcome_date(null);
    }

    if (!obj.isNull("sample_outcome_end")) {
      String date = (String) obj.get("sample_outcome_end");
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.KOREA);
      LocalDate ldate = LocalDate.parse(date, formatter);

      sampleOutcome.setSample_outcome_end(ldate);
    } else {
      sampleOutcome.setSample_outcome_end(null);
    }

    if (!obj.isNull("sample_outcome_country")) {
      sampleOutcome.setSample_outcome_country((String) obj.get("sample_outcome_country"));
    } else {
      sampleOutcome.setSample_outcome_country("");
    }

    if (!obj.isNull("sample_outcome_place")) {
      sampleOutcome.setSample_outcome_place((String) obj.get("sample_outcome_place"));
    } else {
      sampleOutcome.setSample_outcome_place("");
    }

    int result = service.InsertSampleOutcome(sampleOutcome);

    return result;
  }

  // 시교자원 검색
  @ResponseBody
  @RequestMapping("searchSample")
  public Map<String, Object> SearchSample(@RequestParam("sample_name") String sample_name, @RequestParam("page_num") int page_num, @RequestParam("limit") int limit) {
    Map<String, Object> result = new LinkedHashMap<String, Object>();
    Map<String, Object> result1 = new LinkedHashMap<String, Object>();

    int count = service.SelectSampleCount(sample_name);

    int offset = (page_num - 1) * limit;
    int end_page = (count + limit - 1) / limit;

    List<Sample> Sample = service.SearchSample(sample_name, offset, limit);

    result.put("sample", Sample);
    result.put("page_num", page_num);
    result.put("end_page", end_page);
    result.put("offset", offset);

    return result;
  }

  @ResponseBody
  @RequestMapping("searchSample1")
  public Map<String, Object> SearchSample1(@RequestParam("sample_name") String sample_name, @RequestParam("limit") int limit) {
    Map<String, Object> result1 = new LinkedHashMap<String, Object>();

    int count = service.SelectSampleCount(sample_name);

    List<Sample> Sample = service.SearchSampleTest(sample_name);

    result1.put("result", true);

    Map<String, Object> paging = new LinkedHashMap<String, Object>();
//    paging.put("page", 1);
    paging.put("totalCount", count);

    Map<String, Object> data_in = new LinkedHashMap<String, Object>();
    data_in.put("contents", Sample);
    data_in.put("pagination", paging);

    result1.put("data", data_in);

    return result1;
  }

  // 수출자원 검색
  @ResponseBody
  @RequestMapping("searchOutcome")
  public Map<String, Object> SearchOutcome(@RequestParam("sample_name") String sample_name, @RequestParam("page_num") int page_num, @RequestParam("limit") int limit) {
    Map<String, Object> result1 = new LinkedHashMap<String, Object>();

    int count = service.SelectOutcomeCount(sample_name);

    int offset = (page_num - 1) * limit;
    int end_page = (count + limit - 1) / limit;

    List<SampleOutcome> SampleOutcome = service.SearchOutcome(sample_name, offset, limit);

    result1.put("sampleOutcome", SampleOutcome);
    result1.put("page_num", page_num);
    result1.put("end_page", end_page);
    result1.put("offset", offset);

    return result1;
  }

  @ResponseBody
  @RequestMapping("searchSeed")
  public Map<String, Object> SearchSeed(@RequestParam("sample_name") String sample_name) {
    Map<String, Object> result1 = new LinkedHashMap<String, Object>();

    List<SampleOutcome> SampleOutcome = service.SearchSeed(sample_name);

    result1.put("sampleOutcome", SampleOutcome);

    return result1;
  }

  // 선택삭제
  @ResponseBody
  @RequestMapping("deleteSample")
  public int DeleteSample(@RequestParam("sample_id[]") int[] sample_id) {
    service.DeleteSample(sample_id);

    return 1;
  }

  @ResponseBody
  @RequestMapping("deleteOutcome")
  public int DeleteOutcome(@RequestParam("sample_outcome_id[]") int[] sample_outcome_id) {
    service.DeleteOutcome(sample_outcome_id);

    return 1;
  }

  // 시교자원 수정
  @ResponseBody
  @RequestMapping("updateSample")
  public int UpdateSample(@RequestParam("sample_id") int sample_id, @RequestParam("sample_name") String sample_name, @RequestParam("sample_value") String sample_value) {
    int result = service.UpdateSample(sample_id, sample_name, sample_value);

    return result;
  }

  @ResponseBody
  @RequestMapping("updateOutcome")
  public int UpdateOutcome(@RequestParam("sample_outcome_id") int sample_outcome_id, @RequestParam("sample_outcome_name") String sample_outcome_name, @RequestParam("sample_outcome_value") String sample_outcome_value) {
    int result = service.UpdateOutcome(sample_outcome_id, sample_outcome_name, sample_outcome_value);

    return result;
  }

  // 엑셀 등록
  @ResponseBody
  @RequestMapping("excelSample")
  public int excelUpload(ModelAndView mv, @ModelAttribute Sample sample, @RequestParam("excel_list") String excel_list) {
    JSONArray arr = new JSONArray(excel_list);

    for (int i = arr.length() - 1; i > -1; i--) {

      JSONObject obj = arr.getJSONObject(i);

      Set<String> key = obj.keySet();

      for (String k : key) {

        if (k.equals("작물")) {
          sample.setSample_name(obj.getString(k));
        } else if (k.equals("시교명(ID)")) {
          sample.setSample_code(obj.getString(k));
        } else if (k.equals("목표 지역")) {
          sample.setSample_country(obj.getString(k));
        } else if (k.equals("구분")) {
          sample.setSample_type(obj.getString(k));
        } else if (k.equals("교배번호")) {
          sample.setSample_mate(obj.getString(k));
        } else if (k.equals("종자번호(ID)")) {
          sample.setSample_seed(obj.getString(k));
        } else if (k.equals("현 종자량(g)")) {
          sample.setSample_amount(Double.parseDouble(obj.getString(k)));
        } else if (k.equals("기내 발아율(%)")) {
          sample.setSample_sprout(Integer.parseInt(obj.getString(k)));
        } else if (k.equals("기내 순도율 (%)")) {
          sample.setSample_purity(Integer.parseInt(obj.getString(k)));
        } else if (k.equals("비고")) {
          sample.setSample_comment(obj.getString(k));
        }
      }

      service.InsertExcel(sample);
    }

//    mv.setViewName("redirect:/sample");

    return 1;
  }

  @RequestMapping("excelOutcome")
  public ModelAndView outComeExcelUpload(ModelAndView mv, @ModelAttribute SampleOutcome sampleOutcome, @RequestParam("excel_list") String excel_list) {
    JSONArray arr = new JSONArray(excel_list);

    for (int i = arr.length() - 1; i > -1; i--) {

      JSONObject obj = arr.getJSONObject(i);

      Set<String> key = obj.keySet();

      for (String k : key) {

        if (k.equals("시교명")) {
          sampleOutcome.setSample_outcome_code(obj.getString(k));
        } else if (k.equals("종자번호 (ID)")) {
          sampleOutcome.setSample_outcome_num(obj.getString(k));
        } else if (k.equals("종자 보유량")) {
          sampleOutcome.setSample_outcome_amount(Integer.parseInt(obj.getString(k)));
        } else if (k.equals("입고량")) {
          sampleOutcome.setSample_outcome_in(Integer.parseInt(obj.getString(k)));
        } else if (k.equals("출고량")) {
          sampleOutcome.setSample_outcome_out(Integer.parseInt(obj.getString(k)));
        } else if (k.equals("재고량")) {
          sampleOutcome.setSample_outcome_remain(Integer.parseInt(obj.getString(k)));
        } else if (k.equals("담당자")) {
          sampleOutcome.setSample_outcome_person(obj.getString(k));
        } else if (k.equals("인수자")) {
          sampleOutcome.setSample_outcome_reciever(obj.getString(k));
        } else if (k.equals("일자")) {
          String date = (String) obj.get("sample_outcome_date");
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.KOREA);
          LocalDate ldate = LocalDate.parse(date, formatter);

          sampleOutcome.setSample_outcome_date(ldate);
        } else if (k.equals("시교 종료 일자")) {
          String date = (String) obj.get("sample_outcome_end");
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.KOREA);
          LocalDate ldate = LocalDate.parse(date, formatter);

          sampleOutcome.setSample_outcome_end(ldate);
        } else if (k.equals("지역 구분")) {
          sampleOutcome.setSample_outcome_country(obj.getString(k));
        } else if (k.equals("대상 지역")) {
          sampleOutcome.setSample_outcome_place(obj.getString(k));
        }
      }

      service.InsertOutcomeExcel(sampleOutcome);
    }
    mv.setViewName("redirect:/sample");

    return mv;
  }

  @ResponseBody
  @RequestMapping("insertSampleDataList")
  public DataList InsertDataList(@ModelAttribute DataList dataList, @RequestParam("listData") String listData) {
    JSONArray arr = new JSONArray(listData);

    JSONObject obj = arr.getJSONObject(0);

    List<Sample> sample = service.SearchSampleExcel(obj.getString("sample_name"));

    for (int i = 0; i < sample.size(); i++) {

      if (Objects.equals(sample.get(i).getCreate_date().split(" ")[0], obj.getString("datalist_date"))) {
        dataList.setDatalist_type(obj.getString("datalist_type"));
        dataList.setDatalist_date(obj.getString("datalist_date"));
        dataList.setTarget_id(sample.get(i).getSample_id());
      } else {
        continue;
      }

      d_service.InsertDataList(dataList);
    }

    return dataList;
  }

  @ResponseBody
  @RequestMapping("selectSampleDateGroup")
  public Map<String, Object> SelectDateGroup(@RequestParam("datalist_type") String datalist_type) {
    Map<String, Object> result = new LinkedHashMap<String, Object>();

    List<Map<String, String>> dataGroup = d_service.SelectDateGroup(datalist_type);

    result.put("dataGroup", dataGroup);

    return result;
  }

  @ResponseBody
  @RequestMapping("searchSampleExcel")
  public Map<String, Object> SearchSampleExcel(@RequestParam("sample_name") String sample_name) {
    Map<String, Object> result = new LinkedHashMap<String, Object>();

    List<Sample> sample = service.SearchSampleExcel(sample_name);

    result.put("sample", sample);

    return result;
  }


  // 첨부 파일 조회
  @ResponseBody
  @RequestMapping("selectSampleFile")
  public Map<String, Object> SelectSampleFile(@RequestParam("sample_id") int sample_id) {
    Map<String, Object> result = new LinkedHashMap<String, Object>();

    List<SampleFile> sample_file = service.SelectSampleFile(sample_id);

    result.put("sample_file", sample_file);

    return result;
  }

  // 첨부파일 등록
  @RequestMapping("insertSampleFile")
  public ModelAndView InsertSampleFile(ModelAndView mv, @ModelAttribute SampleFile sample_file, @RequestParam("file") MultipartFile file) throws IOException {
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

    int insert_file = service.InsertSampleFile(sample_file);

    Uploads upload = new Uploads();
    upload.setUploads_file(file_name);
    upload.setUploads_origin_file(origin_file_name);
    upload.setSample_file_id(sample_file.getSample_file_id());

    int insert_upload = service.InsertSampleUpload(upload);

    mv.setViewName("redirect:/sample");

    return mv;
  }

  // 첨부파일 수정
  @RequestMapping("updateSampleFile")
  public ModelAndView UpdateSampleFile(ModelAndView mv, @ModelAttribute SampleFile sample_file, @RequestParam("file") MultipartFile file) throws IOException {
    if (file.isEmpty()) {
      int update_file = service.UpdateSampleFile(sample_file);
    } else {
      String delete_path = "upload/" + sample_file.getUploads_file();
      File origin_file = new File(delete_path);

      if (origin_file.delete()) {
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
        upload.setSample_file_id(sample_file.getSample_file_id());

        int update_upload = service.UpdateSampleUpload(upload);
      }
    }

    mv.setViewName("redirect:/sample");

    return mv;
  }

  // 품종 검색
  @ResponseBody
  @RequestMapping("searchBreedList")
  public Map<String, Object> SearchBreed(Authentication auth, @RequestParam("breed_name") String breed_name) {
    Map<String, Object> result = new LinkedHashMap<String, Object>();

    User user = (User) auth.getPrincipal();

    int count = service.SelectBreedCount(breed_name);

    List<Breed> breed = service.SearchBreed(breed_name);                // 품종 검색
    List<Detail> detail = service.SearchBreedDetail(breed_name);                  // 품종 작물별 컬럼 조회
    List<Display> display = service.SelectDisplay(user.getUser_id(), breed_name);          // 사용자별 품종 표시항목 조회

    List<Standard> standard = new ArrayList<Standard>();

    if (!detail.isEmpty()) {
      for (int i = 0; i < breed.size(); i++) {
        standard = service.SearchBreedStandard(detail, user.getUser_id(), breed.get(i).getBreed_id());

        breed.get(i).setBreed_standard(standard);
      }
    }

    result.put("breed", breed);
    result.put("detail", detail);
    result.put("display", display);

    return result;
  }


}
