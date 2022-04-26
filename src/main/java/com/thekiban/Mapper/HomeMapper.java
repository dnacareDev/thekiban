package com.thekiban.Mapper;

import java.util.List;

import com.thekiban.Entity.SampleOutcome;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.thekiban.Entity.Breed;
import com.thekiban.Entity.ChartCount;

@Mapper
public interface HomeMapper
{

	ChartCount SelectChartBreed();
	ChartCount SelectChartSales();
	ChartCount SelectChartApply();
	ChartCount SelectChartProtect();

	List<Breed> SelectChartBar(String year);

	List<SampleOutcome> SelectOutcomeList();
	
	//DB접근 시도. 망가지면 이 밑으로 다 삭제
	List<SampleOutcome> SearchSeed2(@Param("sample_outcome_place") String sample_outcome_place);
}