package com.thekiban.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thekiban.Entity.Basic;
import com.thekiban.Entity.Detail;
import com.thekiban.Entity.Standard;
import com.thekiban.Mapper.BasicMapper;
import com.thekiban.Service.BasicService;

@Service
public class BasicServiceImpl implements BasicService
{
	@Autowired
	private BasicMapper mapper;
	
	// 세부정보 조회
	@Override
	public List<Detail> SelectDetail()
	{
		return mapper.SelectDetail();
	}

	// 원종 등록
	@Override
	public int InsertBasic(Basic basic)
	{
		return mapper.InsertBasic(basic);
	}

	// 원종 상세 정보 등록
	@Override
	public int InsertStandard(List<Standard> standard)
	{
		return mapper.InsertStandard(standard);
	}
}