package com.thekiban.Entity;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Alias("Uploads")
public class Uploads
{
	private int uploads_id;
	private String uploads_file;
	private String uploads_origin_file;
	private int uploads_type;						// 파일 유형(0: 첨부파일, 1: 엑셀파일)
	private int uploads_cat;						// 파일 카테고리(0: 품종, 1: 원종, 2: 도입, 3: 시교)
	private int row_num;		
	private String create_date;
	private String modify_date;

	private int breed_file_id;
	private int basic_file_id;
	private int income_file_id;
	private int sample_file_id;
}