package com.thekiban.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@ToString
@Alias("SampleFile")
public class SampleFile {

  private int sample_file_id;
  private int sample_id;
  private String sample_file_title;
  private String sample_file_contents;
  private String create_date;
  private String modify_date;

  private String uploads_file;
  private String uploads_origin_file;
}
