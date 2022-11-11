package com.thekiban.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.thekiban.Entity.Breed;
import com.thekiban.Entity.ChartCount;
import com.thekiban.Entity.Location;
import com.thekiban.Entity.SampleOutcome;
import com.thekiban.Service.HomeService;
import com.thekiban.Service.LocationService;

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
  public Map<Integer, Object> SelectChartBar() throws ParseException {
    Map<Integer, Object> result = new LinkedHashMap<Integer, Object>();

  
    Date date = new Date();

    for (int i = 1; i > -6; i--) {
      String year = (date.getYear() + 1900 + i) + "-01-01";

      System.out.println("year : " + year);
      
      //List<Breed> breed = service.SelectChartBar(year);
      //System.out.println("breed : " + breed);
      
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
      Date fDate = formatter.parse(year);
      Date sDate = formatter.parse("2023-01-01");
      
      
      List<Breed> breed = new ArrayList<>();
      if(fDate.compareTo(sDate) > 0) {
    	  breed = service.SelectChartBar(year);
    	  result.put((date.getYear() + 1899 + i), breed);
      } else {
    	  System.out.println(year);
    	  Breed pepper = new Breed();
    	  Breed welshOnion = new Breed();
    	  Breed melon = new Breed();
    	  Breed radish = new Breed();
    	  Breed napacabbage = new Breed();
    	  Breed watermelon = new Breed();
    	  Breed cabbage = new Breed();
    	  Breed onion = new Breed();
    	  Breed koreanmelon = new Breed();
    	  Breed tomato = new Breed();
    	  
    	  //주의 : switch에 들어가는 year변수는 1년씩 빼줘야 의미가 맞다. case "2017-01-01":은 2016년도의 데이터를 의미함. 
    	  switch(year) {
    	  	case "2017-01-01":
    	  		pepper.setBreed_name("고추");
    	  		pepper.setBreed_count(2);
    	  		breed.add(pepper);
//    	  		welshOnion.setBreed_name("대파");
//    	  		welshOnion.setBreed_count(0);
//    	  		breed.add(welshOnion);
//    	  		melon.setBreed_name("멜론");
//    	  		melon.setBreed_count(0);
//    	  		breed.add(melon);
//    	  		radish.setBreed_name("무");
//    	  		radish.setBreed_count(0);
//    	  		breed.add(radish);
//    	  		napacabbage.setBreed_name("배추");
//    	  		napacabbage.setBreed_count(0);
//    	  		breed.add(napacabbage);
//    	  		watermelon.setBreed_name("수박");
//    	  		watermelon.setBreed_count(0);
//    	  		breed.add(watermelon);
//    	  		cabbage.setBreed_name("양배추");
//    	  		cabbage.setBreed_count(0);
//    	  		breed.add(cabbage);
//    	  		onion.setBreed_name("양파");
//    	  		onion.setBreed_count(0);
//    	  		breed.add(onion);
    	  		koreanmelon.setBreed_name("참외");
    	  		koreanmelon.setBreed_count(1);
    	  		breed.add(koreanmelon);
//    	  		tomato.setBreed_name("토마토");
//    	  		tomato.setBreed_count(0);
//    	  		breed.add(tomato);
    	  		result.put((date.getYear() + 1899 + i), breed);
    	  		break;
    	  	case "2018-01-01":
    	  		pepper.setBreed_name("고추");
    	  		pepper.setBreed_count(5);
    	  		breed.add(pepper);
//    	  		welshOnion.setBreed_name("대파");
//    	  		welshOnion.setBreed_count(0);
//    	  		breed.add(welshOnion);
    	  		melon.setBreed_name("멜론");
    	  		melon.setBreed_count(6);
    	  		breed.add(melon);
    	  		radish.setBreed_name("무");
    	  		radish.setBreed_count(4);
    	  		breed.add(radish);
    	  		napacabbage.setBreed_name("배추");
    	  		napacabbage.setBreed_count(4);
    	  		breed.add(napacabbage);
//    	  		watermelon.setBreed_name("수박");
//    	  		watermelon.setBreed_count(0);
//    	  		breed.add(watermelon);
    	  		cabbage.setBreed_name("양배추");
    	  		cabbage.setBreed_count(4);
    	  		breed.add(cabbage);
//    	  		onion.setBreed_name("양파");
//    	  		onion.setBreed_count(0);
//    	  		breed.add(onion);
    	  		koreanmelon.setBreed_name("참외");
    	  		koreanmelon.setBreed_count(4);
    	  		breed.add(koreanmelon);
    	  		tomato.setBreed_name("토마토");
    	  		tomato.setBreed_count(2);
    	  		breed.add(tomato);
    	  		result.put((date.getYear() + 1899 + i), breed);
    	  		break;
    	  	case "2019-01-01":
    	  		pepper.setBreed_name("고추");
    	  		pepper.setBreed_count(11);
    	  		breed.add(pepper);
    	  		welshOnion.setBreed_name("대파");
    	  		welshOnion.setBreed_count(1);
    	  		breed.add(welshOnion);
//    	  		melon.setBreed_name("멜론");
//    	  		melon.setBreed_count(10);
//    	  		breed.add(melon);
    	  		radish.setBreed_name("무");
    	  		radish.setBreed_count(2);
    	  		breed.add(radish);
    	  		napacabbage.setBreed_name("배추");
    	  		napacabbage.setBreed_count(6);
    	  		breed.add(napacabbage);
//    	  		watermelon.setBreed_name("수박");
//    	  		watermelon.setBreed_count(0);
//    	  		breed.add(watermelon);
//    	  		cabbage.setBreed_name("양배추");
//    	  		cabbage.setBreed_count(0);
//    	  		breed.add(cabbage);
    	  		onion.setBreed_name("양파");
    	  		onion.setBreed_count(6);
    	  		breed.add(onion);
    	  		koreanmelon.setBreed_name("참외");
    	  		koreanmelon.setBreed_count(7);
    	  		breed.add(koreanmelon);
    	  		tomato.setBreed_name("토마토");
    	  		tomato.setBreed_count(9);
    	  		breed.add(tomato);
    	  		result.put((date.getYear() + 1899 + i), breed);
    	  		break;
    	  	case "2020-01-01":
    	  		pepper.setBreed_name("고추");
    	  		pepper.setBreed_count(8);
    	  		breed.add(pepper);
//    	  		welshOnion.setBreed_name("대파");
//    	  		welshOnion.setBreed_count(0);
//    	  		breed.add(welshOnion);
//    	  		melon.setBreed_name("멜론");
//    	  		melon.setBreed_count(0);
//    	  		breed.add(melon);
    	  		radish.setBreed_name("무");
    	  		radish.setBreed_count(3);
    	  		breed.add(radish);
    	  		napacabbage.setBreed_name("배추");
    	  		napacabbage.setBreed_count(2);
    	  		breed.add(napacabbage);
//    	  		watermelon.setBreed_name("수박");
//    	  		watermelon.setBreed_count(0);
//    	  		breed.add(watermelon);
    	  		cabbage.setBreed_name("양배추");
    	  		cabbage.setBreed_count(3);
    	  		breed.add(cabbage);
//    	  		onion.setBreed_name("양파");
//    	  		onion.setBreed_count(0);
//    	  		breed.add(onion);
    	  		koreanmelon.setBreed_name("참외");
    	  		koreanmelon.setBreed_count(1);
    	  		breed.add(koreanmelon);
    	  		tomato.setBreed_name("토마토");
    	  		tomato.setBreed_count(6);
    	  		breed.add(tomato);
    	  		result.put((date.getYear() + 1899 + i), breed);
    	  		break;
    	  	case "2021-01-01":
    	  		pepper.setBreed_name("고추");
    	  		pepper.setBreed_count(8);
    	  		breed.add(pepper);
//    	  		welshOnion.setBreed_name("대파");
//    	  		welshOnion.setBreed_count(0);
//    	  		breed.add(welshOnion);
    	  		melon.setBreed_name("멜론");
    	  		melon.setBreed_count(1);
    	  		breed.add(melon);
    	  		radish.setBreed_name("무");
    	  		radish.setBreed_count(3);
    	  		breed.add(radish);
    	  		napacabbage.setBreed_name("배추");
    	  		napacabbage.setBreed_count(6);
    	  		breed.add(napacabbage);
//    	  		watermelon.setBreed_name("수박");
//    	  		watermelon.setBreed_count(0);
//    	  		breed.add(watermelon);
    	  		cabbage.setBreed_name("양배추");
    	  		cabbage.setBreed_count(2);
    	  		breed.add(cabbage);
//    	  		onion.setBreed_name("양파");
//    	  		onion.setBreed_count(0);
//    	  		breed.add(onion);
    	  		koreanmelon.setBreed_name("참외");
    	  		koreanmelon.setBreed_count(3);
    	  		breed.add(koreanmelon);
    	  		tomato.setBreed_name("토마토");
    	  		tomato.setBreed_count(4);
    	  		breed.add(tomato);
    	  		result.put((date.getYear() + 1899 + i), breed);
    	  		break;
    	  	case "2022-01-01":
    	  		pepper.setBreed_name("고추");
    	  		pepper.setBreed_count(9);
    	  		breed.add(pepper);
    	  		//welshOnion.setBreed_name("대파");
    	  		//welshOnion.setBreed_count(0);
    	  		//breed.add(welshOnion);
    	  		melon.setBreed_name("멜론");
    	  		melon.setBreed_count(1);
    	  		breed.add(melon);
    	  		radish.setBreed_name("무");
    	  		radish.setBreed_count(2);
    	  		breed.add(radish);
    	  		napacabbage.setBreed_name("배추");
    	  		napacabbage.setBreed_count(3);
    	  		breed.add(napacabbage);
    	  		watermelon.setBreed_name("수박");
    	  		watermelon.setBreed_count(1);
    	  		breed.add(watermelon);
//    	  		cabbage.setBreed_name("양배추");
//    	  		cabbage.setBreed_count(0);
//    	  		breed.add(cabbage);
//    	  		onion.setBreed_name("양파");
//    	  		onion.setBreed_count(0);
//    	  		breed.add(onion);
    	  		koreanmelon.setBreed_name("참외");
    	  		koreanmelon.setBreed_count(1);
    	  		breed.add(koreanmelon);
    	  		tomato.setBreed_name("토마토");
    	  		tomato.setBreed_count(2);
    	  		breed.add(tomato);
    	  		result.put((date.getYear() + 1899 + i), breed);
    	  		break;
    	  	case "2023-01-01":
    	  		pepper.setBreed_name("고추");
    	  		pepper.setBreed_count(7);
    	  		breed.add(pepper);
//    	  		welshOnion.setBreed_name("대파");
//    	  		welshOnion.setBreed_count(0);
//    	  		breed.add(welshOnion);
//    	  		melon.setBreed_name("멜론");
//    	  		melon.setBreed_count(0);
//    	  		breed.add(melon);
//    	  		radish.setBreed_name("무");
//    	  		radish.setBreed_count(0);
//    	  		breed.add(radish);
    	  		napacabbage.setBreed_name("배추");
    	  		napacabbage.setBreed_count(4);
    	  		breed.add(napacabbage);
    	  		watermelon.setBreed_name("수박");
    	  		watermelon.setBreed_count(1);
    	  		breed.add(watermelon);
//    	  		cabbage.setBreed_name("양배추");
//    	  		cabbage.setBreed_count(10);
//    	  		breed.add(cabbage);
//    	  		onion.setBreed_name("양파");
//    	  		onion.setBreed_count(10);
//    	  		breed.add(onion);
    	  		koreanmelon.setBreed_name("참외");
    	  		koreanmelon.setBreed_count(4);
    	  		breed.add(koreanmelon);
    	  		tomato.setBreed_name("토마토");
    	  		tomato.setBreed_count(3);
    	  		breed.add(tomato);
    	  		result.put((date.getYear() + 1899 + i), breed);
    	  }
      }
      
      //result.put((date.getYear() + 1899 + i), breed);
    }
  
    System.out.println("result : " + result);
    
    /*
    Calendar calendar = Calendar.getInstance();
    
    for(int i = 1; i > -4; i--) {
    	String year = calendar.get(Calendar.YEAR) + i - 1 + "-01-01 00:00:00";
    	
    	List<Breed> breed = service.SelectChartBar(year);
    	
    	System.out.println("year" + year);

    	result.put(calendar.get(Calendar.YEAR) + i - 1, breed);
    	
    }
*/
    return result;
  }

  @ResponseBody
  @RequestMapping("selectOutcomeList")
  public Map<String, Object> SelectOutcome(@RequestParam("user_crop") String user_crop) {	// user_crop param 생성중. 망가지면 param을 지운다
	  											// String user_crop
    Map<String, Object> result = new LinkedHashMap<String, Object>();

    //List<Location> locations = locationService.selectAll();
    List<Location> locations = locationService.selectAll(user_crop);

    List<Map<String, Object>> placeK = new ArrayList<>();
    List<Map<String, Object>> placeG = new ArrayList<>();

    for (Location location : locations) {
      Map<String, Object> latlngKo = new LinkedHashMap<>();
      Map<String, Object> latlngGlo = new LinkedHashMap<>();

      if (location.getLocation_type().equals("국내")) {
        latlngKo.put("lat", Double.parseDouble(location.getLocation_lat()));
        latlngKo.put("lng", Double.parseDouble(location.getLocation_lng()));
        latlngKo.put("name", location.getLocation_name());

        placeK.add(latlngKo);
      } else if (location.getLocation_type().equals("해외")) {
        latlngGlo.put("lat", Double.parseDouble(location.getLocation_lat()));
        latlngGlo.put("lng", Double.parseDouble(location.getLocation_lng()));
        latlngGlo.put("name", location.getLocation_name());							// 글로벌 작업중. 한국과 똑같이 하면 된다

        placeG.add(latlngGlo);
      }
    }

    result.put("sampleOutcomeKo", placeK);
    result.put("sampleOutcomeGlo", placeG);

    return result;
  }
  
  // searchSeed2 생성중. 망가지면 이 밑으로 다 지운다
  @ResponseBody
  @RequestMapping("searchSeed2")
  public Map<String, Object> SearchSeed2(@RequestParam("sample_outcome_place") String sample_outcome_place) {
	  Map<String, Object> result2 = new LinkedHashMap<String, Object>();
	  
	  List<SampleOutcome> SampleOutcome = service.SearchSeed2(sample_outcome_place);		

	  																						
	  result2.put("sampleOutcome", SampleOutcome);
	  return result2;							
  }

  // 아이디 권한에 상관없이 모든 지역을 다 부르는 클래스 생성중. param을 집어넣어 수정하기 이전의 코드를 적당히 고치면 됨.
  @ResponseBody
  @RequestMapping("selectOutcomeListAll")
  public Map<String, Object> SelectOutcome() {
    Map<String, Object> result = new LinkedHashMap<String, Object>();
    
    List<Location> locations = locationService.selectAll2();
    
    List<Map<String, Object>> placeK = new ArrayList<>();
    List<Map<String, Object>> placeG = new ArrayList<>();

    for (Location location : locations) {
      Map<String, Object> latlngKo = new LinkedHashMap<>();
      Map<String, Object> latlngGlo = new LinkedHashMap<>();

      if (location.getLocation_type().equals("국내")) {
        latlngKo.put("lat", Double.parseDouble(location.getLocation_lat()));
        latlngKo.put("lng", Double.parseDouble(location.getLocation_lng()));
        latlngKo.put("name", location.getLocation_name());

        placeK.add(latlngKo);
      } else if (location.getLocation_type().equals("해외")) {
        latlngGlo.put("lat", Double.parseDouble(location.getLocation_lat()));
        latlngGlo.put("lng", Double.parseDouble(location.getLocation_lng()));
        latlngGlo.put("name", location.getLocation_name());							

        placeG.add(latlngGlo);
      }
    }

    result.put("sampleOutcomeKo", placeK);
    result.put("sampleOutcomeGlo", placeG);

    return result;
  }
  
}