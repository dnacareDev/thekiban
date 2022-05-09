package com.thekiban.Service;

import java.util.List;

import com.thekiban.Entity.*;
import org.apache.ibatis.annotations.Param;

public interface BasicService
{
	// 세부 정보 조회
	List<Detail> SelectDetailExcel(String basic_name);

	// 원종 갯수 조회
	int SelectBasicCount(String basic_name);

	int SelectRemainCount(String basic_name);

	// 원종 검색
	List<Basic> SearchBasic(String basic_name);

	List<BasicRemain> SearchBasicRemain(String basic_name);

	List<BasicRemain> SearchBasicRemainByNum(String basic_name, String basic_remain_num);

	// 원종별 세부 정보 조회
	List<Detail> SearchBasicDetail(String basic_name);
	
	// 표시항목 조회
	List<Display> SelectDisplay(int user_id, String basic_name);
	
	// 원종별 정보값 조회
	List<Standard> SearchBasicStandard(List<Detail> detail, int user_id, int basic_id);

	// 원종 등록
	int InsertBasic(Basic basic);

	int InsertRemain(BasicRemain basicRemain);

	// 원종 상세 정보 등록
	int InsertStandard(int basic_id, String basic_name, List<Detail> detail);

	// 원종 전체 조회
	List<Basic> SelectBasicAll(String basic_name);
	
	// 원종별 정보 전체 조회
	List<Standard> SelectBasicStandard(int basic_id);
	
	// 원종 수정
	int UpdateBasic(int basic_id, int detail_id, String standard);

	int UpdateBasicRemain(int basic_remain_id, String basic_remain_name, String basic_remain_value);

	// 원종 전체 수정
	int UpdateAllBasic(List<Standard> list);

	int UpdateInsertBasicRemain(BasicRemain basicRemain);

	List<Uploads> SelectUploads(int[] basic_id);
	
	// 원종 삭제
	int[] DeleteBasic(int[] basic_id);
	
	// 원종 값 삭제
	int[] DeleteStandard(int[] basic_id);

	// 표시항목 삭제
	int DeleteDisplay(int user_id, String basic_name);

	// 첨부파일 내용 삭제
	int DeleteFile(int[] basic_id);
	
	// 첨부파일 삭제
	int DeleteUploads(List<Uploads> uploads);

	int[] DeleteBasicRemain(int[] basic_remain_id);

	// 표시항목 등록
	int InsertDisplay(int user_id, String basic_name, int[] detail_list);

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

	List<Basic> SearchBasicExcel(String basic_name);

	List<BasicRemain> SearchBasicRemainExcel(String basic_name);

	List<Basic> SelectBasicExcel(int basic_id);

	List<BasicRemain> SelectBRemainExcel(int basic_remain_id);
	
	// 첨부파일 목록 전체 조회
	List<BasicFile> selectBasicFileAll();

}