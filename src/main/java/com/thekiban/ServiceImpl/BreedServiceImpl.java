package com.thekiban.ServiceImpl;

import com.thekiban.Entity.Detail;
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

}
