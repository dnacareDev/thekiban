package com.thekiban.Mapper;

import com.thekiban.Entity.Income;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IncomeMapper {

  // 도입자원 등록
  int InsertIncome(Income income);

}
