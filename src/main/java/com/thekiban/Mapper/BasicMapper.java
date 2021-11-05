package com.thekiban.Mapper;

import java.util.List;

import com.thekiban.Entity.Sample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.thekiban.Entity.Basic;
import com.thekiban.Entity.Detail;
import com.thekiban.Entity.Display;
import com.thekiban.Entity.Standard;

@Mapper
public interface BasicMapper
{
	// 세부정보 조회
	List<Detail> SelectDetail();

	// 원종 갯수 조회
	int SelectBasicCount(String basic_name);
	
	// 원종 검색
	List<Basic> SearchBasic(@Param("basic_name") String basic_name, @Param("offset") int offset, @Param("limit") int limit);

	// 원종별 세부 정보 조회
	List<Detail> SearchBasicDetail(String basic_name);
	
	// 표시항목 조회
	List<Display> SelectDisplay(@Param("user_id") int user_id, @Param("basic_name") String basic_name);
	
	// 원종별 정보값 조회
	List<Standard> SearchBasicStandard(@Param("detail") List<Detail> detail, @Param("user_id") int user_id, @Param("basic_id") int basic_id);

	// 원종 등록
	int InsertBasic(Basic basic);

	// 원정 상세 정보 등록
	int InsertStandard(@Param("basic_id") int basic_id, @Param("basic_name") String basic_name, @Param("detail") List<Detail> detail);
	
	// 원종별 전체 조회
	List<Basic> SelectBasicAll(@Param("basic_name") String basic_name, @Param("offset") int offset);
	
	// 원종별 정보 전체 조회
	List<Standard> SelectBasicStandard(int basic_id);
	
	// 원종 수정
	int UpdateBasic(@Param("basic_id") int basic_id, @Param("detail_id") int detail_id, @Param("standard") String standard);

	// 원종 전체 수정
	int UpdateAllBasic(List<Standard> list);

	// 원종 삭제
	int[] DeleteBasic(int[] basic_id);

	// 원종 값 삭제
	int[] DeleteStandard(int[] basic_id);

	// 표시항목 삭제
	int DeleteDisplay(int user_id);

	// 표시항목 등록
	int InsertDisplay(@Param("user_id") int user_id, @Param("basic_name") String basic_name, @Param("detail_list") int[] detail_list);
}