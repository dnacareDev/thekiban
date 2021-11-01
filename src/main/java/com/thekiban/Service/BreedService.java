package com.thekiban.Service;

import com.thekiban.Entity.Basic;
import com.thekiban.Entity.Breed;
import com.thekiban.Entity.Detail;
import com.thekiban.Entity.Display;
import com.thekiban.Entity.Standard;

import java.util.List;

public interface BreedService
{
	// 세부 정보 조회
	List<Detail> SelectDetail();
	
	// 품종 갯수 조회
	int SelectBreedCount(String breed_name);
	
	// 품종 검색
	List<Breed> SearchBreed(String breed_name, int offset, int limit);

	// 품종별 세부 정보 조회
	List<Detail> SearchBreedDetail(String breed_name);

	// 표시항목 조회
	List<Display> SelectDisplay(int user_id, String breed_name);

	// 품종별 정보값 조회
	List<Standard> SearchBreedStandard(List<Detail> detail, int user_id, int breed_id);

	// 품종 등록
	int InsertBreed(Breed breed);

	// 품정 상세 정보 등록
	int InsertStandard(List<Standard> standard);

	// 품종 삭제
	int[] DeleteBreed(int[] breed_id);

	// 품종 값 삭제
	int[] DeleteStandard(int[] breed_id);

	// 표시항목 삭제
	int DeleteDisplay(int user_id);

	// 표시항목 등록
	int InsertDisplay(int user_id, String breed_name, int[] detail_list);
}