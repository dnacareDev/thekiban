package com.thekiban.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class SampleOutcome {

  private int sample_outcome_id;
  private String sample_name;
  private String sample_outcome_code;
  private String sample_outcome_num;
  private double sample_outcome_amount;
  private double sample_outcome_in;
  private double sample_outcome_out;
  private double sample_outcome_remain;
  private String sample_outcome_person;
  private String sample_outcome_reciever;
  private LocalDate sample_outcome_date;
  private LocalDate sample_outcome_end;
  private String sample_outcome_country;
  private String sample_outcome_place;
  private String create_date;
  private String modify_date;

}
