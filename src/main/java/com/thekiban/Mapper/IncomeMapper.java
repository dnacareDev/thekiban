package com.thekiban.Mapper;

import com.thekiban.Entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IncomeMapper {

  // 도입자원 목록 조회
  List<Income> SelectIncomeList();
  
  // 도입자원 등록
  int InsertIncome(Income income);

  int InsertIncomeRemain(IncomeRemain incomeRemain);

  // 도입자원 갯수 조회
  int SelectIncomeCount(String income_name);

  int SelectRemainCount(String income_name);

  // 도입자원 검색
  List<Income> SearchIncome(@Param("income_name") String income_name, @Param("offset") int offset, @Param("limit") int limit);

  // 재고관리 검색
  List<IncomeRemain> SearchRemain(@Param("income_name") String income_name, @Param("offset") int offset, @Param("limit") int limit);

  // 재고 팝업
  List<IncomeRemain> SearchIncomeRemain(@Param("income_name") String income_name);

  // 도입자원 삭제
  int[] DeleteIncome(int[] income_id);

  // 수출자원 삭제
  int[] DeleteRemain(int[] income_remain_id);

  // 도입자원 수정
  int UpdateIncome(@Param("income_id") int income_id, @Param("income_name") String income_name, @Param("income_value") String income_value);

  // 재고관리 수정
  int UpdateRemain(@Param("income_remain_id") int income_remain_id, @Param("income_remain_name") String income_remain_name, @Param("income_remain_value") String income_remain_value);

  // 도입자원 입력수정
  int UpdateInsertIncome(Income income);

  // 재고관리 입력수정
  int UpdateInsertRemain(IncomeRemain incomeRemain);

  // 도입자원 엑셀 등록
  int InsertIncomeExcel(Income income);

  // 재고관리 엑셀 등록
  int InsertRemainExcel(IncomeRemain incomeRemain);

  // 도입자원 엑셀 목록 조회
  List<Income> SearchIncomeExcel(@Param("income_name") String income_name);

  // 첨부파일 조회
  List<Uploads> SelectUploads(int[] income_id);

  // 첨부파일 내용 삭제
  int DeleteFile(int[] income_id);

  // 첨부파일 삭제
  int DeleteUploads(List<Uploads> uploads);

  // 첨부파일 목록 조회
  List<IncomeFile> SelectIncomeFile(int income_id);

  // 첨부파일 내용 등록
  int InsertIncomeFile(IncomeFile income_file);

  // 첨부파일 등록
  int InsertIncomeUpload(Uploads upload);

  // 첨부파일 내용 수정
  int UpdateIncomeFile(IncomeFile income_file);

  // 첨부파일 수정
  int UpdateIncomeUpload(Uploads upload);

}
