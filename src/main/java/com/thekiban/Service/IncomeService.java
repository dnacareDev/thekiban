package com.thekiban.Service;

import com.thekiban.Entity.Income;

import java.util.List;

public interface IncomeService {

  // 도입자원 등록
  int InsertIncome(Income income);

  // 원종 갯수 조회
  int SelectIncomeCount(String income_name);

  // 원종 검색
  List<Income> SearchIncome(String income_name, int offset, int limit);

  int[] DeleteIncome(int[] income_id);

}
