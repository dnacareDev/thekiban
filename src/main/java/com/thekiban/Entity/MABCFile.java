package com.thekiban.Entity;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Alias("MABCFile")
public class MABCFile {
	private int mabc_file_id;
	private int mabc_id;
	private String crop;
	private String comment;
}
