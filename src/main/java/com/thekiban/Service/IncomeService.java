package com.thekiban.Service;

import com.thekiban.Entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IncomeService {

  // 도입자원 목록
  List<Income> SelectIncomeList();

  // 도입자원 등록
  int InsertIncome(Income income);

  // 재고자원 등록
  int InsertIncomeRemain(IncomeRemain incomeRemain);

  // 도입자원 갯수 조회
  int SelectIncomeCount(String income_name);

  // 재고자원 갯수 조회
  int SelectRemainCount(String income_name);

  // 도입자원 검색
  List<Income> SearchIncome(String income_name, int offset, int limit);

  // 재고자원 검색
  List<IncomeRemain> SearchRemain(String income_name, int offset, int limit);

  // 재고 팝업
  List<IncomeRemain> SearchIncomeRemain(String income_name);

  // 도입자원 삭제
  int[] DeleteIncome(int[] income_id);

  // 수출자원 삭제
  int[] DeleteRemain(int[] income_remain_id);

  // 도입자원 수정
  int UpdateIncome(int income_id, String income_name, String income_value);

  // 재고자원 수정
  int UpdateRemain(int income_remain_id, String income_remain_name, String income_remain_value);

  // 도입자원 입력수정
  int UpdateInsertIncome(Income income);

  // 재고관리 입력수정
  int UpdateInsertRemain(IncomeRemain incomeRemain);

  // 도입자원 엑셀 등록
  int InsertIncomeExcel(Income income);

  // 재고관리 엑셀 등록
  int InsertRemainExcel(IncomeRemain incomeRemain);

  // 도입자원 엑셀 목록 조회
  List<Income> SearchIncomeExcel(String income_name);

  List<IncomeRemain> SearchIncomeRemainExcel(String income_name);

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

  List<Income> SelectIncomeExcel(int income_id);

  List<IncomeRemain> SelectIncomeRemainExcel(int income_remain_id);


}
