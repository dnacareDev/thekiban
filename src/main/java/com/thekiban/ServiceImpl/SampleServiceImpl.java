package com.thekiban.ServiceImpl;

import com.thekiban.Entity.Sample;
import com.thekiban.Entity.SampleFile;
import com.thekiban.Entity.SampleOutcome;
import com.thekiban.Mapper.SampleMapper;
import com.thekiban.Service.SampleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SampleServiceImpl implements SampleService {

  @Autowired
  private SampleMapper mapper;

  @Override
  public List<Sample> SelectSampleList() {
    return mapper.SelectSampleList();
  }

  // 시교자원 등록
  @Override
  public int InsertSample(Sample sample) {
    return mapper.InsertSample(sample);
  }

  // 수출관리 등록
  @Override
  public int InsertSampleOutcome(SampleOutcome sampleOutcome) {
    return mapper.InsertSampleOutcome(sampleOutcome);
  }

  // 시교자원 갯수 조회
  @Override
  public int SelectSampleCount(String sample_name) {
    return mapper.SelectSampleCount(sample_name);
  }

  // 시교자원 검색
  @Override
  public List<Sample> SearchSample(String sample_name, int offset, int limit) {
    return mapper.SearchSample(sample_name, offset, limit);
  }

  // 수출자원 갯수 조회
  @Override
  public int SelectOutcomeCount(String sample_name) {
    return mapper.SelectOutcomeCount(sample_name);
  }

  // 수출자원 검색
  @Override
  public List<SampleOutcome> SearchOutcome(String sample_name, int offset, int limit) {
    return mapper.SearchOutcome(sample_name, offset, limit);
  }

  // 시교자원 삭제
  @Override
  public int[] DeleteSample(int[] sample_id) { return mapper.DeleteSample(sample_id); }

  // 수출자원 삭제
  @Override
  public int[] DeleteOutcome(int[] sample_outcome_id) {
    return mapper.DeleteOutcome(sample_outcome_id);
  }

  // 시교자원 수정
  @Override
  public int UpdateSample(Sample sample) {
    return mapper.UpdateSample(sample);
  }

  // 수출관리 수정
  @Override
  public int UpdateOutcome(SampleOutcome sampleOutcome) {
    return mapper.UpdateOutcome(sampleOutcome);
  }

  @Override
  public int InsertExcel(Sample sample) {
    return mapper.InsertExcel(sample);
  }

  @Override
  public List<SampleFile> SearchFileList(String sample_id) {
    return mapper.SearchFileList(sample_id);
  }

  @Override
  public int InsertOutcomeExcel(SampleOutcome sampleOutcome) {
    return mapper.InsertOutcomeExcel(sampleOutcome);
  }
}
