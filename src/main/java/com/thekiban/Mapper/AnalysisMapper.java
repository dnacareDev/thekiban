package com.thekiban.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.thekiban.Entity.Basic;
import com.thekiban.Entity.Breed;
import com.thekiban.Entity.Detail;

@Mapper
public interface AnalysisMapper
{
	// 품종 조회
	List<Breed> SelectBreed(@Param("total_id") int[] total_id, @Param("type") int type);

	// 원종 조회
	List<Basic> SelectBasic(@Param("total_id") int[] total_id, @Param("type") int type);

	List<Detail> SelectTrait(String deatil_name);
}