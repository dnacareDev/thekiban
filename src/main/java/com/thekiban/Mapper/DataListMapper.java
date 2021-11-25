package com.thekiban.Mapper;

import com.thekiban.Entity.DataList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Mapper
public interface DataListMapper {

  List<DataList> SelectDataList(String datalist_type);

  int InsertDataList(DataList dataList);

  List<Map<String, String>> SelectDateGroup(String datalist_type);

  List<Integer> SelectTarget(@Param("datalist_date") String datalist_date, @Param("datalist_type") String datalist_type);

  int SelectTargetCount(String datalist_date);

}
