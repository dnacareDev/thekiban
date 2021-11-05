package com.thekiban.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SampleFile {

  private int sample_file_id;
  private int sample_id;
  private String sample_file_title;
  private String sample_file_content;
  private String create_date;
  private String modify_date;

}
