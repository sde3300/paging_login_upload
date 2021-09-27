package com.btc.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.btc.dto.PageDto;
import com.github.pagehelper.Page;

@Mapper
public interface PageMapper {
//	SQL문을 통해서 내가 원하는 모든 데이터를 DB에서 가져온다
	Page<PageDto> findUser() throws Exception;
}
