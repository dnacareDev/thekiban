package com.thekiban.Mapper;

import com.thekiban.Entity.Location;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LocationMapper {

  int insertLocation(Location location);

}
