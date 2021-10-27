package com.thekiban.Controller;

import com.thekiban.Entity.Sample;
import com.thekiban.Service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// 시교자원
@Controller
public class SampleController {

  @Autowired
  private SampleService service;

  // 시교자원 관리 페이지
  @GetMapping(value = "sample")
  public ModelAndView Sample(ModelAndView mv) {

    mv.setViewName("genome/sample");

    return mv;
  }

  @RequestMapping("insertSample")
  public ModelAndView InsertSample(ModelAndView mv, @ModelAttribute Sample sample)
  {
    service.InsertSample(sample);

    mv.setViewName("redirect:/sample");

    return mv;
  }
}
