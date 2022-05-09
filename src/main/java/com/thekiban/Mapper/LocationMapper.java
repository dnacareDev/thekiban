package com.thekiban.Mapper;

import com.thekiban.Entity.Location;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LocationMapper {

  int insertLocation(Location location);

  List<Location> selectAll(@Param("user_crop") String user_crop);
  
  List<Location> selectAll2();
}
