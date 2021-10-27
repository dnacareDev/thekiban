package com.thekiban.Service;

import com.thekiban.Entity.Income;

import java.util.List;

public interface IncomeService {

  // 도입자원 등록
  int InsertIncome(Income income);

  // 목록 개수
  int SelectIncomeListCount(String income_type);

  // 도입자원 목록 읽어오기
  List<Income> SearchIncomeList(String income_type);

}
