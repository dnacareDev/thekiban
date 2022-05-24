package com.thekiban.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MABCSample {
	private int mabc_id;
	private String crop;
	private String mabc_category;
	private String regist_person;
	private String regist_date;
	private String detail;
}
