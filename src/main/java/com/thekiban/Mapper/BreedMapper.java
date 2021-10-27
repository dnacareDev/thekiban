package com.thekiban.Mapper;

import com.thekiban.Entity.Breed;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BreedMapper {

  int InsertExcel(List<Breed> excels);

  // DB 등록
  int InsertBreed(List<Breed> breed);

}
