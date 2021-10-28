package com.thekiban.Controller;

import com.thekiban.Entity.Basic;
import com.thekiban.Entity.Income;
import com.thekiban.Service.IncomeService;
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
  public Map<String, Object> SearchIncome(@RequestParam("page_num") int page_num)
  {
    Map<String, Object> result = new LinkedHashMap<String, Object>();

    int count = service.SelectIncomeCount();

    int limit = 10;
    int offset = (page_num - 1) * limit;
    int end_page = (count + limit - 1) / limit;

    List<Income> income = service.SearchIncome(offset, limit);

    result.put("income", income);
    result.put("page_num", page_num);
    result.put("end_page", end_page);
    result.put("offset", offset);

    System.out.println(result);

    return result;
  }

/*
  @ResponseBody
  @RequestMapping("income/incomeList")
  public Map<String, Object> incomeList(@RequestParam("income_type") String income_type) {
    System.out.println(123);
    Map<String, Object> result = new LinkedHashMap<String, Object>();

    List<Income> income = service.SearchIncomeList(income_type);

    result.put("income", income);

    System.out.println(income);

    return result;
  }*/
}
