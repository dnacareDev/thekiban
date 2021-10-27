package com.thekiban.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thekiban.Entity.Detail;
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
}