package com.thekiban.Service;

import com.thekiban.Entity.Sample;

import java.util.List;

public interface SampleService {

  // 시교자원 등록
  int InsertSample(Sample sample);

  // 원종 갯수 조회
  int SelectSampleCount();

  // 원종 검색
  List<Sample> SearchSample(int offset, int limit);

  int[] DeleteSample(int[] sample_id);
}
