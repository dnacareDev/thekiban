package com.thekiban.ServiceImpl;

import com.thekiban.Entity.Income;
import com.thekiban.Mapper.IncomeMapper;
import com.thekiban.Service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeServiceImpl implements IncomeService {

  @Autowired
  private IncomeMapper mapper;

  // 도입자원 등록
  @Override
  public int InsertIncome(Income income) {
    return mapper.InsertIncome(income);
  }

  // 목록 개수
  @Override
  public int SelectIncomeListCount(String income_type) {
    return mapper.SelectIncomeListCount(income_type);
  }

  // 도입자원 목록 읽어오기
  @Override
  public List<Income> SearchIncomeList(String income_type) {
    return mapper.SearchIncomeList(income_type);
  }
}
