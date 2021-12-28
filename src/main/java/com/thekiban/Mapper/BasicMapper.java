package com.thekiban.Mapper;

import java.util.List;

import com.thekiban.Entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BasicMapper
{
	// 세부정보 조회
	List<Detail> SelectDetailExcel(String basic_name);

	// 원종 갯수 조회
	int SelectBasicCount(String basic_name);

	int SelectRemainCount(String basic_name);

	// 원종 검색
	List<Basic> SearchBasic(@Param("basic_name") String basic_name);

	List<BasicRemain> SearchBasicRemain(@Param("basic_name") String basic_name, @Param("offset") int offset, @Param("limit") int limit);

	// 원종별 세부 정보 조회
	List<Detail> SearchBasicDetail(String basic_name);
	
	// 표시항목 조회
	List<Display> SelectDisplay(@Param("user_id") int user_id, @Param("basic_name") String basic_name);
	
	// 원종별 정보값 조회
	List<Standard> SearchBasicStandard(@Param("detail") List<Detail> detail, @Param("user_id") int user_id, @Param("basic_id") int basic_id);

	// 원종 등록
	int InsertBasic(Basic basic);

	int InsertRemain(BasicRemain basicRemain);

	// 원정 상세 정보 등록
	int InsertStandard(@Param("basic_id") int basic_id, @Param("basic_name") String basic_name, @Param("detail") List<Detail> detail);
	
	// 원종별 전체 조회
	List<Basic> SelectBasicAll(@Param("basic_name") String basic_name);
	
	// 원종별 정보 전체 조회
	List<Standard> SelectBasicStandard(int basic_id);
	
	// 원종 수정
	int UpdateBasic(@Param("basic_id") int basic_id, @Param("detail_id") int detail_id, @Param("standard") String standard);

	int UpdateBasicRemain(@Param("basic_remain_id") int basic_remain_id, @Param("basic_remain_name") String basic_remain_name, @Param("basic_remain_value") String basic_remain_value);

	// 원종 전체 수정
	int UpdateAllBasic(List<Standard> list);

	int UpdateInsertBasicRemain(BasicRemain basicRemain);

	// 첨부파일 조회
	List<Uploads> SelectUploads(int[] basic_id);

	// 원종 삭제
	int[] DeleteBasic(int[] basic_id);

	// 원종 값 삭제
	int[] DeleteStandard(int[] basic_id);

	// 표시항목 삭제
	int DeleteDisplay(@Param("user_id") int user_id, @Param("basic_name") String basic_name);

	// 첨부파일 내용 삭제
	int DeleteFile(int[] basic_id);
	
	// 첨부파일 삭제
	int DeleteUploads(List<Uploads> uploads);

	int[] DeleteBasicRemain(int[] basic_remain_id);

	// 표시항목 등록
	int InsertDisplay(@Param("user_id") int user_id, @Param("basic_name") String basic_name, @Param("detail_list") int[] detail_list);

	// 원종 상세 조회
	Basic SelectBasicDetail(int basic_id);

	// 첨부파일 목록 조회
	List<BasicFile> SelectBasicFile(int basic_id);

	// 첨부파일 내용 등록
	int InsertBasicFile(BasicFile basic_file);

	// 첨부파일 등록
	int InsertBasicUpload(Uploads upload);

	// 첨부파일 내용 수정
	int UpdateBasicFile(BasicFile basic_file);

	// 첨부파일 수정
	int UpdateBasicUpload(Uploads upload);

	int InsertBasicExcel(List<Standard> standards);

	int InsertRemainExcel(BasicRemain basicRemain);

	List<Basic> SearchBasicExcel(@Param("basic_name") String basic_name);

	List<BasicRemain> SearchBasicRemainExcel(@Param("basic_name") String basic_name);

	List<Basic> SelectBasicExcel(int basic_id);

	List<BasicRemain> SelectBRemainExcel(int basic_remain_id);


}