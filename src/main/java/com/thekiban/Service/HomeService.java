package com.thekiban.Service;

import java.util.List;

import com.thekiban.Entity.Breed;
import com.thekiban.Entity.ChartCount;
import com.thekiban.Entity.SampleOutcome;

public interface HomeService
{

	ChartCount SelectChartBreed();
	ChartCount SelectChartSales();
	ChartCount SelectChartApply();
	ChartCount SelectChartProtect();

	List<Breed> SelectChartBar(String year);

	List<SampleOutcome> SelectOutcomeList();
	
//	DB접근 추가중. 망가지면 아래쪽 삭제
	List<SampleOutcome> SearchSeed2(String sample_outcome_place);
	
}