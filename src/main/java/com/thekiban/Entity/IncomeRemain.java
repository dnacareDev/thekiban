package com.thekiban.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class IncomeRemain {

  private int income_remain_id;
  private String income_name;
  private String income_remain_num;
  private double income_remain_amount;
  private double income_remain_in;
  private double income_remain_out;
  private double income_remain_re;
  private String income_remain_person;
  private LocalDate income_remain_date;
  private String create_date;
  private String modify_date;

}
