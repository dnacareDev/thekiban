package com.thekiban.Service;

import java.util.List;

import com.thekiban.Entity.Location;

public interface LocationService {

  int insertLocation(Location location);

  List<Location> selectAll(String user_crop);
  // List<Location> selectAll();

  List<Location> selectAll2();
}
