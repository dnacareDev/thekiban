package com.thekiban.Entity;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.ToString;
import lombok.Setter;

@Getter
@Setter
@ToString
@Alias("Display")
public class Display
{
	private int display_id;
	private String breed_name;
	private String detail_title;
}