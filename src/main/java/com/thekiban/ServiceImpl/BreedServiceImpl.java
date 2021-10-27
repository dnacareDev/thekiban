package com.thekiban.ServiceImpl;

import com.thekiban.Entity.Breed;
import com.thekiban.Mapper.BreedMapper;
import com.thekiban.Service.BreedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreedServiceImpl implements BreedService {

  @Autowired
  private BreedMapper mapper;

  @Override
  public int InsertExcel(List<Breed> excels) {
    return mapper.InsertExcel(excels);
  }

  @Override
  public int InsertBreed(Breed breed) {
    return mapper.InsertBreed(breed);
  }
}
