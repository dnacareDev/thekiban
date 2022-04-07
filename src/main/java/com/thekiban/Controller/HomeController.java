package com.thekiban.Controller;

import java.util.*;

import com.thekiban.Entity.Location;
import com.thekiban.Entity.SampleOutcome;
import com.thekiban.Service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.thekiban.Entity.Breed;
import com.thekiban.Entity.ChartCount;
import com.thekiban.Service.HomeService;

@Controller
@RequestMapping(value = "/")
public class HomeController {
  @Autowired
  private HomeService service;

  @Autowired
  private LocationService locationService;

  @RequestMapping("/")
  public ModelAndView Index(ModelAndView mv) {
    mv.setViewName("home/kiban_home");

    return mv;
  }

  @RequestMapping("/home")
  public ModelAndView Home(ModelAndView mv) {
    mv.setViewName("home/kiban_home");

    return mv;
  }

  @ResponseBody
  @RequestMapping("selectChart")
  public Map<String, Object> SelectChart() {
    Map<String, Object> result = new LinkedHashMap<String, Object>();

    ChartCount breed = service.SelectChartBreed();
    ChartCount sales = service.SelectChartSales();
    ChartCount apply = service.SelectChartApply();
    ChartCount protect = service.SelectChartProtect();

    result.put("breed", breed);
    result.put("sales", sales);
    result.put("apply", apply);
    result.put("protect", protect);

    return result;
  }

  @ResponseBody
  @RequestMapping("selectChartBar")
  public Map<Integer, Object> SelectChartBar() {
    Map<Integer, Object> result = new LinkedHashMap<Integer, Object>();

    Date date = new Date();

    for (int i = 1; i > -4; i--) {
      String year = (date.getYear() + 1900 + i) + "-01-01";

      List<Breed> breed = service.SelectChartBar(year);

      result.put((date.getYear() + 1899 + i), breed);
    }

    return result;
  }

  @ResponseBody
  @RequestMapping("selectOutcomeList")
  public Map<String, Object> SelectOutcome() {
    Map<String, Object> result = new LinkedHashMap<String, Object>();

//    List<SampleOutcome> sampleOutcomes = service.SelectOutcomeList();
    List<Location> locations = locationService.selectAll();

    List<Map<String, Object>> placeK = new ArrayList<>();
    List<Map<String, Object>> placeG = new ArrayList<>();

//    for (int i = 0; i < sampleOutcomes.size(); i++) {
//      if (sampleOutcomes.get(i).getSample_outcome_country().equals("국내")) {
//        if (!placeK.contains(sampleOutcomes.get(i).getSample_outcome_place())) {
//          placeK.add(sampleOutcomes.get(i).getSample_outcome_place());
//        }
//      } else if (sampleOutcomes.get(i).getSample_outcome_country().equals("해외")) {
//        if (!placeG.contains(sampleOutcomes.get(i).getSample_outcome_place())) {
//          placeG.add(sampleOutcomes.get(i).getSample_outcome_place());
//        }
//      }
//    }

    Map<String, Object> latlngKo = new LinkedHashMap<>();
    Map<String, Object> latlngGlo = new LinkedHashMap<>();

    for (Location location : locations) {
      if (location.getLocation_type().equals("국내")) {
        latlngKo.put("lat", Double.parseDouble(location.getLocation_lat()));
        latlngKo.put("lng", Double.parseDouble(location.getLocation_lng()));
        placeK.add(latlngKo);
      } else if (location.getLocation_type().equals("해외")) {
        latlngGlo.put("lat", Double.parseDouble(location.getLocation_lat()));
        latlngGlo.put("lng", Double.parseDouble(location.getLocation_lng()));
        placeG.add(latlngGlo);
      }
    }

    result.put("sampleOutcomeKo", placeK);
    result.put("sampleOutcomeGlo", placeG);

    return result;
  }
}