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
	private String breed_code;				// 품종관리 코드
	private String breed_name;				// 작물명
	private String breed_kind;				// 품종명
	private String breed_mom;				// 모계 계통명
	private String breed_dad;				// 부계 계통명
	private String breed_sale_num;			// 생산판매 번호
	private String breed_sale_date;			// 생산판매 일자
	private String breed_apply_num;			// 보호출원 번호
	private String breed_apply_date;		// 보호출원 일자
	private String breed_enroll_num;		// 보호등록 번호
	private String breed_enroll_date;		// 보호등록 일자
	private String breed_local;				// 미등록(현지상업화)
	private String breed_purpose;			// 목표시장
	private String breed_first_form;		// 형태구분 1
	private String breed_second_form;		// 형태구분 2
	private String breed_first_crop;		// 작물타입 1
	private String breed_second_crop;		// 작물타입 2
	private String breed_comment;			// 비고
	private String create_date;				// 등록일
	private String modify_date;				// 수정일
	
	int standard_count;						// 세부정보 수
	List<Standard> breed_standard;			// 세부정보
}