package com.thekiban.Service;

import java.util.List;

import com.thekiban.Entity.Basic;
import com.thekiban.Entity.Detail;
import com.thekiban.Entity.Display;
import com.thekiban.Entity.Standard;

public interface BasicService
{
	// 세부 정보 조회
	List<Detail> SelectDetail();

	// 원종 갯수 조회
	int SelectBasicCount(String basic_name);

	// 원종 검색
	List<Basic> SearchBasic(String basic_name, int offset, int limit);
	
	// 원종별 세부 정보 조회
	List<Detail> SearchBasicDetail(String basic_name);
	
	// 표시항목 조회
	List<Display> SelectDisplay(int user_id, String basic_name);
	
	// 원종별 정보값 조회
	List<Standard> SearchBasicStandard(List<Detail> detail, int user_id, int basic_id);

	// 원종 등록
	int InsertBasic(Basic basic);

	// 원종 상세 정보 등록
	int InsertStandard(int basic_id, String basic_name, List<Detail> detail);

	// 원종 전체 조회
	List<Basic> SelectBasicAll(String basic_name, int offset);
	
	// 원종별 정보 전체 조회
	List<Standard> SelectBasicStandard(int basic_id);
	
	// 원종 수정
	int UpdateBasic(int basic_id, int detail_id, String standard);

	// 원종 전체 수정
	int UpdateAllBasic(List<Standard> list);
	
	// 원종 삭제
	int[] DeleteBasic(int[] basic_id);
	
	// 원종 값 삭제
	int[] DeleteStandard(int[] basic_id);

	// 표시항목 삭제
	int DeleteDisplay(int user_id);

	// 표시항목 등록
	int InsertDisplay(int user_id, String basic_name, int[] detail_list);
}