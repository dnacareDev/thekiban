package com.thekiban.Controller;

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
  @GetMapping(value = "income")
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

  @ResponseBody
  @RequestMapping("incomeList")
  public Map<String, Object> incomeList(@RequestParam("income_type") String income_type) {
    System.out.println(123);
    Map<String, Object> result = new LinkedHashMap<String, Object>();

    List<Income> income = service.SearchIncomeList(income_type);

    result.put("income", income);

    System.out.println(income);

    return result;
  }
}
