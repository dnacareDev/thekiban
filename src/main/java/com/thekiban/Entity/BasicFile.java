package com.thekiban.Entity;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Alias("BasicFile")
public class BasicFile
{
	private int basic_file_id;
	private int basic_id;
	private String basic_file_title;
	private String basic_file_contents;
	private String create_date;
	private String modify_date;
	
	private String uploads_file;
	private String uploads_origin_file;
}