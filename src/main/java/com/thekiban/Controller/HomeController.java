package com.thekiban.Controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.thekiban.Entity.*;
import com.thekiban.Service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping(value = "/")
public class HomeController {

  @Autowired
  private HomeService service;

  @RequestMapping("/home")
  public ModelAndView getHome(ModelAndView mv) {
    mv.setViewName("home/kiban_home");

    return mv;
  }

  @RequestMapping("/test1")
  public ModelAndView test1(ModelAndView mv) {
    mv.setViewName("template/example47");

    return mv;
  }

  @RequestMapping("/test2")
  public ModelAndView test2(ModelAndView mv) {
    mv.setViewName("template/example52");

    return mv;
  }

  @ResponseBody
  @RequestMapping("selectOutcomeList")
  public Map<String, Object> SearchOutcome(@RequestParam("sample_name") String sample_name) {
    Map<String, Object> result1 = new LinkedHashMap<String, Object>();

    List<SampleOutcome> SampleOutcome = service.SearchOutcome(sample_name);

    result1.put("sampleOutcome", SampleOutcome);

    return result1;
  }

  @ResponseBody
  @RequestMapping("searchChart")
  public Map<String, Object> SearchBreed(Authentication auth, @RequestParam("breed_name") String breed_name) {
    Map<String, Object> result = new LinkedHashMap<String, Object>();

    User user = (User) auth.getPrincipal();

    List<Breed> breed = service.SearchBreed(breed_name); // 품종 검색
    List<Detail> detail = service.SearchBreedDetail(breed_name);   // 품종 작물별 컬럼 조회
    List<Display> display = service.SelectDisplay(user.getUser_id(), breed_name);  // 사용자별 품종 표시항목 조회

    List<Standard> standard = new ArrayList<Standard>();

    if (!detail.isEmpty()) {
      for (int i = 0; i < breed.size(); i++) {
        standard = service.SearchBreedStandard(detail, user.getUser_id(), breed.get(i).getBreed_id());

        breed.get(i).setBreed_standard(standard);
      }
    }

    result.put("breed", breed);
    result.put("detail", detail);
    result.put("display", display);

    return result;
  }
}