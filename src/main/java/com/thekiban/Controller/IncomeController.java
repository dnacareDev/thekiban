package com.thekiban.Controller;

import com.thekiban.Entity.Basic;
import com.thekiban.Entity.Income;
import com.thekiban.Entity.Sample;
import com.thekiban.Service.IncomeService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IncomeController {

  @Autowired
  private IncomeService service;

  //도입자원 관리 페이지
  @RequestMapping(value = "income")
  public ModelAndView getIntroResourceList(ModelAndView mv) {

    mv.setViewName("genome/income");

    return mv;
  }

  @RequestMapping("insertIncome")
  public ModelAndView InsertIncome(ModelAndView mv, @ModelAttribute Income income)
  {
    service.InsertIncome(income);

    mv.setViewName("redirect:/income");

    return mv;
  }

  // 도입자원 검색
  @ResponseBody
  @RequestMapping("searchIncome")
  public Map<String, Object> SearchIncome(@RequestParam("income_name") String income_name, @RequestParam("page_num") int page_num)
  {
    Map<String, Object> result = new LinkedHashMap<String, Object>();

    int count = service.SelectIncomeCount(income_name);

    int limit = 10;
    int offset = (page_num - 1) * limit;
    int end_page = (count + limit - 1) / limit;

    List<Income> income = service.SearchIncome(income_name, offset, limit);

    result.put("income", income);
    result.put("page_num", page_num);
    result.put("end_page", end_page);
    result.put("offset", offset);

    return result;
  }

  // 도입자원 삭제
  @ResponseBody
  @RequestMapping("deleteIncome")
  public int DeleteIncome(@RequestParam("income_id[]") int[] income_id) {
    service.DeleteIncome(income_id);

    return 1;
  }

  // 도입자원 수정
  @RequestMapping("updateIncome")
  public ModelAndView UpdateSample(ModelAndView mv, @ModelAttribute Income income, @RequestParam("update_list") String update_list)  {
    JSONArray arr = new JSONArray(update_list);

    JSONObject obj = arr.getJSONObject(0);

    String value = (String)obj.get("value");

    income.setIncome_id(Integer.parseInt(value));

    service.UpdateIncome(income);

    mv.setViewName("redirect:/income");

    return mv;
  }

  // 엑셀 등록
  @RequestMapping("excelIncome")
  public ModelAndView excelUpload(ModelAndView mv, @ModelAttribute Income income, @RequestParam("excel_list") String excel_list) {
    JSONArray arr = new JSONArray(excel_list);

    for (int i = 1; i < arr.length(); i++) {

      JSONObject obj = arr.getJSONObject(i);

      Iterator<String> keys = obj.keys();

      while(keys.hasNext())
      {
        String key = keys.next().toString();

        JSONObject obj2 = new JSONObject(obj.get(key).toString());

        String detail_id = (String) obj2.get("key");
        String value = (String) obj2.get("value");

        System.out.println(detail_id);

//        if(detail_id.equals("작물")) {
//          income.setSample_name(value);
//        } else if (detail_id.equals("시교명 (ID)")) {
//          income.setSample_code(value);
//        } else if (detail_id.equals("목표 지역")) {
//          income.setSample_country(value);
//        } else if (detail_id.equals("구분")) {
//          income.setSample_type(value);
//        } else if (detail_id.equals("교배번호")) {
//          income.setSample_mate(value);
//        } else if (detail_id.equals("종자번호 (ID)")) {
//          income.setSample_seed(value);
//        } else if (detail_id.equals("현 종자량(g)")) {
//          income.setSample_amount(Double.parseDouble(value));
//        } else if (detail_id.equals("기내 발아율(%)")) {
//          income.setSample_sprout(Integer.parseInt(value));
//        } else if (detail_id.equals("기내 순도율 (%)")) {
//          income.setSample_purity(Integer.parseInt(value));
//        } else if (detail_id.equals("비고")) {
//          income.setSample_comment(value);
//        }
      }

//      service.InsertIncome(income);
    }

    mv.setViewName("redirect:/income");

    return mv;
  }
}
