package com.thekiban.ServiceImpl;

import com.thekiban.Entity.DataList;
import com.thekiban.Mapper.DataListMapper;
import com.thekiban.Service.DataListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DataListServiceImpl implements DataListService {

  @Autowired
  private DataListMapper mapper;

  @Override
  public List<DataList> SelectDataList(String datalist_type) {
    return mapper.SelectDataList(datalist_type);
  }

  @Override
  public int InsertDataList(DataList dataList) {
    return mapper.InsertDataList(dataList);
  }

  @Override
  public List<Map<String, String>> SelectDateGroup(String datalist_type) {
    return mapper.SelectDateGroup(datalist_type);
  }
}
