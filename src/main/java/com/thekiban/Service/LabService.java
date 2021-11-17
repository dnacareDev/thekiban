package com.thekiban.Service;

import com.thekiban.Entity.AnalysisFile;

public interface LabService
{
	// 분석 파일 조회
	AnalysisFile SelectAnalysisFile();
	
	// 분석 파일 등록
	int InsertAnalysisFile(AnalysisFile analysis);
}