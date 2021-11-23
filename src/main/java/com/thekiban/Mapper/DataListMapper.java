package com.thekiban.Mapper;

import com.thekiban.Entity.DataList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DataListMapper {

  List<DataList> SelectDataList(String datalist_type);

  int InsertDataList(DataList dataList);

  List<Map<String, String>> SelectDateGroup(String datalist_type);

}
