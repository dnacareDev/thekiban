package com.thekiban.Controller;

import com.thekiban.Entity.Breed;
import com.thekiban.Entity.Detail;
import com.thekiban.Service.BreedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BreedController {

  @Autowired
  private BreedService service;

  // 품종 관리 페이지
  @RequestMapping("breed")
  public ModelAndView BreedList(ModelAndView mv) {

    List<Detail> detail = service.SelectDetail();

    mv.addObject("detail", detail);

    mv.setViewName("genome/breed");

    return mv;
  }

  // 품종 등록
  @RequestMapping("insertBreed")
  public ModelAndView InsertBreed(ModelAndView mv, @ModelAttribute Breed breed)
  {
    System.out.println(breed);

    mv.setViewName("redirect:/breed");

    return mv;
  }

}
