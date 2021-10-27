package com.thekiban.Mapper;

import com.thekiban.Entity.Sample;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SampleMapper {

  // 시교자원 등록
  int InsertSample(Sample sample);

}
