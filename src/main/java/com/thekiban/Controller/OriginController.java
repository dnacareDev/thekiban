package com.thekiban.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/genome")
public class OriginController {

  // 원종 관리 페이지
  @GetMapping(value = "/basic")
  public ModelAndView getOriginList(ModelAndView mv) {

    mv.setViewName("genome/basic");

    return mv;
  }

}
