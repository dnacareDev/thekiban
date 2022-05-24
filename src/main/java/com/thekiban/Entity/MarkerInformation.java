package com.thekiban.Entity;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MarkerInformation {
	private int marker_num;
	private String crop;
	private String marker_category;
	private String regist_person;
	private String regist_date;
	private String detail;
	private String note;
}
