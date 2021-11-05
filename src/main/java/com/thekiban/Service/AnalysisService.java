package com.thekiban.Service;

import java.util.List;

import com.thekiban.Entity.Basic;
import com.thekiban.Entity.Breed;

public interface AnalysisService
{
	// 품종 조회
	List<Breed> SelectBreed(int[] total_id, int type);

	// 원종 조회
	List<Basic> SelectBasic(int[] total_id, int type);
}