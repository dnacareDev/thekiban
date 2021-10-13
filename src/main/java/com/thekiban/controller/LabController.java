package com.thekiban.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/lab")
public class LabController {

  @GetMapping(value = "data_manage")
  public ModelAndView getDataManage(ModelAndView mv) {

    mv.setViewName("lab/data_manage");

    return mv;
  }

}
