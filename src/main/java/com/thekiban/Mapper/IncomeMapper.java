package com.thekiban.Mapper;

import com.thekiban.Entity.Income;
import com.thekiban.Entity.Sample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IncomeMapper {

  // 도입자원 목록 조회
  List<Income> SelectIncomeList();
  
  // 도입자원 등록
  int InsertIncome(Income income);

  // 도입자원 갯수 조회
  int SelectIncomeCount(String income_name);

  // 도입자원 검색
  List<Income> SearchIncome(@Param("income_name") String income_name, @Param("offset") int offset, @Param("limit") int limit);

  // 도입자원 삭제
  int[] DeleteIncome(int[] income_id);

  // 도입자원 수정
  int UpdateIncome(Income income);

}
