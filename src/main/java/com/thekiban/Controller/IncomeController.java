package com.thekiban.Controller;

import com.thekiban.Entity.Basic;
import com.thekiban.Entity.Income;
import com.thekiban.Entity.Sample;
import com.thekiban.Service.IncomeService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IncomeController {

  @Autowired
  private IncomeService service;

  //도입자원 관리 페이지
  @RequestMapping(value = "income")
  public ModelAndView getIntroResourceList(ModelAndView mv) {

    mv.setViewName("genome/income");

    return mv;
  }

  @RequestMapping("insertIncome")
  public ModelAndView InsertIncome(ModelAndView mv, @ModelAttribute Income income)
  {
    service.InsertIncome(income);

    mv.setViewName("redirect:/income");

    return mv;
  }

  // 도입자원 검색
  @ResponseBody
  @RequestMapping("searchIncome")
  public Map<String, Object> SearchIncome(@RequestParam("income_name") String income_name, @RequestParam("page_num") int page_num)
  {
    Map<String, Object> result = new LinkedHashMap<String, Object>();

    int count = service.SelectIncomeCount(income_name);

    int limit = 10;
    int offset = (page_num - 1) * limit;
    int end_page = (count + limit - 1) / limit;

    List<Income> income = service.SearchIncome(income_name, offset, limit);

    result.put("income", income);
    result.put("page_num", page_num);
    result.put("end_page", end_page);
    result.put("offset", offset);

    return result;
  }

  // 도입자원 삭제
  @ResponseBody
  @RequestMapping("deleteIncome")
  public int DeleteIncome(@RequestParam("income_id[]") int[] income_id) {
    service.DeleteIncome(income_id);

    return 1;
  }

  @RequestMapping("updateIncome")
  public ModelAndView UpdateSample(ModelAndView mv, @ModelAttribute Income income, @RequestParam("update_list") String update_list)  {
    JSONArray arr = new JSONArray(update_list);

    JSONObject obj = arr.getJSONObject(0);

    String value = (String)obj.get("value");

    income.setIncome_id(Integer.parseInt(value));

    service.UpdateIncome(income);

    mv.setViewName("redirect:/income");

    return mv;
  }
}
