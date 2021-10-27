package com.thekiban.Service;

import java.util.List;

import com.thekiban.Entity.Detail;

public interface BasicService
{
	// 세부 정보 조회
	List<Detail> SelectDetail();
}