package com.thekiban.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.thekiban.Entity.Basic;
import com.thekiban.Entity.Breed;
import com.thekiban.Entity.Detail;
import com.thekiban.Entity.Standard;

@Mapper
public interface AnalysisMapper
{
	// 품종 조회
	List<Breed> SelectBreed(@Param("name") String name, @Param("total_id") int[] total_id, @Param("type") int type);

	// 원종 조회
	List<Basic> SelectBasic(@Param("name") String name, @Param("total_id") int[] total_id, @Param("type") int type);

	// 분석 형질 조회
	List<Detail> SelectTrait(@Param("detail_name") String deatil_name, @Param("detail_type") int detail_type);
	
	List<Detail> SelectDetail(@Param("detail_name") String detail_name, @Param("detail_type") int detail_type);

	List<Standard> SelectStandard(@Param("target_id") int[] target_id, @Param("detail_type") int detail_type);
}