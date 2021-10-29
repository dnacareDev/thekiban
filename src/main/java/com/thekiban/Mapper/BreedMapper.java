package com.thekiban.Mapper;

import com.thekiban.Entity.Basic;
import com.thekiban.Entity.Breed;
import com.thekiban.Entity.Detail;
import com.thekiban.Entity.Standard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

@Mapper
public interface BreedMapper {
  // 세부정보 조회
  List<Detail> SelectDetail();

  // 품종 등록
  int InsertBreed(Breed breed);

  // 품정 상세 정보 등록
  int InsertStandard(List<Standard> standard);

  // 품종 갯수 조회
  int SelectBreedCount(String breed_name);

  // 품종 검색
  List<Breed> SearchBreed(@Param("breed_name") String breed_name, @Param("offset") int offset, @Param("limit") int limit);

  // 품종 삭제
  int[] DeleteBreed(int[] breed_id);

}
