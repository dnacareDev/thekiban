package com.thekiban.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.thekiban.Entity.Basic;
import com.thekiban.Entity.Detail;
import com.thekiban.Entity.Standard;

@Mapper
public interface BasicMapper
{
	// 세부정보 조회
	List<Detail> SelectDetail();

	// 원종 갯수 조회
	int SelectBasicCount();
	
	// 원종 검색
	List<Basic> SearchBasic(@Param("offset") int offset, @Param("limit") int limit);

	// 원종 등록
	int InsertBasic(Basic basic);

	// 원정 상세 정보 등록
	int InsertStandard(List<Standard> standard);
}