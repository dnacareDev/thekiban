package com.thekiban.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/genome")
public class GenomeController {

  // 품종 관리 페이지
  @GetMapping(value = "genetic")
  public ModelAndView getGeneticList(ModelAndView mv) {

    mv.setViewName("genome/genetic");

    return mv;
  }

  // 원종 관리 페이지
  @GetMapping(value = "origin")
  public ModelAndView getOriginList(ModelAndView mv) {

    mv.setViewName("genome/origin");

    return mv;
  }

  //도입자원 관리 페이지
  @GetMapping(value = "intro_resource")
  public ModelAndView getIntroResourceList(ModelAndView mv) {

    mv.setViewName("genome/intro_resource");

    return mv;
  }

  //시교자원 관리 페이지
  @GetMapping(value = "test_harvest")
  public ModelAndView getTestHarvestList(ModelAndView mv) {

    mv.setViewName("genome/test_harvest");

    return mv;
  }
}
