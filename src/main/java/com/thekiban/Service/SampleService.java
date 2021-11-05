package com.thekiban.Service;

import com.thekiban.Entity.Sample;
import com.thekiban.Entity.SampleFile;
import com.thekiban.Entity.SampleOutcome;

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

  // 시교자원 삭제
  int[] DeleteSample(int[] sample_id);

  // 시교자원 수정
  int UpdateSample(Sample sample);

  // 시교자원 엑셀 등록
  int InsertExcel(Sample sample);

  // 첨부파일 목록
  List<SampleFile> SearchFileList(String sample_id);

  // 수출관리 엑셀 등록
  int InsertOutcomeExcel(SampleOutcome sampleOutcome);

}
