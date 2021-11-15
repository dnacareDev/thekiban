package com.thekiban.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thekiban.Entity.AnalysisFile;
import com.thekiban.Mapper.LabMapper;
import com.thekiban.Service.LabService;

@Service
public class LabServiceImpl implements LabService
{
	@Autowired
	private LabMapper mapper;
	
	// 분석 파일 등록
	@Override
	public int InsertAnalysisFile(AnalysisFile analysis)
	{
		return mapper.InsertAnalysisFile(analysis);
	}
}