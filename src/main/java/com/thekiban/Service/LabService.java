package com.thekiban.Service;

import java.util.List;

import com.thekiban.Entity.AnalysisFile;
import com.thekiban.Entity.ChromosomeViewer;
import com.thekiban.Entity.MABCDesign;
import com.thekiban.Entity.MABCFile;
import com.thekiban.Entity.MABCSample;
import com.thekiban.Entity.MABC_design_grid;
import com.thekiban.Entity.MarkerInformation;
import com.thekiban.Entity.QTLInformation;
import com.thekiban.Entity.Uploads;

public interface LabService
{
	// 분석 파일 조회
	AnalysisFile SelectAnalysisFile(int user_id, int analysis_type);
	
	// 분석 파일 등록
	int InsertAnalysisFile(AnalysisFile analysis);
	
	//------------------------------ marker_information.html --------------------------------------------------------------
	List<MarkerInformation> SearchMarkerInformation(); 					// 2022-05-09 | marker DB 조회, grid 출력
	int InsertMarkerInformation(MarkerInformation marker_information); 	// 2022-05-10 | marker DB 입력
	int UpdateMarkerInformation(MarkerInformation marker_information);	// marker 첨부파일 내용 수정
	int UpdateMarkerUpload(Uploads upload);								// marker 첨부파일 수정
	int UpdateMarkerData(MarkerInformation marker_information);			// 2022-05-12 | marker DB 수정
	List<MarkerInformation> SearchMarkerById(String marker_id); 		// 2022-05-11 | marker_id값과 일치하는 데이터 조회, 상세정보 출력
	String[] DeleteMarkerInformation(int[] total_marker_num);			// 2022-05-12 | 체크된 행의 maker_num을 기준으로 데이터 삭제
	int InsertMarkerUpload(Uploads upload);								// marker 첨부파일 DB 등록
	//---------------------------------------------------------------------------------------------------------------------
	
	//------------------------------ qtl_information.html -----------------------------------------------------------------
	List<QTLInformation> SearchQTLInformation();						// 2022-05-12 | qtl DB 조회, grid 출력
	int InsertQTLInformation(QTLInformation qtl_information);			// 2022-05-12 | qtl DB 입력
	int UpdateQTLInformation(QTLInformation qtl_information);			// 2022-05-12 | qtl DB 수정
	List<QTLInformation> SearchQTLById(String qtl_id); 					// 2022-05-12 | qtl_id값과 일치하는 데이터 조회, 상세정보 출력
	String[] DeleteQTLInformation(int[] total_qtl_num);					// 2022-05-12 | 체크된 행의 qtl_num을 기준으로 데이터 삭제
	int InsertQTLUpload(Uploads upload);								// qtl 첨부파일 DB 등록	
	//---------------------------------------------------------------------------------------------------------------------
	
	//------------------------------ mabc_analysis.html -----------------------------------------------------------------
	List<MABCSample> SearchMABCSample();								// mabc_sample DB 조회, grid 출력
	int InsertMABCSample(MABCSample mabc_sample);						// mabc_sample DB 입력
	String[] DeleteMABCSample(String[] total_mabc_id);					// 체크된 행의 sys_id를 기준으로 mabc_sample 데이터 삭제
	int InsertMABCFile(MABCFile mabc_file);								// ()
	int InsertMABCUpload(Uploads upload);								// mabc 첨부파일 DB
	//---------------------------------------------------------------------------------------------------------------------
	
	// 업로드된 파일 DB 조회 (marker, qtl, mabc에 전부 쓴다)
	List<Uploads> SearchUploads();			
	
	//map
	int InsertChromosomeViewer(ChromosomeViewer chromosomeViewer);
	
	
	//----------------MABCDesign.html
	int InsertMABCDesign(MABCDesign mabc_design);
	List<MABCDesign> Search_MABCDesign();
}