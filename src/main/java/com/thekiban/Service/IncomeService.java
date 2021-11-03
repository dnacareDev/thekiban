package com.thekiban.Service;

import com.thekiban.Entity.Income;

import java.util.List;

public interface IncomeService {

  // 도입자원 목록
  List<Income> SelectIncomeList();

  // 도입자원 등록
  int InsertIncome(Income income);

  // 도입자원 갯수 조회
  int SelectIncomeCount(String income_name);

  // 도입자원 검색
  List<Income> SearchIncome(String income_name, int offset, int limit);

  // 도입자원 삭제
  int[] DeleteIncome(int[] income_id);

  // 도입자원 수정
  int UpdateIncome(Income income);
}
