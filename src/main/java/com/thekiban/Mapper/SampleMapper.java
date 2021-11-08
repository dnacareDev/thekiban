package com.thekiban.Mapper;

import com.thekiban.Entity.Sample;
import com.thekiban.Entity.SampleFile;
import com.thekiban.Entity.SampleOutcome;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SampleMapper {

  // 시교자원 목록 조회
  List<Sample> SelectSampleList();

  // 시교자원 등록
  int InsertSample(Sample sample);

  // 수출관리 등록
  int InsertSampleOutcome(SampleOutcome sampleOutcome);
  
  // 시교자원 갯수 조회
  int SelectSampleCount(String sample_name);

  // 시교자원 검색
  List<Sample> SearchSample(@Param("sample_name") String sample_name, @Param("offset") int offset, @Param("limit") int limit);

  // 수출자원 갯수 조회
  int SelectOutcomeCount(String sample_name);

  // 수출자원 검색
  List<SampleOutcome> SearchOutcome(@Param("sample_name") String sample_name, @Param("offset") int offset, @Param("limit") int limit);

  // 시교자원 삭제
  int[] DeleteSample(int[] sample_id);

  // 수출자원 삭제
  int[] DeleteOutcome(int[] sample_outcome_id);

  // 시교자원 수정
  int UpdateSample(Sample sample);

  // 수출관리 수정
  int UpdateOutcome(SampleOutcome sampleOutcome);

  // 시교자원 엑셀 등록
  int InsertExcel(Sample sample);

  // 첨부파일 목록
  List<SampleFile> SearchFileList(@Param("sample_id") String sample_id);

  // 수출관리 엑셀 등록
  int InsertOutcomeExcel(SampleOutcome sampleOutcome);
}

