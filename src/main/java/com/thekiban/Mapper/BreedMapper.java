package com.thekiban.Mapper;

import com.thekiban.Entity.Basic;
import com.thekiban.Entity.Breed;
import com.thekiban.Entity.Detail;
import com.thekiban.Entity.Standard;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BreedMapper {
  // 세부정보 조회
  List<Detail> SelectDetail();

  // 품종 등록
  int InsertBreed(Breed breed);

  // 품정 상세 정보 등록
  int InsertStandard(List<Standard> standard);
}
