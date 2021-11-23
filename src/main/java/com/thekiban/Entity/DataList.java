package com.thekiban.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DataList {

  private int datalist_id;
  private String datalist_type;
  private String datalist_date;
  private int target_id;

}
