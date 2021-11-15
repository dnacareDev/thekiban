package com.thekiban.Mapper;

import org.apache.ibatis.annotations.Mapper;

import com.thekiban.Entity.AnalysisFile;

@Mapper
public interface LabMapper
{
	// 분석 파일 등록
	int InsertAnalysisFile(AnalysisFile analysis);
}