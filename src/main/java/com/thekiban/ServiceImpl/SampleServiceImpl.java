package com.thekiban.ServiceImpl;

import com.thekiban.Entity.*;
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

  @Override
  public List<SampleOutcome> SearchSeed(String sample_name) {
    return mapper.SearchSeed(sample_name);
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
  public int UpdateSample(int sample_id, String sample_name, String sample_value) {
    return mapper.UpdateSample(sample_id, sample_name, sample_value);
  }

  // 수출관리 수정
  @Override
  public int UpdateOutcome(int sample_outcome_id, String sample_outcome_name, String sample_outcome_value) {
    return mapper.UpdateOutcome(sample_outcome_id, sample_outcome_name, sample_outcome_value);
  }

  @Override
  public int UpdateInsertSample(Sample sample) {
    return mapper.UpdateInsertSample(sample);
  }

  @Override
  public int UpdateInsertOutcome(SampleOutcome sampleOutcome) {
    return mapper.UpdateInsertOutcome(sampleOutcome);
  }

  @Override
  public int InsertExcel(Sample sample) {
    return mapper.InsertExcel(sample);
  }

  @Override
  public int InsertOutcomeExcel(SampleOutcome sampleOutcome) {
    return mapper.InsertOutcomeExcel(sampleOutcome);
  }

  @Override
  public List<Uploads> SelectUploads(int[] sample_id) {
    return mapper.SelectUploads(sample_id);
  }

  @Override
  public int DeleteFile(int[] sample_id) {
    return mapper.DeleteFile(sample_id);
  }

  @Override
  public int DeleteUploads(List<Uploads> uploads) {
    return mapper.DeleteUploads(uploads);
  }

  @Override
  public List<SampleFile> SelectSampleFile(int sample_id) {
    return mapper.SelectSampleFile(sample_id);
  }

  @Override
  public int InsertSampleFile(SampleFile sample_file) {
    return mapper.InsertSampleFile(sample_file);
  }

  @Override
  public int InsertSampleUpload(Uploads upload) {
    return mapper.InsertSampleUpload(upload);
  }

  @Override
  public int UpdateSampleFile(SampleFile sample_file) {
    return mapper.UpdateSampleFile(sample_file);
  }

  @Override
  public int UpdateSampleUpload(Uploads upload) {
    return mapper.UpdateSampleUpload(upload);
  }

  // 품종 갯수 조회
  @Override
  public int SelectBreedCount(String breed_name)
  {
    return mapper.SelectBreedCount(breed_name);
  }

  // 품종 검색
  @Override
  public List<Breed> SearchBreed(String breed_name)
  {
    return mapper.SearchBreed(breed_name);
  }

  // 품종별 세부 정보 조회
  @Override
  public List<Detail> SearchBreedDetail(String breed_name)
  {
    return mapper.SearchBreedDetail(breed_name);
  }

  // 표시항목 조회
  @Override
  public List<Display> SelectDisplay(int user_id, String breed_name)
  {
    return mapper.SelectDisplay(user_id, breed_name);
  }

  // 품종별 정보값 조회
  @Override
  public List<Standard> SearchBreedStandard(List<Detail> detail, int user_id, int breed_id)
  {
    return mapper.SearchBreedStandard(detail, user_id, breed_id);
  }
}
