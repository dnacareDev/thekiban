package com.thekiban.ServiceImpl;

import com.thekiban.Entity.*;
import com.thekiban.Mapper.IncomeMapper;
import com.thekiban.Service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeServiceImpl implements IncomeService {

  @Autowired
  private IncomeMapper mapper;

  @Override
  public List<Income> SelectIncomeList() {
    return mapper.SelectIncomeList();
  }

  // 도입자원 등록
  @Override
  public int InsertIncome(Income income) {
    return mapper.InsertIncome(income);
  }

  // 재고관리 등록
  @Override
  public int InsertIncomeRemain(IncomeRemain incomeRemain) {
    return mapper.InsertIncomeRemain(incomeRemain);
  }

  // 도입자원 갯수 조회
  @Override
  public int SelectIncomeCount(String income_name) {
    return mapper.SelectIncomeCount(income_name);
  }

  // 재고관리 갯수 조회
  @Override
  public int SelectRemainCount(String income_name) {
    return mapper.SelectRemainCount(income_name);
  }

  // 도입자원 검색
  @Override
  public List<Income> SearchIncome(String income_name, int offset, int limit) {
    return mapper.SearchIncome(income_name, offset, limit);
  }

  // 재고관리 검색
  @Override
  public List<IncomeRemain> SearchRemain(String income_name, int offset, int limit) {
    return mapper.SearchRemain(income_name, offset, limit);
  }

  // 재고 팝업
  @Override
  public List<IncomeRemain> SearchIncomeRemain(String income_name) {
    return mapper.SearchIncomeRemain(income_name);
  }

  // 도입자원 삭제
  @Override
  public int[] DeleteIncome(int[] income_id) {
    return mapper.DeleteIncome(income_id);
  }

  // 재고관리 삭제
  @Override
  public int[] DeleteRemain(int[] income_remain_id) {
    return mapper.DeleteRemain(income_remain_id);
  }

  // 도입자원 수정
  @Override
  public int UpdateIncome(int income_id, String income_name, String income_value) {
    return mapper.UpdateIncome(income_id, income_name, income_value);
  }

  // 재고관리 수정
  @Override
  public int UpdateRemain(int income_remain_id, String income_remain_name, String income_remain_value) {
    return mapper.UpdateRemain(income_remain_id, income_remain_name, income_remain_value);
  }

  // 도입자원 입력수정
  @Override
  public int UpdateInsertIncome(Income income) {
    return mapper.UpdateInsertIncome(income);
  }

  // 재고관리 입력수정
  @Override
  public int UpdateInsertRemain(IncomeRemain incomeRemain) {
    return mapper.UpdateInsertRemain(incomeRemain);
  }

  // 엑셀 입력
  @Override
  public int InsertIncomeExcel(Income income) {
    return mapper.InsertIncomeExcel(income);
  }

  @Override
  public int InsertRemainExcel(IncomeRemain incomeRemain) {
    return mapper.InsertRemainExcel(incomeRemain);
  }

  @Override
  public List<Income> SearchIncomeExcel(String income_name) {
    return mapper.SearchIncomeExcel(income_name);
  }

  @Override
  public List<IncomeRemain> SearchIncomeRemainExcel(String income_name) {
    return mapper.SearchIncomeRemainExcel(income_name);
  }

  @Override
  public List<Uploads> SelectUploads(int[] income_id) {
    return mapper.SelectUploads(income_id);
  }

  @Override
  public int DeleteFile(int[] income_id) {
    return mapper.DeleteFile(income_id);
  }

  @Override
  public int DeleteUploads(List<Uploads> uploads) {
    return mapper.DeleteUploads(uploads);
  }

  @Override
  public List<IncomeFile> SelectIncomeFile(int income_id) {
    return mapper.SelectIncomeFile(income_id);
  }

  @Override
  public int InsertIncomeFile(IncomeFile income_file) {
    return mapper.InsertIncomeFile(income_file);
  }

  @Override
  public int InsertIncomeUpload(Uploads upload) {
    return mapper.InsertIncomeUpload(upload);
  }

  @Override
  public int UpdateIncomeFile(IncomeFile income_file) {
    return mapper.UpdateIncomeFile(income_file);
  }

  @Override
  public int UpdateIncomeUpload(Uploads upload) {
    return mapper.UpdateIncomeUpload(upload);
  }

  @Override
  public List<Income> SelectIncomeExcel(int income_id) {
    return mapper.SelectIncomeExcel(income_id);
  }

  @Override
  public List<IncomeRemain> SelectIncomeRemainExcel(int income_remain_id) {
    return mapper.SelectIncomeRemainExcel(income_remain_id);
  }
  
  // income_file 전체목록 조회
  @Override
  public List<IncomeFile> SelectIncomeFileAll()
  {
	return mapper.SelectIncomeFileAll();
  }
}
