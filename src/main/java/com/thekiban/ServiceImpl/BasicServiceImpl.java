package com.thekiban.ServiceImpl;

import com.thekiban.Entity.*;
import com.thekiban.Mapper.BasicMapper;
import com.thekiban.Service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

	// 원종 갯수 조회
	@Override
	public int SelectBasicCount(String basic_name)
	{
		return mapper.SelectBasicCount(basic_name);
	}

	@Override
	public int SelectRemainCount(String basic_name) {
		return mapper.SelectRemainCount(basic_name);
	}

	// 원종 검색
	@Override
	public List<Basic> SearchBasic(String basic_name, int offset, int limit)
	{
		return mapper.SearchBasic(basic_name, offset, limit);
	}

	@Override
	public List<BasicRemain> SearchRemain(String basic_name, int offset, int limit) {
		return mapper.SearchRemain(basic_name, offset, limit);
	}

	// 원종별 세부 정보 조회
	@Override
	public List<Detail> SearchBasicDetail(String basic_name)
	{
		return mapper.SearchBasicDetail(basic_name);
	}
	
	// 표시항목 조회
	@Override
	public List<Display> SelectDisplay(int user_id, String basic_name)
	{
		return mapper.SelectDisplay(user_id, basic_name);
	}
	
	// 원종별 정보값 조회
	@Override
	public List<Standard> SearchBasicStandard(List<Detail> detail, int user_id, int basic_id)
	{
		return mapper.SearchBasicStandard(detail, user_id, basic_id);
	}

	// 원종 등록
	@Override
	public int InsertBasic(Basic basic)
	{
		return mapper.InsertBasic(basic);
	}

	@Override
	public int InsertRemain(BasicRemain basicRemain) {
		return mapper.InsertRemain(basicRemain);
	}

	// 원종 상세 정보 등록
	@Override
	public int InsertStandard(int basic_id, String basic_name, List<Detail> detail)
	{
		return mapper.InsertStandard(basic_id, basic_name, detail);
	}
	
	// 원종별 전체 조회
	@Override
	public List<Basic> SelectBasicAll(String basic_name, int offset)
	{
		return mapper.SelectBasicAll(basic_name, offset);
	}
	
	// 원종별 정보 전체 조회
	@Override
	public List<Standard> SelectBasicStandard(int basic_id)
	{
		return mapper.SelectBasicStandard(basic_id);
	}
	
	// 원종 수정
	@Override
	public int UpdateBasic(int basic_id, int detail_id, String standard)
	{
		return mapper.UpdateBasic(basic_id, detail_id, standard);
	}
	
	// 원종 전체 수정
	@Override
	public int UpdateAllBasic(List<Standard> list)
	{
		return mapper.UpdateAllBasic(list);
	}
	
	// 첨부파일 조회
	@Override
	public List<Uploads> SelectUploads(int[] basic_id)
	{
		return mapper.SelectUploads(basic_id);
	}

	// 원종 삭제
	@Override
	public int[] DeleteBasic(int[] basic_id)
	{
		return mapper.DeleteBasic(basic_id);
	}
	
	// 원종 값 삭제
	@Override
	public int[] DeleteStandard(int[] basic_id)
	{
		return mapper.DeleteStandard(basic_id);
	}

	// 표시항목 삭제
	@Override
	public int DeleteDisplay(int user_id)
	{
		return mapper.DeleteDisplay(user_id);
	}
	
	// 첨부파일 내용 삭제
	@Override
	public int DeleteFile(int[] basic_id)
	{
		return mapper.DeleteFile(basic_id);
	}
	
	// 첨부파일 삭제
	@Override
	public int DeleteUploads(List<Uploads> uploads)
	{
		return mapper.DeleteUploads(uploads);
	}

	@Override
	public int[] DeleteBasicRemain(int[] basic_remain_id) {
		return mapper.DeleteBasicRemain(basic_remain_id);
	}

	// 표시항목 등록
	@Override
	public int InsertDisplay(int user_id, String basic_name, int[] detail_list)
	{
		return mapper.InsertDisplay(user_id, basic_name, detail_list);
	}

	// 원종 상세 조회
	@Override
	public Basic SelectBasicDetail(int basic_id)
	{
		return mapper.SelectBasicDetail(basic_id);
	}

	// 첨부파일 목록 조회
	@Override
	public List<BasicFile> SelectBasicFile(int basic_id)
	{
		return mapper.SelectBasicFile(basic_id);
	}

	// 첨부파일 내용 등록
	@Override
	public int InsertBasicFile(BasicFile basic_file)
	{
		return mapper.InsertBasicFile(basic_file);
	}

	// 첨부파일 등록
	@Override
	public int InsertBasicUpload(Uploads upload)
	{
		return mapper.InsertBasicUpload(upload);
	}

	// 첨부파일 내용 수정
	@Override
	public int UpdateBasicFile(BasicFile basic_file)
	{
		return mapper.UpdateBasicFile(basic_file);
	}

	// 첨부파일 수정
	@Override
	public int UpdateBasicUpload(Uploads upload)
	{
		return mapper.UpdateBasicUpload(upload);
	}
}