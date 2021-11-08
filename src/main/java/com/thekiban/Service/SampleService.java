package com.thekiban.Service;

import com.thekiban.Entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SampleService {

  // 시교자원 목록
  List<Sample> SelectSampleList();
  
  // 시교자원 등록
  int InsertSample(Sample sample);

  // 수출관리 등록
  int InsertSampleOutcome(SampleOutcome sampleOutcome);

  // 시교자원 갯수 조회
  int SelectSampleCount(String sample_name);

  // 시교자원 검색
  List<Sample> SearchSample(String sample_name, int offset, int limit);

  // 수출자원 갯수 조회
  int SelectOutcomeCount(String sample_name);

  // 수출자원 검색
  List<SampleOutcome> SearchOutcome(@Param("sample_name") String sample_name, @Param("offset") int offset, @Param("limit") int limit);

  // 시교자원 삭제
  int[] DeleteSample(int[] sample_id);

  // 수출자원 삭제
  int[] DeleteOutcome(int[] sample_outcome_id);

  // 시교자원 수정
  int UpdateSample(@Param("sample_id") int sample_id, @Param("sample_name") String sample_name, @Param("sample_value") String sample_value);

  int UpdateOutcome(@Param("sample_outcome_id") int sample_outcome_id, @Param("sample_outcome_name") String sample_outcome_name, @Param("sample_outcome_value") String sample_outcome_value);

  // 시교자원 수정
  int UpdateInsertSample(Sample sample);

  // 수출관리 수정
  int UpdateInsertOutcome(SampleOutcome sampleOutcome);

  // 시교자원 엑셀 등록
  int InsertExcel(Sample sample);

/*  // 첨부파일 목록
  List<SampleFile> SearchFileList(String sample_id);*/

  // 수출관리 엑셀 등록
  int InsertOutcomeExcel(SampleOutcome sampleOutcome);

  // 첨부파일 조회
  List<Uploads> SelectUploads(int[] sample_id);

  // 첨부파일 내용 삭제
  int DeleteFile(int[] sample_id);

  // 첨부파일 삭제
  int DeleteUploads(List<Uploads> uploads);

  // 첨부파일 목록 조회
  List<SampleFile> SelectSampleFile(int sample_id);

  // 첨부파일 내용 등록
  int InsertSampleFile(SampleFile sample_file);

  // 첨부파일 등록
  int InsertSampleUpload(Uploads upload);

  // 첨부파일 내용 수정
  int UpdateSampleFile(SampleFile sample_file);

  // 첨부파일 수정
  int UpdateSampleUpload(Uploads upload);

}
