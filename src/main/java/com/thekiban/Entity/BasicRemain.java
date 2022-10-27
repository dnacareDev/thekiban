package com.thekiban.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class BasicRemain {

  private int basic_remain_id;
  private String basic_name;
  private String basic_remain_num;
  private Double basic_remain_amount;
  private Double basic_remain_in;
  private Double basic_remain_out;
  private Double basic_remain_re;
  private String basic_remain_person;
  private LocalDate basic_remain_date;
  private String create_date;
  private String modify_date;

}
