package com.thekiban.Controller;

import com.thekiban.Entity.Income;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class IncomeController {

  //도입자원 관리 페이지
  @GetMapping(value = "income")
  public ModelAndView getIntroResourceList(ModelAndView mv) {

    mv.setViewName("genome/income");

    return mv;
  }

  @RequestMapping("insertIncome")
  public ModelAndView InsertIncome(ModelAndView mv, @ModelAttribute Income income)
  {
    System.out.println(income);

    mv.setViewName("redirect:/income");

    return mv;
  }
}
