package com.thekiban.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Location {

  private int location_id;
  private String sample_name;
  private String location_name;
  private String location_type;
  private String location_lat;
  private String location_lng;

}
