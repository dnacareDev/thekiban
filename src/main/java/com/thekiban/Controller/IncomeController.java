package com.thekiban.Controller;

import com.thekiban.Entity.*;
import com.thekiban.Service.IncomeService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class IncomeController {

  @Autowired
  private IncomeService service;

  @Autowired
  private FileController fileController;

  //도입자원 관리 페이지
  @RequestMapping(value = "income")
  public ModelAndView getIntroResourceList(ModelAndView mv) {

    mv.setViewName("genome/income");

    return mv;
  }

  @ResponseBody
  @RequestMapping("insertIncome")
  public Income InsertIncome(@ModelAttribute Income income, @RequestParam("input_data") String input_data) {
    JSONArray arr = new JSONArray(input_data);

    JSONObject obj = arr.getJSONObject(0);

    String value = (String) obj.get("value");

    income.setIncome_name(value);

    service.InsertIncome(income);

    return income;
  }

  @ResponseBody
  @RequestMapping("insertIncomeRemain")
  public IncomeRemain InsertIncomeRemain(@ModelAttribute IncomeRemain incomeRemain, @RequestParam("input_data") String input_data) {
    JSONArray arr = new JSONArray(input_data);

    JSONObject obj = arr.getJSONObject(0);

    String value = (String) obj.get("value");

    incomeRemain.setIncome_name(value);

    service.InsertIncomeRemain(incomeRemain);

    return incomeRemain;
  }

  // 도입자원 검색
  @ResponseBody
  @RequestMapping("searchIncome")
  public Map<String, Object> SearchIncome(@RequestParam("income_name") String income_name, @RequestParam("page_num") int page_num, @RequestParam("limit") int limit) {
    Map<String, Object> result = new LinkedHashMap<String, Object>();

    int count = service.SelectIncomeCount(income_name);

    int offset = (page_num - 1) * limit;
    int end_page = (count + limit - 1) / limit;

    List<Income> Income = service.SearchIncome(income_name, offset, limit);

    result.put("income", Income);
    result.put("page_num", page_num);
    result.put("end_page", end_page);
    result.put("offset", offset);

    return result;
  }

  // 자원재고 검색
  @ResponseBody
  @RequestMapping("searchRemain")
  public Map<String, Object> SearchRemain(@RequestParam("income_name") String income_name, @RequestParam("page_num") int page_num, @RequestParam("limit") int limit) {
    Map<String, Object> result = new LinkedHashMap<String, Object>();

    int count = service.SelectRemainCount(income_name);

    int offset = (page_num - 1) * limit;
    int end_page = (count + limit - 1) / limit;

    List<IncomeRemain> IncomeRemain = service.SearchRemain(income_name, offset, limit);

    result.put("incomeRemain", IncomeRemain);
    result.put("page_num", page_num);
    result.put("end_page", end_page);
    result.put("offset", offset);

    return result;
  }

  // 재고 팝업
  @ResponseBody
  @RequestMapping("searchIncomeRemain")
  public Map<String, Object> SearchIncomeRemain(@RequestParam("income_name") String income_name) {
    Map<String, Object> result = new LinkedHashMap<String, Object>();

    int count = service.SelectRemainCount(income_name);

    List<IncomeRemain> IncomeRemain = service.SearchIncomeRemain(income_name);

    result.put("incomeRemain", IncomeRemain);

    return result;
  }

  // 도입자원 삭제
  @ResponseBody
  @RequestMapping("deleteIncome")
  public int DeleteIncome(@RequestParam("income_id[]") int[] income_id) {
    service.DeleteIncome(income_id);

    return 1;
  }

  // 도입자원 삭제
  @ResponseBody
  @RequestMapping("deleteRemain")
  public int DeleteRemain(@RequestParam("income_id[]") int[] income_id) {
    service.DeleteRemain(income_id);

    return 1;
  }

  // 도입자원 수정
  @RequestMapping("updateInsertIncome")
  public ModelAndView UpdateInsertIncome(ModelAndView mv, @ModelAttribute Income income, @RequestParam(value = "update_list", required = false) String update_list, @RequestParam("data") String data) {
    JSONArray arr = new JSONArray(data);

    JSONObject input_id = arr.getJSONObject(0);

    int value_id = (Integer) input_id.get("value");

    income.setIncome_id(value_id);

    for (int i = 1; i < arr.length(); i++) {
      JSONObject obj = arr.getJSONObject(i);

      String key_id = (String) obj.get("key");
      String value = (String) obj.get("value");

      if (!value.equals("")) {
        if (key_id.equals("income_name")) {
          income.setIncome_name(value);
        } else if (key_id.equals("income_code")) {
          income.setIncome_code(value);
        } else if (key_id.equals("income_kind")) {
          income.setIncome_kind(value);
        } else if (key_id.equals("income_division")) {
          income.setIncome_division(value);
        } else if (key_id.equals("income_place")) {
          income.setIncome_place(value);
        } else if (key_id.equals("income_country")) {
          income.setIncome_country(value);
        } else if (key_id.equals("income_person")) {
          income.setIncome_person(value);
        } else if (key_id.equals("income_date")) {
          income.setIncome_date(value);
        } else if (key_id.equals("income_num")) {
          income.setIncome_num(Integer.parseInt(value));
        } else if (key_id.equals("income_type")) {
          income.setIncome_type(value);
        }else if (key_id.equals("income_trait")) {
          income.setIncome_trait(value);
        } else if (key_id.equals("income_comment")) {
          income.setIncome_comment(value);
        }
      } else {
        if (key_id.equals("income_name")) {
          income.setIncome_name("");
        } else if (key_id.equals("income_code")) {
          income.setIncome_code("");
        } else if (key_id.equals("income_kind")) {
          income.setIncome_kind("");
        } else if (key_id.equals("income_division")) {
          income.setIncome_division("");
        } else if (key_id.equals("income_place")) {
          income.setIncome_place("");
        } else if (key_id.equals("income_country")) {
          income.setIncome_country("");
        } else if (key_id.equals("income_person")) {
          income.setIncome_person("");
        } else if (key_id.equals("income_date")) {
          income.setIncome_date(null);
        } else if (key_id.equals("income_num")) {
          income.setIncome_num(0);
        } else if (key_id.equals("income_type")) {
          income.setIncome_type("");
        }else if (key_id.equals("income_trait")) {
          income.setIncome_trait("");
        } else if (key_id.equals("income_comment")) {
          income.setIncome_comment("");
        }
      }
    }
    service.UpdateIncome(income);

    mv.setViewName("redirect:/income");

    return mv;
  }

  @RequestMapping("updateInsertRemain")
  public ModelAndView UpdateInsertRemain(ModelAndView mv, @ModelAttribute IncomeRemain incomeRemain, @RequestParam("data") String data) {
    JSONArray arr = new JSONArray(data);

    JSONObject input_id = arr.getJSONObject(0);

    int value_id = (Integer) input_id.get("value");

    incomeRemain.setIncome_remain_id(value_id);

    for (int i = 1; i < arr.length(); i++) {
      JSONObject obj = arr.getJSONObject(i);

      String key_id = (String) obj.get("key");
      String value = (String) obj.get("value");

      if (!value.equals("")) {
        if (key_id.equals("income_remain_num")) {
          incomeRemain.setIncome_remain_num(value);
        } else if (key_id.equals("income_remain_amount")) {
          incomeRemain.setIncome_remain_amount(Integer.parseInt(value));
        } else if (key_id.equals("income_remain_in")) {
          incomeRemain.setIncome_remain_in(Integer.parseInt(value));
        } else if (key_id.equals("income_remain_out")) {
          incomeRemain.setIncome_remain_out(Integer.parseInt(value));
        } else if (key_id.equals("income_remain_re")) {
          incomeRemain.setIncome_remain_re(Integer.parseInt(value));
        } else if (key_id.equals("income_remain_person")) {
          incomeRemain.setIncome_remain_person(value);
        } else if (key_id.equals("income_remain_date")) {
          incomeRemain.setIncome_remain_date(value);
        }
      } else {
        if (key_id.equals("income_remain_num")) {
          incomeRemain.setIncome_remain_num("");
        } else if (key_id.equals("income_remain_amount")) {
          incomeRemain.setIncome_remain_amount(0);
        } else if (key_id.equals("income_remain_in")) {
          incomeRemain.setIncome_remain_in(0);
        } else if (key_id.equals("income_remain_out")) {
          incomeRemain.setIncome_remain_out(0);
        } else if (key_id.equals("income_remain_re")) {
          incomeRemain.setIncome_remain_re(0);
        } else if (key_id.equals("income_remain_person")) {
          incomeRemain.setIncome_remain_person("");
        } else if (key_id.equals("income_remain_date")) {
          incomeRemain.setIncome_remain_date(null);
        }
      }
    }
    service.UpdateInsertRemain(incomeRemain);

    mv.setViewName("redirect:/income");

    return mv;
  }

  // 시교자원 수정
  @ResponseBody
  @RequestMapping("updateIncome")
  public int UpdateIncome(@RequestParam("income_id") int income_id, @RequestParam("income_name") String income_name, @RequestParam("income_value") String income_value)
  {
    int result = service.UpdateIncome(income_id, income_name, income_value);

    return result;
  }

  @ResponseBody
  @RequestMapping("updateRemain")
  public int UpdateRemain(@RequestParam("income_remain_id") int income_remain_id, @RequestParam("income_remain_name") String income_remain_name, @RequestParam("income_remain_value") String income_remain_value)
  {
    int result = service.UpdateRemain(income_remain_id, income_remain_name, income_remain_value);

    return result;
  }

  // 엑셀 등록
  @RequestMapping("excelIncome")
  public ModelAndView excelUpload(ModelAndView mv, @ModelAttribute Income income, @RequestParam("excel_list") String excel_list) {
    JSONArray arr = new JSONArray(excel_list);

    for (int i = arr.length() - 1; i > -1; i--) {

      JSONObject obj = arr.getJSONObject(i);

      Set<String> key = obj.keySet();

      for (String k : key) {
        if (k.equals("작물")) {
          income.setIncome_name(obj.getString(k));
        } else if (k.equals("도입번호 (ID)")) {
          income.setIncome_code(obj.getString(k));
        } else if (k.equals("품종명")) {
          income.setIncome_kind(obj.getString(k));
        } else if (k.equals("구분")) {
          income.setIncome_division(obj.getString(k));
        } else if (k.equals("도입처")) {
          income.setIncome_place(obj.getString(k));
        } else if (k.equals("도입국가")) {
          income.setIncome_country(obj.getString(k));
        } else if (k.equals("도입자")) {
          income.setIncome_person(obj.getString(k));
        } else if (k.equals("도입연월일")) {
          income.setIncome_date(obj.getString(k));
        } else if (k.equals("현 종자량")) {
          income.setIncome_num(Integer.parseInt(obj.getString(k)));
        } else if (k.equals("타입")) {
          income.setIncome_type(obj.getString(k));
        } else if (k.equals("주요 특성")) {
          income.setIncome_trait(obj.getString(k));
        } else if (k.equals("비고")) {
          income.setIncome_comment(obj.getString(k));
        }
      }

      service.InsertIncome(income);
    }

    mv.setViewName("redirect:/income");

    return mv;
  }

  // 첨부 파일 조회
  @ResponseBody
  @RequestMapping("selectIncomeFile")
  public Map<String, Object> SelectIncomeFile(@RequestParam("income_id") int income_id)
  {
    Map<String, Object> result = new LinkedHashMap<String, Object>();

    List<IncomeFile> income_file = service.SelectIncomeFile(income_id);

    result.put("income_file", income_file);

    return result;
  }

  // 첨부파일 등록
  @RequestMapping("insertIncomeFile")
  public ModelAndView InsertIncomeFile(ModelAndView mv, @ModelAttribute IncomeFile income_file, @RequestParam("file") MultipartFile file) throws IOException
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

    int insert_file = service.InsertIncomeFile(income_file);

    Uploads upload = new Uploads();
    upload.setUploads_file(file_name);
    upload.setUploads_origin_file(origin_file_name);
    upload.setIncome_file_id(income_file.getIncome_file_id());

    int insert_upload = service.InsertIncomeUpload(upload);

    mv.setViewName("redirect:/income");

    return mv;
  }

  // 첨부파일 수정
  @RequestMapping("updateIncomeFile")
  public ModelAndView UpdateIncomeFile(ModelAndView mv, @ModelAttribute IncomeFile income_file, @RequestParam("file") MultipartFile file) throws IOException
  {
    if(file.isEmpty())
    {
      int update_file = service.UpdateIncomeFile(income_file);
    }
    else
    {
      String delete_path = "upload/" + income_file.getUploads_file();
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
        upload.setIncome_file_id(income_file.getIncome_file_id());

        int update_upload = service.UpdateIncomeUpload(upload);
      }
    }

    mv.setViewName("redirect:/income");

    return mv;
  }
}
