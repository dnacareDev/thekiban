package com.thekiban.Mapper;

import com.thekiban.Entity.Income;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IncomeMapper {

  // 도입자원 등록
  int InsertIncome(Income income);


  // 원종 갯수 조회
  int SelectIncomeCount();

  // 원종 검색
  List<Income> SearchIncome(@Param("offset") int offset, @Param("limit") int limit);

  int[] DeleteIncome(int[] income_id);

}
