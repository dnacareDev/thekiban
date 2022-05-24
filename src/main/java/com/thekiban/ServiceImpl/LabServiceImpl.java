package com.thekiban.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thekiban.Entity.AnalysisFile;
import com.thekiban.Entity.ChromosomeViewer;
import com.thekiban.Entity.MABCDesign;
import com.thekiban.Entity.MABCFile;
import com.thekiban.Entity.MABCSample;
import com.thekiban.Entity.MABC_design_grid;
import com.thekiban.Entity.MarkerInformation;
import com.thekiban.Entity.QTLInformation;
import com.thekiban.Entity.Uploads;
import com.thekiban.Mapper.LabMapper;
import com.thekiban.Service.LabService;

@Service
public class LabServiceImpl implements LabService
{
	@Autowired
	private LabMapper mapper;

	// 분석 파일 조회
	@Override
	public AnalysisFile SelectAnalysisFile(int user_id, int analysis_type)
	{
		return mapper.SelectAnalysisFile(user_id, analysis_type);
	}
	
	// 분석 파일 등록
	@Override
	public int InsertAnalysisFile(AnalysisFile analysis)
	{
		return mapper.InsertAnalysisFile(analysis);
	}
	
	//------------------------------ marker_information.html --------------------------------------------------------------
	
	// 마커 정보 조회
	@Override
	public List<MarkerInformation> SearchMarkerInformation()
	{
		return mapper.SearchMarkerInformation();
	}
	

	
	// 마커 정보 등록
	@Override
	public int InsertMarkerInformation(MarkerInformation marker_information)
	{
		return mapper.InsertMarkerInformation(marker_information);
	}
	
	// 마커 정보 조회(1개만, 상세정보)
	@Override
	public List<MarkerInformation> SearchMarkerById(String marker_id) 
	{
		return mapper.SearchMarkerById(marker_id);
	}
	
	// 마커 정보 수정
	@Override
	public int UpdateMarkerData(MarkerInformation marker_information) 
	{
		return mapper.UpdateMarkerData(marker_information);
	}
	
	// 2022-05-12 | 체크된 행의 maker_id를 기준으로 데이터 삭제
	@Override
	public String[] DeleteMarkerInformation(int[] total_marker_num)
	{
		return mapper.DeleteMarkerInformation(total_marker_num);
	}
	
	// 첨부파일 서버 DB 등록
	@Override
	public int InsertMarkerUpload(Uploads upload)
	{
		return mapper.InsertMarkerUpload(upload);
	}
	
	// 첨부파일 내용 수정
	@Override
	public int UpdateMarkerInformation(MarkerInformation marker_information)
	{
		return mapper.UpdateMarkerInformation(marker_information);
	}
	
	public int UpdateMarkerUpload(Uploads upload)
	{
		return mapper.UpdateMarkerUpload(upload);
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	
	
	
	
	//------------------------------ qtl_information.html -----------------------------------------------------------------
	@Override
	public List<QTLInformation> SearchQTLInformation() 
	{
		return mapper.SearchQTLInformation();
	}
	
	// qtl 정보 입력
	@Override
	public int InsertQTLInformation(QTLInformation qtl_information)
	{
		return mapper.InsertQTLInformation(qtl_information);
	}
	
	// qtl 정보 수정
	@Override
	public int UpdateQTLInformation(QTLInformation qtl_information)
	{
		return mapper.UpdateQTLInformation(qtl_information);
	}
	
	// qtl 정보 조회(1개만, 상세정보)
	@Override
	public List<QTLInformation> SearchQTLById(String qtl_id) 
	{
		return mapper.SearchQTLById(qtl_id);
	}
	
	// 2022-05-12 | 체크된 행의 qtl_id를 기준으로 데이터 삭제
	@Override
	public String[] DeleteQTLInformation(int[] total_qtl_num)
	{
		return mapper.DeleteQTLInformation(total_qtl_num);
	}
	
	// qtl 첨부파일 서버등록
	@Override
	public int InsertQTLUpload(Uploads upload)
	{
		return mapper.InsertQTLUpload(upload);
	}
	//---------------------------------------------------------------------------------------------------------------------
	
	
	//-------------------------------- mabc_analysis.html -----------------------------------------------------------------
	// mabc_sample DB 조회, grid 출력
	@Override
	public List<MABCSample> SearchMABCSample() 
	{
		return mapper.SearchMABCSample();
	}
	
	
	@Override
	public List<Uploads> SearchUploads()
	{
		return mapper.SearchUploads();
	}
	
	
	//  체크된 행의 qtl_id를 기준으로 데이터 삭제
	@Override
	public String[] DeleteMABCSample(String[] total_mabc_id)
	{
		return mapper.DeleteMABCSample(total_mabc_id);
	}
	
	// mabc_sample DB 입력
	@Override
	public int InsertMABCSample(MABCSample mabc_sample) 
	{
		return mapper.InsertMABCSample(mabc_sample);
	}
	
	// mabc 첨부파일 DB 등록
	@Override
	public int InsertMABCFile(MABCFile mabc_file)
	{
		return mapper.InsertMABCFile(mabc_file);
	}
	
	// mabc 첨부파일 서버 등록
	@Override
	public int InsertMABCUpload(Uploads upload)
	{
		return mapper.InsertMABCUpload(upload);
	}
	//---------------------------------------------------------------------------------------------------------------------
	
	
	// map 
	@Override
	public int InsertChromosomeViewer(ChromosomeViewer chromosomeViewer)
	{
		System.out.println(chromosomeViewer+"impl");
		return mapper.InsertChromosomeViewer(chromosomeViewer);
	}
	
	
	// ------------- MABCDesign.html
	@Override
	public int InsertMABCDesign(MABCDesign mabc_design)
	{
		return mapper.InsertMABCDesign(mabc_design);
	}
	
	@Override
	public List<MABCDesign> Search_MABCDesign() 
	{
		System.out.println("aaa");
		return mapper.Search_MABCDesign();
	}
}