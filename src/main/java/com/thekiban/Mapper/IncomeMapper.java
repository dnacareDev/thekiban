package com.thekiban.Mapper;

import com.thekiban.Entity.Income;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IncomeMapper {

  // 도입자원 등록
  int InsertIncome(Income income);
  
  // 목록 개수
  int SelectIncomeListCount(@Param("income_type") String income_type);

  // 도입자원 목록
  List<Income> SearchIncomeList(@Param("income_type") String income_type);

}
