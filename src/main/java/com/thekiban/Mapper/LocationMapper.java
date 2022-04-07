package com.thekiban.Mapper;

import com.thekiban.Entity.Location;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LocationMapper {

  int insertLocation(Location location);

  List<Location> selectAll();

}
