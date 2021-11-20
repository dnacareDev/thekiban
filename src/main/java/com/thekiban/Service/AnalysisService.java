package com.thekiban.Service;

import java.util.List;

import com.thekiban.Entity.Basic;
import com.thekiban.Entity.Breed;
import com.thekiban.Entity.Detail;

public interface AnalysisService
{
	// 품종 조회
	List<Breed> SelectBreed(String name, int[] total_id, int type);

	// 원종 조회
	List<Basic> SelectBasic(String name, int[] total_id, int type);

	// 분석 형질 조회
	List<Detail> SelectTrait(String deatil_name, int detail_type);
}