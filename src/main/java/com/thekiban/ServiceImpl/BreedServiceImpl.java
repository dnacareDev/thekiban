package com.thekiban.ServiceImpl;

import com.thekiban.Entity.Basic;
import com.thekiban.Entity.Breed;
import com.thekiban.Entity.Detail;
import com.thekiban.Entity.Standard;
import com.thekiban.Mapper.BreedMapper;
import com.thekiban.Service.BreedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreedServiceImpl implements BreedService {

  @Autowired
  private BreedMapper mapper;

  // 세부정보 조회
  @Override
  public List<Detail> SelectDetail()
  {
    return mapper.SelectDetail();
  }

  // 품종 등록
  @Override
  public int InsertBreed(Breed breed)
  {
    return mapper.InsertBreed(breed);
  }

  // 품종 상세 정보 등록
  @Override
  public int InsertStandard(List<Standard> standard)
  {
    return mapper.InsertStandard(standard);
  }

  // 원종 갯수 조회
  @Override
  public int SelectBreedCount()
  {
    return mapper.SelectBreedCount();
  }

  // 원종 검색
  @Override
  public List<Breed> SearchBreed(int offset, int limit)
  {
    return mapper.SearchBreed(offset, limit);
  }

}
