package com.thekiban.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@ToString
@Alias("Income")
public class Income {

  private int income_id;
  private String income_code;
  private String income_name;
  private String income_kind;
  private String income_division;
  private String income_place;
  private String income_country;
  private String income_person;
  private String income_date;
  private int income_num;
  private String income_type;
  private String income_trait;
  private String income_comment;
  private String create_date;
  private String modify_date;

}
