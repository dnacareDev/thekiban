package com.thekiban.ServiceImpl;

import com.thekiban.Entity.Sample;
import com.thekiban.Mapper.IncomeMapper;
import com.thekiban.Service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SampleServiceImpl implements SampleService {

  @Autowired
  private IncomeMapper mapper;

  @Override
  public int InsertSample(Sample sample) {
    return mapper.InsertSample(sample);
  }

}
