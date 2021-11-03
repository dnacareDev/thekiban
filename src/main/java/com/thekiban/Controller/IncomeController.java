package com.thekiban.Controller;

import com.thekiban.Entity.Income;
import com.thekiban.Service.IncomeService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

  @ResponseBody
  @RequestMapping("insertIncome")
  public Income InsertIncome(@ModelAttribute Income income, @RequestParam("input_data") String input_data) {
    JSONArray arr = new JSONArray(input_data);

    JSONObject obj = arr.getJSONObject(0);

    String value = (String) obj.get("value");

    income.setIncome_name(value);

    service.InsertIncome(income);

    return income;
  }

  // 도입자원 검색
  @ResponseBody
  @RequestMapping("searchIncome")
  public Map<String, Object> SearchIncome(@RequestParam("income_name") String income_name, @RequestParam("page_num") int page_num) {
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
  public ModelAndView UpdateSample(ModelAndView mv, @ModelAttribute Income income, @RequestParam(value = "update_list", required = false) String update_list, @RequestParam("data") String data) {
    /*JSONArray arr = new JSONArray(update_list);

    JSONObject obj = arr.getJSONObject(0);

    String value = (String) obj.get("value");

    income.setIncome_id(Integer.parseInt(value));*/

    JSONArray arr = new JSONArray(data);

    JSONObject input_id = arr.getJSONObject(0);

    int value_id = (Integer) input_id.get("value");

    income.setIncome_id(value_id);

    for (int i = 1; i < arr.length(); i++) {
      JSONObject obj = arr.getJSONObject(i);

      String key_id = (String) obj.get("key");
      String value = (String) obj.get("value");

      if (!value.equals("")) {
        if (key_id.equals("income_name")) {
          income.setIncome_name(value);
        } else if (key_id.equals("income_code")) {
          income.setIncome_code(value);
        } else if (key_id.equals("income_kind")) {
          income.setIncome_kind(value);
        } else if (key_id.equals("income_division")) {
          income.setIncome_division(value);
        } else if (key_id.equals("income_place")) {
          income.setIncome_place(value);
        } else if (key_id.equals("income_country")) {
          income.setIncome_country(value);
        } else if (key_id.equals("income_person")) {
          income.setIncome_person(value);
        } else if (key_id.equals("income_date")) {
          income.setIncome_date(value);
        } else if (key_id.equals("income_num")) {
          income.setIncome_num(Integer.parseInt(value));
        } else if (key_id.equals("income_type")) {
          income.setIncome_type(value);
        }else if (key_id.equals("income_trait")) {
          income.setIncome_trait(value);
        } else if (key_id.equals("income_comment")) {
          income.setIncome_comment(value);
        }
      } else {
        if (key_id.equals("income_name")) {
          income.setIncome_name("");
        } else if (key_id.equals("income_code")) {
          income.setIncome_code("");
        } else if (key_id.equals("income_kind")) {
          income.setIncome_kind("");
        } else if (key_id.equals("income_division")) {
          income.setIncome_division("");
        } else if (key_id.equals("income_place")) {
          income.setIncome_place("");
        } else if (key_id.equals("income_country")) {
          income.setIncome_country("");
        } else if (key_id.equals("income_person")) {
          income.setIncome_person("");
        } else if (key_id.equals("income_date")) {
          income.setIncome_date(null);
        } else if (key_id.equals("income_num")) {
          income.setIncome_num(0);
        } else if (key_id.equals("income_type")) {
          income.setIncome_type("");
        }else if (key_id.equals("income_trait")) {
          income.setIncome_trait("");
        } else if (key_id.equals("income_comment")) {
          income.setIncome_comment("");
        }
      }
    }
    service.UpdateIncome(income);

    mv.setViewName("redirect:/income");

    return mv;
  }

  // 엑셀 등록
  @RequestMapping("excelIncome")
  public ModelAndView excelUpload(ModelAndView mv, @ModelAttribute Income income, @RequestParam("excel_list") String excel_list) {
    JSONArray arr = new JSONArray(excel_list);

    for (int i = arr.length() - 1; i > -1; i--) {

      JSONObject obj = arr.getJSONObject(i);

      Set<String> key = obj.keySet();

      for (String k : key) {
        if (k.equals("작물")) {
          income.setIncome_name(obj.getString(k));
        } else if (k.equals("도입번호 (ID)")) {
          income.setIncome_code(obj.getString(k));
        } else if (k.equals("품종명")) {
          income.setIncome_kind(obj.getString(k));
        } else if (k.equals("구분")) {
          income.setIncome_division(obj.getString(k));
        } else if (k.equals("도입처")) {
          income.setIncome_place(obj.getString(k));
        } else if (k.equals("도입국가")) {
          income.setIncome_country(obj.getString(k));
        } else if (k.equals("도입자")) {
          income.setIncome_person(obj.getString(k));
        } else if (k.equals("도입연월일")) {
          income.setIncome_date(obj.getString(k));
        } else if (k.equals("현 종자량")) {
          income.setIncome_num(Integer.parseInt(obj.getString(k)));
        } else if (k.equals("타입")) {
          income.setIncome_type(obj.getString(k));
        } else if (k.equals("주요 특성")) {
          income.setIncome_trait(obj.getString(k));
        } else if (k.equals("비고")) {
          income.setIncome_comment(obj.getString(k));
        }
      }

      service.InsertIncome(income);
    }

    mv.setViewName("redirect:/income");

    return mv;
  }
}
