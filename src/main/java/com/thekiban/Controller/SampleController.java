package com.thekiban.Controller;

import com.thekiban.Entity.Breed;
import com.thekiban.Entity.Sample;
import com.thekiban.Entity.Standard;
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

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
  @RequestMapping("insertSample")
  public ModelAndView InsertSample(ModelAndView mv, @ModelAttribute Sample sample) {
    service.InsertSample(sample);

    mv.setViewName("redirect:/sample");

    return mv;
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

  // 선택삭제
  @ResponseBody
  @RequestMapping("deleteSample")
  public int DeleteSample(@RequestParam("sample_id[]") int[] sample_id)
  {
    service.DeleteSample(sample_id);

    return 1;
  }

  // 시교자원 수정
  @RequestMapping("updateSample")
  public ModelAndView UpdateSample(ModelAndView mv, @ModelAttribute Sample sample, @RequestParam("update_list") String update_list)  {
    JSONArray arr = new JSONArray(update_list);

    JSONObject obj = arr.getJSONObject(0);

    String value = (String)obj.get("value");

    sample.setSample_id(Integer.parseInt(value));

    service.UpdateSample(sample);

    mv.setViewName("redirect:/sample");

    return mv;
  }
}
