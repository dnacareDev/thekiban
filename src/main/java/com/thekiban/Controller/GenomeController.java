package com.thekiban.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/genome")
public class GenomeController {

  // 품종 관리 페이지
  @GetMapping(value = "/breed")
  public ModelAndView getGeneticList(ModelAndView mv) {

    mv.setViewName("genome/breed");

    return mv;
  }

  //도입자원 관리 페이지
  @GetMapping(value = "/income")
  public ModelAndView getIntroResourceList(ModelAndView mv) {

    mv.setViewName("genome/income");

    return mv;
  }

  //시교자원 관리 페이지
  @GetMapping(value = "/sample")
  public ModelAndView getTestHarvestList(ModelAndView mv) {

    mv.setViewName("genome/sample");

    return mv;
  }
}
