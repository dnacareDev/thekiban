package com.thekiban.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.thekiban.Entity.Detail;

@Mapper
public interface BasicMapper
{
	// 세부정보 조회
	List<Detail> SelectDetail();
}