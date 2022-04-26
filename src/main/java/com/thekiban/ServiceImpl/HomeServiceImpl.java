package com.thekiban.ServiceImpl;

import java.util.List;

import com.thekiban.Entity.SampleOutcome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thekiban.Entity.Breed;
import com.thekiban.Entity.ChartCount;
import com.thekiban.Mapper.HomeMapper;
import com.thekiban.Service.HomeService;

@Service
public class HomeServiceImpl implements HomeService
{
	@Autowired
	private HomeMapper mapper;

	@Override
	public ChartCount SelectChartBreed()
	{
		return mapper.SelectChartBreed();
	}
	@Override
	public ChartCount SelectChartSales()
	{
		return mapper.SelectChartSales();
	}
	@Override
	public ChartCount SelectChartApply()
	{
		return mapper.SelectChartApply();
	}
	@Override
	public ChartCount SelectChartProtect()
	{
		return mapper.SelectChartProtect();
	}

	@Override
	public List<Breed> SelectChartBar(String year)
	{
		return mapper.SelectChartBar(year);
	}

	@Override
	public List<SampleOutcome> SelectOutcomeList() {
		return mapper.SelectOutcomeList();
	}

	// DB접근중. 망가지면 이 밑으로 다 지움
	@Override
	public List<SampleOutcome> SearchSeed2(String sample_outcome_place) {
		return mapper.SearchSeed2(sample_outcome_place);
	}

}