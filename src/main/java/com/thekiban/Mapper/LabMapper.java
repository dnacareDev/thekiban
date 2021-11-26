package com.thekiban.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.thekiban.Entity.AnalysisFile;

@Mapper
public interface LabMapper
{
	// 분석 파일 조회
	AnalysisFile SelectAnalysisFile(@Param("user_id") int user_id, @Param("analysis_type") int analysis_type);
	
	// 분석 파일 등록
	int InsertAnalysisFile(AnalysisFile analysis);
}