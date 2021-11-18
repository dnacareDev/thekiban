package com.thekiban.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thekiban.Entity.Basic;
import com.thekiban.Entity.Breed;
import com.thekiban.Entity.Detail;
import com.thekiban.Mapper.AnalysisMapper;
import com.thekiban.Service.AnalysisService;

@Service
public class AnalysisServiceImpl implements AnalysisService
{
	@Autowired
	private AnalysisMapper mapper;
	
	// 품종 조회
	@Override
	public List<Breed> SelectBreed(int[] total_id, int type)
	{
		return mapper.SelectBreed(total_id, type);
	}

	// 원종 조회
	@Override
	public List<Basic> SelectBasic(int[] total_id, int type)
	{
		return mapper.SelectBasic(total_id, type);
	}

	@Override
	public List<Detail> SelectTrait(String deatil_name)
	{
		return mapper.SelectTrait(deatil_name);
	}
}