package com.thekiban.ServiceImpl;

import com.thekiban.Entity.Basic;
import com.thekiban.Entity.Detail;
import com.thekiban.Entity.Standard;
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

	// 원종 검색
	@Override
	public List<Basic> SearchBasic(String basic_name, int offset, int limit)
	{
		return mapper.SearchBasic(basic_name, offset, limit);
	}

	// 원종 등록
	@Override
	public int InsertBasic(Basic basic)
	{
		return mapper.InsertBasic(basic);
	}

	// 원종 상세 정보 등록
	@Override
	public int InsertStandard(List<Standard> standard)
	{
		return mapper.InsertStandard(standard);
	}

	// 원종 삭제
	@Override
	public int[] DeleteBasic(int[] basic_id) {
		return mapper.DeleteBasic(basic_id);
	}
}