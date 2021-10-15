package com.thekiban.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/genome")
public class GenomeController {

  @GetMapping(value = "genetic")
  public ModelAndView getGeneticList(ModelAndView mv) {

    mv.setViewName("genome/genetic");

    return mv;
  }

  @GetMapping(value = "origin")
  public ModelAndView getOriginList(ModelAndView mv) {

    mv.setViewName("genome/origin");

    return mv;
  }

  @GetMapping(value = "intro_resource")
  public ModelAndView getIntroResourceList(ModelAndView mv) {

    mv.setViewName("genome/intro_resource");

    return mv;
  }

  @GetMapping(value = "test_harvest")
  public ModelAndView getTestHarvestList(ModelAndView mv) {

    mv.setViewName("genome/test_harvest");

    return mv;
  }
}
