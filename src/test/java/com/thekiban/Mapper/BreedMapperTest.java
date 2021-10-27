package com.thekiban.Mapper;

import com.thekiban.Entity.Breed;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BreedMapperTest {

  @Autowired
  private BreedMapper mapper;

  @Test
  void insertBreed() {

    Breed breed = new Breed();

    breed.setBreed_name("test");
    breed.setBreed_code("test");
    breed.setBreed_dad("test");

    mapper.InsertBreed(breed);
  }
}