package com.thekiban.Controller;

import com.thekiban.Entity.Breed;
import com.thekiban.Entity.Detail;
import com.thekiban.Entity.Standard;
import com.thekiban.Service.BreedService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
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
  public ModelAndView InsertBreed(ModelAndView mv, @ModelAttribute Breed breed, @RequestParam("detail_list") String detail_list)
  {
    int insert_breed = service.InsertBreed(breed);

    JSONArray arr = new JSONArray(detail_list);

    List<Standard> standard = new ArrayList<Standard>();

    for(int i = 0; i < arr.length(); i++) {
      Standard item = new Standard();

      JSONObject obj = arr.getJSONObject(i);

      String detail_id = (String)obj.get("key");
      String value = (String)obj.get("value");

      if(value != ""){
        item.setBreed_id(breed.getBreed_id());
        item.setDetail_id(Integer.parseInt(detail_id));
        item.setStandard((String) obj.get("value"));

        standard.add(item);
      }
    }

    if(insert_breed != 0) {
      int insert_standard = service.InsertStandard(standard);
    }

    mv.setViewName("redirect:/breed");

    return mv;
  }

}
