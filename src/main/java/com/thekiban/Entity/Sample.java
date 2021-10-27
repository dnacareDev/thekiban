package com.thekiban.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Sample {

  private int sample_id;          // 시교관리 고유번호
  private String sample_code;     // 시교관리 코드
  private String sample_name;     // 시교관리명
  private String sample_country;  // 목표 지역
  private String sample_type;     // 구분
  private String sample_mate;     // 교배번호
  private String sample_seed;     // 종자번호
  private double sample_amount;   // 현 종자량
  private int sample_sprout;      // 발아율
  private int sample_purity;      // 순도율
  private String sample_comment;  // 비고
  private String create_date;
  private String modify_date;

}
