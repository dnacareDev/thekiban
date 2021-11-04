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
	private int detail_id;						// 세부정보 고유번호
	private String breed_name;					// 품종 작목명
	private String basic_name;					// 원종 작물명
	
	private String detail_spec;
	private String detail_title;
}