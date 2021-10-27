package com.thekiban.Service;

import com.thekiban.Entity.Basic;
import com.thekiban.Entity.Breed;
import com.thekiban.Entity.Detail;
import com.thekiban.Entity.Standard;

import java.util.List;

public interface BreedService {

  // 세부 정보 조회
  List<Detail> SelectDetail();

  // 품종 등록
  int InsertBreed(Breed breed);

  // 품정 상세 정보 등록
  int InsertStandard(List<Standard> standard);

  // 원종 갯수 조회
  int SelectBreedCount();

  // 원종 검색
  List<Breed> SearchBreed(int offset, int limit);
}
