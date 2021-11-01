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
	private int user_id;
	private String breed_name;					// 작목명					
	private int detail_id;						// 세부정보 고유번호
	
	private String detail_spec;
	private String detail_title;
}