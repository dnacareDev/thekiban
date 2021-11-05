package com.thekiban.Controller;

import com.thekiban.Entity.Sample;
import com.thekiban.Entity.SampleFile;
import com.thekiban.Entity.SampleOutcome;
import com.thekiban.Service.SampleService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

// 시교자원
@Controller
public class SampleController {

  @Autowired
  private SampleService service;

  // 시교자원 관리 페이지
  @RequestMapping("sample")
  public ModelAndView SampleList(ModelAndView mv) {

    mv.setViewName("genome/sample");

    return mv;
  }

  // 시교자원 입력
/*  @ResponseBody
  @RequestMapping("insertSample")
  public ModelAndView InsertSample(ModelAndView mv, @ModelAttribute Sample sample, @RequestParam("data") String data)
  {
    JSONArray arr = new JSONArray(data);

    System.out.println(sample);

    System.out.println(arr);

//    service.InsertSample(sample);

    mv.setViewName("redirect:/sample");

    return mv;
  }*/

  @ResponseBody
  @RequestMapping("insertSample")
  public Sample InsertSample(@ModelAttribute Sample sample, @RequestParam("input_data") String input_data) {
    JSONArray arr = new JSONArray(input_data);

    JSONObject obj = arr.getJSONObject(0);

    String value = (String) obj.get("value");

    sample.setSample_name(value);

    service.InsertSample(sample);

    return sample;
  }

  // 시교자원 검색
  @ResponseBody
  @RequestMapping("searchSample")
  public Map<String, Object> SearchSample(@RequestParam("sample_name") String sample_name, @RequestParam("page_num") int page_num) {
    Map<String, Object> result = new LinkedHashMap<String, Object>();

    int count = service.SelectSampleCount(sample_name);

    int limit = 10;
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
  @RequestMapping("searchFileList")
  public Map<String, Object> SearchFileList(@RequestParam("sample_id") String sample_id) {
    Map<String, Object> result = new LinkedHashMap<String, Object>();

    List<SampleFile> SampleFile = service.SearchFileList(sample_id);

    result.put("sampleFile", SampleFile);

    return result;
  }

  // 선택삭제
  @ResponseBody
  @RequestMapping("deleteSample")
  public int DeleteSample(@RequestParam("sample_id[]") int[] sample_id) {
    service.DeleteSample(sample_id);

    return 1;
  }

  // 시교자원 수정
  @RequestMapping("updateSample")
  public ModelAndView UpdateSample(ModelAndView mv, @ModelAttribute Sample sample, @RequestParam(value = "update_list", required = false) String update_list, @RequestParam("data") String data) {
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

    service.UpdateSample(sample);

    mv.setViewName("redirect:/sample");

    return mv;
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
          sampleOutcome.setSample_outcome_num(Integer.parseInt(obj.getString(k)));
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
}
