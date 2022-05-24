package com.thekiban.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QTLInformation {
	private int qtl_num;
	private String crop;
	private String qtl_category;
	private String regist_person;
	private String regist_date;
	private String detail;
	private String note;
}
