package com.thekiban.Service;

import com.thekiban.Entity.Breed;

import java.util.List;

public interface BreedService {

  int InsertExcel(List<Breed> excels);

  // DB 등록
  int InsertBreed(Breed breed);

}
