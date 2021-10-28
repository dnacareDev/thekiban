package com.thekiban.Mapper;

import com.thekiban.Entity.Basic;
import com.thekiban.Entity.Sample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SampleMapper {

  // 시교자원 등록
  int InsertSample(Sample sample);

  // 원종 갯수 조회
  int SelectSampleCount();

  // 원종 검색
  List<Sample> SearchSample(@Param("offset") int offset, @Param("limit") int limit);

  String Delete(String no);

}
