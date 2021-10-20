package com.thekiban.Entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ExcelData {

  private String code;

  private String kind;

  private String name;

  private String mom;

  private String dad;

  private String sale_num;

  private Date sale_date;

  private String apply_num;

  private Date apply_date;

  private String enroll_num;

  private Date enroll_date;

  private int local;

  private String purpose;

  private String first_form;

  private String second_form;

  private String first_crop;

  private String second_crop;

  private String comment;

}
