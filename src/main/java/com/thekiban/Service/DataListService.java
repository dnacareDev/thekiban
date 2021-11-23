package com.thekiban.Service;

import com.thekiban.Entity.DataList;

import java.util.List;
import java.util.Map;

public interface DataListService {

  List<DataList> SelectDataList(String datalist_type);

  int InsertDataList(DataList dataList);

  List<Map<String, String>> SelectDateGroup(String datalist_type);

}
