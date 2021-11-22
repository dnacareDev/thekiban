package com.thekiban.Entity;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Alias("ChartCount")
public class ChartCount
{
	private int crop_count_1;
	private int crop_count_2;
	private int crop_count_3;
	private int crop_count_4;
	private int crop_count_5;
	private int crop_count_6;
	private int crop_count_7;
	private int crop_count_8;
	private int crop_count_9;
	private int crop_count_10;
}