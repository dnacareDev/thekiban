package com.thekiban.Mapper;

import com.thekiban.Entity.Sample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SampleMapper {

  // 시교자원 목록 조회
  List<Sample> SelectSampleList();

  // 시교자원 등록
  int InsertSample(Sample sample);

  // 시교자원 갯수 조회
  int SelectSampleCount(String sample_name);

  // 시교자원 검색
  List<Sample> SearchSample(@Param("sample_name") String sample_name, @Param("offset") int offset, @Param("limit") int limit);

  // 시교자원 삭제
  int[] DeleteSample(int[] sample_id);

  // 시교자원 수정
//  int UpdateSample(@Param("sample_id") int sample_id);

  int UpdateSample(Sample sample);


}

