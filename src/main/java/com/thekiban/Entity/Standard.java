package com.thekiban.Entity;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Alias("Standard")
public class Standard
{
	private int standard_id;
	private int detail_id;
	private int breed_id;
	private int basic_id;
	private String standard;
	
	private String detail_title;
}