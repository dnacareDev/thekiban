package com.thekiban.ServiceImpl;

import com.thekiban.Entity.Location;
import com.thekiban.Mapper.LocationMapper;
import com.thekiban.Service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

  @Autowired
  private LocationMapper locationMapper;

  @Override
  public int insertLocation(Location location) {
    return locationMapper.insertLocation(location);
  }

  @Override
  public List<Location> selectAll(String user_crop) {
	  return locationMapper.selectAll(user_crop);  
	  //public List<Location> selectAll() {
		//  return locationMapper.selectAll();  
  }
  
  @Override
  public List<Location> selectAll2() {
	  return locationMapper.selectAll2();  
  }
}
