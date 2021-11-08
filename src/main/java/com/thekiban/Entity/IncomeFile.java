package com.thekiban.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@ToString
@Alias("IncomeFile")
public class IncomeFile {

  private int income_file_id;
  private int income_id;
  private String income_file_title;
  private String income_file_contents;
  private String create_date;
  private String modify_date;

  private String uploads_file;
  private String uploads_origin_file;
}
