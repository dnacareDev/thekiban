package com.thekiban.ServiceImpl;

import com.thekiban.Entity.Income;
import com.thekiban.Mapper.IncomeMapper;
import com.thekiban.Service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncomeServiceImpl implements IncomeService {

  @Autowired
  private IncomeMapper mapper;

  @Override
  public int InsertIncome(Income income) {
    return mapper.InsertIncome(income);
  }

}
