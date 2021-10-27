package com.thekiban.Service;

import java.util.List;

import com.thekiban.Entity.Basic;
import com.thekiban.Entity.Detail;
import com.thekiban.Entity.Standard;

public interface BasicService
{
	// 세부 정보 조회
	List<Detail> SelectDetail();

	// 원종 등록
	int InsertBasic(Basic basic);

	// 원종 상세 정보 등록
	int InsertStandard(List<Standard> standard);
}