package com.thekiban.Entity;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Alias("Seed")
public class Seed
{
	private int breed_id;
	private String breed_name;
	
	private int basic_id;
	private String basic_name;
	
	private int detail_id;
	private int detail_type;
	private String detail_title;
	
	private int standard_id;
	private int standard;
}