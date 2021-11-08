package com.thekiban.ServiceImpl;

import com.thekiban.Entity.Income;
import com.thekiban.Entity.IncomeFile;
import com.thekiban.Entity.IncomeRemain;
import com.thekiban.Entity.Uploads;
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
  public int InsertIncome(Income income)
  {
    return mapper.InsertIncome(income);
  }

  // 도입자원 갯수 조회
  @Override
  public int SelectIncomeCount(String income_name)
  {
    return mapper.SelectIncomeCount(income_name);
  }

  // 도입자원 검색
  @Override
  public List<Income> SearchIncome(String income_name, int offset, int limit)
  {
    return mapper.SearchIncome(income_name, offset, limit);
  }

  // 도입자원 삭제
  @Override
  public int[] DeleteIncome(int[] income_id) {
    return mapper.DeleteIncome(income_id);
  }

  @Override
  public int[] DeleteRemain(int[] income_remain_id) {
    return mapper.DeleteRemain(income_remain_id);
  }

  // 도입자원 수정
  @Override
  public int UpdateIncome(Income income) {
    return mapper.UpdateIncome(income);
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
  public int UpdateInsertIncome(Income income) {
    return mapper.UpdateInsertIncome(income);
  }

  @Override
  public int UpdateInsertRemain(IncomeRemain incomeRemain) {
    return mapper.UpdateInsertRemain(incomeRemain);
  }

  @Override
  public int UpdateIncome(int income_id, String income_name, String income_value) {
    return mapper.UpdateIncome(income_id, income_name, income_value);
  }

  @Override
  public int UpdateRemain(int income_remain_id, String income_remain_name, String income_remain_value) {
    return mapper.UpdateRemain(income_remain_id, income_remain_name, income_remain_value);
  }
}
