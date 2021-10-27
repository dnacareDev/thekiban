package com.thekiban.ServiceImpl;

import com.thekiban.Entity.Basic;
import com.thekiban.Entity.Sample;
import com.thekiban.Mapper.SampleMapper;
import com.thekiban.Service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SampleServiceImpl implements SampleService {

  @Autowired
  private SampleMapper mapper;

  @Override
  public int InsertSample(Sample sample) {
    return mapper.InsertSample(sample);
  }

  // 원종 갯수 조회
  @Override
  public int SelectSampleCount()
  {
    return mapper.SelectSampleCount();
  }

  // 원종 검색
  @Override
  public List<Sample> SearchSample(int offset, int limit)
  {
    return mapper.SearchSample(offset, limit);
  }
}
