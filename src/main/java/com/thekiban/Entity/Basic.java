package com.thekiban.Entity;

import java.util.List;

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
	private String basic_name;					// 작물명
	private String create_date;					// 등록일
	private String modify_date;					// 수정일
	
	int standard_count;							// 세부정보 수
	List<Standard> basic_standard;				// 세부정보
}