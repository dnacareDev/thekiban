package com.thekiban.Service;

import com.thekiban.Entity.Location;

import java.util.List;

public interface LocationService {

  int insertLocation(Location location);

  List<Location> selectAll();

}
