package com.thekiban.ServiceImpl;

import com.thekiban.Entity.Basic;
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

  // 원종 갯수 조회
  @Override
  public int SelectIncomeCount(String income_name)
  {
    return mapper.SelectIncomeCount(income_name);
  }

  // 원종 검색
  @Override
  public List<Income> SearchIncome(String income_name, int offset, int limit)
  {
    return mapper.SearchIncome(income_name, offset, limit);
  }

  @Override
  public int[] DeleteIncome(int[] income_id) {
    return mapper.DeleteIncome(income_id);
  }
}
