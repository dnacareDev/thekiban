package com.thekiban.Entity;

import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Alias("Breed")
public class Breed
{
	private int breed_id;					// 품종관리 고유번호
	private String breed_name;				// 작물명
	private String create_date;				// 등록일
	private String modify_date;				// 수정일
	private int breed_count;
	
	private String standard;
	private int standard_count;						// 세부정보 수
	private List<Standard> breed_standard;			// 세부정보
}