package com.thekiban.Entity;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Alias("Basic")
public class Basic
{
	private int basic_id;						// 원종관리 코드
	private String basic_code;
	private String basic_name;
	private String basic_num;
	private String basic_raw_num;
	private int basic_bn_year;
	private String basic_bn_num;
	private String basic_seed;
	private String basic_amount;
	private String basic_weight;
	private String basic_date;
	private String basic_comment;
	private String create_date;
	private String modify_date;
}