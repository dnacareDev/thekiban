package com.thekiban.Service;

import com.thekiban.Entity.Sample;

import java.util.List;

public interface SampleService {

  // 시교자원 목록
  List<Sample> SelectSampleList();
  
  // 시교자원 등록
  int InsertSample(Sample sample);

  // 시교자원 갯수 조회
  int SelectSampleCount(String sample_name);

  // 시교자원 검색
  List<Sample> SearchSample(String sample_name, int offset, int limit);

  // 삭제
  int[] DeleteSample(int[] sample_id);

  int UpdateSample(int sample_id);
}
