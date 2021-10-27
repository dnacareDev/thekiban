package com.thekiban.Mapper;

import com.thekiban.Entity.Detail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BreedMapper {
  // 세부정보 조회
  List<Detail> SelectDetail();

}
