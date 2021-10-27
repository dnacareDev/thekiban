package com.thekiban.Entity;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Alias("Detail")
public class Detail
{
	private int detail_id;
	private int detail_type;
	private String detail_name;
	private String detail_spec;
	private String detail_title;
}