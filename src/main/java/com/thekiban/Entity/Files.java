package com.thekiban.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Files {

  private int files_id;
  private String files_name;
  private String files_original;
  private int files_type;
  private int row_num;
  private int files_cat;
  private String create_date;
  private String modify_date;

  private int breed_file_id;
  private int basic_file_id;
  private int income_file_id;
  private int sample_file_id;

}
