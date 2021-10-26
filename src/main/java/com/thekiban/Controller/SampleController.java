package com.thekiban.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// 시교자원
@Controller
@RequestMapping(value = "/genome")
public class SampleController {

  @GetMapping(value = "/sample")
  public ModelAndView Sample(ModelAndView mv) {
    mv.setViewName("genome/sample");

    return mv;
  }

}
