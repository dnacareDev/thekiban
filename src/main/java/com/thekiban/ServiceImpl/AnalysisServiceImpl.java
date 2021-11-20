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
	public List<Breed> SelectBreed(String name, int[] total_id, int type)
	{
		return mapper.SelectBreed(name, total_id, type);
	}

	// 원종 조회
	@Override
	public List<Basic> SelectBasic(String name, int[] total_id, int type)
	{
		return mapper.SelectBasic(name, total_id, type);
	}

	// 분석 형질 조회
	@Override
	public List<Detail> SelectTrait(String deatil_name, int detail_type)
	{
		return mapper.SelectTrait(deatil_name, detail_type);
	}
}