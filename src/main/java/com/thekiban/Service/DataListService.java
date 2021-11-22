package com.thekiban.Service;

import com.thekiban.Entity.DataList;

import java.util.List;

public interface DataListService {

  List<DataList> SelectDataList(String datalist_type);

  int InsertDataList(DataList dataList);

}
