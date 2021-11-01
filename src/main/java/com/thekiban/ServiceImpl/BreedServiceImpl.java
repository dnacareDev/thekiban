package com.thekiban.ServiceImpl;

import com.thekiban.Entity.Breed;
import com.thekiban.Entity.Detail;
import com.thekiban.Entity.Display;
import com.thekiban.Entity.Standard;
import com.thekiban.Mapper.BreedMapper;
import com.thekiban.Service.BreedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreedServiceImpl implements BreedService
{
	@Autowired
	private BreedMapper mapper;
  
	// 세부정보 조회
	@Override
	public List<Detail> SelectDetail()
	{
		return mapper.SelectDetail();
	}

	// 원종 갯수 조회
	@Override
	public int SelectBreedCount(String breed_name)
	{
		return mapper.SelectBreedCount(breed_name);
	}

	// 원종 검색
	@Override
	public List<Breed> SearchBreed(String breed_name, int offset, int limit)
	{
		return mapper.SearchBreed(breed_name, offset, limit);
	}

	// 품종별 세부 정보 조회
	@Override
	public List<Detail> SearchBreedDetail(String breed_name)
	{
		return mapper.SearchBreedDetail(breed_name);
	}

	// 표시항목 조회
	@Override
	public List<Display> SelectDisplay(int user_id, String breed_name)
	{
		return mapper.SelectDisplay(user_id, breed_name);
	}

	// 품종별 정보값 조회
	@Override
	public List<Standard> SearchBreedStandard(List<Detail> detail, int user_id, int breed_id)
	{
		return mapper.SearchBreedStandard(detail, user_id, breed_id);
	}
 
	// 품종 등록
	@Override
	public int InsertBreed(Breed breed)
	{
		return mapper.InsertBreed(breed);
	}

	// 품종 상세 정보 등록
	@Override
	public int InsertStandard(List<Standard> standard)
	{
		return mapper.InsertStandard(standard);
	}

	// 품종 삭제
	@Override
	public int[] DeleteBreed(int[] breed_id)
	{
		return mapper.DeleteBreed(breed_id);
	}

	// 품종 값 삭제
	@Override
	public int[] DeleteStandard(int[] breed_id)
	{
		return mapper.DeleteStandard(breed_id);
	}

	// 표시항목 삭제
	@Override
	public int DeleteDisplay(int user_id)
	{
		return mapper.DeleteDisplay(user_id);
	}

	// 표시항목 등록
	@Override
	public int InsertDisplay(int user_id, String breed_name, int[] detail_list)
	{
		return mapper.InsertDisplay(user_id, breed_name, detail_list);
	}
}