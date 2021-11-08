package com.thekiban.Service;

import com.thekiban.Entity.Income;
import com.thekiban.Entity.IncomeFile;
import com.thekiban.Entity.IncomeRemain;
import com.thekiban.Entity.Uploads;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IncomeService {

  // 도입자원 목록
  List<Income> SelectIncomeList();

  // 도입자원 등록
  int InsertIncome(Income income);

  // 도입자원 갯수 조회
  int SelectIncomeCount(String income_name);

  // 도입자원 검색
  List<Income> SearchIncome(String income_name, int offset, int limit);

  // 도입자원 삭제
  int[] DeleteIncome(int[] income_id);

  // 수출자원 삭제
  int[] DeleteRemain(int[] income_remain_id);

  // 도입자원 수정
  int UpdateIncome(Income income);

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

  // 시교자원 수정
  int UpdateInsertIncome(Income income);

  // 수출관리 수정
  int UpdateInsertRemain(IncomeRemain incomeRemain);

  // 시교자원 수정
  int UpdateIncome(@Param("income_id") int income_id, @Param("income_name") String income_name, @Param("income_value") String income_value);

  int UpdateRemain(@Param("income_remain_id") int income_remain_id, @Param("income_remain_name") String income_remain_name, @Param("income_remain_value") String income_remain_value);
}
