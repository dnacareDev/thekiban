package com.thekiban.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/genome")
public class IncomeController {

  //도입자원 관리 페이지
  @GetMapping(value = "/income")
  public ModelAndView getIntroResourceList(ModelAndView mv) {

    mv.setViewName("genome/income");

    return mv;
  }

}
