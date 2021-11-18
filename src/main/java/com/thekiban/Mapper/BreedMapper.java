package com.thekiban.Mapper;

import com.thekiban.Entity.*;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

@Mapper
public interface BreedMapper
{
	// 세부정보 조회
	List<Detail> SelectDetail(String breed_name);
 
	// 품종 갯수 조회
	int SelectBreedCount(String breed_name);

	// 품종 검색
	List<Breed> SearchBreed(@Param("breed_name") String breed_name, @Param("offset") int offset, @Param("limit") int limit);

	// 품종별 세부 정보 조회
	List<Detail> SearchBreedDetail(String breed_name);

	// 표시항목 조회
	List<Display> SelectDisplay(@Param("user_id") int user_id, @Param("breed_name") String breed_name);

	// 품종별 정보값 조회
	List<Standard> SearchBreedStandard(@Param("detail") List<Detail> detail, @Param("user_id") int user_id, @Param("breed_id") int breed_id);

	// 품종 등록
	int InsertBreed(Breed breed);

	// 품정 상세 정보 등록
	int InsertStandard(@Param("breed_id") int breed_id, @Param("breed_name") String breed_name, @Param("detail") List<Detail> detail);
	
	// 품종별 전체 조회
	List<Breed> SelectBreedAll(@Param("breed_name") String breed_name, @Param("offset") int offset);
	
	// 품종별 정보 전체 조회
	List<Standard> SelectBreedStandard(int breed_id);

	// 품종 수정
	int UpdateBreed(@Param("breed_id") int breed_id, @Param("detail_id") int detail_id, @Param("standard") String standard);

	// 품목 전체 수정
	int UpdateAllBreed(List<Standard> list);

	// 첨부파일 조회
	List<Uploads> SelectUploads(int[] breed_id);

	// 품종 삭제
	int[] DeleteBreed(int[] breed_id);

	// 품종 값 삭제
	int[] DeleteStandard(int[] breed_id);
	
	// 표시항목 삭제
	int DeleteDisplay(int user_id);

	// 첨부파일 내용 삭제
	int DeleteFile(int[] breed_id);
	
	// 첨부파일 삭제
	int DeleteUploads(List<Uploads> uploads);

	// 표시항목 등록
	int InsertDisplay(@Param("user_id") int user_id, @Param("breed_name") String breed_name, @Param("detail_list") int[] detail_list);

	// 품종 상세 조회
	Breed SelectBreedDetail(int breed_id);

	// 첨부파일 목록 조회
	List<BreedFile> SelectBreedFile(int breed_id);

	// 첨부파일 내용 등록
	int InsertBreedFile(BreedFile breed_file);

	// 첨부파일 등록
	int InsertBreedUpload(Uploads upload);

	// 첨부파일 내용 수정
	int UpdateBreedFile(BreedFile breed_file);

	// 첨부파일 수정
	int UpdateBreedUpload(Uploads upload);

	// 시교자원 목록 조회
	List<Sample> SelectSampleList();

	// 시교자원 갯수 조회
	int SelectSampleCount(String sample_name);

	// 시교자원 검색
	List<Sample> SearchSample(@Param("sample_name") String sample_name);

	// 세부정보 조회
	List<Detail> SelectDetail1();

	// 원종 갯수 조회
	int SelectBasicCount(String basic_name);

	// 원종 검색
	List<Basic> SearchBasic(@Param("basic_name") String basic_name);

	// 원종별 세부 정보 조회
	List<Detail> SearchBasicDetail(String basic_name);

	// 표시항목 조회
	List<Display> SelectDisplay1(@Param("user_id") int user_id, @Param("basic_name") String basic_name);

	// 원종별 정보값 조회
	List<Standard> SearchBasicStandard(@Param("detail") List<Detail> detail, @Param("user_id") int user_id, @Param("basic_id") int basic_id);

	int InsertExcel(List<Standard> standards);
}