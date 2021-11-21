package com.thekiban.Service;

import java.util.List;

import com.thekiban.Entity.Breed;
import com.thekiban.Entity.ChartCount;

public interface HomeService
{
	
	ChartCount SelectChartBreed();
	ChartCount SelectChartSales();
	ChartCount SelectChartApply();
	ChartCount SelectChartProtect();

	List<Breed> SelectChartBar(String year);
}