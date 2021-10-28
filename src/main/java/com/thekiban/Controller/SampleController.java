package com.thekiban.Controller;

import com.thekiban.Entity.Sample;
import com.thekiban.Entity.Standard;
import com.thekiban.Service.SampleService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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

  @RequestMapping("insertSample")
  public ModelAndView InsertSample(ModelAndView mv, @ModelAttribute Sample sample)
  {
    service.InsertSample(sample);

    mv.setViewName("redirect:/sample");

    return mv;
  }

  @ResponseBody
  @RequestMapping("searchSample")
  public Map<String, Object> SearchSample(@RequestParam("page_num") int page_num)
  {
    Map<String, Object> result = new LinkedHashMap<String, Object>();

    int count = service.SelectSampleCount();

    int limit = 10;
    int offset = (page_num - 1) * limit;
    int end_page = (count + limit - 1) / limit;

    List<Sample> Sample = service.SearchSample(offset, limit);

    result.put("sample", Sample);
    result.put("page_num", page_num);
    result.put("end_page", end_page);
    result.put("offset", offset);

    System.out.println(result);

    return result;
  }

  // 선택삭제
  @RequestMapping("delete")
  public String boardDelete(@RequestParam("num") int num) throws Exception {

    SampleService.Delete(num);

    return "redirect:list";
  }

}
