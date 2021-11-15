package com.thekiban.Controller;

import com.thekiban.Entity.*;
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
import java.util.*;

// 시교자원
@Controller
public class SampleController {

  @Autowired
  private SampleService service;

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
  public Sample InsertSample(@ModelAttribute Sample sample, @RequestParam("input_data") String input_data) {
    JSONArray arr = new JSONArray(input_data);

    JSONObject obj = arr.getJSONObject(0);

    sample.setSample_name((String) obj.get("sample_name"));

    service.InsertSample(sample);

    return sample;
  }

  @ResponseBody
  @RequestMapping("insertSampleOutcome")
  public SampleOutcome InsertSampleOutcome(@ModelAttribute SampleOutcome sampleOutcome, @RequestParam("input_data") String input_data) {
    JSONArray arr = new JSONArray(input_data);

    JSONObject obj = arr.getJSONObject(0);

    String value = (String) obj.get("value");

    sampleOutcome.setSample_name(value);

    service.InsertSampleOutcome(sampleOutcome);

    return sampleOutcome;
  }

  // 시교자원 검색
  @ResponseBody
  @RequestMapping("searchSample")
  public Map<String, Object> SearchSample(@RequestParam("sample_name") String sample_name, @RequestParam("page_num") int page_num, @RequestParam("limit") int limit) {
    Map<String, Object> result = new LinkedHashMap<String, Object>();

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

  @RequestMapping("updateInsertSample")
  public ModelAndView UpdateInsertSample(ModelAndView mv, @ModelAttribute Sample sample, @RequestParam(value = "update_list", required = false) String update_list, @RequestParam("data") String data) {
    JSONArray arr = new JSONArray(data);

    JSONObject input_id = arr.getJSONObject(0);

    int value_id = (Integer) input_id.get("value");

    sample.setSample_id(value_id);

    for (int i = 1; i < arr.length(); i++) {
      JSONObject obj = arr.getJSONObject(i);

      String key_id = (String) obj.get("key");
      String value = (String) obj.get("value");

      if (!value.equals("")) {
        if (key_id.equals("sample_name")) {
          sample.setSample_name(value);
        } else if (key_id.equals("sample_code")) {
          sample.setSample_code(value);
        } else if (key_id.equals("sample_country")) {
          sample.setSample_country(value);
        } else if (key_id.equals("sample_type")) {
          sample.setSample_type(value);
        } else if (key_id.equals("sample_mate")) {
          sample.setSample_mate(value);
        } else if (key_id.equals("sample_seed")) {
          sample.setSample_seed(value);
        } else if (key_id.equals("sample_amount")) {
          sample.setSample_amount(Double.parseDouble(value));
        } else if (key_id.equals("sample_sprout")) {
          sample.setSample_sprout(Integer.parseInt(value));
        } else if (key_id.equals("sample_purity")) {
          sample.setSample_purity(Integer.parseInt(value));
        } else if (key_id.equals("sample_comment")) {
          sample.setSample_comment(value);
        }
      } else {
        if (key_id.equals("sample_name")) {
          sample.setSample_name("");
        } else if (key_id.equals("sample_code")) {
          sample.setSample_code("");
        } else if (key_id.equals("sample_country")) {
          sample.setSample_country("");
        } else if (key_id.equals("sample_type")) {
          sample.setSample_type("");
        } else if (key_id.equals("sample_mate")) {
          sample.setSample_mate("");
        } else if (key_id.equals("sample_seed")) {
          sample.setSample_seed("");
        } else if (key_id.equals("sample_amount")) {
          sample.setSample_amount(0);
        } else if (key_id.equals("sample_sprout")) {
          sample.setSample_sprout(0);
        } else if (key_id.equals("sample_purity")) {
          sample.setSample_purity(0);
        } else if (key_id.equals("sample_comment")) {
          sample.setSample_comment("");
        }
      }
    }

    service.UpdateInsertSample(sample);

    mv.setViewName("redirect:/sample");

    return mv;
  }

  @RequestMapping("updateInsertOutcome")
  public ModelAndView UpdateInsertOutcome(ModelAndView mv, @ModelAttribute SampleOutcome sampleOutcome, @RequestParam("data") String data) {
    JSONArray arr = new JSONArray(data);

    JSONObject input_id = arr.getJSONObject(0);

    int value_id = (Integer) input_id.get("value");

    sampleOutcome.setSample_outcome_id(value_id);

    for (int i = 1; i < arr.length(); i++) {
      JSONObject obj = arr.getJSONObject(i);

      String key_id = (String) obj.get("key");
      String value = (String) obj.get("value");

      if (!value.equals("")) {
        if (key_id.equals("sample_outcome_code")) {
          sampleOutcome.setSample_outcome_code(value);
        } else if (key_id.equals("sample_outcome_num")) {
          sampleOutcome.setSample_outcome_num(value);
        } else if (key_id.equals("sample_outcome_amount")) {
          sampleOutcome.setSample_outcome_amount(Integer.parseInt(value));
        } else if (key_id.equals("sample_outcome_in")) {
          sampleOutcome.setSample_outcome_in(Integer.parseInt(value));
        } else if (key_id.equals("sample_outcome_out")) {
          sampleOutcome.setSample_outcome_out(Integer.parseInt(value));
        } else if (key_id.equals("sample_outcome_remain")) {
          sampleOutcome.setSample_outcome_remain(Integer.parseInt(value));
        } else if (key_id.equals("sample_outcome_person")) {
          sampleOutcome.setSample_outcome_person(value);
        } else if (key_id.equals("sample_outcome_reciever")) {
          sampleOutcome.setSample_outcome_reciever(value);
        } else if (key_id.equals("sample_outcome_date")) {
          sampleOutcome.setSample_outcome_date(value);
        } else if (key_id.equals("sample_outcome_end")) {
          sampleOutcome.setSample_outcome_end(value);
        } else if (key_id.equals("sample_outcome_country")) {
          sampleOutcome.setSample_outcome_country(value);
        } else if (key_id.equals("sample_outcome_place")) {
          sampleOutcome.setSample_outcome_place(value);
        }
      } else {
        if (key_id.equals("sample_outcome_code")) {
          sampleOutcome.setSample_outcome_code("");
        } else if (key_id.equals("sample_outcome_num")) {
          sampleOutcome.setSample_outcome_num("");
        } else if (key_id.equals("sample_outcome_amount")) {
          sampleOutcome.setSample_outcome_amount(0);
        } else if (key_id.equals("sample_outcome_in")) {
          sampleOutcome.setSample_outcome_in(0);
        } else if (key_id.equals("sample_outcome_out")) {
          sampleOutcome.setSample_outcome_out(0);
        } else if (key_id.equals("sample_outcome_remain")) {
          sampleOutcome.setSample_outcome_remain(0);
        } else if (key_id.equals("sample_outcome_person")) {
          sampleOutcome.setSample_outcome_person("");
        } else if (key_id.equals("sample_outcome_reciever")) {
          sampleOutcome.setSample_outcome_reciever("");
        } else if (key_id.equals("sample_outcome_date")) {
          sampleOutcome.setSample_outcome_date(null);
        } else if (key_id.equals("sample_outcome_end")) {
          sampleOutcome.setSample_outcome_end(null);
        } else if (key_id.equals("sample_outcome_country")) {
          sampleOutcome.setSample_outcome_country("");
        } else if (key_id.equals("sample_outcome_place")) {
          sampleOutcome.setSample_outcome_place("");
        }
      }
    }

    service.UpdateInsertOutcome(sampleOutcome);

    mv.setViewName("redirect:/sample");

    return mv;
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
  @RequestMapping("excelSample")
  public ModelAndView excelUpload(ModelAndView mv, @ModelAttribute Sample sample, @RequestParam("excel_list") String excel_list) {
    JSONArray arr = new JSONArray(excel_list);

    for (int i = arr.length() - 1; i > -1; i--) {

      JSONObject obj = arr.getJSONObject(i);

      Set<String> key = obj.keySet();

      for (String k : key) {

        if (k.equals("작물")) {
          sample.setSample_name(obj.getString(k));
        } else if (k.equals("시교명 (ID)")) {
          sample.setSample_code(obj.getString(k));
        } else if (k.equals("목표 지역")) {
          sample.setSample_country(obj.getString(k));
        } else if (k.equals("구분")) {
          sample.setSample_type(obj.getString(k));
        } else if (k.equals("교배번호")) {
          sample.setSample_mate(obj.getString(k));
        } else if (k.equals("종자번호 (ID)")) {
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

    mv.setViewName("redirect:/sample");

    return mv;
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
          sampleOutcome.setSample_outcome_date(obj.getString(k));
        } else if (k.equals("시교 종료 일자")) {
          sampleOutcome.setSample_outcome_end(obj.getString(k));
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
