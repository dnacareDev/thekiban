package com.thekiban.Mapper;

import java.util.List;

import com.thekiban.Entity.SampleOutcome;
import org.apache.ibatis.annotations.Mapper;

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
}