package com.thekiban.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.thekiban.Entity.AnalysisFile;
import com.thekiban.Entity.ChromosomeViewer;
import com.thekiban.Entity.MABCDesign;
import com.thekiban.Entity.MABCFile;
import com.thekiban.Entity.MABCSample;
import com.thekiban.Entity.MABC_design_grid;
import com.thekiban.Entity.MarkerInformation;
import com.thekiban.Entity.QTLInformation;
import com.thekiban.Entity.Uploads;

@Mapper
public interface LabMapper
{
	// 분석 파일 조회
	AnalysisFile SelectAnalysisFile(@Param("user_id") int user_id, @Param("analysis_type") int analysis_type);
	
	// 분석 파일 등록
	int InsertAnalysisFile(AnalysisFile analysis);
	
	//------------------------------ marker_information.html -----------------------------------------------------------------
	List<MarkerInformation> SearchMarkerInformation();						// 조회
	int InsertMarkerInformation(MarkerInformation marker_information);		// 입력
	int UpdateMarkerData(MarkerInformation marker_information);				// 수정
	int UpdateMarkerInformation(MarkerInformation marker_information);
	int UpdateMarkerUpload(Uploads upload);
	List<MarkerInformation> SearchMarkerById(String marker_id);				// marker id, name 클릭시 조회
	String[] DeleteMarkerInformation(int[] total_marker_id);				// 삭제
	int InsertMarkerUpload(Uploads upload);									// marker 첨부파일 DB 등록
	//---------------------------------------------------------------------------------------------------------------------
	
	//------------------------------ qtl_information.html -----------------------------------------------------------------
	List<QTLInformation> SearchQTLInformation();							// 조회
	int InsertQTLInformation(QTLInformation qtl_information);				// 입력
	int UpdateQTLInformation(QTLInformation qtl_information);				// 수정
	List<QTLInformation> SearchQTLById(String qtl_id);						// marker id, name 클릭시 조회
	String[] DeleteQTLInformation(int[] total_qtl_num);						// 삭제
	int InsertQTLUpload(Uploads upload);									// qtl 첨부파일 DB 등록
	//---------------------------------------------------------------------------------------------------------------------
	
	//------------------------------ mabc_analysis.html -----------------------------------------------------------------
	List<MABCSample> SearchMABCSample();									// 조회
	
	int InsertMABCSample(MABCSample mabc_sample);							// 입력
	String[] DeleteMABCSample(String[] total_mabc_id);						// 삭제
	int InsertMABCFile(MABCFile mabc_file);									// 
	int InsertMABCUpload(Uploads upload);									// mabc 첨부파일 DB 등록
	//---------------------------------------------------------------------------------------------------------------------
	
	//
	List<Uploads> SearchUploads();											// 업로드된 파일 조회
	//
	
	//
	int InsertChromosomeViewer(ChromosomeViewer chromosomeViewer);
	//
	
	//----------------MABCDesign.html--------------------
	int InsertMABCDesign(MABCDesign mabc_design);
	List<MABCDesign> Search_MABCDesign();
	
}