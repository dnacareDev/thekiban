package com.thekiban.ServiceImpl;

import com.thekiban.Entity.*;
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
	public List<Detail> SelectDetail(String breed_name)
	{
		return mapper.SelectDetail(breed_name);
	}

	// 품종 갯수 조회
	@Override
	public int SelectBreedCount(String breed_name)
	{
		return mapper.SelectBreedCount(breed_name);
	}

	// 품종 검색
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
	public int InsertStandard(int breed_id, String breed_name, List<Detail> detail)
	{
		return mapper.InsertStandard(breed_id, breed_name, detail);
	}

	// 품종별 전체 조회
	@Override
	public List<Breed> SelectBreedAll(String breed_name, int offset)
	{
		return mapper.SelectBreedAll(breed_name, offset);
	}

	// 품종별 정보 전체 조회
	@Override
	public List<Standard> SelectBreedStandard(int breed_id)
	{
		return mapper.SelectBreedStandard(breed_id);
	}

	// 품종 수정
	@Override
	public int UpdateBreed(int breed_id, int detail_id, String standard)
	{
		return mapper.UpdateBreed(breed_id, detail_id, standard);
	}

	// 품목 전체 수정
	@Override
	public int UpdateAllBreed(List<Standard> list)
	{
		return mapper.UpdateAllBreed(list);
	}

	// 첨부파일 조회
	@Override
	public List<Uploads> SelectUploads(int[] breed_id)
	{
		return mapper.SelectUploads(breed_id);
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

	// 첨부파일 내용 삭제
	@Override
	public int DeleteFile(int[] breed_id)
	{
		return mapper.DeleteFile(breed_id);
	}

	// 첨부파일 삭제
	@Override
	public int DeleteUploads(List<Uploads> uploads)
	{
		return mapper.DeleteUploads(uploads);
	}

	// 표시항목 등록
	@Override
	public int InsertDisplay(int user_id, String breed_name, int[] detail_list)
	{
		return mapper.InsertDisplay(user_id, breed_name, detail_list);
	}

	// 품종 상세 조회
	@Override
	public Breed SelectBreedDetail(int breed_id)
	{
		return mapper.SelectBreedDetail(breed_id);
	}

	// 첨부파일 목록 조회
	@Override
	public List<BreedFile> SelectBreedFile(int breed_id)
	{
		return mapper.SelectBreedFile(breed_id);
	}

	// 첨부파일 내용 등록
	@Override
	public int InsertBreedFile(BreedFile breed_file)
	{
		return mapper.InsertBreedFile(breed_file);
	}

	// 첨부파일 등록
	@Override
	public int InsertBreedUpload(Uploads upload)
	{
		return mapper.InsertBreedUpload(upload);
	}

	// 첨부파일 내용 수정
	@Override
	public int UpdateBreedFile(BreedFile breed_file)
	{
		return mapper.UpdateBreedFile(breed_file);
	}

	// 첨부파일 수정
	@Override
	public int UpdateBreedUpload(Uploads upload)
	{
		return mapper.UpdateBreedUpload(upload);
	}

	@Override
	public List<Sample> SelectSampleList() {
		return mapper.SelectSampleList();
	}

	@Override
	public int SelectSampleCount(String sample_name) {
		return mapper.SelectSampleCount(sample_name);
	}

	@Override
	public List<Sample> SearchSample(String sample_name) {
		return mapper.SearchSample(sample_name);
	}

	@Override
	public List<Detail> SelectDetail1() {
		return mapper.SelectDetail1();
	}

	@Override
	public int SelectBasicCount(String basic_name) {
		return mapper.SelectBasicCount(basic_name);
	}

	@Override
	public List<Basic> SearchBasic(String basic_name) {
		return mapper.SearchBasic(basic_name);
	}

	@Override
	public List<Detail> SearchBasicDetail(String basic_name) {
		return mapper.SearchBasicDetail(basic_name);
	}

	@Override
	public List<Display> SelectDisplay1(int user_id, String basic_name) {
		return mapper.SelectDisplay1(user_id, basic_name);
	}

	@Override
	public List<Standard> SearchBasicStandard(List<Detail> detail, int user_id, int basic_id) {
		return mapper.SearchBasicStandard(detail, user_id, basic_id);
	}

	@Override
	public int InsertExcel(List<Standard> standards) {
		return mapper.InsertExcel(standards);
	}
}