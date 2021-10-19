package com.thekiban.service;

import com.thekiban.entity.Items;
import org.springframework.stereotype.Service;

public interface ItemsService {

  int SelectCatId(String Category);

  int InsertItems(Items items);
}
